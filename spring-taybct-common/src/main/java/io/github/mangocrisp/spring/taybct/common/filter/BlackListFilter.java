package io.github.mangocrisp.spring.taybct.common.filter;

import io.github.mangocrisp.spring.taybct.common.prop.SecureProp;
import io.github.mangocrisp.spring.taybct.tool.core.util.StringUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;

/**
 * 黑名单过滤器
 *
 * @author xijieyin
 * @since 2.4.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@AutoConfiguration
@RequiredArgsConstructor
public class BlackListFilter implements Filter {

    final SecureProp secureProp;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        PathMatcher pathMatcher = new AntPathMatcher();
        String requestUri = request.getRequestURI();
        if (secureProp.getBlackList().getUris().stream()
                .anyMatch(url -> pathMatcher.match(url, requestUri))) {
            // 地址在黑名单里面，就直接拦截掉
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
        if (StringUtil.isNotBlank(request.getRemoteHost())) {
            if (secureProp.getBlackList().getUriIpSet().stream().anyMatch(uriIP -> pathMatcher.match(uriIP.getUri().getPath(), requestUri)
                    // 如果配置上的 url 包含的 ip 是需要被限制的 ip 如果和请求的 ip 匹配上了就要限制访问
                    && uriIP.getIpSet().contains(request.getRemoteHost()))) {
                // 地址在黑名单里面，就直接拦截掉
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

}

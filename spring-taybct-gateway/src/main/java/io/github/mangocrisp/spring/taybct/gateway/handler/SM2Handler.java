package io.github.mangocrisp.spring.taybct.gateway.handler;

import io.github.mangocrisp.spring.taybct.tool.core.result.R;
import io.github.mangocrisp.spring.taybct.tool.core.util.sm.SM2Coder;
import javax.validation.constraints.NotNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 获取 RSA 加密公钥
 *
 * @author xijieyin <br> 2022/8/5 20:50
 * @since 1.0.0
 */
@Slf4j
@AutoConfiguration
public class SM2Handler implements HandlerFunction<ServerResponse> {

    @SneakyThrows
    @NotNull
    @Override
    public Mono<ServerResponse> handle(@NotNull ServerRequest serverRequest) {
        return ServerResponse.status(HttpStatus.OK)
                .body(BodyInserters.fromValue(R.data(SM2Coder.getWebPublicKey())));
    }

}

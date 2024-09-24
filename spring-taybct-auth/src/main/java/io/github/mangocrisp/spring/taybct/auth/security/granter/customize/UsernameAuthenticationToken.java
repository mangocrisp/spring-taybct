package io.github.mangocrisp.spring.taybct.auth.security.granter.customize;


/**
 * 自定义的用户名鉴权 token
 *
 * @author xijieyin <br> 2022/12/30 11:30
 */
public class UsernameAuthenticationToken extends CustomizeAuthenticationToken {
    private static final long serialVersionUID = -9039706192089274594L;

    public UsernameAuthenticationToken(CustomizeAuthenticationToken token) {
        super(token);
    }
}

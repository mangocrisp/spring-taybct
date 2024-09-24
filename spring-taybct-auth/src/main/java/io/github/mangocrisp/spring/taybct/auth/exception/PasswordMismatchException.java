package io.github.mangocrisp.spring.taybct.auth.exception;


/**
 * 用户密码不匹配异常
 *
 * @author xijieyin <br> 2022/12/30 14:02
 */
public class PasswordMismatchException extends PasswordException implements JsonResponseException {

    private static final long serialVersionUID = 8564958096945987751L;

    public PasswordMismatchException(String msg) {
        super(msg);
    }

    public PasswordMismatchException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

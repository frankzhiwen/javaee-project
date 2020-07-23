package frank.exception;

public class SystemException extends BaseException {

    public SystemException(String code) {
        this(code, null);
    }

    public SystemException(String code, String message) {
        this(code, message, null);
    }

    public SystemException(String code, String message, Throwable cause) {
        super("SYS" + code, message, cause);
    }
}

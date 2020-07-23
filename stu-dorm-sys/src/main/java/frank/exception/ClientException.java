package frank.exception;

public class ClientException extends BaseException {

    public ClientException(String code) {
        this(code, null);
    }

    public ClientException(String code, String message) {
        this(code, message, null);
    }

    public ClientException(String code, String message, Throwable cause) {
        super("CLI" + code, message, cause);
    }
}

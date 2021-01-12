package cn.huangdayu.almanac.exception;

/**
 * @author huangdayu create at 2021/1/12 16:27
 */
public class AlmanacException extends RuntimeException {

    private Exception exception;

    private String message;

    public AlmanacException(String message, Exception exception) {
        super(message);
        this.message = message;
        this.exception = exception;
    }

    public AlmanacException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}

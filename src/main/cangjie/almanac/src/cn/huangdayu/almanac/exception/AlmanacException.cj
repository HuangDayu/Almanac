package almanac.cn.huangdayu.almanac.exception

import j2cjlib.mappings.lang.HashableImpl

/**
 * @author huangdayu create at 2021/1/12 16:27
 */
public class AlmanacException <: Exception {

    private var exception: ?Exception = None

    private var message: ?String = None

    public init(message: ?String, exception: ?Exception) {
        super(message.getOrThrow())
        this.message = message
        this.exception = exception
    }

    public init(message: ?String) {
        super(message.getOrThrow())
        this.message = message
    }

    override public func getMessage(): ?String {
        return message
    }

    public func setMessage(message: ?String): Unit {
        this.message = message
    }

    public func getException(): ?Exception {
        return exception
    }

    public func setException(exception: ?Exception): Unit {
        this.exception = exception
    }

    public open func toString(): String {
        return "AlmanacException@${this.hashCodeImpl()}"
    }
}
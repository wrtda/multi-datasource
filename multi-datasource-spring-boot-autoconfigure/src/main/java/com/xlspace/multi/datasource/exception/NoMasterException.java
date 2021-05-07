package com.xlspace.multi.datasource.exception;

/**
 * @ClassName: NoMasterException
 * @Description
 * @Author gxy
 * @Date 2021/4/29 22:19
 * @Copyright: 2021 gxy. All rights reserved.
 **/
public class NoMasterException extends RuntimeException {
    public NoMasterException(String message) {
        super(message);
    }

    public NoMasterException(Throwable cause) {
        super(cause);
    }

    public NoMasterException(String message, Throwable cause) {
        super(message, cause);
    }
}

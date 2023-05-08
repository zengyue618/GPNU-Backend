package com.gpnu.core.exception;

public interface ErrorCodes {
    //系统正常返回
    int SYSTEM_SUCCESS = 200;
    //系统异常
    int SYSTEM_EXCEPTION = 100;
    //系统输入参数异常
    int ERROR_PARAM = 101;
    //用户不存在
    int ERROR_USER_NOT_EXISTS = 102;
    //密码错误
    int ERROR_PASSWORD = 103;
    //权限不足
    int ERROR_PERMISSION = 104;
}

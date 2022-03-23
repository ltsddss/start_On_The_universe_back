package com.lts.start.exception;

public enum BaseExceptionEnum {
    VALID_EXCEPTION(10002,"数据校验异常"),
    SYSTEM_EXCEPTION(10001,"系统未知异常");

    private Integer code;
    private String msg;

    BaseExceptionEnum(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public Integer getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}

package com.alpha.pandemic.utils.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FinalResult
{
    @ApiModelProperty(value = "whether is success")
    private Boolean success;

    @ApiModelProperty(value = "whether returns the code")
    private Integer code;

    @ApiModelProperty(value = "whether returns the message")
    private String message;
    private Object data;

    public FinalResult() {}
    public static  FinalResult ok()
    {
        FinalResult result=new FinalResult();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    public static FinalResult ok(ResultCode resultCode)
    {
        FinalResult result = new FinalResult();
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData("");
        return result;
    }
    public static FinalResult error()
    {
        FinalResult result = new FinalResult();
        result.setSuccess(false);
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(ResultCode.FAILED.getMessage());
        result.setData("");
        return result;
    }

    public static FinalResult error(ResultCode resultCode)
    {
        FinalResult result = new FinalResult();
        result.setSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        result.setData("");
        return result;
    }

    public static FinalResult error(String errorMsg)
    {
        FinalResult result = new FinalResult();
        result.setSuccess(false);
        result.setCode(ResultCode.FAILED.getCode());
        result.setMessage(errorMsg);
        result.setData("");
        return result;
    }

//
    public FinalResult success(Boolean success)
    {
        this.setSuccess(success);
        return this;
    }
    public FinalResult message(String message)
    {
        this.setMessage(message);
        return this;
    }
    public FinalResult code(Integer code)
    {
        this.setCode(code);
        return this;
    }

    public FinalResult data(Object obj)
    {
        this.setData(obj);
        return this;
    }

}

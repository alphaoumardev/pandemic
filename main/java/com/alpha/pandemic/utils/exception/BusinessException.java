package com.alpha.pandemic.utils.exception;


import com.alpha.pandemic.utils.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException
{
    @ApiModelProperty(value = "Status code")
    private Integer code;

    @ApiModelProperty(value = "error message")
    private String message;

    public BusinessException(ResultCode resultCode)
    {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public BusinessException(ResultCode resultCode, Exception e)
    {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage() + e.getMessage();
    }

    public BusinessException(String message)
    {
        this.code = ResultCode.FAILED.getCode();
        this.message = message;
    }

    public BusinessException(ResultCode resultCode, String message)
    {
        this.code = resultCode.getCode();
        this.message = message;
    }
}

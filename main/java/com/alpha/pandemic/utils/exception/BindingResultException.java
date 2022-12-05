package com.alpha.pandemic.utils.exception;

import com.alpha.pandemic.utils.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BindingResultException extends RuntimeException
{
    @ApiModelProperty(value = "status code")
    private Integer code;

    @ApiModelProperty(value = "error message")
    private String message;

    public BindingResultException(BindingResult result)
    {
        List<String> errors = result.getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        this.code = ResultCode.PARAM_ERROR.getCode();
        this.message = ResultCode.PARAM_ERROR.getMessage() + "[ " + String.join(",", errors) + " ]";
    }
}

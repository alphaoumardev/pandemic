package com.alpha.pandemic.structor.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StockForm
{
    private String type;
    private String inNum;
    private Integer status;
    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-DD HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-DD HH:mm:ss")
    private Date endTime;
}

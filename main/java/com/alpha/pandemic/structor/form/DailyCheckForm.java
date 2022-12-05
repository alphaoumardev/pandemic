package com.alpha.pandemic.structor.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class DailyCheckForm
{
    private String province;
    private Integer situation;
    private Integer touch;
    private Integer passby;
    private Integer reception;
    private String city;
    private String origin;
    private String username;

    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-DD HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "YYYY-MM-DD HH:mm:ss")
    private Date endTime;

}

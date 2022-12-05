package com.alpha.pandemic.beans.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HealthVo
{
    private Long id;
    private Long userId;
    private String address;
    private String username;
    private Integer situation;
    private Integer touch;
    private Integer passby;
    private Integer reception;
    private LocalDateTime createTime;
}

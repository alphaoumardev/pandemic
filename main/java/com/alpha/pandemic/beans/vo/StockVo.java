package com.alpha.pandemic.beans.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class StockVo
{
    private Long id;

    private String inNum;

    @NotNull(message = "入库单类型不能为空")
    private Integer type;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime modified;

    /** 该入库单的总数**/
    private Integer productNumber;

    private Long supplierId;

    private String supplierName;

    @NotBlank(message = "入库备注不能为空")
    private String remark;

    private Integer status;

    /** 如果supplierId不存在需要添加供应商信息**/

    private String name;

    private String address;

    private String email;

    private String phone;

    private Integer sort;

    private String contact;

    private List<Object> products = new ArrayList<>();
}

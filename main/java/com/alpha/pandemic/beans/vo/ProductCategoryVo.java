package com.alpha.pandemic.beans.vo;

import lombok.Data;
import org.apache.http.annotation.Contract;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Data
public class ProductCategoryVo
 {
    private Long id;
    private String name;
    private String remark;
    private Integer sort;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
    private Long pid;
    private int lev;
    private List<ProductCategoryVo> children;
      //排序,根据order排序
    @NotNull
    public static Comparator<ProductCategoryVo> order()
    {
        return (o1, o2) ->
        {
            if(!o1.getSort().equals(o2.getSort())){
                return o1.getSort() - o2.getSort();
            }
            return 0;
        };
    }
}

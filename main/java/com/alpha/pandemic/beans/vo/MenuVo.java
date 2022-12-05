package com.alpha.pandemic.beans.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Data
public class MenuVo
{
    private Long id;

    private Long parentId;

    private String menuName;

    private String url = null;

    private String icon;

    private Long orderNum;

    private Integer open;

    private String perms;

    private String type;

    private Date createTime;
    @NotNull(message = "菜单状态不能为空")
    private Boolean disabled;

    private Date modifiedTime;
    @NotNull(message = "菜单状态不能为空")

    private List<Long> ids;
    private Long[] mids;
    private List<MenuVo> children = new ArrayList<>();

    /*
     * 排序,根据order排序
     */
    public static Comparator<MenuVo> order()
    {
        return (o1, o2) ->
        {
            if (!o1.getOrderNum().equals(o2.getOrderNum()))
            {
                return (int) (o1.getOrderNum() - o2.getOrderNum());
            }
            return 0;
        };
    }
}

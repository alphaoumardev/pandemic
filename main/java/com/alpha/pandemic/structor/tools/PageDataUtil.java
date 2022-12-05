package com.alpha.pandemic.structor.tools;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PageDataUtil
{
    public static <T> List<T> getPageData(Integer page, Integer size, List<T> list)
    {
        int start = (page - 1) * size + 1;
        // 第一页默认从 1 开始截取
        // 如果开始截取的位置大于 list 的长度大小
        if (start > list.size())
        {
            return null;
        }
        List<T> res = new ArrayList<>();
        // 代表剩下的数据总数
        int rum = list.size() - start;
        if (rum < 0)
        {
            return null;
        }
        // 刚好减完，说明是最后一条数据
        if (rum == 0)
        {
            // 说明正好是最后一个了
            int index = list.size() - 1;
            res.add(list.get(index));
            return res;
        }
        if (rum / size >= 1)
        {
            // 剩下的数据还够1页，返回整页的数据
            for (int i = start; i < start + size; i++)
            {
                // 截取从startNum开始的数据
                res.add(list.get(i - 1));
            }
            return res;
        }
        else if (rum / size == 0)
        {
            //不够一页，直接返回剩下数据
            for (int j = start; j <= list.size(); j++)
            {
                res.add(list.get(j - 1));
            }
            return res;
        }
        else
        {
            return null;
        }
    }
}

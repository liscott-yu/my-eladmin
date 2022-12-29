package com.scott.utils;

import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * project name  my-eladmin-backend1
 * filename  PageUtil
 * @author liscott
 * @date 2022/12/29 15:15
 * description  分页工具
 */
public class PageUtil extends cn.hutool.core.util.PageUtil {

    /**
     * List 分页
     */
    public static List toPage(int page, int size, List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;
        // 1.fromIndex > 总页数，返回 空白页
        if(fromIndex > list.size()) {
            return new ArrayList();
        } else if(toIndex >= list.size()) {
            // 2. fromIndex<=总页数<=toIndex, 返回 [fromIndex, 总页数]
            return list.subList(fromIndex, list.size());
        } else {
            //3. 总页数 > toIndex，返回 [fromIndex, toIndex]
            return list.subList(fromIndex, toIndex);
        }
    }
    /**
     * Page 数据处理，预防redis反序列化报错
     */
    public static Map<String, Object> toPage(Page page) {
        Map<String, Object> map = new LinkedHashMap<String, Object>(2){{
            put("content", page.getContent());
            put("totalElements", page.getTotalElements());
        }};
        return map;
    }

    /**
     * 自定义分页
     */
    public static Map<String,Object> toPage(Object object, Object totalElements) {
        Map<String,Object> map = new LinkedHashMap<>(2);
        map.put("content",object);
        map.put("totalElements",totalElements);
        return map;
    }
}

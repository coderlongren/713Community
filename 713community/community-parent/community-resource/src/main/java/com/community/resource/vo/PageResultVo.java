package com.community.resource.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @Author: 王喜
 * @Description: 分页数据传输对象
 * @Date:create in 2017/10/20 18:39
 */
public class PageResultVo {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer total;

    private Integer pageCount;

    private List<?> rows;



    public PageResultVo() {
    }

    public PageResultVo(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
        this.pageCount = pageCount;
    }

    public PageResultVo(Long total, List<?> rows, Integer pageCount) {
        this.total = total.intValue();
        this.rows = rows;
        this.pageCount = pageCount;
    }

    public PageResultVo(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static PageResultVo formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");
            List<?> list = null;
            if (data.isArray() && data.size() > 0) {
                list = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new PageResultVo(jsonNode.get("total").intValue(), list);
        } catch (Exception e) {
            return null;
        }
    }
}

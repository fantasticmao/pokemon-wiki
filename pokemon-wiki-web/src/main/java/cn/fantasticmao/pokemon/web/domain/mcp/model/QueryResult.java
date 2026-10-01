package cn.fantasticmao.pokemon.web.domain.mcp.model;

import lombok.Data;

import java.util.List;

/**
 * 只读查询结果
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
@Data
public class QueryResult {
    /**
     * 列名
     */
    private List<String> columns;

    /**
     * 行数据
     */
    private List<List<Object>> rows;

    /**
     * 是否因行数上限被截断
     */
    private boolean truncated;
}

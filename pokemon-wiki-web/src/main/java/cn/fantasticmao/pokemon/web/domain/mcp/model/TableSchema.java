package cn.fantasticmao.pokemon.web.domain.mcp.model;

import lombok.Data;

import java.util.List;

/**
 * 表结构
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
@Data
public class TableSchema {
    /**
     * 表名
     */
    private String name;

    /**
     * 字段
     */
    private List<ColumnSchema> columns;
}

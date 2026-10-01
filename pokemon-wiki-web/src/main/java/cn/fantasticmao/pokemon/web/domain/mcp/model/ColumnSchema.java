package cn.fantasticmao.pokemon.web.domain.mcp.model;

import lombok.Data;

/**
 * 表字段
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
@Data
public class ColumnSchema {
    /**
     * 字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 是否主键
     */
    private boolean primaryKey;
}

package cn.fantasticmao.pokemon.web.application.mcp;

import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;
import cn.fantasticmao.pokemon.web.domain.mcp.service.SqliteQueryDomainService;
import jakarta.annotation.Resource;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 对 {@code pokemon_wiki.db} 提供只读 SQL 工具。
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
@Component
public class SqliteMcpTools {
    @Resource
    private SqliteQueryDomainService sqliteQueryDomainService;

    @McpTool(name = "list_schema",
        description = "列出 pokemon_wiki.db 中业务表的列名、类型与主键。",
        annotations = @McpTool.McpAnnotations(readOnlyHint = true, openWorldHint = false))
    public List<TableSchema> listSchema() {
        return sqliteQueryDomainService.listSchema();
    }

    @McpTool(name = "query",
        description = "对 pokemon_wiki.db 执行一条只读 SQL。仅接受 SELECT 或 WITH，拒绝写操作与多语句。结果最多 100 行。",
        annotations = @McpTool.McpAnnotations(readOnlyHint = true, openWorldHint = false))
    public QueryResult query(@McpToolParam(description = "一条 SELECT 或 WITH 语句") String sql) {
        return sqliteQueryDomainService.query(sql);
    }
}

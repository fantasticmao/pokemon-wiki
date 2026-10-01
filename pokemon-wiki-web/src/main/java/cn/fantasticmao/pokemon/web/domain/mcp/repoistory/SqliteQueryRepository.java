package cn.fantasticmao.pokemon.web.domain.mcp.repoistory;

import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;

import java.util.List;

/**
 * SqliteQueryRepository
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
public interface SqliteQueryRepository {

    List<TableSchema> listSchema();

    QueryResult query(String sql, int maxRows, int maxCellLength);
}

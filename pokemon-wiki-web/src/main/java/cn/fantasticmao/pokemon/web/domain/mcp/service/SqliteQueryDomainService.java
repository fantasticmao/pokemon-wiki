package cn.fantasticmao.pokemon.web.domain.mcp.service;

import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;

import java.util.List;

/**
 * SqliteQueryDomainService
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
public interface SqliteQueryDomainService {

    List<TableSchema> listSchema();

    QueryResult query(String sql);
}

package cn.fantasticmao.pokemon.web.domain.mcp.service.impl;

import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;
import cn.fantasticmao.pokemon.web.domain.mcp.repoistory.SqliteQueryRepository;
import cn.fantasticmao.pokemon.web.domain.mcp.service.SqliteQueryDomainService;
import jakarta.annotation.Resource;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.select.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SqliteQueryDomainServiceImpl
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
@Service
public class SqliteQueryDomainServiceImpl implements SqliteQueryDomainService {
    private static final int MAX_ROWS = 100;
    private static final int MAX_CELL_LENGTH = 500;

    @Resource
    private SqliteQueryRepository sqliteQueryRepository;

    @Override
    public List<TableSchema> listSchema() {
        return sqliteQueryRepository.listSchema();
    }

    @Override
    public QueryResult query(String sql) {
        return sqliteQueryRepository.query(validate(sql), MAX_ROWS, MAX_CELL_LENGTH);
    }

    private static String validate(String sql) {
        if (sql == null || sql.isBlank()) {
            throw new IllegalArgumentException("SQL is empty");
        }
        String statement = sql.trim();
        Statements statements;
        try {
            statements = CCJSqlParserUtil.parseStatements(statement);
        } catch (JSQLParserException exception) {
            throw new IllegalArgumentException(
                "Only a single SELECT or WITH statement is allowed", exception);
        }
        if (statements.size() != 1 || !(statements.getFirst() instanceof Select)) {
            throw new IllegalArgumentException("Only a single SELECT or WITH statement is allowed");
        }
        if (statement.endsWith(";")) {
            statement = statement.substring(0, statement.length() - 1).trim();
        }
        return statement;
    }
}

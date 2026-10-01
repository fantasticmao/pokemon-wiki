package cn.fantasticmao.pokemon.web.infra.repoistory.impl;

import cn.fantasticmao.pokemon.web.domain.mcp.model.ColumnSchema;
import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;
import cn.fantasticmao.pokemon.web.domain.mcp.repoistory.SqliteQueryRepository;
import jakarta.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * SqliteQueryRepositoryImpl
 *
 * @author fantasticmao
 * @since 2026/10/1
 */
@Repository
public class SqliteQueryRepositoryImpl implements SqliteQueryRepository {
    private static final Pattern IDENTIFIER = Pattern.compile("[A-Za-z_][A-Za-z0-9_]*");

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TableSchema> listSchema() {
        try {
            String sql = """
                SELECT name FROM sqlite_master
                WHERE type = 'table' AND name NOT LIKE 'sqlite_%'
                ORDER BY name
                """;
            List<String> names = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name"));
            List<TableSchema> tables = new ArrayList<>();
            for (String name : names) {
                if (IDENTIFIER.matcher(name).matches()) {
                    tables.add(tableSchema(name));
                }
            }
            return tables;
        } catch (DataAccessException exception) {
            throw new IllegalStateException("Failed to list schema", exception);
        }
    }

    @Override
    public QueryResult query(String sql, int maxRows, int maxCellLength) {
        try {
            return jdbcTemplate.execute((StatementCallback<QueryResult>) statement -> {
                statement.setMaxRows(maxRows + 1);
                try (ResultSet rows = statement.executeQuery(sql)) {
                    return toResult(rows, maxRows, maxCellLength);
                }
            });
        } catch (DataAccessException exception) {
            throw new IllegalArgumentException(
                "Query failed: " + exception.getMostSpecificCause().getMessage(), exception);
        }
    }

    private TableSchema tableSchema(String table) {
        List<ColumnSchema> columns = jdbcTemplate.query("PRAGMA table_info(\"" + table + "\")",
            (rs, rowNum) -> {
                ColumnSchema column = new ColumnSchema();
                column.setName(rs.getString("name"));
                column.setType(rs.getString("type"));
                column.setPrimaryKey(rs.getInt("pk") > 0);
                return column;
            });
        TableSchema schema = new TableSchema();
        schema.setName(table);
        schema.setColumns(columns);
        return schema;
    }

    private static QueryResult toResult(ResultSet rows, int maxRows, int maxCellLength) throws SQLException {
        ResultSetMetaData metaData = rows.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> columns = new ArrayList<>(columnCount);
        for (int index = 1; index <= columnCount; index++) {
            columns.add(metaData.getColumnLabel(index));
        }
        List<List<Object>> values = new ArrayList<>();
        boolean truncated = false;
        while (rows.next()) {
            if (values.size() == maxRows) {
                truncated = true;
                break;
            }
            List<Object> row = new ArrayList<>(columnCount);
            for (int index = 1; index <= columnCount; index++) {
                row.add(truncate(rows.getObject(index), maxCellLength));
            }
            values.add(row);
        }
        QueryResult result = new QueryResult();
        result.setColumns(columns);
        result.setRows(values);
        result.setTruncated(truncated);
        return result;
    }

    private static Object truncate(Object value, int maxCellLength) {
        if (value instanceof byte[]) {
            return "[binary]";
        }
        if (value instanceof String text && text.length() > maxCellLength) {
            return text.substring(0, maxCellLength) + "…";
        }
        return value;
    }
}

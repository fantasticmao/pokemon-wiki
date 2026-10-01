package cn.fantasticmao.pokemon.web.domain;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.mcp.model.ColumnSchema;
import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;
import cn.fantasticmao.pokemon.web.domain.mcp.service.SqliteQueryDomainService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * SqliteQueryDomainServiceTest
 *
 * @author fantasticmao
 * @see SqliteQueryDomainService
 * @since 2026/10/1
 */
public class SqliteQueryDomainServiceTest extends SpringTest {
    @Resource
    private SqliteQueryDomainService sqliteQueryDomainService;

    @Test
    public void listSchema_containsPokemon() {
        TableSchema pokemon = sqliteQueryDomainService.listSchema().stream()
            .filter(table -> "t_pokemon".equals(table.getName()))
            .findFirst()
            .orElse(null);
        Assertions.assertNotNull(pokemon);
        Assertions.assertTrue(pokemon.getColumns().stream()
            .map(ColumnSchema::getName)
            .anyMatch("name_zh"::equals));
        Assertions.assertTrue(pokemon.getColumns().stream()
            .anyMatch(column -> "id".equals(column.getName()) && column.isPrimaryKey()));
    }

    @Test
    public void query_bulbasaur() {
        QueryResult result = sqliteQueryDomainService.query("SELECT name_zh FROM t_pokemon WHERE idx = 1");
        Assertions.assertEquals("name_zh", result.getColumns().getFirst());
        Assertions.assertFalse(result.isTruncated());
        Assertions.assertEquals("妙蛙种子", result.getRows().getFirst().getFirst());
    }

    @Test
    public void query_truncatesRows() {
        QueryResult result = sqliteQueryDomainService.query("SELECT name_zh FROM t_pokemon");
        Assertions.assertEquals(100, result.getRows().size());
        Assertions.assertTrue(result.isTruncated());
    }

    @Test
    public void query_selectAndWith() {
        Assertions.assertEquals(1, sqliteQueryDomainService.query("  SELECT 1;  ").getRows().size());
        Assertions.assertEquals(1, sqliteQueryDomainService.query(
            "WITH x AS (SELECT 1) SELECT * FROM x").getRows().size());
        Assertions.assertNotNull(sqliteQueryDomainService.query(
            "SELECT name_zh FROM t_pokemon WHERE name_zh = ';'"));
        Assertions.assertEquals("妙蛙", sqliteQueryDomainService.query(
            "SELECT replace(name_zh, '种子', '') FROM t_pokemon WHERE idx = 1").getRows().getFirst().getFirst());
    }

    @Test
    public void query_rejectWriteAndMultipleStatements() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> sqliteQueryDomainService.query("  "));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("INSERT INTO t_pokemon DEFAULT VALUES"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("UPDATE t_pokemon SET name_zh = ''"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("DELETE FROM t_pokemon"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("REPLACE INTO t_pokemon DEFAULT VALUES"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("CREATE TABLE t (id INTEGER)"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("DROP TABLE t_pokemon"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("ALTER TABLE t_pokemon RENAME TO t"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("ATTACH DATABASE 'other.db' AS other"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("DETACH DATABASE other"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("PRAGMA query_only = OFF"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> sqliteQueryDomainService.query("VACUUM"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("SELECT 1; DROP TABLE t_pokemon"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("SELECT 1; DROP TABLE t_pokemon;"));
        Assertions.assertThrows(IllegalArgumentException.class,
            () -> sqliteQueryDomainService.query("SELECT 1 /* ; */ ; DROP TABLE t"));
    }
}

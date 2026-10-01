package cn.fantasticmao.pokemon.web.application.mcp;

import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.domain.mcp.model.QueryResult;
import cn.fantasticmao.pokemon.web.domain.mcp.model.TableSchema;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * SqliteMcpToolsTest
 *
 * @author fantasticmao
 * @see SqliteMcpTools
 * @since 2026/10/1
 */
public class SqliteMcpToolsTest extends SpringTest {
    @Resource
    private SqliteMcpTools sqliteMcpTools;

    @Test
    public void listSchema_containsPokemon() {
        boolean containsPokemon = sqliteMcpTools.listSchema().stream()
            .map(TableSchema::getName)
            .anyMatch("t_pokemon"::equals);
        Assertions.assertTrue(containsPokemon);
    }

    @Test
    public void query_bulbasaur() {
        QueryResult result = sqliteMcpTools.query("SELECT name_zh FROM t_pokemon WHERE idx = 1");
        Assertions.assertEquals("妙蛙种子", result.getRows().getFirst().getFirst());
    }
}

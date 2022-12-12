package cn.fantasticmao.pokemon.web.repoistory.impl;

import cn.fantasticmao.pokemon.web.domain.Pokemon;
import cn.fantasticmao.pokemon.web.repoistory.PokemonComplexRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.relational.core.sql.*;
import org.springframework.data.relational.core.sql.render.SqlRenderer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PokemonComplexRepositoryImpl
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
@Repository
public class PokemonComplexRepositoryImpl implements PokemonComplexRepository {
    @Resource
    private SqlRenderer sqlRenderer;
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Pokemon> findByGeneration(@Nullable Integer generation, @Nonnull Pageable pageable) {
        Map<String, Object> paramMap = new HashMap<>();
        SelectBuilder.BuildSelect selectBuilder = Select.builder()
            .select(Expressions.asterisk())
            .from(Table.create("t_pokemon"));
        if (pageable.isPaged()) {
            selectBuilder = ((SelectBuilder.SelectFromAndJoin) selectBuilder)
                .limitOffset(pageable.getPageSize(), pageable.getOffset());
        }
        if (generation != null) {
            paramMap.put("generation", generation);
            selectBuilder = ((SelectBuilder.SelectFromAndJoin) selectBuilder)
                .where(Conditions.isEqual(
                    Expressions.just("generation"),
                    SQL.bindMarker(":generation")
                ));
        }
        String sql = sqlRenderer.render(selectBuilder.build());
        return namedParameterJdbcTemplate.query(sql, paramMap,
            new BeanPropertyRowMapper<>(Pokemon.class));
    }
}

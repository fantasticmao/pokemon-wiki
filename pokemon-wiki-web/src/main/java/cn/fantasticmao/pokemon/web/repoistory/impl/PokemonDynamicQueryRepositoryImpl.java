package cn.fantasticmao.pokemon.web.repoistory.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.domain.Pokemon;
import cn.fantasticmao.pokemon.web.repoistory.PokemonDynamicQueryRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PokemonDynamicQueryRepositoryImpl
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public class PokemonDynamicQueryRepositoryImpl implements PokemonDynamicQueryRepository {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Pokemon> listByGenerationAndEggGroup(@Nullable Integer generation, @Nullable String eggGroup,
                                                     @Nullable Integer page, int size) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT t_pokemon.* " +
            "FROM t_pokemon " +
            "LEFT JOIN t_pokemon_detail " +
            "ON t_pokemon.idx = t_pokemon_detail.idx " +
            "WHERE 1 = 1 ";
        if (generation != null && generation > 0) {
            sql += "AND t_pokemon.generation = :generation ";
            paramMap.put("generation", generation);
        }
        if (StringUtils.isNotBlank(eggGroup)) {
            sql += "AND (t_pokemon_detail.egg_group1 = :eggGroup OR t_pokemon_detail.egg_group2 = :eggGroup) ";
            paramMap.put("eggGroup", eggGroup);
        }
        if (page != null && page >= 0) {
            sql += "LIMIT :limit OFFSET :offset";
            paramMap.put("limit", PageUtil.limit(size));
            paramMap.put("offset", PageUtil.offset(page, size));
        }
        return namedParameterJdbcTemplate.query(sql, paramMap,
            new BeanPropertyRowMapper<>(Pokemon.class));
    }
}

package cn.fantasticmao.pokemon.web.infra.dao.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.infra.dao.PokemonDynamicDao;
import cn.fantasticmao.pokemon.web.infra.model.PokemonPo;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PokemonDynamicDaoImpl
 *
 * @author fantasticmao
 * @since 2022-12-12
 */
public class PokemonDynamicDaoImpl implements PokemonDynamicDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<PokemonPo> findByIndexAndForm(Integer index, @Nullable String form) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = """
            SELECT * FROM t_pokemon
            WHERE idx = :index""";
        paramMap.put("index", index);
        if (StringUtils.isNotEmpty(form)) {
            sql += " AND form = :form";
            paramMap.put("form", form);
        }
        return namedParameterJdbcTemplate.query(sql, paramMap,
            new BeanPropertyRowMapper<>(PokemonPo.class));
    }

    @Override
    public List<PokemonPo> findByNameAndForm(@Nullable String nameZh, @Nullable String nameEn, @Nullable String form) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        Map<String, Object> paramMap = new HashMap<>();
        String sql = """
            SELECT * FROM t_pokemon
            WHERE 1 = 1""";
        if (StringUtils.isNotEmpty(nameZh)) {
            sql += " AND name_zh LIKE :nameZh";
            paramMap.put("nameZh", "%" + nameZh + "%");
        }
        if (StringUtils.isNotEmpty(nameEn)) {
            sql += " AND name_en LIKE :nameEn";
            paramMap.put("nameEn", "%" + nameEn + "%");
        }
        if (StringUtils.isNotEmpty(form)) {
            sql += " AND form = :form";
            paramMap.put("form", form);
        }
        return namedParameterJdbcTemplate.query(sql, paramMap,
            new BeanPropertyRowMapper<>(PokemonPo.class));
    }

    @Override
    public List<PokemonPo> findByGenerationAndEggGroup(Integer generation, @Nullable String eggGroup,
                                                       @Nullable Integer page, int size) {
        Map<String, Object> paramMap = new HashMap<>();
        String sql = """
            SELECT t_pokemon.*
            FROM t_pokemon
            LEFT JOIN t_pokemon_detail
            ON t_pokemon.idx = t_pokemon_detail.idx
            WHERE t_pokemon.generation = :generation""";
        paramMap.put("generation", generation);
        if (StringUtils.isNotEmpty(eggGroup)) {
            sql += " AND (t_pokemon_detail.egg_group1 = :eggGroup OR t_pokemon_detail.egg_group2 = :eggGroup)";
            paramMap.put("eggGroup", eggGroup);
        }
        if (page != null && page >= 0) {
            sql += " LIMIT :limit OFFSET :offset";
            paramMap.put("limit", PageUtil.limit(size));
            paramMap.put("offset", PageUtil.offset(page, size));
        }
        return namedParameterJdbcTemplate.query(sql, paramMap,
            new BeanPropertyRowMapper<>(PokemonPo.class));
    }
}

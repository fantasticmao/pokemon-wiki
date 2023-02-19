package cn.fantasticmao.pokemon.web.repoistory.impl;

import cn.fantasticmao.pokemon.web.domain.Item;
import cn.fantasticmao.pokemon.web.repoistory.ItemDynamicRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ItemDynamicRepository
 *
 * @author fantasticmao
 * @since 2023-02-20
 */
public class ItemDynamicRepositoryImpl implements ItemDynamicRepository {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Item> findByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT * FROM t_item " +
            "WHERE 1 = 1 ";
        if (StringUtils.isNotEmpty(nameZh)) {
            sql += "AND name_zh LIKE '%' || :nameZh || '%' ";
            paramMap.put("nameZh", nameZh);
        }
        if (StringUtils.isNotEmpty(nameEn)) {
            sql += "AND name_en LIKE '%' || :nameEn || '%' ";
            paramMap.put("nameEn", nameEn);
        }
        return namedParameterJdbcTemplate.query(sql, paramMap,
            new BeanPropertyRowMapper<>(Item.class));
    }
}

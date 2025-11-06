package cn.fantasticmao.pokemon.web.infra.dao.impl;

import cn.fantasticmao.pokemon.web.infra.model.AbilityPo;
import cn.fantasticmao.pokemon.web.infra.dao.AbilityDynamicDao;
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
 * AbilityDynamicDaoImpl
 *
 * @author fantasticmao
 * @since 2023-02-19
 */
public class AbilityDynamicDaoImpl implements AbilityDynamicDao {
    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<AbilityPo> findByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        Map<String, Object> paramMap = new HashMap<>();
        String sql = "SELECT * FROM t_ability " +
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
            new BeanPropertyRowMapper<>(AbilityPo.class));
    }
}

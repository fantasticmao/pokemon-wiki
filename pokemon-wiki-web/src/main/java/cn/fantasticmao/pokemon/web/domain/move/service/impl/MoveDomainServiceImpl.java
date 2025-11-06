package cn.fantasticmao.pokemon.web.domain.move.service.impl;

import cn.fantasticmao.pokemon.web.domain.move.model.Move;
import cn.fantasticmao.pokemon.web.domain.move.repoistory.MoveRepository;
import cn.fantasticmao.pokemon.web.domain.move.service.MoveDomainService;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

/**
 * MoveDomainServiceImpl
 *
 * @author fantasticmao
 * @since 2018/8/6
 */
@Service
public class MoveDomainServiceImpl implements MoveDomainService {
    @Resource
    private MoveRepository moveRepository;

    @Override
    public List<Move> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }
        return moveRepository.listByName(nameZh, nameEn);
    }

    @Override
    public List<Move> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }
        return moveRepository.list(page, size);
    }
}

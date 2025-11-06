package cn.fantasticmao.pokemon.web.infra.repoistory.impl;

import cn.fantasticmao.mundo.core.util.PageUtil;
import cn.fantasticmao.pokemon.web.domain.move.model.Move;
import cn.fantasticmao.pokemon.web.domain.move.model.MoveDetail;
import cn.fantasticmao.pokemon.web.domain.move.repoistory.MoveRepository;
import cn.fantasticmao.pokemon.web.infra.converter.MoveConverter;
import cn.fantasticmao.pokemon.web.infra.dao.MoveDao;
import cn.fantasticmao.pokemon.web.infra.dao.MoveDetailDao;
import cn.fantasticmao.pokemon.web.infra.model.MoveDetailPo;
import cn.fantasticmao.pokemon.web.infra.model.MovePo;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * MoveRepositoryImpl
 *
 * @author fantasticmao
 * @since 2025-11-06
 */
@Repository
public class MoveRepositoryImpl implements MoveRepository {
    @Resource
    private MoveDao moveDao;
    @Resource
    private MoveDetailDao moveDetailDao;
    @Resource
    private MoveConverter moveConverter;

    @Override
    public List<Move> listByName(@Nullable String nameZh, @Nullable String nameEn) {
        if (StringUtils.isAllEmpty(nameZh, nameEn)) {
            return Collections.emptyList();
        }

        List<MovePo> movePoList = moveDao.findByName(nameZh, nameEn);
        if (CollectionUtils.isEmpty(movePoList)) {
            return Collections.emptyList();
        }

        Set<Integer> moveIdSet = movePoList.stream()
            .map(MovePo::getId)
            .collect(Collectors.toSet());
        Map<Integer, MoveDetailPo> detailPoMap = moveDetailDao.findByIdIn(moveIdSet).stream()
            .collect(Collectors.toMap(MoveDetailPo::getId, Function.identity()));

        return movePoList.stream()
            .map(movePo -> {
                Move move = moveConverter.toMove(movePo);

                MoveDetail detail = moveConverter.toMoveDetail(
                    detailPoMap.get(move.getId())
                );
                move.setDetail(detail);

                return move;
            })
            .sorted()
            .collect(Collectors.toList());
    }

    @Override
    public List<Move> list(int page, int size) {
        if (page < 0 || size < 1) {
            return Collections.emptyList();
        }
        List<MovePo> movePoList = moveDao.find(PageUtil.limit(size), PageUtil.offset(page, size));
        return movePoList.stream()
            .map(moveConverter::toMove)
            .sorted()
            .collect(Collectors.toList());
    }
}

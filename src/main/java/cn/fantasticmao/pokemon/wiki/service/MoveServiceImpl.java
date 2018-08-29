package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.Move;
import cn.fantasticmao.pokemon.wiki.repoistory.MoveRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * MoveServiceImpl
 *
 * @author maodh
 * @since 2018/8/6
 */
@Service
public class MoveServiceImpl implements MoveService {
    @Resource
    private MoveRepository moveRepository;

    @Override
    public List<Move> listAll() {
        return moveRepository.findAll();
    }
}

package cn.fantasticmao.pokemon.wiki.service;

import cn.fantasticmao.pokemon.wiki.domain.Nature;
import cn.fantasticmao.pokemon.wiki.repoistory.NatureRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * NatureServiceImpl
 *
 * @author maodh
 * @since 2018/8/6
 */
@Service
public class NatureServiceImpl implements NatureService {
    @Resource
    private NatureRepository natureRepository;

    @Override
    public List<Nature> listAll() {
        return natureRepository.findAll();
    }
}

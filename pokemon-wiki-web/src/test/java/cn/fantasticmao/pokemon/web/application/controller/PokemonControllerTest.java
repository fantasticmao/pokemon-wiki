package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.application.model.PokemonResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * PokemonControllerTest
 *
 * @author fantasticmao
 * @see cn.fantasticmao.pokemon.web.application.controller.PokemonController
 * @since 2022/3/2
 */
public class PokemonControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void listPokemonDetail_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pokemon/detail"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void listPokemonDetail_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pokemon/detail?index=1"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                List<PokemonResponse> list = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });
                Assertions.assertEquals(1, list.size());

                PokemonResponse bulbasaur = list.getFirst();
                super.assertBulbasaur(bulbasaur);
            });
    }

    @Test
    public void listPokemon_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pokemon/list?generation=1"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                List<PokemonResponse> list = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Integer maxIndex = list.stream().map(PokemonResponse::getIndex).max(Integer::compareTo).orElse(0);
                Assertions.assertEquals(151, maxIndex);
            });
    }
}

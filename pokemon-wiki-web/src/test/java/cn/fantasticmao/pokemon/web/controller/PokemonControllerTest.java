package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.PokemonBean;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * PokemonControllerTest
 *
 * @author fantasticmao
 * @see PokemonController
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
                JsonApi<List<PokemonBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertTrue(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.OK.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), jsonApi.getMessage());

                List<PokemonBean> list = jsonApi.getData();
                Assertions.assertEquals(1, list.size());

                PokemonBean pokemon = list.get(0);
                Assertions.assertEquals(1, pokemon.getIndex());
                Assertions.assertEquals("妙蛙种子", pokemon.getNameZh());
            });
    }

    @Test
    public void listPokemon_ok() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pokemon/list?generation=1"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                JsonApi<List<PokemonBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertTrue(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.OK.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), jsonApi.getMessage());

                List<PokemonBean> list = jsonApi.getData();
                Assertions.assertEquals(153, list.size());

                PokemonBean pokemon = list.get(0);
                Assertions.assertEquals(1, pokemon.getIndex());
                Assertions.assertEquals("妙蛙种子", pokemon.getNameZh());
            });
    }
}

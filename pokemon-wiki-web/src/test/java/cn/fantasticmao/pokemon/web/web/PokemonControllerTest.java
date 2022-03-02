package cn.fantasticmao.pokemon.web.web;

import cn.fantasticmao.pokemon.web.SpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * PokemonControllerTest
 *
 * @author fantasticmao
 * @since 2022/3/2
 */
public class PokemonControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void listPokemonDetail() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/pokemon/detail?index=1"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                System.out.println(bodyJson);
            });
    }
}

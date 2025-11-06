package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.application.model.MoveResponse;
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
 * MoveControllerTest
 *
 * @author fantasticmao
 * @see cn.fantasticmao.pokemon.web.application.controller.MoveController
 * @since 2022/3/3
 */
public class MoveControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void listMoveDetail_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/move/detail"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void listMoveDetail_ok() throws Exception {
        final String nameZh = "拍击";
        final String nameEn = "Pound";
        mvc.perform(MockMvcRequestBuilders.get("/move/detail?nameZh={nameZh}&nameEn={nameEn}", nameZh, nameEn))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                List<MoveResponse> list = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });
                Assertions.assertEquals(1, list.size());

                MoveResponse pound = list.getFirst();
                super.assertPound(pound);
            });
    }

    @Test
    public void listMove() throws Exception {
        final int pageSize = 20;
        mvc.perform(MockMvcRequestBuilders.get("/move/list?size=" + pageSize))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                List<MoveResponse> list = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });
                Assertions.assertEquals(pageSize, list.size());
            });
    }
}

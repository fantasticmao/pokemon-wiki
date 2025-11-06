package cn.fantasticmao.pokemon.web.application.controller;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.application.model.ItemResponse;
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
 * ItemControllerTest
 *
 * @author fantasticmao
 * @see cn.fantasticmao.pokemon.web.application.controller.ItemController
 * @since 2022/3/3
 */
public class ItemControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void listItemDetail_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/item/detail"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void listItemDetail_ok() throws Exception {
        final String nameZh = "除虫喷雾";
        final String nameEn = "Repel";
        mvc.perform(MockMvcRequestBuilders.get("/item/detail?nameZh={nameZh}&nameEn={nameEn}", nameZh, nameEn))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                List<ItemResponse> list = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });
                Assertions.assertEquals(1, list.size());

                ItemResponse repel = list.getFirst();
                super.assertRepel(repel);
            });
    }

    @Test
    public void listItem() throws Exception {
        final int pageSize = 20;
        mvc.perform(MockMvcRequestBuilders.get("/item/list?page=1&size=" + pageSize))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                List<ItemResponse> list = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });
                Assertions.assertEquals(pageSize, list.size());
            });
    }
}

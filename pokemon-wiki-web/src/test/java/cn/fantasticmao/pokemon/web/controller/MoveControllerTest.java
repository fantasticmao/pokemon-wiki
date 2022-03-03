package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.MoveBean;
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
 * MoveControllerTest
 *
 * @author fantasticmao
 * @see MoveController
 * @since 2022/3/3
 */
public class MoveControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void listMoveDetail_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/move/detail"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                JsonApi<List<MoveBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertFalse(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), jsonApi.getMessage());
            });
    }

    @Test
    public void listMoveDetail_ok() throws Exception {
        final String nameZh = "火焰拳";
        mvc.perform(MockMvcRequestBuilders.get("/move/detail?nameZh=" + nameZh))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                JsonApi<List<MoveBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertTrue(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.OK.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), jsonApi.getMessage());

                List<MoveBean> list = jsonApi.getData();
                Assertions.assertEquals(1, list.size());

                MoveBean move = list.get(0);
                Assertions.assertEquals(nameZh, move.getNameZh());
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
                JsonApi<List<MoveBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertTrue(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.OK.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), jsonApi.getMessage());

                List<MoveBean> list = jsonApi.getData();
                Assertions.assertEquals(pageSize, list.size());
            });
    }
}

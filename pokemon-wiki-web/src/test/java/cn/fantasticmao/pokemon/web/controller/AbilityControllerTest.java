package cn.fantasticmao.pokemon.web.controller;

import cn.fantasticmao.mundo.core.util.JsonUtil;
import cn.fantasticmao.mundo.web.support.JsonApi;
import cn.fantasticmao.pokemon.web.SpringTest;
import cn.fantasticmao.pokemon.web.bean.AbilityBean;
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
 * AbilityControllerTest
 *
 * @author fantasticmao
 * @see AbilityController
 * @since 2022/3/3
 */
public class AbilityControllerTest extends SpringTest {
    @Resource
    private MockMvc mvc;

    @Test
    public void listAbilityDetail_badRequest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/ability/detail"))
            .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void listAbilityDetail_ok() throws Exception {
        final String nameZh = "恶臭";
        final String nameEn = "Stench";
        mvc.perform(MockMvcRequestBuilders.get("/ability/detail?nameZh={nameZh}&nameEn={nameEn}", nameZh, nameEn))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                JsonApi<List<AbilityBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertTrue(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.OK.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), jsonApi.getMessage());

                List<AbilityBean> list = jsonApi.getData();
                Assertions.assertEquals(1, list.size());

                AbilityBean stench = list.get(0);
                super.assertStench(stench);
            });
    }

    @Test
    public void listAbility() throws Exception {
        final int pageSize = 20;
        mvc.perform(MockMvcRequestBuilders.get("/ability/list?size=" + pageSize))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andDo(result -> {
                MockHttpServletResponse response = result.getResponse();
                String bodyJson = response.getContentAsString(StandardCharsets.UTF_8);
                JsonApi<List<AbilityBean>> jsonApi = JsonUtil.fromJson(bodyJson, new TypeReference<>() {
                });

                Assertions.assertTrue(jsonApi.isStatus());
                Assertions.assertEquals(HttpStatus.OK.value(), jsonApi.getCode());
                Assertions.assertEquals(HttpStatus.OK.getReasonPhrase(), jsonApi.getMessage());

                List<AbilityBean> list = jsonApi.getData();
                Assertions.assertEquals(pageSize, list.size());
            });
    }
}

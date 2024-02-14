package com.ed.coindesk.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class CurrencyInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itShouldGetUpdatedTimeCorrectly() throws Exception {
        // GIVEN
        ResultActions resultActions = mockMvc.perform(get("/v1/currency-info/get-updated-time")
                .contentType(MediaType.APPLICATION_JSON));
        // WHEN
        // THEN
        resultActions.andExpect(status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void itShouldFindCurrencyInfoByCodeCorrectly() throws Exception {
        // GIVEN
        String code = "USD";
        ResultActions resultActions = mockMvc.perform(get("/v1/currency-info/{code}", code)
                .contentType(MediaType.APPLICATION_JSON));
        // WHEN
        // THEN
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("美元"))
                .andExpect(jsonPath("$.code").value(code))
                .andExpect(jsonPath("$.rate").exists());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }
}
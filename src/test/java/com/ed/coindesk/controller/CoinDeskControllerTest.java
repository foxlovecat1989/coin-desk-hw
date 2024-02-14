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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class CoinDeskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itShouldCallApiSuccess() throws Exception {
        // GIVEN
        ResultActions resultActions = mockMvc.perform(get("/v1/coin-desk")
                        .contentType(MediaType.APPLICATION_JSON));
        log.info(resultActions.toString());
        // WHEN
        // THEN
        resultActions.andExpect(status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }
}
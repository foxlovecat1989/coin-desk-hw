package com.ed.coindesk.controller;

import com.ed.coindesk.model.payload.CurrencyCreationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void itShouldGetCurrencyNameCorrectly() throws Exception {
        // GIVEN
        ResultActions resultActions = mockMvc.perform(get("/v1/currency/get-currency-name/{code}", "USD")
                .contentType(MediaType.APPLICATION_JSON));

        resultActions.andExpect(status().isOk());
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void itShouldCreateCurrencyCorrectly() throws Exception {
        // GIVEN
        String mockName = "mockName";
        String mockCode = "mockCode";
        CurrencyCreationRequest request = CurrencyCreationRequest.builder()
                .name("mockName")
                .code("mockCode")
                .build();
        ResultActions resultActions = mockMvc.perform(post("/v1/currency")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectToJson(request)));
        // WHEN
        // THEN
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(mockName))
                .andExpect(jsonPath("$.code").value(mockCode));
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void itShouldUpdateCurrencyNameCorrectly() throws Exception {
        // GIVEN
        String code = "USD";
        String mockUpdatedName = "mockCode";
        ResultActions resultActions = mockMvc.perform(patch("/v1/currency/{code}/{name}", code, mockUpdatedName)
                .contentType(MediaType.APPLICATION_JSON));
        // WHEN
        // THEN
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(mockUpdatedName));
        log.info(resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void itShouldDeleteCorrectly() throws Exception {
        // GIVEN
        String code = "USD";
        ResultActions resultActions = mockMvc.perform(delete("/v1/currency/{code}", code)
                .contentType(MediaType.APPLICATION_JSON));
        // WHEN
        // THEN
        resultActions.andExpect(status().isOk());
    }

    public String objectToJson(Object o) {
        try {
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Convert object to JSON Fail", e);
        }
    }
}
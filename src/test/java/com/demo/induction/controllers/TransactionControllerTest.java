package com.demo.induction.controllers;

import com.demo.induction.DemoApplication;
import com.demo.induction.entity.BaseResponse;
import com.demo.induction.entity.Transaction;
import com.demo.induction.services.LogService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DemoApplication.class,
        TransactionController.class
})
@WebAppConfiguration
public class TransactionControllerTest {

    private JavaType type;
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper mapper;


    @Autowired
    private LogService logService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        JavaType transactionType = mapper.getTypeFactory().constructParametricType(List.class, Transaction.class);
        type = mapper.getTypeFactory().constructParametricType(BaseResponse.class, transactionType);
    }

    @Test
    @DirtiesContext
    public void testIfTheEndpointIsSuccessfullySetup() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/transactions").param("fileName", "data.xml"))
                .andDo(print()).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentType()).isNotEmpty();
        BaseResponse<List<Transaction>> response = mapper.readValue(result.getResponse().getContentAsString(), type);
        assertThat(response.getData().size()).isEqualTo(5);
        assertThat(response.getMessage()).isNotEmpty();

        assertThat(logService.getLogByName(result.getRequest().getRequestURL().toString()))
                .get().hasFieldOrPropertyWithValue("count",1);
    }

    @Test
    @DirtiesContext
    public void testTheEndpointForBadRequest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/transactions").param("fileName", "data.xyz"))
                .andDo(print()).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(result.getResponse().getContentType()).isNotEmpty();
        BaseResponse<List<Transaction>> response = mapper.readValue(result.getResponse().getContentAsString(), type);
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isNotEmpty();
        assertThat(response.getMessage()).isNotEmpty();
    }


    @Test
    public void testTheEndpointForFileNotFound() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/transactions").param("fileName",
                "does_not_exist.xml"))
                .andDo(print()).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(result.getResponse().getContentType()).isNotEmpty();
        BaseResponse<List<Transaction>> response = mapper.readValue(result.getResponse().getContentAsString(), type);
        assertThat(response.getData()).isNull();
        assertThat(response.getMessage()).isNotEmpty();
    }

    @Test
    @DirtiesContext
    public void testIfTheCSVEndpointIsSuccessfullySetup() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/transactions/csv"))
                .andDo(print()).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentType()).isNotEmpty();
        BaseResponse<List<Transaction>> response = mapper.readValue(result.getResponse().getContentAsString(), type);
        assertThat(response.getData().size()).isEqualTo(5);
        assertThat(response.getMessage()).isNotEmpty();



        assertThat(logService.getLogByName(result.getRequest().getRequestURL().toString()))
                .get().hasFieldOrPropertyWithValue("count",1);
    }


    @Test
    @DirtiesContext
    public void testIfTheXMLEndpointIsSuccessfullySetup() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/transactions/xml"))
                .andDo(print()).andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
        assertThat(result.getResponse().getContentType()).isNotEmpty();
        BaseResponse<List<Transaction>> response = mapper.readValue(result.getResponse().getContentAsString(), type);
        assertThat(response.getData().size()).isEqualTo(5);
        assertThat(response.getMessage()).isNotEmpty();



        assertThat(logService.getLogByName(result.getRequest().getRequestURL().toString()))
                .get().hasFieldOrPropertyWithValue("count",1);
    }
}
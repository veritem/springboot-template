package com.example.template.controllers;

import com.example.template.config.WebSecurity;
import com.example.template.models.Cat;
import com.example.template.services.CatService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CatControllerIntTest {

    @MockBean
    private CatService catServiceMock;


    private MockMvc mockMvc;


    @Test
    void getAllCats() throws Exception {
        List<Cat> cats = Arrays.asList(
                new Cat("Bella", UUID.fromString("c255bdff-a545-49ea-b291-ccabbe50acd2")),
                new Cat("Leo",UUID.fromString("7da6069d-8c9c-49dc-b3a0-7f53a847de63")),
                new Cat("Milo",UUID.fromString("c4128169-979e-4e6d-85ea-1ba3f504e474"))
        );

        when(catServiceMock.getAllCats()).thenReturn(cats);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/v1/cats")
                .contentType(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"name\":\"Leo\",\"id\":\"7da6069d-8c9c-49dc-b3a0-7f53a847de63\"},{\"name\":\"Bella\",\"id\":\"c255bdff-a545-49ea-b291-ccabbe50acd2\"},{\"name\":\"Milo\",\"id\":\"c4128169-979e-4e6d-85ea-1ba3f504e474\"}]"))
                .andReturn();
    }

    /*
    @Test
    void getCatById() {
    }

    @Test
    void createCat() {
    }

    @Test
    void updateCat() {
    }

    @Test
    void deleteCat() {
    }
     */
}


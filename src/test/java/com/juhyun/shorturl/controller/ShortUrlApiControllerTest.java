package com.juhyun.shorturl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juhyun.shorturl.controller.dto.ReadShortUrlResponse;
import com.juhyun.shorturl.controller.dto.ShortUrlRequest;
import com.juhyun.shorturl.controller.dto.ShortUrlResponse;
import com.juhyun.shorturl.service.ShortUrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShortUrlApiController.class)
@AutoConfigureMockMvc
class ShortUrlApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    ShortUrlService shortUrlService;

    @InjectMocks
    ShortUrlApiController controller;

    @Autowired
    private ObjectMapper objectMapper;

    private String shortUrl;
    private String longUrl;

    @BeforeEach
    void setUp() {
        shortUrl = "xk8M";
        longUrl = "http://localhost:8080/abcabcabc";
        // https://stackoverflow.com/questions/32348270/spring-mvc-test-failing
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build(); // NPE 처리
    }

    @Test
    void readTest() throws Exception {
        ShortUrlResponse response = ShortUrlResponse.builder()
                ._id(10L)
                .shortUrl(shortUrl)
                .longUrl(longUrl)
                .customUrl("")
                .requestCount(1)
                .build();

        // when
        when(shortUrlService.find(any(String.class))).thenReturn(response);

        // then
        mockMvc.perform(
                get("/api/xk8M")
                        .content(objectMapper.writeValueAsString(shortUrl))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$._id").value(10))
                .andExpect(jsonPath("$.shortUrl").value(shortUrl))
                .andExpect(jsonPath("$.longUrl").value(longUrl))
                .andDo(print());
    }

    @Test
    void createTest() throws Exception {
        // given
        ShortUrlRequest request = new ShortUrlRequest(longUrl, "");
        ReadShortUrlResponse response = new ReadShortUrlResponse(shortUrl);

        // when
        when(shortUrlService.create(any(ShortUrlRequest.class))).thenReturn(response);

        // then
        mockMvc.perform(
                post("/api/short-url")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.shortUrl").value(shortUrl))
                .andDo(print());

    }

}
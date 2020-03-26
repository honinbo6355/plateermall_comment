package com.plateer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.plateer.CommentControllerTestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CommentControllerTestApplication.class)
@AutoConfigureMockMvc
class CommentControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
    public void test() throws Exception {

        mockMvc.perform(get("http://localhost:9999/api/comments/getmycomment/testId"))
                .andExpect(status().isOk());
    }

}

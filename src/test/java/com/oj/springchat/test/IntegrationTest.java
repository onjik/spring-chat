package com.oj.springchat.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oj.springchat.test.config.TestProfiles;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base test class for spring integration testing
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(TestProfiles.TEST)
@Transactional
@Disabled
public class IntegrationTest {
    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;


}

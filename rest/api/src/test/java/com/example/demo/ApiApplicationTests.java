package com.example.demo;


import org.api.ApiApplication;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest(classes = {ApiApplication.class})
@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles("test")
class ApiApplicationTests {



}

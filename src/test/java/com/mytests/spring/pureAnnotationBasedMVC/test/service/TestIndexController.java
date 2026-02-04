package com.mytests.spring.pureAnnotationBasedMVC.test.service;

import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringJUnitWebConfig(classes = TestWebConfig.class, resourcePath = "src/test/resources/static")
class TestIndexController {
    @Value("${prop1}")
    String attrValue;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected MockServletContext mockServletContext;


    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testWac() {
        ServletContext servletContext = wac.getServletContext();
        Assertions.assertNotNull(servletContext);
        Assertions.assertInstanceOf(MockServletContext.class, servletContext);
        for (String beanName : wac.getBeanDefinitionNames()) {

                System.out.println("Bean Name: " + beanName);
                System.out.println("Bean " + wac.getBean(beanName));

        }
    }

    @Test
    public void testModelAttrs() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/"));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(view().name("index"));
        resultActions.andExpect(model().attribute("title",attrValue));
    }
}

package org.pes.onecemulator.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PayerController.class)
public class PayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PayerController controller;

    @Test
    public void checkTest() {}
}
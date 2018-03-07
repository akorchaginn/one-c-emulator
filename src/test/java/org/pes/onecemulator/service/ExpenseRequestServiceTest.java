package org.pes.onecemulator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.impl.ExpenseRequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ExpenseRequestServiceTest {

    @TestConfiguration
    static class ExpenseRequestServiceTestContextConfiguration {

        @Bean
        public ExpenseRequestService expenseRequestService() {
            return new ExpenseRequestServiceImpl();
        }
    }

    @MockBean
    private ExpenseRequestRepository expenseRequestRepository;

    @MockBean
    private SourceRepository sourceRepository;

    @Autowired
    private ExpenseRequestService service;

    @Test
    public void checkTest() {

    }
}

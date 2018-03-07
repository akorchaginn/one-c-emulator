package org.pes.onecemulator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.repository.AccountingEntryRepository;
import org.pes.onecemulator.repository.ExpenseRequestRepository;
import org.pes.onecemulator.service.impl.AccountingEntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountingEntryServiceTest {

    @TestConfiguration
    static class AccountingEntryServiceTestContextConfiguration {

        @Bean
        public AccountingEntryService accountingEntryService() {
            return new AccountingEntryServiceImpl();
        }
    }

    @MockBean
    private AccountingEntryRepository accountingEntryRepository;

    @MockBean
    private ExpenseRequestRepository expenseRequestRepository;

    @MockBean
    private CrmInteractionService crmInteractionService;

    @Autowired
    private AccountingEntryService service;

    @Test
    public void checkTest() {

    }
}

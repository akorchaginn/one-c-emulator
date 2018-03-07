package org.pes.onecemulator.service;

import com.google.common.eventbus.AsyncEventBus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.impl.CrmInteractionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CrmInteractionServiceTest {

    @TestConfiguration
    static class CrmInteractionServiceTestContextConfiguration {

        @Bean
        public CrmInteractionService crmInteractionService() {
            return new CrmInteractionServiceImpl();
        }
    }

    @Autowired
    private CrmInteractionService service;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private SourceRepository sourceRepository;

    @MockBean
    private AsyncEventBus asyncEventBus;

    @Test
    public void checkTest() {

    }
}

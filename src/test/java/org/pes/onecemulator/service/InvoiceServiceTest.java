package org.pes.onecemulator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.pes.onecemulator.repository.PayerRepository;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.impl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InvoiceServiceTest {

    @TestConfiguration
    static class InvoiceServiceTestContextConfiguration {

        @Bean
        public InvoiceService invoiceService() {
            return new InvoiceServiceImpl();
        }
    }

    @MockBean
    private InvoiceRepository invoiceRepository;

    @MockBean
    private PayerRepository payerRepository;

    @MockBean
    private SourceRepository sourceRepository;

    @Autowired
    private InvoiceService service;

    @Test
    public void checkTest() {

    }
}

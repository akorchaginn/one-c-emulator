package org.pes.onecemulator.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CrmInteractionServiceTest {

    @Autowired
    private CrmInteractionService service;

    @MockBean
    private InvoiceRepository invoiceRepository;

    @Test
    public void getDocumentsCrmByIdEmptyResultTest() {
        when(invoiceRepository.findByIdAndSource(any(), anyString())).thenReturn(Optional.empty());
        Assert.assertTrue(service.getDocumentsCrmById(Collections.emptyList(), "1db").isEmpty());
    }

    @Test
    public void getDocumentsCrmByIdOneResultTest() {
        DocumentCrm documentCrm = new DocumentCrm();
        documentCrm.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        Invoice invoice = new Invoice();
        invoice.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        when(invoiceRepository.findByIdAndSource(any(UUID.class), anyString())).thenReturn(Optional.of(invoice));

        List<DocumentCrm> documentCrmResult =
                service.getDocumentsCrmById(Collections.singletonList(documentCrm), "1db");

        Assert.assertEquals(1, documentCrmResult.size());
        Assert.assertEquals(documentCrmResult.get(0).getId(), documentCrm.getId());
    }

    @Test
    public void getDocumentsCrmByIdWithNullResultTest() {
        DocumentCrm documentCrm = new DocumentCrm();
        documentCrm.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        Invoice invoice = new Invoice();
        invoice.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        when(invoiceRepository.findByIdAndSource(any(UUID.class), anyString())).thenReturn(Optional.of(invoice));

        List<DocumentCrm> documentCrmResult =
                service.getDocumentsCrmById(Arrays.asList(documentCrm, null), "1db");

        Assert.assertEquals(1, documentCrmResult.size());
        Assert.assertEquals(documentCrmResult.get(0).getId(), documentCrm.getId());
    }

    @Test
    public void getDocumentsCrmByIdWithNullIdResultTest() {
        DocumentCrm documentCrm1 = new DocumentCrm();
        documentCrm1.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        DocumentCrm documentCrm2 = new DocumentCrm();
        documentCrm2.setId(null);

        Invoice invoice = new Invoice();
        invoice.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        when(invoiceRepository.findByIdAndSource(any(UUID.class), anyString())).thenReturn(Optional.of(invoice));

        List<DocumentCrm> documentCrmResult =
                service.getDocumentsCrmById(Arrays.asList(documentCrm1, documentCrm2), "1db");

        Assert.assertEquals(1, documentCrmResult.size());
        Assert.assertEquals(documentCrmResult.get(0).getId(), documentCrm1.getId());
    }

    @Test
    public void getDocumentsCrmByExternalIdEmptyResultTest() {
        when(invoiceRepository.findByExternalIdAndSource(any(), anyString())).thenReturn(Optional.empty());
        Assert.assertTrue(service.getDocumentsCrmByExternalId(Collections.emptyList(), "1db").isEmpty());
    }

    @Test
    public void getDocumentsCrmByExternalIdOneResultTest() {
        DocumentCrm documentCrm = new DocumentCrm();
        documentCrm.setExternalId("123123123");

        Invoice invoice = new Invoice();
        invoice.setExternalId("123123123");

        when(invoiceRepository.findByExternalIdAndSource(anyString(), anyString())).thenReturn(Optional.of(invoice));

        List<DocumentCrm> documentCrmResult =
                service.getDocumentsCrmByExternalId(Collections.singletonList(documentCrm), "1db");

        Assert.assertEquals(1, documentCrmResult.size());
        Assert.assertEquals(documentCrmResult.get(0).getExternalId(), documentCrm.getExternalId());
    }

    @Test
    public void getDocumentsCrmByExternalIdWithNullResultTest() {
        DocumentCrm documentCrm = new DocumentCrm();
        documentCrm.setExternalId("123123123");

        Invoice invoice = new Invoice();
        invoice.setExternalId("123123123");

        when(invoiceRepository.findByExternalIdAndSource(anyString(), anyString())).thenReturn(Optional.of(invoice));

        List<DocumentCrm> documentCrmResult =
                service.getDocumentsCrmByExternalId(Arrays.asList(documentCrm, null), "1db");

        Assert.assertEquals(1, documentCrmResult.size());
        Assert.assertEquals(documentCrmResult.get(0).getExternalId(), documentCrm.getExternalId());
    }

    @Test
    public void getDocumentsCrmByExternalIdWithNullExternalIdResultTest() {
        DocumentCrm documentCrm1 = new DocumentCrm();
        documentCrm1.setExternalId("123123123");

        DocumentCrm documentCrm2 = new DocumentCrm();
        documentCrm2.setExternalId(null);

        Invoice invoice = new Invoice();
        invoice.setExternalId("123123123");

        when(invoiceRepository.findByExternalIdAndSource(any(String.class), anyString())).thenReturn(Optional.of(invoice));

        List<DocumentCrm> documentCrmResult =
                service.getDocumentsCrmByExternalId(Arrays.asList(documentCrm1, documentCrm2), "1db");

        Assert.assertEquals(1, documentCrmResult.size());
        Assert.assertEquals(documentCrmResult.get(0).getExternalId(), documentCrm1.getExternalId());
    }
}

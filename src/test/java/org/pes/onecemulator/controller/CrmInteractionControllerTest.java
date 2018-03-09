package org.pes.onecemulator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.service.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CrmInteractionController.class)
public class CrmInteractionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CrmInteractionService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getDocByIdWithEmptyListTest() throws Exception {
        String SOURCE = "1db";

        given(service.getDocumentsCrmById(new ArrayList<>(), SOURCE)).willReturn(new ArrayList<>());

        mvc.perform(post("/{0}/hs/DocID", SOURCE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));
    }

    @Test
    public void getDocByIdWithOneElementAtListTest() throws Exception {
        String SOURCE = "1db";

        DocumentCrm documentCrmInput = new DocumentCrm();
        documentCrmInput.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        DocumentCrm documentCrmOutput = new DocumentCrm();
        documentCrmOutput.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));
        documentCrmOutput.setPayerName("РогаИКопыта");
        documentCrmOutput.setStatus("Продано");
        documentCrmOutput.setPaymentSum(new BigDecimal(123));
        documentCrmOutput.setSum(new BigDecimal(321));
        documentCrmOutput.setExternalId("4839e2fc-8909-4161-ad0b-5b0c66b5c08b");
        documentCrmOutput.setNumberOq("123");
        documentCrmOutput.setNumber("321");
        documentCrmOutput.setDate(LocalDate.parse("2018-03-08", DateTimeFormatter.ISO_DATE));
        documentCrmOutput.setPaymentDate(LocalDate.parse("2018-03-09", DateTimeFormatter.ISO_DATE));

        List<DocumentCrm> docIn = Collections.singletonList(documentCrmInput);
        List<DocumentCrm> docOut = Collections.singletonList(documentCrmOutput);

        given(service.getDocumentsCrmById(anyList(), anyString())).willReturn(docOut);

        mvc.perform(post("/{0}/hs/DocID", SOURCE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(docIn)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(documentCrmOutput.getId().toString())))
                .andExpect(jsonPath("$[0].nom", is(documentCrmOutput.getNumber())))
                .andExpect(jsonPath("$[0].NomOQ", is(documentCrmOutput.getNumberOq())))
                .andExpect(jsonPath("$[0].name", is(documentCrmOutput.getPayerName())))
                .andExpect(jsonPath("$[0].sum", is(documentCrmOutput.getSum().intValue())))
                .andExpect(jsonPath("$[0].date", is(documentCrmOutput.getDate().toString())))
                .andExpect(jsonPath("$[0].status", is(documentCrmOutput.getStatus())))
                .andExpect(jsonPath("$[0].dataOplat", is(documentCrmOutput.getPaymentDate().toString())))
                .andExpect(jsonPath("$[0].sumOplat", is(documentCrmOutput.getPaymentSum().intValue())))
                .andExpect(jsonPath("$[0].uuid", is(documentCrmOutput.getExternalId())));
    }

    @Test
    public void getDocByIdWithManyElementsAtListTest() throws Exception {
        String SOURCE = "1db";

        DocumentCrm documentCrmInput1 = new DocumentCrm();
        documentCrmInput1.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));

        DocumentCrm documentCrmOutput1 = new DocumentCrm();
        documentCrmOutput1.setId(UUID.fromString("468e2104-109b-4f85-b0f8-d2d13a9e501a"));
        documentCrmOutput1.setPayerName("РогаИКопыта");
        documentCrmOutput1.setStatus("Продано");
        documentCrmOutput1.setPaymentSum(new BigDecimal(123));
        documentCrmOutput1.setSum(new BigDecimal(321));
        documentCrmOutput1.setExternalId("4839e2fc-8909-4161-ad0b-5b0c66b5c08b");
        documentCrmOutput1.setNumberOq("123");
        documentCrmOutput1.setNumber("321");
        documentCrmOutput1.setDate(LocalDate.parse("2018-03-08", DateTimeFormatter.ISO_DATE));
        documentCrmOutput1.setPaymentDate(LocalDate.parse("2018-03-09", DateTimeFormatter.ISO_DATE));

        DocumentCrm documentCrmInput2 = new DocumentCrm();
        documentCrmInput2.setId(UUID.fromString("90cf7646-c1bf-4fbe-a3df-cf74eca234b7"));

        DocumentCrm documentCrmOutput2 = new DocumentCrm();
        documentCrmOutput2.setId(UUID.fromString("90cf7646-c1bf-4fbe-a3df-cf74eca234b7"));
        documentCrmOutput2.setPayerName("КопытаИрония");
        documentCrmOutput2.setStatus("Продается");
        documentCrmOutput2.setPaymentSum(new BigDecimal(100000));
        documentCrmOutput2.setSum(new BigDecimal(2000));
        documentCrmOutput2.setExternalId("08e1c400-e2fd-4ae4-a9ff-3bf2ccd24ad3");
        documentCrmOutput2.setNumberOq("фыв");
        documentCrmOutput2.setNumber("уцй");
        documentCrmOutput2.setDate(LocalDate.parse("2018-03-09", DateTimeFormatter.ISO_DATE));
        documentCrmOutput2.setPaymentDate(LocalDate.parse("2018-03-10", DateTimeFormatter.ISO_DATE));

        List<DocumentCrm> docIn = Arrays.asList(documentCrmInput1, documentCrmInput2);
        List<DocumentCrm> docOut = Arrays.asList(documentCrmOutput1, documentCrmOutput2);

        given(service.getDocumentsCrmById(anyList(), anyString())).willReturn(docOut);

        mvc.perform(post("/{0}/hs/DocID", SOURCE)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(docIn)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(documentCrmOutput1.getId().toString())))
                .andExpect(jsonPath("$[0].nom", is(documentCrmOutput1.getNumber())))
                .andExpect(jsonPath("$[0].NomOQ", is(documentCrmOutput1.getNumberOq())))
                .andExpect(jsonPath("$[0].name", is(documentCrmOutput1.getPayerName())))
                .andExpect(jsonPath("$[0].sum", is(documentCrmOutput1.getSum().intValue())))
                .andExpect(jsonPath("$[0].date", is(documentCrmOutput1.getDate().toString())))
                .andExpect(jsonPath("$[0].status", is(documentCrmOutput1.getStatus())))
                .andExpect(jsonPath("$[0].dataOplat", is(documentCrmOutput1.getPaymentDate().toString())))
                .andExpect(jsonPath("$[0].sumOplat", is(documentCrmOutput1.getPaymentSum().intValue())))
                .andExpect(jsonPath("$[0].uuid", is(documentCrmOutput1.getExternalId())))
                .andExpect(jsonPath("$[1].id", is(documentCrmOutput2.getId().toString())))
                .andExpect(jsonPath("$[1].nom", is(documentCrmOutput2.getNumber())))
                .andExpect(jsonPath("$[1].NomOQ", is(documentCrmOutput2.getNumberOq())))
                .andExpect(jsonPath("$[1].name", is(documentCrmOutput2.getPayerName())))
                .andExpect(jsonPath("$[1].sum", is(documentCrmOutput2.getSum().intValue())))
                .andExpect(jsonPath("$[1].date", is(documentCrmOutput2.getDate().toString())))
                .andExpect(jsonPath("$[1].status", is(documentCrmOutput2.getStatus())))
                .andExpect(jsonPath("$[1].dataOplat", is(documentCrmOutput2.getPaymentDate().toString())))
                .andExpect(jsonPath("$[1].sumOplat", is(documentCrmOutput2.getPaymentSum().intValue())))
                .andExpect(jsonPath("$[1].uuid", is(documentCrmOutput2.getExternalId())));
    }
}
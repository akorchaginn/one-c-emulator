package org.pes.onecemulator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pes.onecemulator.model.DocumentCrm;
import org.pes.onecemulator.model.EmployeeCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.service.CrmInteractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CrmInteractionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmInteractionController.class);

    private final ObjectMapper mapper = new ObjectMapper();

    private final CrmInteractionService crmInteractionService;

    @Autowired
    public CrmInteractionController(CrmInteractionService crmInteractionService) {
        this.crmInteractionService = crmInteractionService;
    }

    @PostMapping(value = "{source}/hs/DocID")
    public @ResponseBody List<DocumentCrm> getDocById(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documents) {
        try {
            LOGGER.info("Request: " + source + "/hs/DocID " + mapper.writeValueAsString(documents));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        final List<DocumentCrm> documentList =
                crmInteractionService.getDocumentsCrmById(documents, source);

        try {
            LOGGER.info("Response: " + mapper.writeValueAsString(documentList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return documentList;
    }

    @PostMapping(value = "{source}/hs/NewDoc")
    public @ResponseBody List<DocumentCrm> getDocByExternalId(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documents) {
        try {
            LOGGER.info("Request: " + source + "/hs/NewDoc " + mapper.writeValueAsString(documents));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        final List<DocumentCrm> documentList =
                crmInteractionService.getDocumentsCrmByExternalId(documents, source);

        try {
            LOGGER.info("Response: " + mapper.writeValueAsString(documentList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return documentList;
    }

    @PostMapping(value = "{source}/hs/Con")
    public @ResponseBody List<PayerCrm> getAllPayers(
            @PathVariable(value = "source") String source) {
        LOGGER.info("Request: source=" + source);

        final List<PayerCrm> payerList =
                crmInteractionService.getAllPayersCrmBySource(source);

        LOGGER.info("Response: size=" + payerList.size());

        return payerList;
    }

    @PostMapping(value = "{source}/hs/Sotrudnik")
    public @ResponseBody List<EmployeeCrm> getAllEmployees(
            @PathVariable(value = "source") String source, @RequestBody List<EmployeeCrm> employees) {
        try {
            LOGGER.info("Request: source=" + source + " ids= " + mapper.writeValueAsString(employees));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (employees.stream().anyMatch(e -> e.getExternalId() == null || e.getExternalId().isEmpty())) {
            return crmInteractionService.getAllEmployeesCrmBySource(source);
        }

        final List<EmployeeCrm> result = crmInteractionService.getAllEmployeesCrmBySourceAndExternalIds(source,
                employees.stream()
                        .map(EmployeeCrm::getExternalId)
                        .collect(Collectors.toList()));

        try {
            LOGGER.info("Response: " + mapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}

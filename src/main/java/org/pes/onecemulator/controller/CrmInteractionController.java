package org.pes.onecemulator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pes.onecemulator.model.onec.DocumentModel;
import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;
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

    private final ObjectMapper objectMapper;

    private final CrmInteractionService crmInteractionService;

    @Autowired
    public CrmInteractionController(final ObjectMapper objectMapper,
                                    final CrmInteractionService crmInteractionService) {
        this.objectMapper = objectMapper;
        this.crmInteractionService = crmInteractionService;
    }

    @PostMapping(value = "{source}/hs/DocID")
    public @ResponseBody List<DocumentModel> getDocById(
            @PathVariable(value = "source") final String source,
            @RequestBody final List<DocumentModel> documents) {
        try {
            LOGGER.info("Request: " + source + "/hs/DocID " + objectMapper.writeValueAsString(documents));
        } catch (JsonProcessingException e) {
            LOGGER.error("Request error: ", e);
        }

        final List<DocumentModel> documentList =
                crmInteractionService.getDocumentsCrmById(documents, source);

        try {
            LOGGER.info("Response: " + objectMapper.writeValueAsString(documentList));
        } catch (JsonProcessingException e) {
            LOGGER.error("Response error: ", e);
        }

        return documentList;
    }

    @PostMapping(value = "{source}/hs/NewDoc")
    public @ResponseBody List<DocumentModel> getDocByExternalId(
            @PathVariable(value = "source") final String source,
            @RequestBody final List<DocumentModel> documents) {
        try {
            LOGGER.info("Request: " + source + "/hs/NewDoc " + objectMapper.writeValueAsString(documents));
        } catch (JsonProcessingException e) {
            LOGGER.error("Request error: ", e);
        }

        final List<DocumentModel> documentList =
                crmInteractionService.getDocumentsCrmByExternalId(documents, source);

        try {
            LOGGER.info("Response: " + objectMapper.writeValueAsString(documentList));
        } catch (JsonProcessingException e) {
            LOGGER.error("Response error: ", e);
        }

        return documentList;
    }

    @PostMapping(value = "{source}/hs/Con")
    public @ResponseBody List<PayerModel> getPayers(
            @PathVariable(value = "source") final String source) {
        LOGGER.info("Request: source=" + source);

        final List<PayerModel> payerList =
                crmInteractionService.getAllPayersCrmBySource(source);

        LOGGER.info("Response: size=" + payerList.size());

        return payerList;
    }

    @PostMapping(value = "{source}/hs/Sotrudnik")
    public @ResponseBody List<EmployeeModel> getEmployees(
            @PathVariable(value = "source") final String source,
            @RequestBody final List<EmployeeModel> employees) {
        try {
            LOGGER.info("Request: source=" + source + " ids= " + objectMapper.writeValueAsString(employees));
        } catch (JsonProcessingException e) {
            LOGGER.error("Request error: ", e);
        }

        if (employees.stream().anyMatch(e -> e.getExternalId() == null || e.getExternalId().isEmpty())) {
            return crmInteractionService.getAllEmployeesCrmBySource(source);
        }

        final List<EmployeeModel> result = crmInteractionService.getAllEmployeesCrmBySourceAndExternalIds(source,
                employees.stream()
                        .map(EmployeeModel::getExternalId)
                        .collect(Collectors.toList()));

        try {
            LOGGER.info("Response: " + objectMapper.writeValueAsString(result));
        } catch (JsonProcessingException e) {
            LOGGER.error("Response error: ", e);
        }

        return result;
    }
}

package org.pes.onecemulator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.pes.onecemulator.model.DocumentCrm;
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

@RestController
public class CrmInteractionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmInteractionController.class);

    @Autowired
    private CrmInteractionService crmInteractionService;

    private final ObjectMapper mapper = new ObjectMapper();

    @PostMapping(value = "{source}/hs/DocID")
    public @ResponseBody List<DocumentCrm> getDocById(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {
        try {
            LOGGER.info("Request: " + source + "/hs/DocID " + mapper.writeValueAsString(documentCrms));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<DocumentCrm> documentCrmList = crmInteractionService.getDocumentsCrmById(documentCrms, source);

        try {
            LOGGER.info("Response: " + mapper.writeValueAsString(documentCrmList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return documentCrmList;
    }

    @PostMapping(value = "{source}/hs/NewDoc")
    public @ResponseBody List<DocumentCrm> getDocByExternalId(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {
        try {
            LOGGER.info("Request: " + source + "/hs/NewDoc " + mapper.writeValueAsString(documentCrms));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<DocumentCrm> documentCrmList = crmInteractionService.getDocumentsCrmByExternalId(documentCrms, source);

        try {
            LOGGER.info("Response: " + mapper.writeValueAsString(documentCrmList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return documentCrmList;
    }

    @PostMapping(value = "{source}/hs/Con")
    public @ResponseBody List<PayerCrm> getAllPayers(
            @PathVariable(value = "source") String source) {
        LOGGER.info("Request: source=" + source);

        List<PayerCrm> payerCrmList = crmInteractionService.getAllPayersCrmBySource(source);

        LOGGER.info("Response: size=" + payerCrmList.size());

        return payerCrmList;
    }
}

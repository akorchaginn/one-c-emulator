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
            LOGGER.info(source + "/hs/DocID " + mapper.writeValueAsString(documentCrms));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return crmInteractionService.getDocumentsCrmById(documentCrms, source);
    }

    @PostMapping(value = "{source}/hs/NewDoc")
    public @ResponseBody List<DocumentCrm> getDocByExternalId(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {
        try {
            LOGGER.info(source + "/hs/NewDoc " + mapper.writeValueAsString(documentCrms));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return crmInteractionService.getDocumentsCrmByExternalId(documentCrms, source);
    }

    @PostMapping(value = "{source}/hs/Con")
    public @ResponseBody List<PayerCrm> getAllPayers(
            @PathVariable(value = "source") String source) {
        LOGGER.info("source=" + source);
        return crmInteractionService.getAllPayersCrmBySource(source);
    }
}

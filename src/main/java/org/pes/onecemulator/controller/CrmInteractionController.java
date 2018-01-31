package org.pes.onecemulator.controller;

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

import java.util.ArrayList;
import java.util.List;

@RestController
public class CrmInteractionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrmInteractionController.class);

    @Autowired
    private CrmInteractionService crmInteractionService;

    @PostMapping(value = "{source}/hs/DocID")
    public @ResponseBody List<DocumentCrm> getDocById(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {

        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.add(
                        crmInteractionService.getDocumentsCrmById(documentCrm.getId(), source)
                )
        );

        return documentCrmList;
    }

    @PostMapping(value = "{source}/hs/NewDoc")
    public @ResponseBody List<DocumentCrm> getDocByExternalId(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {

        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.add(
                        crmInteractionService.getDocumentCrmByExternalId(documentCrm.getExternalId(), source)
                )
        );

        return documentCrmList;
    }

    @PostMapping(value = "{source}/hs/Con")
    public @ResponseBody List<PayerCrm> getAllPayers(@PathVariable(value = "source") String source) {
        return crmInteractionService.getAllPayersCrm();
    }
}

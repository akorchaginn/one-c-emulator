package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.DocumentCrm;
import org.pes.onecemulator.dto.PayerCrm;
import org.pes.onecemulator.service.api.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class CrmInteractionController {

    @Autowired
    private CrmInteractionService crmInteractionService;

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/DocID")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getDocById(@RequestBody List<DocumentCrm> documentCrms) {
        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
            documentCrmList.addAll(
                    crmInteractionService.getDocumentsCrmById(UUID.fromString(documentCrm.getId()))
            )
        );
        try {
            return new ResponseEntity<>(
                    documentCrmList,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/NewDoc")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getDocByParameters(@RequestBody List<DocumentCrm> documentCrms) {
        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.addAll(
                        crmInteractionService.getDocumentsCrmByParameters(
                                documentCrm.getPayerName(),
                                documentCrm.getSum(),
                                documentCrm.getDate()
                        )
                )
        );
        if (documentCrmList.size() > 0) {
            return new ResponseEntity<>(
                    documentCrmList,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/Con")
    public @ResponseBody ResponseEntity<List<PayerCrm>> getAllPayers() {
        try {
            return new ResponseEntity<>(
                    crmInteractionService.getAllPayersCrm(),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
}

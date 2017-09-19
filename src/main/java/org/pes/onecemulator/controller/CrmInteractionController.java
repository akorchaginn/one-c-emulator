package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.DocumentCrm;
import org.pes.onecemulator.service.api.CrmInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@RestController
public class CrmInteractionController {

    @Autowired
    private CrmInteractionService crmInteractionService;

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/DocID")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getById(@RequestBody List<DocumentCrm> documentCrms) {
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
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("warning:", e.getMessage());
            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/NewDoc")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getByParameters(@RequestBody List<DocumentCrm> documentCrms) {
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
        try {
            return new ResponseEntity<>(
                    documentCrmList,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("warning:", e.getMessage());
            return new ResponseEntity<>(
                    responseHeaders,
                    HttpStatus.NOT_FOUND
            );
        }
    }
}

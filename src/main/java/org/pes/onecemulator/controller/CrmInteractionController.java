package org.pes.onecemulator.controller;

import org.pes.onecemulator.dto.DocumentCrm;
import org.pes.onecemulator.dto.PayerCrm;
import org.pes.onecemulator.service.api.CrmInteractionService;
import org.pes.onecemulator.utils.CrmSecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class CrmInteractionController {

    private static final Logger log = LoggerFactory.getLogger(CrmInteractionController.class);

    @Autowired
    private CrmInteractionService crmInteractionService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    private final HttpHeaders crmSecurityHeader = new HttpHeaders();

    private CrmInteractionController() {
        crmSecurityHeader.add("crm-api-token", CrmSecurityUtils.CRM_TOKEN);
    }

    @RequestMapping(method = RequestMethod.POST, value = "{source}/hs/DocID")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getDocById(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {

        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.add(
                        crmInteractionService.getDocumentsCrmById(documentCrm.getId(), source)
                )
        );
        if (documentCrmList.size() > 0 && documentCrmList.stream().allMatch(Objects::nonNull)) {
            return new ResponseEntity<>(
                    documentCrmList,
                    crmSecurityHeader,
                    HttpStatus.OK
            );
        } else {
            documentCrmList.clear();
            return new ResponseEntity<>(
                    documentCrmList,
                    HttpStatus.OK
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "{source}/hs/NewDoc")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getDocByExternalId(
            @PathVariable(value = "source") String source,
            @RequestBody List<DocumentCrm> documentCrms) {

        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.add(
                        crmInteractionService.getDocumentCrmByExternalId(documentCrm.getExternalId(), source)
                )
        );
        if (documentCrmList.size() > 0 && documentCrmList.stream().allMatch(Objects::nonNull)) {
            return new ResponseEntity<>(
                    documentCrmList,
                    crmSecurityHeader,
                    HttpStatus.OK
            );
        } else {
            documentCrmList.clear();
            return new ResponseEntity<>(
                    documentCrmList,
                    HttpStatus.OK
            );
        }
    }

    @Deprecated
    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/NewDocOld")
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
        if (documentCrmList.size() > 0 && documentCrmList.stream().allMatch(Objects::nonNull)) {
            return new ResponseEntity<>(
                    documentCrmList,
                    crmSecurityHeader,
                    HttpStatus.OK
            );
        } else {
            documentCrmList.clear();
            return new ResponseEntity<>(
                    documentCrmList,
                    HttpStatus.OK
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "{source}/hs/Con")
    public @ResponseBody ResponseEntity<List<PayerCrm>> getAllPayers(@PathVariable(value = "source") String source) {
        try {
            log.info("Requested payers by path: " + source + "/hs/Con");
            return new ResponseEntity<>(
                    crmInteractionService.getAllPayersCrm(),
                    crmSecurityHeader,
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }
    }
}

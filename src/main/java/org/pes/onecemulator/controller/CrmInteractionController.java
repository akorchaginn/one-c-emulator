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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/DocID")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getDocById(@RequestBody List<DocumentCrm> documentCrms) {

        if (httpServletRequest != null) {
            try {
                StringBuilder content = new StringBuilder();
                BufferedReader reader = httpServletRequest.getReader();
                while(reader.readLine() != null) {
                    content.append(reader.readLine());
                }
                log.info(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.add(
                        crmInteractionService.getDocumentsCrmById(UUID.fromString(documentCrm.getId()))
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

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/NewDoc")
    public @ResponseBody ResponseEntity<List<DocumentCrm>> getDocByExternalId(@RequestBody List<DocumentCrm> documentCrms) {

        if (httpServletRequest != null) {
            try {
                StringBuilder content = new StringBuilder();
                BufferedReader reader = httpServletRequest.getReader();
                while(reader.readLine() != null) {
                    content.append(reader.readLine());
                }
                log.info(content.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<DocumentCrm> documentCrmList = new ArrayList<>();
        documentCrms.forEach(documentCrm ->
                documentCrmList.add(
                        crmInteractionService.getDocumentCrmByExternalId(documentCrm.getExternalId())
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
        if (documentCrmList.size() > 0) {
            return new ResponseEntity<>(
                    documentCrmList,
                    crmSecurityHeader,
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    HttpStatus.OK
            );
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "B1Cbuh2015/hs/Con")
    public @ResponseBody ResponseEntity<List<PayerCrm>> getAllPayers() {
        try {
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

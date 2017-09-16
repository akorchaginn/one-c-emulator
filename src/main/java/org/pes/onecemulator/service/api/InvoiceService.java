package org.pes.onecemulator.service.api;

import org.pes.onecemulator.mapping.MapperFactoryService;
import org.pes.onecemulator.service.repository.InvoiceRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private MapperFactoryService mapperFactoryService;

    @Autowired
    private InvoiceRepositoryService invoiceRepositoryService;
}

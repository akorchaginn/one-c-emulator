package org.pes.onecemulator.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.entity.Invoice;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InvoiceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InvoiceRepository repository;

    @Test
    public void checkFindByIdAndSource() {
        Source newSource = new Source();
        newSource.setName("1db");
        Set<Source> sourceSet = new HashSet<>();
        sourceSet.add(newSource);

        Payer newPayer = new Payer();
        newPayer.setSources(sourceSet);
        newPayer.setCode("1234567890");
        newPayer.setName("0987654321");

        entityManager.persist(newPayer);
        entityManager.flush();

        Invoice newInvoice = new Invoice();
        newInvoice.setPayer(newPayer);
        newInvoice.setSource(newSource);
        newInvoice.setExternalId("123");
        newInvoice.setNumber("456");

        entityManager.persist(newInvoice);
        entityManager.flush();

        Invoice foundByIdAndSourceInvoice =
                repository.findByIdAndSource(newInvoice.getId(), newInvoice.getSource().getName());

        Assert.assertNotNull(foundByIdAndSourceInvoice);
        Assert.assertTrue(foundByIdAndSourceInvoice.getId() == newInvoice.getId());
        Assert.assertTrue(foundByIdAndSourceInvoice.getSource().getName().equals(newInvoice.getSource().getName()));
    }

    @Test
    public void findByExternalIdAndSource() {
        Source newSource = new Source();
        newSource.setName("1db");
        Set<Source> sourceSet = new HashSet<>();
        sourceSet.add(newSource);

        Payer newPayer = new Payer();
        newPayer.setSources(sourceSet);
        newPayer.setCode("1234567890");
        newPayer.setName("0987654321");

        entityManager.persist(newPayer);
        entityManager.flush();

        Invoice newInvoice = new Invoice();
        newInvoice.setPayer(newPayer);
        newInvoice.setSource(newSource);
        newInvoice.setExternalId("123");
        newInvoice.setNumber("456");

        entityManager.persist(newInvoice);
        entityManager.flush();

        Invoice foundByExternalIdAndSourceInvoice =
                repository.findByExternalIdAndSource(newInvoice.getExternalId(), newInvoice.getSource().getName());

        Assert.assertNotNull(foundByExternalIdAndSourceInvoice);
        Assert.assertTrue(foundByExternalIdAndSourceInvoice.getExternalId().equals(newInvoice.getExternalId()));
        Assert.assertTrue(
                foundByExternalIdAndSourceInvoice.getSource().getName().equals(newInvoice.getSource().getName()));
    }
}

package org.pes.onecemulator.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PayerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PayerRepository repository;

    @Test
    public void checkFindByCode() {
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

        Payer foundByCode = repository.findByCode(newPayer.getCode());

        assertNotNull(foundByCode);
        assertFalse(foundByCode.getSources().isEmpty());
    }
}

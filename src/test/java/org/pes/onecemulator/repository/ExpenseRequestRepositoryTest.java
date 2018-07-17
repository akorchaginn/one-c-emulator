package org.pes.onecemulator.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.entity.ExpenseRequest;
import org.pes.onecemulator.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpenseRequestRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ExpenseRequestRepository repository;

    @Test
    public void checkFindByNumber() {
        Source newSource = new Source();
        newSource.setName("1db");

        entityManager.persist(newSource);
        entityManager.flush();

        ExpenseRequest newExpenseRequest = new ExpenseRequest();
        newExpenseRequest.setSource(newSource);
        newExpenseRequest.setCurrency("RUR");
        newExpenseRequest.setNumber("1234567890");
        newExpenseRequest.setSum("1000");

        entityManager.persist(newExpenseRequest);
        entityManager.flush();

        ExpenseRequest foundByNumber = repository.findByNumber(newExpenseRequest.getNumber()).orElse(null);

        assertNotNull(foundByNumber);
    }
}

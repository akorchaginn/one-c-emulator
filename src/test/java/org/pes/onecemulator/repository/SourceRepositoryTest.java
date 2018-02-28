package org.pes.onecemulator.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.entity.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SourceRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SourceRepository repository;

    @Test
    public void checkFindByName() {
        Source newSource = new Source();
        newSource.setName("1db");
        entityManager.persist(newSource);
        entityManager.flush();

        Source foundSource = repository.findByName(newSource.getName());

        assertNotNull(foundSource);
    }
}

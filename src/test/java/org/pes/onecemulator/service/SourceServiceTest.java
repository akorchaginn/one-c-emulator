package org.pes.onecemulator.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pes.onecemulator.entity.Payer;
import org.pes.onecemulator.entity.Source;
import org.pes.onecemulator.exception.NotFoundException;
import org.pes.onecemulator.exception.ValidationException;
import org.pes.onecemulator.model.SourceModel;
import org.pes.onecemulator.repository.SourceRepository;
import org.pes.onecemulator.service.impl.SourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class SourceServiceTest {

    @TestConfiguration
    static class SourceServiceTestContextConfiguration {

        @Bean
        public SourceService sourceService() {
            return new SourceServiceImpl();
        }
    }

    @MockBean
    private SourceRepository repository;

    @Autowired
    private SourceService service;

    @Before
    public void setUp() {
        Source source1 = new Source();
        source1.setId(UUID.fromString("8c88af9b-9702-4a04-a1cc-c00c8caf5b03"));
        source1.setName("1db");

        Source source2 = new Source();
        source2.setId(UUID.fromString("1a72e1d6-4815-46fe-8dd6-77f78575e31c"));
        source2.setName("2db");

        Source source3 = new Source();
        source3.setId(UUID.fromString("2cebf1c0-e209-4306-9d3c-00a6bf4c19a9"));
        source3.setName("3db");
        source3.getPayers().add(new Payer());

        when(repository.findById(source1.getId()))
                .thenReturn(Optional.empty());

        when(repository.findById(source2.getId()))
                .thenReturn(Optional.of(source2));

        when(repository.findByName(source1.getName()))
                .thenReturn(Optional.empty());

        when(repository.findByName(source2.getName()))
                .thenReturn(Optional.of(source2));

        when(repository.findByName(source3.getName()))
                .thenReturn(Optional.of(source3));

        when(repository.findAll())
                .thenReturn(new ArrayList<>(Arrays.asList(source1, source2)));
    }

    @Test(expected = NotFoundException.class)
    public void getByIdNotFoundTest() throws NotFoundException {
        service.getById(UUID.fromString("8c88af9b-9702-4a04-a1cc-c00c8caf5b03"));
    }

    @Test
    public void getByIdFoundTest() throws NotFoundException {
        assertNotNull(service.getById(UUID.fromString("1a72e1d6-4815-46fe-8dd6-77f78575e31c")));
    }

    @Test(expected = NotFoundException.class)
    public void getByNameNotFoundTest() throws NotFoundException {
        service.getByName("1db");
    }

    @Test
    public void getByNameFoundTest() throws NotFoundException {
        assertNotNull(service.getByName("2db"));
    }

    @Test
    public void listNotEmptyTest() {
        assertFalse(service.list().isEmpty());
    }

    @Test(expected = NotFoundException.class)
    public void payerListNotFoundSourceTest() throws NotFoundException {
        service.getPayerList("1db");
    }

    @Test
    public void payerListEmptyTest() throws NotFoundException {
        assertTrue(service.getPayerList("2db").isEmpty());
    }

    @Test
    public void payerListNotEmptyTest() throws NotFoundException {
        assertFalse(service.getPayerList("3db").isEmpty());
    }

    @Test(expected = ValidationException.class)
    public void createModelIsNullValidationTest() throws Exception {
        service.create(null);
    }

    @Test(expected = ValidationException.class)
    public void createSourceNameIsNullValidationTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setName(null);
        service.create(model);
    }

    @Test(expected = ValidationException.class)
    public void createSourceNameIsEmptyValidationTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setName("");
        service.create(model);
    }

    @Test
    public void createTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setId(null);
        model.setName("4db");

        Source source = new Source();
        source.setId(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124"));
        source.setName("4db");

        SourceModel modelReturned = new SourceModel();
        modelReturned.setId(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124"));
        modelReturned.setName("4db");

        when(repository.save(any(Source.class))).thenReturn(source);

        SourceModel returned = service.create(model);

        assertEquals(returned, modelReturned);
    }

    @Test(expected = ValidationException.class)
    public void updateModelIsNullValidationTest() throws Exception {
        service.update(null);
    }

    @Test(expected = ValidationException.class)
    public void updateIdIsNullValidationTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setId(null);
        service.update(model);
    }

    @Test(expected = ValidationException.class)
    public void updateSourceNameIsNullValidationTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setName(null);
        service.update(model);
    }

    @Test(expected = ValidationException.class)
    public void updateSourceNameIsEmptyValidationTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setName("");
        service.update(model);
    }

    @Test(expected = NotFoundException.class)
    public void updateSourceNotFoundTest() throws Exception {
        SourceModel model = new SourceModel();
        model.setId(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124"));
        model.setName("3db");

        when(repository.findById(any(UUID.class)))
                .thenReturn(Optional.empty());

        service.update(model);
    }

    @Test
    public void updateTest() throws Exception {
        SourceModel inputModel = new SourceModel();
        inputModel.setId(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124"));
        inputModel.setName("3db");

        Source inputSource = new Source();
        inputSource.setId(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124"));
        inputSource.setName("3db");

        Source outputSource = new Source();
        outputSource.setId(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124"));
        outputSource.setName("4db");

        when(repository.findById(UUID.fromString("e0f30698-ca29-4ed2-9451-e20759930124")))
                .thenReturn(Optional.of(outputSource));

        when(repository.save(any(Source.class)))
                .thenReturn(inputSource);

        SourceModel outputModel = service.update(inputModel);

        assertEquals(outputModel.getId(), inputModel.getId());
        assertEquals(outputModel.getName(), inputModel.getName());
    }
}

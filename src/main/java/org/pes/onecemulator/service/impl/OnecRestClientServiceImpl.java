package org.pes.onecemulator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pes.onecemulator.model.EmployeeCrm;
import org.pes.onecemulator.model.PayerCrm;
import org.pes.onecemulator.service.OnecRestClientService;
import org.pes.onecemulator.service.RestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class OnecRestClientServiceImpl extends RestService implements OnecRestClientService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Value("${onec.interaction.host:#{null}}")
    private String onecHost;

    @Value("${onec.interaction.payers.uri:#{null}}")
    private String payersUri;

    @Value("${onec.interaction.employees.uri:#{null}}")
    private String employeesUri;

    @Override
    public List<PayerCrm> getPayers(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(onecHost + payersUri))
                .build();

        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        final List<PayerCrm> result = readJson(response.body(), PayerCrm.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(OBJECT_MAPPER.writeValueAsString(result));

        return result;
    }

    @Override
    public List<EmployeeCrm> getEmployees(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(onecHost + employeesUri))
                .build();

        final HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        final List<EmployeeCrm> result = readJson(response.body(), EmployeeCrm.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(OBJECT_MAPPER.writeValueAsString(result));

        return result;
    }

    private static <T> List<T> readJson(String json, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(json, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}

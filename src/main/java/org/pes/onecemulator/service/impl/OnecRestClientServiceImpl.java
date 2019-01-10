package org.pes.onecemulator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;
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
    public List<PayerModel> getPayers(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(source, payersUri))
                .build();

        final List<PayerModel> result = readJson(
                HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()), PayerModel.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(OBJECT_MAPPER.writeValueAsString(result));

        return result;
    }

    @Override
    public List<EmployeeModel> getEmployees(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(source, employeesUri))
                .build();

        final List<EmployeeModel> result = readJson(
                HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString()), EmployeeModel.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(OBJECT_MAPPER.writeValueAsString(result));

        return result;
    }

    private URI getUri(final String source, final String uri) {
        return URI.create(onecHost + "/" + source + uri);
    }

    private static <T> List<T> readJson(HttpResponse<String> response, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(
                response.body(),
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}

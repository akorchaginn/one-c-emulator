package org.pes.onecemulator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;
import org.pes.onecemulator.service.OnecRestClientService;
import org.pes.onecemulator.service.RestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class OnecRestClientServiceImpl extends RestService implements OnecRestClientService {

    @Value("${onec.interaction.auth.user:#{null}}")
    private String user;

    @Value("${onec.interaction.auth.password:#{null}}")
    private String password;

    @Value("${onec.interaction.host:#{null}}")
    private String onecHost;

    @Value("${onec.interaction.payers.uri:#{null}}")
    private String payersUri;

    @Value("${onec.interaction.employees.uri:#{null}}")
    private String employeesUri;

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    public OnecRestClientServiceImpl(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = createHttpClient(user, password);
    }

    @Override
    public List<PayerModel> getPayers(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(source, payersUri))
                .setHeader(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        final List<PayerModel> result = send(request, PayerModel.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(objectMapper.writeValueAsString(result));

        return result;
    }

    @Override
    public List<EmployeeModel> getEmployees(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(source, employeesUri))
                .setHeader(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString("[{\"id\":\"\"}]"))
                .build();

        final List<EmployeeModel> result = send(request, EmployeeModel.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(objectMapper.writeValueAsString(result));

        return result;
    }

    private URI getUri(final String source, final String uri) {
        return URI.create(onecHost + SLASH + source + uri);
    }

    private <T> List<T> send(final HttpRequest request, final Class<T> responseClazz) throws IOException, InterruptedException {
        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        final TypeFactory typeFactory = objectMapper.getTypeFactory();
        final CollectionType collectionType = typeFactory.constructCollectionType(List.class, responseClazz);

        return objectMapper.readValue(response.body(), collectionType);
    }
}

package org.pes.onecemulator.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.pes.onecemulator.model.onec.EmployeeModel;
import org.pes.onecemulator.model.onec.PayerModel;
import org.pes.onecemulator.service.OnecRestClientService;
import org.pes.onecemulator.service.RestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class OnecRestClientServiceImpl extends RestService implements OnecRestClientService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

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

    @Override
    public List<PayerModel> getPayers(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(source, payersUri))
                .setHeader(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(""))
                .build();

        final List<PayerModel> result = readJson(
                getHttpClient().send(request, HttpResponse.BodyHandlers.ofString()), PayerModel.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(OBJECT_MAPPER.writeValueAsString(result));

        return result;
    }

    @Override
    public List<EmployeeModel> getEmployees(final String source) throws IOException, InterruptedException {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(getUri(source, employeesUri))
                .setHeader(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString("[{\"id\":\"\"}]"))
                .build();

        final List<EmployeeModel> result = readJson(
                getHttpClient().send(request, HttpResponse.BodyHandlers.ofString()), EmployeeModel.class);

        // для проверки
        logger.info(String.valueOf(result.size()));
        logger.info(OBJECT_MAPPER.writeValueAsString(result));

        return result;
    }

    private URI getUri(final String source, final String uri) {
        return URI.create(onecHost + "/" + source + uri);
    }

    private HttpClient getHttpClient() {
        return getHttpClientBuilder().authenticator(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password.toCharArray());
            }
        }).build();
    }

    private static <T> List<T> readJson(HttpResponse<String> response, Class<T> clazz) throws IOException {
        return OBJECT_MAPPER.readValue(
                response.body(),
                OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}

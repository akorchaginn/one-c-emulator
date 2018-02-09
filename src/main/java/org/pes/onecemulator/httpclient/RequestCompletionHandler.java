package org.pes.onecemulator.httpclient;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class RequestCompletionHandler extends AsyncCompletionHandler<Void> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCompletionHandler.class);

    private final UUID id;

    RequestCompletionHandler(UUID id) {
        this.id = id;
    }

    @Override
    public Void onCompleted(Response response) {
        LOGGER.info("End request to CRM for id:{} : {}", id, response.getStatusCode());
        return null;
    }

    @Override
    public void onThrowable(Throwable t) {
        LOGGER.error("Error request to CRM: ", t);
    }
}

package org.pes.onecemulator.bus.processor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeadEventProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeadEventProcessor.class);

    private final AsyncEventBus asyncEventBus;

    @Autowired
    DeadEventProcessor(AsyncEventBus asyncEventBus) {
        this.asyncEventBus = asyncEventBus;
    }

    @PostConstruct
    public void init() {
        asyncEventBus.register(this);
    }

    @Subscribe
    public void processDeadEvent(DeadEvent deadEvent){
        LOGGER.error("Dead event detected: {}, from source: {}",
                deadEvent.getEvent().getClass(), deadEvent.getSource().getClass());
    }
}

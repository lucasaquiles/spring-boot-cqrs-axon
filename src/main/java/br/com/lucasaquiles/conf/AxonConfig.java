package br.com.lucasaquiles.conf;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventHandlingConfiguration;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.MongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class AxonConfig {

    private final EventHandlingConfiguration eventHandlingConfiguration;

    @Autowired
    public AxonConfig(EventHandlingConfiguration eventHandlingConfiguration) {
        this.eventHandlingConfiguration = eventHandlingConfiguration;
    }

    @PostConstruct
    public void registerErrorHandling() {

        eventHandlingConfiguration.configureListenerInvocationErrorHandler(configuration -> (exception, event, listener) ->{

            String msg = String.format("[EventHandling] Event handler failed when processing event with id %s. Aborting all further event handlers.", event.getIdentifier());
            log.error(msg, exception);
            throw exception;
        });
    }

    @Bean
    public EventStorageEngine eventStore(MongoTemplate mongoTemplate){

        return new MongoEventStorageEngine(new JacksonSerializer(), null, mongoTemplate, new DocumentPerEventStorageStrategy());
    }
}

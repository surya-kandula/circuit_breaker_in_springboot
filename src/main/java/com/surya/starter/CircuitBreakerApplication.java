package com.surya.starter;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CircuitBreakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApplication.class);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     *  This is used to intercept events happening on circuit breaker.
     *  We can optionally publish those events via eventListener of CircuitBreakerEvent
     *
     *  eventPublisher.publishEvent(new CircuitBreakerEvent(message, event));
     *
     *  @EventListener
     *  public void listenToMessage(CircuitBreakerEvent cbEvent) {}
     */
    @Bean
    public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {
        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                System.out.println("++> " + entryAddedEvent);
                entryAddedEvent.getAddedEntry().getEventPublisher().onStateTransition(event -> {
                    System.out.printf(">>> %s , %s = %s%n", System.currentTimeMillis(),
                            event.getCircuitBreakerName(), event.getStateTransition().getToState());
                });
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                System.out.println("--> " + entryRemoveEvent);
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                System.out.println("~~> " + entryReplacedEvent);
            }
        };
    }
}

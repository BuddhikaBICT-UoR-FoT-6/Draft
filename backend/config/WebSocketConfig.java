package com.draft.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration class for WebSockets using STOMP.
 * Architecture: Implements the Publisher/Subscriber (Observer) Design Pattern.
 */
@Configuration // IoC: Tells Spring to manage the lifecycle of this configuration bean.
@EnableWebSocketMessageBroker // Bootstraps the message broker infrastructure.
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Configures the message broker routing.
     * SOLID: Open/Closed Principle - overriding default behavior to inject our
     * specific routing rules.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        // Sets up an in-memory broker (backed by Concurrent collections)
        // to broadcast messages to clients subscribed to topics prefixed with "/topic".
        config.enableSimpleBroker("/topic");

        // Routes incoming messages from clients prefixed with "/app"
        // to our custom @MessageMapping controllers.
        config.setApplicationDestinationPrefixes("/app");
    }

    /**
     * Registers the initial HTTP endpoint for the WebSocket handshake.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {

        registry.addEndpoint("/ws")
                // Security: Explicitly allow CORS from our local React dev server.
                .setAllowedOriginPatterns("http://localhost:5173")
                // Network Resilience: Provides fallback options (like long-polling)
                // if strict WebSockets are blocked by proxies or firewalls.
                .withSockJS();
    }
}
package com.draft.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 1. Enable a simple in-memory message broker to send messages back to the
        // client.
        // Clients will subscribe to destinations prefixed with "/topic" (e.g.,
        // /topic/board/1)
        config.enableSimpleBroker("/topic");

        // 2. Designate the prefix for messages sent FROM the client TO the server.
        // These will be routed to your @MessageMapping controllers.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 3. Register the endpoint that clients will use to connect to the WebSocket
        // server.
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // Crucial for local dev: allows the React frontend (port 5173) to
                                               // connect to Spring Boot (port 8080)
                .withSockJS(); // Fallback mechanism for browsers that don't support raw WebSockets
    }
}

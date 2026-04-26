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
        // 1. Enable a simple memory-based message broker.
        // The server will broadcast messages to all clients subscribed to topics
        // prefixed with "/topic".
        // Example: /topic/board/123
        config.enableSimpleBroker("/topic");

        // 2. Designate the prefix for messages sent FROM the client TO the server.
        // When a client sends a message to "/app/shape.move", it routes to a
        // @MessageMapping("shape.move") controller.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 3. Register the STOMP endpoint that the frontend will use to establish the
        // initial connection.
        registry.addEndpoint("/ws")
                // Allow Vite's default dev server port. In production, this would be restricted
                // to your frontend domain.
                .setAllowedOriginPatterns("http://localhost:5173")
                // SockJS provides fallback options (like long-polling) if raw WebSockets are
                // blocked by proxies or firewalls.
                .withSockJS();
    }
}
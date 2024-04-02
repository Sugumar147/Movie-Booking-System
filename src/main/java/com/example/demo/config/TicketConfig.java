package com.example.demo.config;

import com.example.demo.model.Ticket;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class TicketConfig {
    public Ticket ticket() {
        return new Ticket();
    }
}

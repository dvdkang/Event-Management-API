package com.example.EventAPI.event;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(EventRepository repository) {
        return args -> {
            Event study = new Event(
                        "Mariam",
                        LocalDateTime.of(2000, 2, 10, 1, 30),
                        LocalDateTime.of(2024, 2, 15, 12, 30),
                        "Study"
            );

            Event workout = new Event(
                        "Mariam",
                        LocalDateTime.of(2000, 2, 10, 1, 30),
                        LocalDateTime.of(2024, 2, 15, 12, 30),
                        "Workout"
            );

            repository.saveAll(
                List.of(study, workout)
            );
        };
    }
}

package com.example.EventAPI.event;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void addNewEvent(Event event) {
        eventRepository.save(event);
    }

    // Deleting events by id
    public void deleteEvent(Long eventId) {
        boolean exists = eventRepository.existsById(eventId);
        if (!exists) {
            throw new IllegalStateException("event with id " + eventId + " does not exist");
        }
        eventRepository.deleteById(eventId);
    }

    // Updating specific variables of an event such as name, description, and date/time
    @Transactional
    public void updateEvent(Long eventId, String name, String description, LocalDateTime startDate, LocalDateTime endDate) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalStateException("event with id " + eventId + " does not exist"));

        if (name != null && name.length() > 0 && !Objects.equals(event.getName(), name)) {
            event.setName(name);
        }

        if (description != null && description.length() > 0 && !Objects.equals(event.getDescription(), description)) {
            event.setDescription(description);
        }

        if (startDate != null && !Objects.equals(event.getStartDate(), startDate)) {
            event.setStartDate(startDate);
        }

        if (endDate != null && !Objects.equals(event.getEndDate(), endDate)) {
            event.setEndDate(endDate);
        }

    }

}

package com.farmpulse.backend.service;

import com.farmpulse.backend.entity.Event;
import com.farmpulse.backend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Integer eventId, Event updatedEvent) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);
        if (existingEvent.isPresent()) {
            Event event = existingEvent.get();
            event.setEventName(updatedEvent.getEventName());
            event.setEventDescription(updatedEvent.getEventDescription());
            event.setEventVenue(updatedEvent.getEventVenue());
            event.setEventDate(updatedEvent.getEventDate());
            event.setEventSpeaker(updatedEvent.getEventSpeaker());
            event.setEventGuests(updatedEvent.getEventGuests());
            event.setEventImageUrl(updatedEvent.getEventImageUrl());
            return eventRepository.save(event);
        }
        throw new RuntimeException("Event not found");
    }

    public void deleteEvent(Integer eventId) {
        eventRepository.deleteById(eventId);
    }
}

    package com.farmpulse.backend.entity;

    import jakarta.persistence.*;
    import lombok.Data;

    @Data
    @Entity
    @Table(name = "events")
    public class Event {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer eventId;

        private String eventName;
        private String eventDescription;
        private String eventVenue;
        private String eventSpeaker;
        private String eventGuests;
        private String eventImageUrl;
        private String eventDate;

        @ManyToOne
        @JoinColumn(name = "created_by", nullable = false)
        private OurUsers createdBy;
    }

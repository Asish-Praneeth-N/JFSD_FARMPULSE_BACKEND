package com.farmpulse.backend.repository;

import com.farmpulse.backend.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findByCreatedBy_Id(Integer userId);
}

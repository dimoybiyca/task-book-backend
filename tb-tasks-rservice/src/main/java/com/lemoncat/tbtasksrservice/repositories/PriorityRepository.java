package com.lemoncat.tbtasksrservice.repositories;

import com.lemoncat.tbtasksrservice.models.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    Optional<Priority> getByName(String name);
}

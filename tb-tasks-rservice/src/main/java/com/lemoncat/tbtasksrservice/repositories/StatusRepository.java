package com.lemoncat.tbtasksrservice.repositories;

import com.lemoncat.tbtasksrservice.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    Optional<Status> getByName(String name);
}

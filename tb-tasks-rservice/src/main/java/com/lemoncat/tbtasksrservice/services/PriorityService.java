package com.lemoncat.tbtasksrservice.services;

import com.lemoncat.tbtasksrservice.models.Priority;
import com.lemoncat.tbtasksrservice.repositories.PriorityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public Priority getByName(String name) {
        Optional<Priority> priority = priorityRepository.getByName(name);

        return priority.orElseThrow(() -> new EntityNotFoundException(
                String.format("Category with name %s not found", name)
        ));
    }
}

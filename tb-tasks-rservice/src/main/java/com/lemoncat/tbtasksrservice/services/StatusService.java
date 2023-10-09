package com.lemoncat.tbtasksrservice.services;

import com.lemoncat.tbtasksrservice.models.Status;
import com.lemoncat.tbtasksrservice.repositories.StatusRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatusService {

    private final StatusRepository statusRepository;

    public Status getByName(String name) {
        Optional<Status> status = statusRepository.getByName(name);

        return status.orElseThrow(() -> new EntityNotFoundException(
                String.format("Category with name %s not found", name)
        ));
    }
}

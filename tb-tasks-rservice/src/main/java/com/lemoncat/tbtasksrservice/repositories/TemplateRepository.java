package com.lemoncat.tbtasksrservice.repositories;

import com.lemoncat.tbtasksrservice.models.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> getAllByOwnerId(String ownerId);
}

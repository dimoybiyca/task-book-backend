package com.lemoncat.tbtasksrservice.repositories;

import com.lemoncat.tbtasksrservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getAllByOwnerId(String ownerId);

    Optional<Category> getByIdAndOwnerId(Long id, String ownerId);
}

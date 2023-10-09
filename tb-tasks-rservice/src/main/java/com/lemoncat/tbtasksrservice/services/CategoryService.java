package com.lemoncat.tbtasksrservice.services;

import com.lemoncat.tbtasksrservice.DTO.Category.CategoryDTO;
import com.lemoncat.tbtasksrservice.DTO.Category.CategoryMapper;
import com.lemoncat.tbtasksrservice.DTO.Category.CreateCategory;
import com.lemoncat.tbtasksrservice.models.Category;
import com.lemoncat.tbtasksrservice.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllDTO(String ownerId) {
        return this.getAll(ownerId).stream()
                .map(categoryMapper::categoryToDTO)
                .toList();
    }

    @Transactional
    public CategoryDTO create(CreateCategory createCategory, String ownerId) {
        Category category = categoryMapper.toObject(createCategory, ownerId);

        return categoryMapper.categoryToDTO(this.categoryRepository.save(category));
    }

    @Transactional
    public CategoryDTO update(CategoryDTO categoryDTO, String ownerId) {
        Category oldCategory = this.getById(categoryDTO.id(), ownerId);
        Category category = categoryMapper.toObject(categoryDTO, ownerId);

        return categoryMapper.categoryToDTO(this.categoryRepository.save(category));
    }

    @Transactional
    public void delete(Long id, String ownerId) {
        Category category = this.getById(id, ownerId);
        categoryRepository.delete(category);
    }

    public Category getById(Long categoryId, String ownerId) {
        Optional<Category> category = categoryRepository.getByIdAndOwnerId(categoryId, ownerId);

        return category.orElseThrow(() -> new EntityNotFoundException(
                String.format("Category with id %s not found", categoryId)
        ));
    }

    private List<Category> getAll(String ownerId) {
        return categoryRepository.getAllByOwnerId(ownerId);
    }
}

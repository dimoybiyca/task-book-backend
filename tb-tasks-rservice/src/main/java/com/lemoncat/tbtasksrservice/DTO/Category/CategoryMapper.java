package com.lemoncat.tbtasksrservice.DTO.Category;

import com.lemoncat.tbtasksrservice.models.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryMapper {

    public CategoryDTO categoryToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .icon(category.getIcon())
                .build();
    }

    public Category toObject(CategoryDTO categoryDTO, String ownerId) {
        return Category.builder()
                .id(categoryDTO.id())
                .icon(categoryDTO.icon())
                .name(categoryDTO.name())
                .ownerId(ownerId)
                .build();
    }

    public Category toObject(CreateCategory createCategory, String ownerId) {
        return Category.builder()
                .icon(createCategory.icon())
                .name(createCategory.name())
                .ownerId(ownerId)
                .build();
    }
}

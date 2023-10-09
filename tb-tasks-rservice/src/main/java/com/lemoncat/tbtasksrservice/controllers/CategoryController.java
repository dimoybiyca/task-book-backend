package com.lemoncat.tbtasksrservice.controllers;

import com.lemoncat.tbtasksrservice.DTO.Category.CategoryDTO;
import com.lemoncat.tbtasksrservice.DTO.Category.CreateCategory;
import com.lemoncat.tbtasksrservice.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<CategoryDTO>> getCategories(@AuthenticationPrincipal Jwt principal) {
        List<CategoryDTO> categories = categoryService.getAllDTO(principal.getSubject());

        return Map.of("categories", categories);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, CategoryDTO> createCategory(
            @RequestBody CreateCategory createCategory,
            @AuthenticationPrincipal Jwt principal) {
        CategoryDTO category = categoryService.create(createCategory, principal.getSubject());

        return Map.of("category", category);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Map<String, CategoryDTO> updateCategory(
            @RequestBody CategoryDTO categoryDTO,
            @AuthenticationPrincipal Jwt principal) {
        CategoryDTO category = categoryService.update(categoryDTO, principal.getSubject());

        return Map.of("category", category);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  delete(
            @PathVariable Long id,
            @AuthenticationPrincipal Jwt principal) {
        categoryService.delete(id, principal.getSubject());

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

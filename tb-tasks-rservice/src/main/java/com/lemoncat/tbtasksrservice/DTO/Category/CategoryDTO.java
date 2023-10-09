package com.lemoncat.tbtasksrservice.DTO.Category;

import lombok.Builder;

@Builder
public record CategoryDTO(Long id, String name, String icon) {

}

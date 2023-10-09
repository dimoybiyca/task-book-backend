package com.lemoncat.tbtasksrservice.services;

import com.lemoncat.tbtasksrservice.DTO.Template.CreateTemplate;
import com.lemoncat.tbtasksrservice.DTO.Template.TemplateDTO;
import com.lemoncat.tbtasksrservice.DTO.Template.TemplateMapper;
import com.lemoncat.tbtasksrservice.models.Template;
import com.lemoncat.tbtasksrservice.repositories.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    public List<Template> getAllByOwner(String ownerId) {
        return templateRepository.getAllByOwnerId(ownerId);
    }

    public List<TemplateDTO> getAllDTOByOwner(String ownerId) {
        return getAllByOwner(ownerId).stream()
                .map(templateMapper::templateToDTO)
                .toList();
    }

    public TemplateDTO create(CreateTemplate request, String ownerId) {
        Template template = templateMapper.toObject(request, ownerId);

        return templateMapper.templateToDTO(templateRepository.save(template));
    }
}

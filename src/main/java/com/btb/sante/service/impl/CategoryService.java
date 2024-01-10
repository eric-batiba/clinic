package com.btb.sante.service.impl;

import com.btb.sante.dto.request.CategoryRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.entity.Category;
import com.btb.sante.entity.Service;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ServiceNotFoundException;
import com.btb.sante.mapper.CategoryMapper;
import com.btb.sante.repository.CategoryRepository;
import com.btb.sante.repository.ServiceRepository;
import com.btb.sante.service.Icategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@org.springframework.stereotype.Service
@Component
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CategoryService implements Icategory {
    public static final String CATEGORY_SERVICE = "CategoryService";
    private final CategoryRepository categoryRepository;
    private final ServiceRepository serviceRepository;
     private final CategoryMapper mapper;

    @Override
    public CategoryResponseDto saveCat(CategoryRequestDto categoryRequestDto) throws ServiceNotFoundException {
       log.info(CATEGORY_SERVICE + ":  saveCat()");

        Service serviceByName = serviceRepository.findByNom(categoryRequestDto.getNomService()).orElseThrow(() ->
                new ServiceNotFoundException(String.format("le service %s existe pas", categoryRequestDto.getNomService())));
        Category category = mapper.toEntity(categoryRequestDto);
        category.setService(serviceByName);
        return mapper.toDto(categoryRepository.save(category));

    }

    @Override
    public List<CategoryResponseDto> findAll() {
        log.info(CATEGORY_SERVICE + ": findAll()");
        return categoryRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<CategoryResponseDto> findById(Long id) throws CategoryNotFoundException {
        log.info(CATEGORY_SERVICE + " {} : findById()",id);
        return Optional.ofNullable(mapper.toDto(getCategoryById(id)));
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto categoryRequestDto, Long id) throws CategoryNotFoundException {
        log.info(CATEGORY_SERVICE + " {} : update()",id);
        getCategoryById(id);
        Category category = mapper.toEntity(categoryRequestDto);
        Service service = serviceRepository.save(category.getService());
        category.setService(service);
        return mapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void updateOther(CategoryRequestDto categoryRequestDto, Long id) throws CategoryNotFoundException {
        log.info(CATEGORY_SERVICE + " {} : update()",id);
        Category category = getCategoryById(id);
        mapper.updateToDto(categoryRequestDto, category);
    }

    @Override
    public void delete(Long id) throws CategoryNotFoundException {
        log.info(CATEGORY_SERVICE + ": delete()");
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }
    private Category getCategoryById(Long id) throws CategoryNotFoundException {
        log.info(CATEGORY_SERVICE + " {} : getCategoryById()",id);
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(String.format("category with id %s not found", id)));
    }
}

package com.btb.sante.controller;

import com.btb.sante.controller.api.IcategoryController;
import com.btb.sante.dto.request.CategoryRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ServiceNotFoundException;
import com.btb.sante.service.impl.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Tag(name = "Category")
public class CategoryController implements IcategoryController {
    private final CategoryService categoryService;
    @Override
    public ResponseEntity<CategoryResponseDto> save(CategoryRequestDto categoryRequestDto) throws ServiceNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCat(categoryRequestDto));
    }

    @Override
    public ResponseEntity<List<CategoryResponseDto>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @Override
    public ResponseEntity<Optional<CategoryResponseDto>> getById(Long id) throws CategoryNotFoundException {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @Override
    public ResponseEntity<CategoryResponseDto> update(CategoryRequestDto categoryRequestDto, Long id) throws CategoryNotFoundException, ServiceNotFoundException {
        return ResponseEntity.ok().body(categoryService.update(categoryRequestDto,id));
    }

    @Override
    public ResponseEntity<String> updateOther(CategoryRequestDto categoryRequestDto, Long id) throws CategoryNotFoundException {
        categoryService.updateOther(categoryRequestDto, id);
       return ResponseEntity.ok().body("Updated ok ");
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws CategoryNotFoundException {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

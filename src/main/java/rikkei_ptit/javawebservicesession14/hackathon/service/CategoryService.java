package rikkei_ptit.javawebservicesession14.hackathon.service;


import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import rikkei_ptit.javawebservicesession14.hackathon.exception.ResourceNotFoundException;
import rikkei_ptit.javawebservicesession14.hackathon.model.Category;
import rikkei_ptit.javawebservicesession14.hackathon.repo.CategoryRepository;

@Service    
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryRepository.softDelete(id);
    }
}

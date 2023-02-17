package ru.geekbrains.winter.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.core.api.CategoryDto;
import ru.geekbrains.winter.market.core.entities.Category;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final ProductConverter productConverter;
    //из category в dto
    public CategoryDto entityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream()
                .map(productConverter::entityToDto)
                .collect(Collectors.toList()));
        return categoryDto;
    }
    //из dto в category
    public Category dtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setProducts(categoryDto.getProducts().stream()
                .map(productConverter::dtoToEntity)
                .collect(Collectors.toList()));
        return category;
    }
}

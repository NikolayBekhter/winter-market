package ru.geekbrains.winter.market.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.winter.market.core.api.ProductDto;
import ru.geekbrains.winter.market.core.api.ResourceNotFoundException;
import ru.geekbrains.winter.market.core.entities.Category;
import ru.geekbrains.winter.market.core.entities.Product;
import ru.geekbrains.winter.market.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    //из product в dto
    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost(), product.getCategory().getTitle());
    }
    //из dto в product
    public Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setCost(productDto.getCost());
        Category category = categoryService.findByTitle(productDto.getCategoryTitle())
                .orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + productDto.getCategoryTitle() + " не найдена."));
        product.setCategory(category);
        return product;
    }
}

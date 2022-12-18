package ru.geekbrains.spring.winter.market.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.dtos.ProductDto;
import ru.geekbrains.spring.winter.market.entities.Product;

@Service
public class MappingUtils {
    //из product в dto
    public ProductDto mapToProductDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getCost());
    }
    //из dto в product
    public Product mapToProduct(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost());
    }
}

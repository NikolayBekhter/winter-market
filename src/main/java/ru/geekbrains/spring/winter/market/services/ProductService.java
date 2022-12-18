package ru.geekbrains.spring.winter.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.winter.market.entities.Product;
import ru.geekbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.winter.market.repositories.ProductRepository;
import ru.geekbrains.spring.winter.market.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> find(Integer minCost, Integer maxCost, String titlePart, Integer page) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterOrEqualsThan(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessOrEqualsThan(maxCost));
        }
        if (titlePart != null) {
            spec = spec.and(ProductSpecifications.titleLike(titlePart));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteByIdProduct(Long id) {
        productRepository.deleteById(id);
    }

}


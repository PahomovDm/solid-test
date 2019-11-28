package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.model.dto.ProductInformationDto;
import com.pakhomov.solidtest.model.entity.Product;
import com.pakhomov.solidtest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    private final static int COUNT_ON_PAGE = 10;

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    private ResponseEntity<List<Product>> getListProduct(@RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            return ResponseEntity.ok(productService.getProductsOnPage(1, COUNT_ON_PAGE));
        }
        return ResponseEntity.ok(productService.getProductsOnPage(page, COUNT_ON_PAGE));
    }

    @PostMapping("/edit")
    private ResponseEntity editProduct(@RequestBody Product editProduct) {
        productService.updateProduct(editProduct);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    private ResponseEntity saveNewProduct(@RequestBody Product newProduct) {
        productService.saveProduct(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pagination")
    private ResponseEntity<Long> getPagesNumber() {
        Long countOfProducts = productService.getCountOfProducts();
        if (countOfProducts == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(Double.valueOf(Math.ceil(countOfProducts.doubleValue() / COUNT_ON_PAGE)).longValue());
        }
    }

    @GetMapping("/information")
    private ResponseEntity<ProductInformationDto> getListProductInformationDto(
            @RequestParam(name = "id") Long productId) {
        ProductInformationDto productInformationDto = productService.getProductInformationByProductId(productId);
        if (productInformationDto == null || productInformationDto.getNumber() == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productInformationDto);
    }
}

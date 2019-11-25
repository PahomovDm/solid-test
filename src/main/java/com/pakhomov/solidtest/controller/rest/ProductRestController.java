package com.pakhomov.solidtest.controller.rest;

import com.pakhomov.solidtest.model.Product;
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
    public ResponseEntity<List<Product>> getListProduct(
            @RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            return ResponseEntity.ok(productService.getProductsOnPage(1, COUNT_ON_PAGE));
        }
        return ResponseEntity.ok(productService.getProductsOnPage(page, COUNT_ON_PAGE));
    }

    @PostMapping("/edit")
    public ResponseEntity editProduct(@RequestBody Product editProduct) {
        productService.updateProduct(editProduct);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public ResponseEntity saveNewProduct(@RequestBody Product newProduct) {
        productService.saveProduct(newProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pagination")
    public ResponseEntity<Long> getPagesNumber() {
        Long countOfProducts = productService.getCountOfProducts();
        if (countOfProducts == 0) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(new Double(Math.ceil(countOfProducts.doubleValue() / COUNT_ON_PAGE)).longValue());
        }
    }
}

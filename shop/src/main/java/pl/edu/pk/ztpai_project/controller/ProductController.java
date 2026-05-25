package pl.edu.pk.ztpai_project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pk.ztpai_project.dto.Product.ProductMapper;
import pl.edu.pk.ztpai_project.dto.Product.ProductRequest;
import pl.edu.pk.ztpai_project.dto.Product.ProductResponse;
import pl.edu.pk.ztpai_project.model.Product;
import pl.edu.pk.ztpai_project.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return service.getAllProducts().stream()
            .map(ProductMapper::toResponse)
            .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductResponse createProduct(@RequestBody ProductRequest request) {
        Product productSaved = service.createProduct(ProductMapper.toEntity(request));
        return ProductMapper.toResponse(productSaved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProductById(id);
    }
}

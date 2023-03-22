package kg.sanack.newsstand.rest;

import io.swagger.v3.oas.annotations.Operation;
import kg.sanack.newsstand.entity.Product;
import kg.sanack.newsstand.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ProductService productService;

    public ApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @Operation(summary = "API for getting list of products")
    Page<Product> findAll(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);

        return productService.findAll(pageable);
    }

    @GetMapping("/products/kiosk/{id}")
    @Operation(summary = "API for getting list of products using newsstand id")
    Page<Product> findByNewsstand(@RequestParam int page, @RequestParam int size, @PathVariable int id) {
        Pageable pageable = PageRequest.of(page, size);

        return productService.findByNewsstandId((long)id, pageable);
    }

    @PostMapping("/products/create")
    @Operation(summary = "API for creating product")
    public ResponseEntity<Void> createProduct(@Valid @RequestBody Product product){
        productService.save(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

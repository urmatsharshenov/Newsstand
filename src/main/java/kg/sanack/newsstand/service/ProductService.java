package kg.sanack.newsstand.service;

import kg.sanack.newsstand.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByNewsstandId(Long id, Pageable pageable);

    void save(Product product);

    void deleteById(Long theId);

    void edit(Product product);
}

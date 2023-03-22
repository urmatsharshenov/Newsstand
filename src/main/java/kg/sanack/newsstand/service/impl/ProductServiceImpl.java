package kg.sanack.newsstand.service.impl;

import kg.sanack.newsstand.entity.Product;
import kg.sanack.newsstand.repository.ProductRepository;
import kg.sanack.newsstand.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByNewsstandId(Long id, Pageable pageable) {
        return productRepository.findByNewsstandId(id, pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long theId) {
        productRepository.deleteById(theId);
    }

    @Override
    public void edit(Product newProduct) {
        Product product = productRepository.findById(newProduct.getId()).get();
        product.setProductType(newProduct.getProductType());
        product.setTitle(newProduct.getTitle());
        product.setQuantity(newProduct.getQuantity());

        productRepository.save(product);
    }
}

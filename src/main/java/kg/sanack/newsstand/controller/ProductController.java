package kg.sanack.newsstand.controller;

import kg.sanack.newsstand.entity.Newsstand;
import kg.sanack.newsstand.entity.Product;
import kg.sanack.newsstand.repository.NewsstandRepository;
import kg.sanack.newsstand.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final NewsstandRepository newsstandRepository;

    public ProductController(ProductService productService, NewsstandRepository newsstandRepository) {
        this.productService = productService;
        this.newsstandRepository = newsstandRepository;
    }

    @GetMapping()
    public String listProducts( @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam Long kioskId,
                                Model model) {
        Pageable pageable = PageRequest.of(page,size);

        Page<Product> products = productService.findByNewsstandId(kioskId, pageable);
        Newsstand newsstand = newsstandRepository.findById(kioskId).get();
        Product newProduct = new Product();
        newProduct.setNewsstand(newsstand);

        model.addAttribute("products", products);
        model.addAttribute("newsstand", newsstand);
        model.addAttribute("newProduct", newProduct);

        return "products/list-products";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("newProduct") Product product) {
        product.setDatePublished(LocalDate.now());
        // save the employee
        productService.save(product);

        // use a redirect to prevent duplicate submissions
        return "redirect:/products?kioskId="+product.getNewsstand().getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int theId,@RequestParam("kioskId") int kioskId) {
        productService.deleteById((long) theId);

        return "redirect:/products?kioskId="+kioskId;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("newProduct") Product product,
                       @RequestParam("kioskId") int kioskId) {
        productService.edit(product);
        return "redirect:/products?kioskId="+kioskId;
    }
}

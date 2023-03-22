package kg.sanack.newsstand.controller;

import kg.sanack.newsstand.entity.Newsstand;
import kg.sanack.newsstand.service.NewsstandService;
import kg.sanack.newsstand.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final NewsstandService newsstandService;
    private final ProductService productService;

    public HomeController(NewsstandService newsstandService, ProductService productService) {
        this.newsstandService = newsstandService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String listNewsstands(Model model) {
        List<Newsstand> newsstands = newsstandService.listAll();
        model.addAttribute("newstands", newsstands);

        return "newsstands/list-newsstands";
    }
}

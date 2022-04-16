package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping(value = {"/", "/product"})
@AllArgsConstructor
public class ProductServlet {

    private final ProductService productService;

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());

        return "product";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String search, Model model) {
        model.addAttribute("products", productService.findByMatchInDescription(search));

        return "product";
    }

    @GetMapping("/add")
    public String addProduct() {
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Double price,
                             @RequestParam String description) {
        productService.add(Product.builder()
                .name(name)
                .price(price)
                .description(description)
                .build());

        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editProduct(@RequestParam Long id, Model model) {
        model.addAttribute("product", productService.getById(id));

        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProduct(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam Double price,
                              @RequestParam LocalDateTime date,
                              @RequestParam String description) {
        productService.update(Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .creationDate(date)
                .description(description)
                .build());

        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeProduct(@RequestParam Long id) {
        productService.delete(id);

        return "redirect:/";
    }
}

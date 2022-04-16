package com.pankov.roadtosenior.onlineshop.web.servlet;

import com.pankov.roadtosenior.onlineshop.entity.Product;
import com.pankov.roadtosenior.onlineshop.security.SecurityService;
import com.pankov.roadtosenior.onlineshop.security.Session;
import com.pankov.roadtosenior.onlineshop.service.CartService;
import com.pankov.roadtosenior.onlineshop.web.util.CookieParser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartServlet {

    private CartService cartService;

    @GetMapping
    public String getCartBySession(@RequestAttribute Session session, Model model) {
        if (session != null) {
            List<Product> productList = session.getCart();
            model.addAttribute("products", productList);
        }
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long id,
                            @CookieValue(value = "user-token") String token) {
        cartService.addProductToCart(token, id);

        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(@RequestParam Long id,
                                 @CookieValue(value = "user-token") String token) {
        cartService.removeProductFromCart(token, id);

        return "redirect:/cart";
    }
}

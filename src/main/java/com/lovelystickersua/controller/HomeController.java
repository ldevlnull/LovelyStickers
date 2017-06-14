package com.lovelystickersua.controller;

import com.lovelystickersua.POJO.Product;
import com.lovelystickersua.POJO.User;
import com.lovelystickersua.service.ProductService;
import com.lovelystickersua.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.lovelystickersua.controller.PAGE.*;

@Controller
public class HomeController {

    @Autowired
    private UserService uService;

    @Autowired
    private ProductService pService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home(Model model, Principal principal) {
        if (principal != null) {
            if (principal.getName().equals("admin")) {
                return REFRESH.val() + ADMIN.val();
            } else {
                model.addAttribute("user", uService.userFetch(Long.parseLong(principal.getName())));
            }
        }
        model.addAttribute("products", pService.findAllProductDTOs());
        return STORE.val();
    }

    @RequestMapping(value = "/buy/{ID}", method = RequestMethod.POST)
    public String home(Principal principal, @PathVariable String ID/*, @RequestParam int productsNum*/) {
        User user = uService.userFetch(Long.parseLong(principal.getName()));
        Product product = pService.productFetch(Long.parseLong(ID));
//		for(int i = 0; i < productsNum; i++) {
        product.getUsers().add(user);
        pService.save(product);
//		}

        return REFRESH.val() + PRODUCT.val();
    }

    @RequestMapping(value = "/deleteFromCart/{ID}", method = RequestMethod.GET)
    public String delete(Principal principal, @PathVariable long ID) {
        Product product = pService.productFetch(ID);
        User user = uService.userFetch(Long.parseLong(principal.getName()));
        product.getUsers().remove(user);
        pService.save(product);
        return REFRESH.val() + PROFILE.val();
    }

    @RequestMapping(value = "/clearCart")
    public String clearCart(Principal principal) {
        User user = uService.userFetch(Long.parseLong(principal.getName()));
        user.setProducts(null);
        uService.save(user);
        return REFRESH.val() + PROFILE.val();
    }

    @RequestMapping(value = "/cookie")
    public String cookie(HttpServletRequest request) {
        for (Cookie c:
             request.getCookies()) {
            System.out.println(c.getName()+" "+c.getValue());
        }
        return REFRESH.val();
    }
}
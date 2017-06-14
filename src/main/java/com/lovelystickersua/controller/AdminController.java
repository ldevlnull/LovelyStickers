package com.lovelystickersua.controller;

import com.lovelystickersua.POJO.Category;
import com.lovelystickersua.service.CategoryService;
import com.lovelystickersua.service.ProductService;
import com.lovelystickersua.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.lovelystickersua.controller.PAGE.*;

/**
 * Created by devnull on 16.11.16.
 */
@Controller
public class AdminController {

    @Autowired
    private CategoryService cService;
    @Autowired
    private ProductService pService;
    @Autowired
    private UserService uService;

    @RequestMapping(value = "/admin")
    public String admin(Model model){
        model.addAttribute("categoryEmpty", new Category());
        return ADMIN.val();
    }
    @RequestMapping(value = "/adminProducts")
    public String adminProducts(Model model){
        model.addAttribute("products", pService.findAll());
        model.addAttribute("categories", cService.findAllDTOS());
        return ADMIN_PRODUCTS.val();
    }
    @RequestMapping(value = "/adminUsers")
    public String adminUsers(Model model){
        model.addAttribute("users", uService.findAllUserDTOs());
        return ADMIN_USERS.val();
    }
    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute Category category){
        cService.save(category);
        return REFRESH.val()+ADMIN.val();
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@PathVariable String id){
        uService.delete(Long.parseLong(id));
        return REFRESH.val()+ADMIN_USERS.val();
    }
}

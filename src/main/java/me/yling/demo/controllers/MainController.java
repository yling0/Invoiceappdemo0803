package me.yling.demo.controllers;

import me.yling.demo.models.Product;
import me.yling.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String showIndex(Model model){
        String myMessage = "Welcome to the Invoice App";
        model.addAttribute("message", myMessage);
        return "index";
    }

    @GetMapping("/addproduct")
    public String addProduct(Model model){
        model.addAttribute("newProduct", new Product());
        return "addproduct";
    }

    @PostMapping("/addproduct")
    public String postProduct(@Valid @ModelAttribute("newProduct") Product product, BindingResult bindingResult)
    {

        System.out.println(bindingResult.toString());
        System.out.println(product.getDescription());

        if (bindingResult.hasErrors())
        {
            return "addproduct";
        }

        productRepository.save(product);
        return "result";
    }

    @GetMapping("/showproducts")
    public @ResponseBody String showAllProducts()
    {
        Iterable<Product> productList = productRepository.findAll();
        return productList.toString();
    }


}

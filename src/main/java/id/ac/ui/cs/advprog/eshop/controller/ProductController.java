package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage (Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost (@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        System.out.println("Accessing edit page for productId: " + productId);
        Product product = service.findById(productId);
        model.addAttribute("product", product);
        if (product == null) {
            System.out.println("Produk tidak ditemukan!");
            return "redirect:/product/list";
        }
        return "editProduct";
    }

    @PostMapping("/edit/{productId}")
    public String editProductPost(@PathVariable String productId,
                                  @RequestParam String productName,
                                  @RequestParam int productQuantity) {
        service.update(productId, productName, productQuantity);
        return "redirect:/product/list";
    }
}
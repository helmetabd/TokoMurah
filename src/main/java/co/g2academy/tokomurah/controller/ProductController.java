/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.tokomurah.controller;

import co.g2academy.tokomurah.dao.DAO;
import co.g2academy.tokomurah.model.Product;
import co.g2academy.tokomurah.springJdbc.ProductDaoSpringJdbc;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author personal
 */
@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    private ProductDaoSpringJdbc dao;
    @GetMapping("/product")
    public List<Product> getProducts() {
        return dao.getProductsData();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable Integer id) {        
        return dao.getProductById(id);
    }
    @PostMapping("/product")
    public String save(@RequestBody Product product) {
        dao.save(product);
        return "success";
    }
    @PutMapping("/product/{id}")
    public String update(@RequestBody Product product) {
        dao.save(product);
        return "success";
    }
    @DeleteMapping("/product/{id}")
    public String deleteById(@PathVariable Integer id) {
        dao.delete(id);
        return "success";
    }
    @DeleteMapping("/product")
    public String delete() {
        dao.deleteAll();
        return "success";
    }
}

package com.uoit.network.supeasy.controller;

import com.uoit.network.supeasy.model.ProductInfo;
import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.service.ProductService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    @ResponseBody
    public Result<Integer> add(@RequestBody ProductInfo productInfo , HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");

        productInfo.setStoreId(Integer.parseInt(String.valueOf(claims.get("userid"))));
        return productService.addProduct(productInfo);
    }

    @GetMapping("/all")
    @ResponseBody
    public Result<List<ProductInfo>> get(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");

        return productService.getProductsByStoreId(Integer.parseInt(String.valueOf(claims.get("userid"))));
    }
}

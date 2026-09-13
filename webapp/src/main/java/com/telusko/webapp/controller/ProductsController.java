package com.telusko.webapp.controller;

import com.telusko.webapp.model.Product;
import com.telusko.webapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductsController {

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getProductsList() {
        return new ResponseEntity<>(this.productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){
        Product product = this.productService.getProductById(prodId);

        if(product != null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/products")
    public void addProductById(@RequestBody Product product){
        this.productService.addProductOld(product);
    }
    @PutMapping("/products")
    public void updateProductById(@RequestBody Product product){
        this.productService.updateProduct(product);
    }
    @DeleteMapping("/products/{prodId}")
    public void deleteProductById(@PathVariable int prodId){
        this.productService.deleteProduct(prodId);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            return this.productService.addProduct(product, imageFile);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.getProductById(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }
}

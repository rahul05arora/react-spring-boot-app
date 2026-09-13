package com.telusko.webapp.service;

import com.telusko.webapp.model.Product;
import com.telusko.webapp.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

/*
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
*/
    /*List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1,"Android",2000),
            new Product(2,"Iphone",3000)
    ));*/

    public List<Product> getProducts() {
        /*System.out.println(this.products.toString());
        log.info(products.toString());*/
        return productRepo.findAll();
    }

   /* public void setProducts(List<Product> products) {
        this.products = products;
    }*/
    public Product getProductById(int productId){
       return productRepo.findById(productId).orElse(null);
        /* return this.products
                .stream()
                .filter(product -> product.getProdId()==productId).findFirst().orElse(new Product(100, "No Item", 0));*/
    }
    public ResponseEntity<?> addProductOld(Product product){
        /*this.products.add(product);*/
        productRepo.save(product);
        return null;
    }
    public ResponseEntity<?> addProduct(Product product, MultipartFile imageFile) throws IOException {
        /*this.products.add(product);*/
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        product.setImageName(imageFile.getOriginalFilename());
        Product savedProduct = productRepo.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    public void updateProduct(Product product) {
        productRepo.save(product);
        /*int index=0;
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getProdId()==product.getProdId()){
                index = i;
            }
        }
        products.set(index,product);*/
    }

    public void deleteProduct(int prodId) {
        productRepo.deleteById(prodId);
        /*int index=0;
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getProdId()==prodId){
                index = i;
            }
        }
        products.remove(index);*/
    }
}

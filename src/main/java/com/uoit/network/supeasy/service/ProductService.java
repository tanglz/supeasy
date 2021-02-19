package com.uoit.network.supeasy.service;

import com.uoit.network.supeasy.model.ProductInfo;

import java.util.List;

public interface ProductService {
    List<ProductInfo> getProductsByStoreId(Integer storeId);

    Integer addProduct(ProductInfo productInfo);

    ProductInfo getProductById(Integer id);

    ProductInfo getProductByNameAndStoreId(String name, Integer storeId);

}

package com.uoit.network.supeasy.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductDO,Integer> {
    List<ProductDO> findAllByStoreId(Integer storeId);

    ProductDO findByStoreIdAndName(Integer storeId,String name);
}

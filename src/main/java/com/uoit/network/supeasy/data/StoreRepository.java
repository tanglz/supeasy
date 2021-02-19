package com.uoit.network.supeasy.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StoreRepository extends CrudRepository<StoreDO,Integer> {
        List<StoreDO> findByName(String name);
}

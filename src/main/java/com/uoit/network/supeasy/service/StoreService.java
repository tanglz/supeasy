package com.uoit.network.supeasy.service;

import com.uoit.network.supeasy.model.StoreInfo;

public interface StoreService {
    Integer addStore(StoreInfo storeInfo);

    StoreInfo getStoreByName(String name);
}

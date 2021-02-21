package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.data.StoreDO;
import com.uoit.network.supeasy.data.StoreRepository;
import com.uoit.network.supeasy.model.Result;
import com.uoit.network.supeasy.model.StoreInfo;
import com.uoit.network.supeasy.service.StoreService;
import com.uoit.network.supeasy.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Result<Integer> addStore(StoreInfo storeInfo) {
        Result<Integer> result=new Result<>();


        StoreDO storeDO=new StoreDO();
        storeDO= (StoreDO) ConvertUtil.modelToDo(storeInfo,storeDO);
        StoreDO save = storeRepository.save(storeDO);
        result.setStatus(true);
        result.setObject(save.getId());
        return result;
    }

    @Override
    public Result<StoreInfo> getStoreByName(String name) {
        Result<StoreInfo> result=new Result<>();
        StoreInfo storeInfo=new StoreInfo();
        List<StoreDO> storeDOS = storeRepository.findByName(name);
        if(storeDOS==null||storeDOS.isEmpty()){
            result.setStatus(false);
            result.setErrorCode("E_NO_STORE");
            result.setErrorMessage("store name is not exist, please register first.");
            return result;
        }
        storeInfo= (StoreInfo) ConvertUtil.modelToDo(storeDOS.get(0),storeInfo);
        result.setStatus(true);
        result.setObject(storeInfo);
        return result;
    }


}

package com.uoit.network.supeasy.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserDO,Integer> {

    List<UserDO> findByName(String name);
}

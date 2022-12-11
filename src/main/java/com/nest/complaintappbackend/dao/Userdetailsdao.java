package com.nest.complaintappbackend.dao;

import com.nest.complaintappbackend.model.ComplaintModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Userdetailsdao extends CrudRepository<ComplaintModel,Integer> {


    @Query(value = "SELECT `id`, `address`, `email`, `name`, `password`, `phone`, `username` FROM `user` WHERE `username`= :username AND `password`= :password", nativeQuery = true)
    List<ComplaintModel> UserLogin(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT `id`, `address`, `email`, `name`, `password`, `phone`, `username` FROM `user` WHERE `username`= :username",nativeQuery = true)
    List<ComplaintModel> FindUser(@Param("username") String username);

    @Query(value = "SELECT `id`, `address`, `email`, `name`, `password`, `phone`, `username` FROM `user` WHERE `id`= :id",nativeQuery = true)
    List<ComplaintModel> FindUserById(@Param("id") String id);


}

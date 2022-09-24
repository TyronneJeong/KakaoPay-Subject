package com.kakaopay.memebership.repository;

import com.kakaopay.memebership.model.entity.BarcodeEntity;
import com.kakaopay.memebership.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}

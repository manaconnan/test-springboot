package com.smart.dao;

import com.smart.bean.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

public interface GirlRepository extends JpaRepository<Girl,Integer> {

}

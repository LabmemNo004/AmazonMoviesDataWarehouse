package com.example.demo.dao;

import com.example.demo.Entity.cooperation;
import com.example.demo.Entity.cooperationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface cooperationRepository extends JpaRepository<cooperation, cooperationPK>,
        JpaSpecificationExecutor<cooperation> {


}

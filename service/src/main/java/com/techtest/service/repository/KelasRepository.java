package com.techtest.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtest.service.entity.KelasModel;

@Repository
public interface KelasRepository extends JpaRepository<KelasModel, String> {

}

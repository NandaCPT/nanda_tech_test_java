package com.techtest.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtest.service.entity.SiswaModel;

@Repository
public interface SiswaRepository extends JpaRepository<SiswaModel, String> {

}

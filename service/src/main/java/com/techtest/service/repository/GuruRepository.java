package com.techtest.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtest.service.entity.GuruModel;

@Repository
public interface GuruRepository extends JpaRepository<GuruModel, String> {

}

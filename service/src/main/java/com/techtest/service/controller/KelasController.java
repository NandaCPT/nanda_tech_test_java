package com.techtest.service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.service.dto.KelasDTO;
import com.techtest.service.repository.KelasRepository;
import com.techtest.service.service.KelasService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kelas")
@Slf4j
public class KelasController {

	@Autowired
	KelasService service;
	
	@Autowired
	KelasRepository repo;
	
	@PostMapping
	public ResponseEntity<Object> addKelas(@RequestBody KelasDTO dto){
		Map<String, Object> result = new HashMap<>();
		try {
			if(repo.existsById(dto.getKodeKelas())) {
				result.put("message", "Kelas Already Exist!");
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}
			String kodeKelas = service.addKelas(dto);
			if(!kodeKelas.equals("")) {
				result.put("message", "Add Kelas Success!");
				result.put("data", dto);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			else {
				result.put("message", "Add Kelas Failed!");
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("status", false);
			result.put("message", "Something Wrong!");
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

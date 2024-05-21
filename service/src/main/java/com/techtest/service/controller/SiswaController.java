package com.techtest.service.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techtest.service.dto.SiswaDTO;
import com.techtest.service.entity.SiswaModel;
import com.techtest.service.repository.SiswaRepository;
import com.techtest.service.service.SiswaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/siswa")
@Slf4j
public class SiswaController {
	
	@Autowired
	SiswaService service;
	
	@Autowired
	SiswaRepository repo;
	
	@PostMapping
	public ResponseEntity<Object> insertSiswa(@RequestBody SiswaDTO dto){
		Map<String, Object> result = new HashMap<>();
		
		try {
			if(repo.existsById(dto.getNis())) {
				result.put("message", "Siswa Already Exist!");
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}
			String nis = service.addSiswa(dto);
			if(!nis.equals("")) {
				result.put("message", "Add Siswa Success!");
				result.put("data", dto);
				return new ResponseEntity<>(result, HttpStatus.OK);
			}
			else {
				result.put("message", "Add Siswa Failed!");
				return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}	
		} catch (Exception e) {
			log.error(e.getMessage());
			result.put("status", false);
			result.put("message", "Something Wrong!");
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSiswaById(@PathVariable String id){
		Map<String, Object> result = new HashMap<>();
		SiswaDTO dto = new SiswaDTO();
		Optional<SiswaModel> findSiswa = repo.findById(id);
		if(findSiswa.isPresent()) {
			SiswaModel getSiswa = findSiswa.get();
			try {
				dto.setNis(getSiswa.getNis());
				dto.setNama(getSiswa.getNama());
				dto.setKodeKelas(getSiswa.getKelas().getKodeKelas());
				dto.setWaliKelas(getSiswa.getKelas().getGuru().getNama());
				dto.setTanggalLahir(getSiswa.getTanggalLahir());
				dto.setNamaKelas(getSiswa.getKelas().getNamaKelas());
				dto.setDeleted(getSiswa.getDeleted());
				
			} catch (Exception e) {
				log.error(e.getMessage(),e);
			}
			result.put("message", "Get Siswa Success!");
			result.put("data", dto);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			result.put("message", "Siswa Not Found");
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping
	public ResponseEntity<Object> updateSiswa(@RequestBody SiswaDTO dto){
		Map<String, Object> result = new HashMap<>();
		try {
			if(!repo.existsById(dto.getNis())) {
				result.put("message", "Employee Not Found!");
				return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
			}else {
				service.editSiswa(dto);
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		result.put("message", "Edit Siswa Success!");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<Object> getAllSiswa(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		List<SiswaDTO> listSiswa = new ArrayList<SiswaDTO>();
		Pageable paging = PageRequest.of(page, size);
		
		Page<SiswaModel> siswaModel = repo.findAll(paging);
		
		siswaModel.stream().forEach(e -> {
			SiswaDTO dto = new SiswaDTO();
			try {
				dto.setNis(e.getNis());
				dto.setNama(e.getNama());
				dto.setTanggalLahir(e.getTanggalLahir());
				dto.setKodeKelas(e.getKelas().getKodeKelas());
				dto.setNamaKelas(e.getKelas().getNamaKelas());
				dto.setWaliKelas(e.getKelas().getGuru().getNama());
				dto.setDeleted(e.getDeleted());
				listSiswa.add(dto);
			} catch (Exception e2) {
				log.error(e2.getMessage(),e2);
			}
		});
		
		Map<String, Object> result = new HashMap<>();
		result.put("data", listSiswa);
		result.put("Page", siswaModel.getNumber());
		result.put("TotalItem", siswaModel.getTotalElements());
		
		if(listSiswa.size() == 0) {
			result.put("message", "No Data Found!");
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		}else {
			result.put("message", "Get Job Success!");
			return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}

}

package com.techtest.service.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techtest.service.dto.KelasDTO;
import com.techtest.service.entity.GuruModel;
import com.techtest.service.entity.KelasModel;
import com.techtest.service.repository.GuruRepository;
import com.techtest.service.repository.KelasRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KelasService {
	
	@Autowired
	KelasRepository repo;
	
	@Autowired
	GuruRepository guruRepo;
	
	@Transactional
	public String addKelas(KelasDTO dto) {
		KelasModel model = new KelasModel();
		String result = "";
		try {
			GuruModel guruModel = new GuruModel();
			Optional<GuruModel> existingGuru = guruRepo.findById(dto.getNip());
			if (existingGuru.isPresent()) {
				guruModel = existingGuru.get();
				model.setGuru(guruModel);
			}else {
				log.error("Guru " + dto.getNip() + " Not Found");
			}
			
			model.setKodeKelas(dto.getKodeKelas());
			model.setNamaKelas(dto.getNamaKelas());
			model.setGuru(guruModel);
			model.setInsertBy(dto.getInsertBy());
			repo.save(model);
			
			result = dto.getNip();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return result;
	}

}

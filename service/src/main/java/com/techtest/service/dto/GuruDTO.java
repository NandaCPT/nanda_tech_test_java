package com.techtest.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuruDTO {
	
    private String nip;
    private String nama;
    private String hpNo;
    private boolean deleted;
    private String insertBy;;
    private String updateBy;

}

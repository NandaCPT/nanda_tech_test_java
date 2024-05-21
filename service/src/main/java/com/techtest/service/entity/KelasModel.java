package com.techtest.service.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.utility.nullability.MaybeNull;

@Getter
@Setter
@Entity
@Table(name = "kelas", schema = "public")
public class KelasModel {
	
	@Id
	@Column(name = "kode_kelas")
	private String kodeKelas;
	
	@Column(name = "nama_kelas")
	private String namaKelas;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nip")
    private GuruModel guru;
    
    @CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Jakarta")
	private Date insertDate;
	@CreatedBy private String insertBy;
	 
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Jakarta")
	private Date updateDate;
	@LastModifiedBy private String updateBy;

}

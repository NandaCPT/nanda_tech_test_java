# nanda_tech_test_java

#List of Service
1. discovery : Service Eureka Discovery
2. gateway : Untuk Routing services
3. service : sebagai Main Project

#Stack
1. Java JDK 8
2. Spring Boot 2.7.5
3. PostgreSQL

#Step to Run
1. Jalankan query berikut pada postgreSQL local/server, nama database yang diset adalah postgres dengan schema public

--Table Siswa
CREATE TABLE IF NOT EXISTS public.siswa
(
    nis character varying(10) NOT NULL,
    nama character varying(50) NOT NULL,
    tanggal_lahir date,
    kode_kelas character varying(5),
	deleted boolean,
    insert_by character varying(50),
    insert_date timestamp without time zone,
    update_by character varying(50),
    update_date timestamp without time zone,
    PRIMARY KEY (nis)
);

ALTER TABLE public.siswa
    OWNER to postgres;

--Table Guru
CREATE TABLE IF NOT EXISTS public.guru
(
    nip character varying(10) NOT NULL,
    nama character varying(100) NOT NULL,
    hp_no character varying(15),
    deleted boolean,
    insert_by character varying(100),
    insert_date timestamp without time zone,
    update_by character varying(100),
    update_date character varying,
    PRIMARY KEY (nip)
);

ALTER TABLE public.guru
    OWNER to postgres;
	
--Table Kelas
CREATE TABLE IF NOT EXISTS public.kelas
(
    kode_kelas character varying(10) NOT NULL,
    nama_kelas character varying(50) NOT NULL,
    nip character varying(10) NOT NULL,
    insert_by character varying(100),
    insert_date timestamp without time zone,
    update_by character varying(100),
    update_date timestamp without time zone,
    PRIMARY KEY (kode_kelas)
);

ALTER TABLE public.kelas
    OWNER to postgres;

2. Run secara terurut dari discovery, gateway, service
3. discovery running pada port 8072, gateway port 8089 dan service port 8093

#Fitur
1. Create Guru (Menambahkan data ke table guru, terdapat validasi jika nip sudah terdaftar)
POST
url : http://localhost:8089/service/guru
body
{
    "nip" : "22000124",
    "nama" : "test guru dua",
    "hpNo" : "088723683276",
    "insertBy" : "admin"
}

2. Create Kelas (Menambahkan data ke table kelas, terdapat validasi jika kode kelas sudah ada, berelasi dengan table guru pada field nip)
POST
url : http://localhost:8089/service/kelas/
body
{
    "kodeKelas" : "BCD",
    "namaKelas" : "TEST KELAS",
    "nip" : "22000124",
    "insertBy" : "Admin"
}

3. Create Siswa (Menambahkan data ke  table siswa, terdapat validasi jika nis sudah ada, berelasi dengan table kelas pada field kode kelas)
POST
url : http://localhost:8089/service/siswa/
body
{
    "nis" : "20240001",
    "nama" : "nandac",
    "tanggalLahir" : "2005-12-06",
    "kodeKelas" : "BCD",
    "insertBy" : "admin"
}

4. Get Siswa By Id (Menampilkan detail data siswa berdasarkan NIS beserta kelas, nama kelas dan juga wali kelas)
GET
url : http://localhost:8089/service/siswa/20240001

5. Get All Siswa (Menampilan data seluruh siswa yang terdaftar, jumlah yang diget berdasarkan page dan juga size)
GET
url : http://localhost:8089/service/siswa?page=0&size=5

5. Edit Siswa (Melakukan edit pada data siswa berdasarkan NIS)
PUT
url : http://localhost:8089/service/siswa/
body
{
    "nis" : "20240001",
    "nama" : "nandac edit",
    "tanggalLahir" : "2005-12-06",
    "kodeKelas" : "BCD",
    "deleted" : true,
    "updateBy" : "admin"
}
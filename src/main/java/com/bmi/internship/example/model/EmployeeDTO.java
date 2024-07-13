package com.bmi.internship.example.model;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String namaKaryawan;
    private String nikKaryawan;
    private String titelPekerjaan; // Menggunakan String untuk mencari relasi
}

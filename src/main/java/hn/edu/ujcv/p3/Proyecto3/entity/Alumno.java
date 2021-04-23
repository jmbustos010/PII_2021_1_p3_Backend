package hn.edu.ujcv.p3.Proyecto3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long    id;
    private String  nombre;
    private long    telefono;
    private long    numeroidentidad;
    private String  fechanacimiento;
    private String  carrera;
}

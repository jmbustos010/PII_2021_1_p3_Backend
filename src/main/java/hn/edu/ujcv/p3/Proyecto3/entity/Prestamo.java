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
@Table(name = "Prestamo")
public class Prestamo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long    id;
    private String  nombrelibro;
    private String  descripcion;
    private String  condicion;
    private String  fechasalida;
    private String  fechaentrega;
}

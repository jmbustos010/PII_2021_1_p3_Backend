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
@Table(name = "Reposicion")
public class Reposicion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long    id;
    private String  nombrealumno;
    private long    numerocuentaalumno;
    private String  nombrelibro;
    private String  motivo;
    private long    precio;
    private String  fechareposicion;
}

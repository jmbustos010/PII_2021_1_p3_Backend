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
@Table(name = "Multa")
public class Multa {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long    id;
    private String  nombre;
    private String  descripcion;
    private long    numerocuenta;
    private long    monto;
    private String    fechacreacion;
}
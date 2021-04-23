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
@Table(name = "Suscripcion")
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long     id;
    private String   tiposuscripcion;
    private String   fechainicio;
    private String   fechacierre;
    private String   nombrealumno;
    private long     precio;
}

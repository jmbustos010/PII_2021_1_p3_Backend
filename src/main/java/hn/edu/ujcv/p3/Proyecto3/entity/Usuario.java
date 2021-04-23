package hn.edu.ujcv.p3.Proyecto3.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long    id;
    private String  nombre;
    private String  apellido;
    private String  nombreusuario;
    private String  contrasenia;

}

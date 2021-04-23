package hn.edu.ujcv.p3.Proyecto3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Editorial")
public class Editorial {
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        private long    id;
        private String  nombre;
        private String  ubicacion;
        private String  correo;
        private long    telefono;
        private String  fechafundacion;
}

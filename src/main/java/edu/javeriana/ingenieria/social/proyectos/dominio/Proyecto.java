package edu.javeriana.ingenieria.social.proyectos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer codigo, entidad, lider, ubicacion;

    private String nombre, descripcion, objetivo, tematica;

    private Boolean activo, aprobacion;
}

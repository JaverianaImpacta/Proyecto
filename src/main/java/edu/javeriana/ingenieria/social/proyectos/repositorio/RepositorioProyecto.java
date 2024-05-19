package edu.javeriana.ingenieria.social.proyectos.repositorio;

import edu.javeriana.ingenieria.social.proyectos.dominio.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioProyecto extends JpaRepository<Proyecto, Integer> {
    List<Proyecto> findAllByEntidad(Integer entidad);

    List<Proyecto> findAllByUbicacion(Integer ubicacion);

    List<Proyecto> findAllByActivo(boolean activo);

    List<Proyecto> findAllByAprobacion(boolean aprobacion);

    List<Proyecto> findAllByObjetivo(String objetivo);

    boolean existsByCodigo(Integer codigo);

    Proyecto findOneByCodigo(Integer codigo);
}

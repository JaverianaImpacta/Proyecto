package edu.javeriana.ingenieria.social.proyectos.servicio;

import edu.javeriana.ingenieria.social.proyectos.dominio.Proyecto;
import edu.javeriana.ingenieria.social.proyectos.repositorio.RepositorioProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ServicioProyecto {

    @Autowired
    private RepositorioProyecto repositorio;

    public List<Proyecto> obtenerProyectos(){
        return repositorio.findAll();
    }

    public List<Proyecto> obtenerProyectos(Integer entidad){
        return repositorio.findAllByEntidad(entidad);
    }

    public List<Proyecto> obtenerProyectos(boolean activo){
        return repositorio.findAllByActivo(activo);
    }

    public List<Proyecto> obtenerProyectos(String objetivo){
        return repositorio.findAllByObjetivo(objetivo);
    }

    public List<Proyecto> obtenerProyectosPorUbicacion(Integer ubicacion){
        return repositorio.findAllByUbicacion(ubicacion);
    }

    public List<Proyecto> obtenerProyectosPorAprobacion(boolean aprobacion){
        return repositorio.findAllByAprobacion(aprobacion);
    }

    public Proyecto obtenerProyecto(Integer id){
        return repositorio.findById(id).orElse(null);
    }

    public Proyecto obtenerProyectoPorCodigo(Integer codigo){
        return repositorio.findOneByCodigo(codigo);
    }

    public Proyecto crearProyecto(Proyecto proyecto){
        return repositorio.save(proyecto);
    }

    public Proyecto actualizarProyecto(Integer id, Proyecto proyecto){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        proyecto.setId(id);
        return repositorio.save(proyecto);
    }

    public Proyecto borrarProyecto(Integer id){
        Proyecto aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return null;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean proyectoExiste(Integer id){
        return repositorio.existsById(id);
    }

    public boolean proyectoExistePorCodigo(Integer codigo){
        return repositorio.existsByCodigo(codigo);
    }

    public boolean proyectoExiste(Proyecto proyecto){
        List<Proyecto> aux =  obtenerProyectos(proyecto.getEntidad());
        for(Proyecto p : aux){
            if(Objects.equals(p.getTematica(), proyecto.getTematica()) && Objects.equals(p.getObjetivo(), proyecto.getObjetivo())){
                return true;
            }
        }
        return false;
    }
}

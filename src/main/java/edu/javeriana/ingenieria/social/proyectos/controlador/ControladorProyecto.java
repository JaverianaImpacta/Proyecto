package edu.javeriana.ingenieria.social.proyectos.controlador;

import edu.javeriana.ingenieria.social.proyectos.dominio.Proyecto;
import edu.javeriana.ingenieria.social.proyectos.servicio.ServicioProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/proyectos")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorProyecto {
    @Autowired
    private ServicioProyecto servicio;

    @GetMapping("listar")
    public List<Proyecto> obtenerProyectos(){return servicio.obtenerProyectos();}

    @GetMapping("obtenerActivo")
    public ResponseEntity<List<Proyecto>> obtenerProyectos(@RequestParam boolean activo){
        if(servicio.obtenerProyectos(activo).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyectos(activo), HttpStatus.OK);
    }

    @GetMapping("obtenerEntidad")
    public ResponseEntity<List<Proyecto>> obtenerProyectos(@RequestParam Integer entidad){
        if(entidad == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(servicio.obtenerProyectos(entidad).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyectos(entidad), HttpStatus.OK);
    }

    @GetMapping("obtenerObjetivo")
    public ResponseEntity<List<Proyecto>> obtenerProyectos(@RequestParam String objetivo){
        if(objetivo == null || objetivo.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerProyectos(objetivo).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyectos(objetivo), HttpStatus.OK);
    }

    @GetMapping("obtenerUbicacion")
    public ResponseEntity<List<Proyecto>> obtenerProyectosPorUbicacion(@RequestParam Integer ubicacion){
        if(ubicacion == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerProyectosPorUbicacion(ubicacion).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyectosPorUbicacion(ubicacion), HttpStatus.OK);
    }

    @GetMapping("obtenerAprobacion")
    public ResponseEntity<List<Proyecto>> obtenerProyectosPorAprobacion(@RequestParam boolean aprobacion){
        if(servicio.obtenerProyectosPorAprobacion(aprobacion).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyectosPorAprobacion(aprobacion), HttpStatus.OK);
    }

    @GetMapping("obtener")
    public ResponseEntity<Proyecto> obtenerProyecto(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerProyecto(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyecto(id), HttpStatus.OK);
    }

    @GetMapping("obtenerCodigo")
    public ResponseEntity<Proyecto> obtenerProyectoPorCodigo(@RequestParam Integer codigo){
        if(codigo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!servicio.proyectoExistePorCodigo(codigo)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerProyectoPorCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Proyecto> crearProyecto(@RequestBody Proyecto proyecto){
        if(proyecto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.proyectoExiste(proyecto.getId()) || servicio.proyectoExistePorCodigo(proyecto.getCodigo()) || servicio.proyectoExiste(proyecto)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearProyecto(proyecto), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Proyecto> actualizarProyecto(@RequestParam Integer id, @RequestBody Proyecto proyecto){
        if(id == null || proyecto == null || !id.equals(proyecto.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarProyecto(id, proyecto) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> borrarEntidad(@RequestParam Integer id){
        if( id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarProyecto(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

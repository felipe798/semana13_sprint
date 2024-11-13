package pe.edu.tecsup.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.tecsup.back.Exception.ResourceNotFoundException;
import pe.edu.tecsup.back.model.Producto;
import pe.edu.tecsup.back.repository.ProductoRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("productos")
    public List<Producto> ListarProductos(){
        return productoRepository.findAll();
    }

    @PostMapping("/productos")
    public Producto guardarProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> ListarProductoPorId(@PathVariable long id){
        Producto producto = productoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("El producto no existe"+ id));
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> ActualizarProducto(@PathVariable long id, @RequestBody Producto productoRequest){
        Producto producto = productoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("El producto no existe"+ id));

        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setPrecio(productoRequest.getPrecio());
        producto.setCantidad(productoRequest.getCantidad());

        Producto productoActualizado= productoRepository.save(producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarProducto(@PathVariable long id){
        Producto producto = productoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("El producto no existe"+ id));

        productoRepository.delete(producto);
        Map<String, Boolean> response = new HashMap<>();
        response.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

package pe.edu.tecsup.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.tecsup.back.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}

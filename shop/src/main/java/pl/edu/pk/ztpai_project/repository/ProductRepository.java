package pl.edu.pk.ztpai_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pk.ztpai_project.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}


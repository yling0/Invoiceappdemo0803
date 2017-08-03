package me.yling.demo.repositories;

import me.yling.demo.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {

}

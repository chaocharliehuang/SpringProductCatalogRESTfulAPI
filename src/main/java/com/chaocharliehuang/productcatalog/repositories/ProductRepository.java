package com.chaocharliehuang.productcatalog.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chaocharliehuang.productcatalog.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}

package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    private long exintingId;
    private long nonexintingId;
    @Autowired
    private ProductRepository repository;

    @BeforeEach
    void setUp() throws Exception {
       exintingId = 1L;
       nonexintingId = 1000L;
    }

    @Test
    public void deleteShouldDeleteObjectWhereIdExists(){

        repository.deleteById(exintingId);
        Optional<Product> result = repository.findById(exintingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhereIdDoesNotExists(){
        
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonexintingId);
        });
    }

}

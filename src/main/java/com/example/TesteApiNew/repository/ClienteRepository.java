package com.example.TesteApiNew.repository;

import com.example.TesteApiNew.model.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
}

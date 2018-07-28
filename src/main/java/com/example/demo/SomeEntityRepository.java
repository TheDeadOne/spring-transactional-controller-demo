package com.example.demo;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

public interface SomeEntityRepository extends CrudRepository<SomeEntity, Long> {}

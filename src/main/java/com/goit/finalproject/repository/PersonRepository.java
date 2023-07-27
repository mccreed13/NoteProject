package com.goit.finalproject.repository;

import com.goit.finalproject.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}

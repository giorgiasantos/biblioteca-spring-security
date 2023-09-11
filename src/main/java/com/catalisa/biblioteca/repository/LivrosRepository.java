package com.catalisa.biblioteca.repository;

import com.catalisa.biblioteca.model.LivrosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //significa que nosso repositório é uma interface
public interface LivrosRepository extends JpaRepository <LivrosModel, Long> {
}

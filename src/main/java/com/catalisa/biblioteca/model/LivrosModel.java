package com.catalisa.biblioteca.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Table(name = "TB_LIVROS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class LivrosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera os ids de forma crescente
    private Long id;

    @Column(length = 50, nullable = false) //não deixa o nome ser nulo
    private String nome;

    @Column(length = 50, nullable = false)
    private String autor;

    @Column(length = 8, nullable = false)
    private LocalDate data;

    @Column(length = 15, nullable = false)
    private String codigo;
}

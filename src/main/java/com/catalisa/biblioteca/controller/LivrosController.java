package com.catalisa.biblioteca.controller;


import com.catalisa.biblioteca.model.LivrosModel;
import com.catalisa.biblioteca.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.Endpoint;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController //possui os 4 endpoints principais
public class LivrosController {
    @Autowired
    LivrosService livrosService;

    //Endpoints de CONSULTA
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "/livros")
    public List<LivrosModel> buscarTodosLivros(){
        return livrosService.buscarTodos();
    }

    //pega uma lista e procura dentro dela uma informação
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping(path = "/livros/{id}")
    public ResponseEntity<?> buscaLivroPorId(@PathVariable Long id){
        Optional<LivrosModel> livro = livrosService.buscaLivrosPorId(id);
        if(livro.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado!");
        }
        return ResponseEntity.ok().body(livro.get());
    }

    //Endpoints de INSERÇÃO

    //Requisição POST - insere algum dado dentro do banco
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping(path = "/livros")
    public ResponseEntity<?> cadastrarNovoLivro(@RequestBody LivrosModel livrosModel){ //colocamos o livrosModel como parametro para saber o que presiamos colocar no corpo da requisição

        LivrosModel livro = livrosService.cadastrarLivro(livrosModel);
        return ResponseEntity.ok().body("Novo livro cadastrado com sucesso! \n" + livro);
    }

    //Endpoint de ALTERAÇÃO
    //Requisição PUT - altera algum dado dentro do banco
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping(path = "/livros/{id}")
    public ResponseEntity<LivrosModel> alteraLivro(@PathVariable Long id, @RequestBody LivrosModel livrosModel){
        LivrosModel livro = livrosService.alterarLivro(id, livrosModel);
        return ResponseEntity.ok().body(livro);
    }

    //Endpoint de deleção
    //Requisição DELETE - deleta algum dado dentro do banco
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path = "/livros/{id}")
    public void deletaLivros(@PathVariable Long id){
        livrosService.deletarLivro(id);
    }
}

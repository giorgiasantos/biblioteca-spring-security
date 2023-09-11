package com.catalisa.biblioteca.service;

import com.catalisa.biblioteca.model.LivrosModel;
import com.catalisa.biblioteca.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {
    @Autowired
    LivrosRepository livrosRepository;

    //método que busca todos os Livros no banco
    public List<LivrosModel> buscarTodos() {
        return livrosRepository.findAll();
    }

    //método que busca Livros por ID
    public Optional<LivrosModel> buscaLivrosPorId(Long id){
        return livrosRepository.findById(id);
    }

    //método que cadastra um novo livro
    public LivrosModel cadastrarLivro(LivrosModel livrosModel){
        return livrosRepository.save(livrosModel);
    }

    //método que altera um livro já existente
    public LivrosModel alterarLivro(Long id, LivrosModel livrosModel){
        LivrosModel livros = buscaLivrosPorId(id).get();

        if(livrosModel.getNome() != null){
            livros.setNome(livrosModel.getNome());
        }
        if(livrosModel.getAutor() != null){
            livros.setAutor(livrosModel.getAutor());
        }
        if(livrosModel.getData() != null){
            livros.setData(livrosModel.getData());
        }
        if(livrosModel.getCodigo() != null){
            livros.setCodigo(livrosModel.getCodigo());
        }
        return livrosRepository.save(livros);
    }

    //método que deleta um livro já existente no banco
        public void deletarLivro(Long id){
        livrosRepository.deleteById(id);

    }
}

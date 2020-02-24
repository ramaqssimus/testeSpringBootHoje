package br.com.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.forum.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

	List<Autor> findByNome(String nome);
	
	


}

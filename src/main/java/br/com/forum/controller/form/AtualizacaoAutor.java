package br.com.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.forum.modelo.Autor;
import br.com.forum.repository.AutorRepository;

public class AtualizacaoAutor {
	
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	
	public Autor atualizar(Long id, AutorRepository autorRepository) {
		Autor autor = autorRepository.getOne(id);
		
		autor.setNome(this.nome);
		
		return autor;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}

package br.com.forum.controller.dto;

import br.com.forum.modelo.Autor;

public class DetalharAutorId {
	
	private Long id;
	private String nome;
	
	
	public DetalharAutorId(Autor autor) {
		
		
		this.id = autor.getId();
		this.nome = autor.getNome();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	

}

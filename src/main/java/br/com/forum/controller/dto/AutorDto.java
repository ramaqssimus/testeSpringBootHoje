package br.com.forum.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.forum.modelo.Autor;

public class AutorDto {
	
	private Long id;
	private String nome;
	
	public AutorDto(Autor autor) {
		
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

	public static List<AutorDto> converter(List<Autor> autor) {
		return autor.stream().map(AutorDto::new).collect(Collectors.toList());
	}

}

package br.com.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.forum.modelo.Autor;

public class AutorForm {
	
	
	private Long id;
	@NotNull @NotEmpty @Length(min = 5)
	private String nome;
	
	
	
	public AutorForm(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
    public Autor converter(Autor autor) {
    	return new Autor(id, nome);	
    }
	

}

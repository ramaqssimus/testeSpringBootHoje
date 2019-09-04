package br.com.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.controller.dto.AutorDto;
import br.com.forum.controller.dto.DetalharAutorId;
import br.com.forum.controller.form.AtualizacaoAutor;
import br.com.forum.controller.form.AutorForm;
import br.com.forum.modelo.Autor;
import br.com.forum.repository.AutorRepository;

@RestController
@RequestMapping("/autor")
public class AutorControler {
	
	@Autowired
	private AutorRepository autorRepository;
	
	
	@GetMapping
	public List<AutorDto> lista(String nome) {
		if (nome == null) {
			List<Autor> autor = autorRepository.findAll();
			return AutorDto.converter(autor);
		} else {
			List<Autor> autor = autorRepository.findByNome(nome);
			return AutorDto.converter(autor);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalharAutorId> detalhar(@PathVariable Long id) {
		Optional<Autor> autor = autorRepository.findById(id);
		if (autor.isPresent()) {
			return ResponseEntity.ok(new DetalharAutorId(autor.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<AutorDto> salvar(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder){
		
		Autor autor = new Autor();
		autor.setId(form.getId());
		autor.setNome(form.getNome());
		autorRepository.save(autor);
		
		URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).body(new AutorDto(autor));
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<AutorDto> atualizar(@PathVariable Long id, @RequestBody AtualizacaoAutor form){
		Optional<Autor> optional = autorRepository.findById(id);
		if(optional.isPresent()) {
			Autor autor = form.atualizar(id, autorRepository);
			return ResponseEntity.ok(new AutorDto(autor));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Autor> optional = autorRepository.findById(id);
		if (optional.isPresent()) {
			autorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}

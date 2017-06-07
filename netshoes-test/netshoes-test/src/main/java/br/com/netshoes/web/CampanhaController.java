package br.com.netshoes.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.netshoes.domain.Campanha;
import br.com.netshoes.domain.Message;
import br.com.netshoes.exception.ResourceNotFoundException;
import br.com.netshoes.service.CampanhaService;


@RestController
@RequestMapping(value="/campanha")
public class CampanhaController {
	
	private static final Logger log = LoggerFactory.getLogger(CampanhaController.class);

	@Autowired
	private CampanhaService service;

	//outra alternativa seria usar regex no mapeamento do value, assim uma chamada invalida geraria 404
	@RequestMapping(value = "/{chave}", method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha findPorChave(@PathVariable String chave) {
		Optional<Campanha> optCampanha = service.findCampanhaPorChave(chave);
		if (!optCampanha.isPresent()) {
			log.error("Campanha {} não encontrado", chave);
			throw new ResourceNotFoundException();
		}
		return optCampanha.get();
	}
	
	@RequestMapping(method = RequestMethod.GET,
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Campanha findPorChave(@RequestBody Campanha filtro) {
		//TODO seria possivel implementar uma consulta levando outros campos do Campanh
		return findPorChave(filtro.getChave()); 
	}
	
	@RequestMapping(value = "/health", method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Message health() {
		return new Message("ok");
	}
	
}

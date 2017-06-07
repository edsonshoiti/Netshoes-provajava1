package br.com.netshoes.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.netshoes.domain.Timecoracao;
import br.com.netshoes.domain.TimecoracaoId;
import br.com.netshoes.domain.Message;
import br.com.netshoes.exception.ResourceNotFoundException;
import br.com.netshoes.service.EnderecoService;

import com.google.common.base.Preconditions;


@RestController
@RequestMapping(value="/timecoracao")
public class EnderecoController {
	
	private static final Logger log = LoggerFactory.getLogger(EnderecoController.class);

	private static final int TAMANHO_PAGINA = 10;
	
	@Autowired
	private EnderecoService service;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Timecoracao findAll(@RequestParam(defaultValue="0") Integer pagina) {
		Pageable page = new PageRequest(pagina, TAMANHO_PAGINA);
		Timecoracao l = service.findTimecoracaoPorPagina(page);
		if (log.isDebugEnabled()) {
			log.debug("Consulta de Endereco retornando {} registro(s).", l.getItems() );
		}
		return l;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Timecoracao findPorId(@PathVariable Long id) {
		Optional<Timecoracao> optEnd = service.findTimecoracaoPorId(id);
		if (!optEnd.isPresent()) {
			log.error("Endereco com id {}, não encontrado", id);
			throw new ResourceNotFoundException();
		}
		return optEnd.get();
	}
	
	@RequestMapping(method = RequestMethod.POST,
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public TimecoracaoId save(@RequestBody Timecoracao timecoracao) {
		Preconditions.checkArgument(timecoracao.isNew(), 
				"Utilize o método POST para inserir o Endereço");
		Timecoracao persistido = service.persiste(timecoracao);
		return new TimecoracaoId(persistido.getId());
	}
	
	@RequestMapping(method = RequestMethod.PUT,
			consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public Message update(@RequestBody Timecoracao timecoracao) {
		Preconditions.checkArgument(!timecoracao.isNew(), 
				"Utilize o método PUT para atualizar o Endereço");
		service.persiste(timecoracao);
		return new Message("ok");
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public Message delete(@PathVariable Long id) {
		service.delete(id);
		return new Message("ok");
	}
	
	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public Message health() {
		return new Message("ok");
	}
	
}

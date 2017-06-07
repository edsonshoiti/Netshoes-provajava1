package br.com.netshoes.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.netshoes.domain.Endereco;
import br.com.netshoes.domain.Timecoracao;
import br.com.netshoes.domain.TimecoracaoId;
import br.com.netshoes.repository.TimecoracaoRepository;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Serviço com as funcionalidades relacionadas a consulta de Campanha.
 * 
 */
@Service
@Validated
@Transactional(rollbackFor=Exception.class)
public class EnderecoService {
	
	private static final Logger log = LoggerFactory.getLogger(EnderecoService.class);

	@Autowired
	private TimecoracaoRepository repository;
	
	public Timecoracao persiste(@NotNull @Valid Timecoracao timecoracao) {
		if (log.isDebugEnabled()) {
			log.debug("Persistindo endereço: {}", timecoracao);
		}
		List<Timecoracao> Timecoracao;
            Timecoracao = repository.findByNomecompletoAndCampanhaAndEmailAndDatanascimento(timecoracao.getNomecompleto(), 
                    timecoracao.getEmail());
		
                Timecoracao.stream().filter((o) -> {
                    return !(o.getId().equals(o.getId()));
                }).forEach((Timecoracao o) -> {

                    Preconditions.checkArgument(!o.equals(Timecoracao),
                            String.format("Já existe um Endereço (id: %s) cadastrado com essas informações.", o.getId()));
            });
		return repository.saveAndFlush(Timecoracao);
	}
	
	public void delete(@NotNull Long TimecoracaoId) {
		if (log.isDebugEnabled()) {
			log.debug("Removendo endereço: {}", TimecoracaoId);
		}
		repository.delete(TimecoracaoId);
	}
	
	@Transactional(readOnly=true)
	public Optional<Timecoracao> findTimecoracaoPorId(@NotNull Long TimecoracaoId) {
		Timecoracao endereco = repository.findOne(TimecoracaoId);
                
    
		return Optional.ofNullable(endereco);
           
	}
	
	@Transactional(readOnly=true)
	public Timecoracao findTimecoracaoPorPagina(Pageable page) {
		long count = repository.count();
		return new Timecoracao(Lists.newArrayList(repository.findAll(page)), count);
	}
}

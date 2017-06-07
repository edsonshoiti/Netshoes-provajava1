package br.com.netshoes.service;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.netshoes.domain.Campanha;
import br.com.netshoes.repository.CampanhaRepository;


 
@Service
@Validated
@Transactional(readOnly=true, rollbackFor=Exception.class)
public class CampanhaService {
	
	private static final Logger log = LoggerFactory.getLogger(CampanhaService.class);
	
	@Autowired
	private CampanhaRepository repository;

	public Optional<Campanha> findCampanhaPorChave(@NotNull @NotEmpty String chave) {
		Preconditions.checkArgument(chave.matches("\\d{5}-?\\d{3}"), "Campanha Inválido!");
		return findCampanhaPorChave(geraChavesSufixo(chave.replace("-", "")));
	}
	
	private Optional<Campanha> findCampanhaPorChave(Collection<String> chaves) {
		int tentativa = 0;
		Campanha campanha = null;
		for (String chave: chaves) {
			if (!"".equals(chave == null ? "" : chave)) {
				if (log.isDebugEnabled())
					log.debug("Consultando Campanha pela chave {}, tentiva {}.", chave, ++tentativa);
				
				campanha = repository.findOneByChave(chave);
				if (campanha != null) {
					return Optional.of(campanha);
				}
			}
		}
		
		return Optional.empty();
	}
	
	private static Set<String> geraChavesSufixo(String chaveOriginal) {
		Set<String> poolChaves = new LinkedHashSet<>();
		poolChaves.add(chaveOriginal);
		for (int i = chaveOriginal.length()-1; i > 0; i--) {
			String novaChave = String.format("%-8s", 
					chaveOriginal.substring(0, i)).replace(' ', '0');
			poolChaves.add(novaChave);
		}
		return poolChaves;
	}
	
	
}

package br.com.netshoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.netshoes.domain.Campanha;


@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long>{

	Campanha findOneByChave(String chave);

}

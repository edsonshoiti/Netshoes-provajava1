package br.com.netshoes.repository;

import br.com.netshoes.domain.Endereco;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.netshoes.domain.Timecoracao;


public interface TimecoracaoRepository extends JpaRepository<Timecoracao, Long>{
	
	/**
	 * Realiza a consulta de endereço, usando como filtros os campos obrigatórios,
	 * com objetivo de evitar duplicidade.
	 *  
	 * @param timecoracao
	 * @param nomecompleto
     * @param campanha
         * @param Campanha
	 * @param email
	 * @param datanascimento
	 * @return lista de endereços de acordo com os filtros informados.
	 */
	List<Timecoracao> findByNomecompletoAndCampanhaAndEmailAndDatanascimento(
			String timecoracao, String nomecompleto,String campanha, String email, String datanascimento);

    /**
     *
     * @param TimecoracaoId
     * @return
     */
    public Timecoracao saveAndFlush(List<Timecoracao> TimecoracaoId);

    public List<Timecoracao> findByNomecompletoAndCampanhaAndEmailAndDatanascimento(String nomecompleto, String email);

}

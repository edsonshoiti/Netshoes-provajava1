package br.com.netshoes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.ArrayList;



@Entity
public class Timecoracao extends BaseEntity {
	
        @Size(max=60, message="Chave tem capacidade de 8 caracteres (numéricos).")
	@Pattern(regexp="\\d{5}-?\\d{3}", message="Cep deve seguir o formato \\d{5}-?\\{d3}")
	@NotNull(message="Chave é obrigatória!")
	@Column(nullable = false, unique=true, length=8)
	private String chave;
    
	@Size(min=3, max=200, message="Time tem capacidade de 3 a 200 caracteres.")
	@NotNull(message="Time é obrigatório!")
	@Column(nullable = false, length=200)
	private String time;
	
	@NotNull(message="Número é obrigatório!")
	@Size(min=1, max=60, message="Número tem capacidade de até 60 caracteres.")
	@Column(nullable = false, length=60)
	private String datanascimento;
	
	@Size(max=60, message="Complemento tem capacidade de até 60 caracteres.")
	@Column(length=60)
	private String nomecompleto;
	
	@Size(max=60, message="Bairro tem capacidade de até 60 caracteres.")
	@Column(length=60)
	private String email;
    private String campanha;

    public Timecoracao() {
    }

    public Timecoracao(ArrayList<Timecoracao> newArrayList, long count) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento (String datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getNomecompleto() {
		return nomecompleto;
	}

	public void setNomecompleto(String nomecompleto) {
		this.nomecompleto = nomecompleto;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public void preencheComCampanha(Campanha campanha) {
		Preconditions.checkNotNull(campanha, "Informe o Time do Coração!");
		this.time = campanha.getTime();
		this.nomecompleto = campanha.getNomecompleto();
		this.email = campanha.getEmail();
                this.datanascimento = campanha.getDatanascimento();
		this.campanha = campanha.getChave();
	}

	@Override
	public String toString() {
		return "Endereco [time=" + time + ", datanascimento=" + datanascimento
				+ ", nomecompleto=" + nomecompleto + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.time, this.datanascimento, this.nomecompleto, 
				this.email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Timecoracao outro = (Timecoracao) obj;
		
		return Objects.equal(this.time, outro.time)
				&& Objects.equal(this.datanascimento, outro.datanascimento)
				&& Objects.equal(this.nomecompleto, outro.nomecompleto)
				&& Objects.equal(this.time, outro.time)
				&& Objects.equal(this.email, outro.email);
	}

    public Object getItems() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String timecoracao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}

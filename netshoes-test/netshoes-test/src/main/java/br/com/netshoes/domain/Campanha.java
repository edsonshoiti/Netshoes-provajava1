package br.com.netshoes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.google.common.base.Objects;

@Entity
public class Campanha extends BaseEntity {
	
	@Size(max=60, message="Chave tem capacidade de 8 caracteres (numéricos).")
	@Pattern(regexp="\\d{5}-?\\d{3}", message="Cep deve seguir o formato \\d{5}-?\\{d3}")
	@NotNull(message="Chave é obrigatória!")
	@Column(nullable = false, unique=true, length=8)
	private String chave;
	
	@Size(min=3, max=200, message="Logradouro tem capacidade de 3 a 200 caracteres.")
	@NotNull(message="Logradouro é obrigatório!")
	@Column(nullable = false, length=200)
	private String time;
	
	@Size(max=60, message="Complemento tem capacidade de até 60 caracteres.")
	@Column(length=60)
	private String nomecompleto;
	
	@Size(max=60, message="Cidade tem capacidade de até 100 caracteres.")
	@NotNull(message="Cidade é obrigatória!")
	@Column(nullable = false, length=100)
	private String email;
	
	@Size(max=60, message="Estado tem capacidade de 2 caracteres.")
	@NotNull(message="Estado é obrigatório!")
	@Column(nullable = false, length=2)
	private String campanha;
	
	public Campanha() {
	}
	
	public Campanha(String chave) {
		this.chave = chave;
	}
	
	public Campanha(String time, 
			String bairro, String cidade, String estado) {
		this.time = time;
		this.nomecompleto = nomecompleto;
		this.email = email;
		this.campanha = campanha;
	}

	public String getChave() {
		return chave;
	}

	public String gettime() {
		return time;
	}

	public String getNomecompleto() {
		return nomecompleto;
	}

	public String getEmail() {
		return email;
	}

	public String getCampanha() {
		return campanha;
	}

	@Override
	public String toString() {
		return "Campanha [chave=" + chave + ", time=" + time
				+ ", nomecompleto=" + nomecompleto + ", email=" + email + ", campanha="
				+ campanha + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.chave, this.time, 
				this.nomecompleto, this.email, this.campanha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Campanha outro = (Campanha) obj;
		
		return Objects.equal(this.chave, outro.chave)
				&& Objects.equal(this.time, outro.time)
				&& Objects.equal(this.nomecompleto, outro.nomecompleto)
				&& Objects.equal(this.email, outro.email)
				&& Objects.equal(this.campanha, outro.campanha);
	}

    String getTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    String getDatanascimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

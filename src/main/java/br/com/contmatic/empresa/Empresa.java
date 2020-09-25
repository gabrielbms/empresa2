package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_SIZE;
import static br.com.contmatic.util.Constantes.CNPJ_VAZIO;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.SITE_MAX_SIZE;
import static br.com.contmatic.util.Constantes.SITE_MIN_SIZE;
import static br.com.contmatic.util.Constantes.SITE_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_SITE_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_SITE_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.NOME;
import static br.com.contmatic.util.RegexType.URL;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CNPJ;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Empresa.
 * 
 * @author gabriel.santos
 */
public class Empresa {

	/** The cnpj. */
	@CNPJ(message = "O CNPJ do funcionario está inválido")
	@NotBlank(message = "O campo CPF não pode estar nulo")
	private String cnpj;

	/** The nome. */
	@NotBlank(message = "O campo nome não pode estar vazio")
	@Pattern(regexp = NOME, message = "O nome da empresa está incorreto")
	@Size(min = 2, max = 100, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
	private String nome;

	/** The site. */
	@URL
	@Length(min = 5, max = 60)
	@NotBlank(message = "O campo site não pode estar nulo")
	@Pattern(regexp = URL, message = "O site da empresa está inválido")
	private String site;

	/** The telefones. */
	@Valid
	@NotNull(message = "O telefone da empresa não pode ser nulo")
	@Size.List({ @Size(min = 1, message = "os telefones da empresa não devem ser menor que um"),
			@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
	private Set<Telefone> telefones;

	/** The enderecos. */
	@Valid
	@NotNull(message = "O endereço da empresa está vazio")
	@Size.List({ @Size(min = 1, message = "Tem que cadastrar pelo menos um endereço"),
			@Size(max = 3, message = "A quantidade máxima de endeços é {max} endereços") })
	private Set<Endereco> enderecos;

	/**
	 * Instantiates a new empresa.
	 *
	 * @param cnpj     the cnpj
	 * @param nome     the nome
	 * @param telefone the telefone
	 * @param endereco the endereco
	 */
	public Empresa(String cnpj, String nome, @Valid Set<Telefone> telefone, @Valid Set<Endereco> endereco) {
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setTelefones(telefone);
		this.setEnderecos(endereco);
	}

	/**
	 * Instantiates a new empresa.
	 */
	public Empresa() {

	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.validaCnpjIncorreto(cnpj);
		this.cnpj = cnpj;
	}
	
	private void validaCnpjIncorreto(String cnpj) {
		this.validaCnpjNulloOuVazio(cnpj);
		this.validaCnpjComTamanhoMenor(cnpj);
		this.validaCnpjComTamanhoMaior(cnpj);
	}

	private void validaCnpjComTamanhoMaior(String cnpj) {
		if (cnpj.length() > CNPJ_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CNPJ_GRANDE_DEMAIS);
		}
	}

	private void validaCnpjComTamanhoMenor(String cnpj) {
		if (cnpj.length() < CNPJ_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CNPJ_PEQUENO_DEMAIS);
		}
	}
	
	private void validaCnpjNulloOuVazio(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty()) {
			throw new IllegalArgumentException(CNPJ_VAZIO);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.validaNomeIncorreto(nome);
		this.nome = nome;
	}
	
	private void validaNomeIncorreto(String nome) {
		this.validaNomeNulloOuIncorreto(nome);
		this.validaNomeMenorQueOTamanhoMinimo(nome);
		this.validaNomeMaiorQueOTamanhoMaximo(nome);
	}

	private void validaNomeMaiorQueOTamanhoMaximo(String nome) {
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}

	private void validaNomeMenorQueOTamanhoMinimo(String nome) {
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
	}

	private void validaNomeNulloOuIncorreto(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.validaSiteIncorreto(site);
		this.site = site;
	}
	
	private void validaSiteIncorreto(String site) {
		this.validaSiteNulloOuIncorreto(site);
		this.validaSiteMenorQueOTamanhoMinimo(site);
		this.validaSiteMaiorQueOTamanhoMaximo(site);
	}

	private void validaSiteMaiorQueOTamanhoMaximo(String site) {
		if (nome.length() > SITE_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_SITE_GRANDE_DEMAIS);
		}
	}

	private void validaSiteMenorQueOTamanhoMinimo(String site) {
		if (nome.length() < SITE_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_SITE_PEQUENO_DEMAIS);
		}
	}

	private void validaSiteNulloOuIncorreto(String site) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(SITE_VAZIO);
		}
	}

	public @Valid Set<Telefone> getTelefone() {
		return telefones;
	}
	
	public void setTelefones(Set<Telefone> telefone) {
		this.validaTelefoneNullo(telefone);
		this.telefones = telefone;
	}
	
	private void validaTelefoneNullo(Set<Telefone> telefone) {
		if (telefone == null) {
			throw new IllegalArgumentException(TELEFONE_VAZIO);
		}
	}

	public @Valid Set<Endereco> getEndereco() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> endereco) {
		this.validaEnderecoNullo(endereco);
		this.enderecos = endereco;
	}
	
	private void validaEnderecoNullo(Set<Endereco> endereco) {
		if (endereco == null) {
			throw new IllegalArgumentException(ENDERECO_VAZIO);
		}
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}

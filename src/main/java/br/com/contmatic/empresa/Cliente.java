package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.BOLETO_NEGATIVO;
import static br.com.contmatic.util.Constantes.CPF_INVALIDO;
import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.CPF_VAZIO;
import static br.com.contmatic.util.Constantes.EMAIL_MAX_SIZE;
import static br.com.contmatic.util.Constantes.EMAIL_MIN_SIZE;
import static br.com.contmatic.util.Constantes.EMAIL_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_INVALIDO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_EMAIL_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_EMAIL_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.EMAIL;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Cliente.
 * 
 * @author gabriel.santos
 */
public class Cliente {

	/** The cpf. */
	@CPF(message = CPF_INVALIDO)
	@NotNull(message = CPF_VAZIO)
	private String cpf;

	/** The nome. */
	@NotBlank(message = NOME_VAZIO)
	@Pattern(regexp = RegexType.NOME, message = NOME_INVALIDO)
	@Size(min = 2, max = 80, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
	private String nome;

	/** The email. */
	@Email(message = "O email do cliente está invalido")
	@NotBlank(message = "O campo e-mail não pode estar vazio")
	@Pattern(regexp = EMAIL, message = "O email do cliente está invalido")
	@Size(min = 5, max = 100, message = "O e-mail do funcionario pode ter no máximo {max} caracteres")
	private String email;

	/** The telefones. */
	@Valid
	@NotNull(message = "O telefone do cliente não pode ser vazio")
	@Size.List({ @Size(min = 1, message = "os telefones do cliente não devem ser menor que um"),
			@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
	private Set<Telefone> telefones;

	/** The boleto. */
	@Min(value = 1, message = "O valor do boleto não pode ser negativo")
	@NotEmpty(message = "O campo boleto não pode estar vazio")
	private BigDecimal boleto;

	/**
	 * Instantiates a new cliente.
	 *
	 * @param cpf      the cpf
	 * @param nome     the nome
	 * @param telefone the telefone
	 * @param boleto   the boleto
	 */
	public Cliente(String cpf, String nome, @Valid Set<Telefone> telefone, BigDecimal boleto) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setTelefones(telefone);
		this.setBoleto(boleto);
	}

	/**
	 * Instantiates a new cliente.
	 */
	public Cliente() {

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.validaCpfIncorreto(cpf);
		this.cpf = cpf;
	}

	private void validaCpfIncorreto(String cpf) {
		this.validaCpfNulloOuVazio(cpf);
		this.validaCpfComTamanhoMenor(cpf);
		this.validaCpfComTamanhoMaior(cpf);
	}

	private void validaCpfComTamanhoMaior(String cpf) {
		if (cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_GRANDE_DEMAIS);
		}
	}

	private void validaCpfComTamanhoMenor(String cpf) {
		if (cpf.length() < CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_PEQUENO_DEMAIS);
		}
	}

	private void validaCpfNulloOuVazio(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException(CPF_VAZIO);
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
		this.validaNomeNulloOuVazio(nome);
		this.validaNomeMenorQueOTamanhoMinimo(nome);
		this.validaNomeMaiorQueOTamanhoMinimo(nome);
	}

	private void validaNomeMaiorQueOTamanhoMinimo(String nome) {
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}

	private void validaNomeMenorQueOTamanhoMinimo(String nome) {
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
	}

	private void validaNomeNulloOuVazio(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.validaEmailIncorreto(email);
		this.email = email;
	}
	
	private void validaEmailIncorreto(String email) {
		this.validaEmailNulloOuVazio(email);
		this.validaEmailMenorQueOTamanhoMinimo(email);
		this.validaEmailMaiorQueOTamanhoMinimo(email);
	}

	private void validaEmailMaiorQueOTamanhoMinimo(String nome) {
		if (nome.length() > EMAIL_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_EMAIL_GRANDE_DEMAIS);
		}
	}

	private void validaEmailMenorQueOTamanhoMinimo(String nome) {
		if (nome.length() < EMAIL_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_EMAIL_PEQUENO_DEMAIS);
		}
	}

	private void validaEmailNulloOuVazio(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(EMAIL_VAZIO);
		}
	}

	public @Valid Set<Telefone> getTelefone() {
		return telefones;
	}

	public void setTelefones(Set<Telefone> telefone) {
		this.validaTelefoneNullo(telefone);
		this.telefones = telefone;
	}
	
	private void validaTelefoneNullo(Set<Telefone> telefones) {
		if (telefones == null) {
			throw new IllegalArgumentException(TELEFONE_VAZIO);
		}
	}

	public BigDecimal getBoleto() {
		return boleto;
	}

	public void setBoleto(BigDecimal boleto) {
		this.validaValorBoleto(boleto);
		this.boleto = boleto;
	}
	
	private void validaValorBoleto(BigDecimal boleto) {
		if (boleto.doubleValue() < 1) {
			throw new IllegalArgumentException(BOLETO_NEGATIVO);
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

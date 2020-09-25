package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CPF_SIZE;
import static br.com.contmatic.util.Constantes.CPF_VAZIO;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.IDADE_MINIMA;
import static br.com.contmatic.util.Constantes.IDADE_MINIMA_MENSAGEM;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.SALARIO_MINIMO;
import static br.com.contmatic.util.Constantes.SALARIO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CPF_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;
import static br.com.contmatic.util.RegexType.NOME;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.LocalDate;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;

/**
 * The Class Funcionario.
 * 
 * @author gabriel.santos
 */ 
public class Funcionario {

	/** The cpf. */
	@CPF(message = "O CPF do cliente está inválido")
	@NotNull(message = "O campo CPF não pode estar nulo")
	private String cpf;

	/** The nome. */
	@NotBlank(message = "O campo nome não pode estar vazio")
	@Pattern(regexp = NOME, message = "O nome do funcionário está incorreto")
	@Size(min = 2, max = 80, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
	private String nome;

	/** The idade. */
	@NotEmpty
	@Min(value = 1, message = "A idade do funcionario não pode ser menor que 1")
	private Integer idade;

	/** The telefones. */
	@Valid
	@NotNull(message = "O telefone do funcionario não pode ser vazio")
	@Size.List({ @Size(min = 1, message = "os telefones do funcionario não devem ser menor que um"),
			@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
	private Set<Telefone> telefones;

	/** The enderecos. */
	@Valid
	@NotNull(message = "O endereço da empresa está vazio")
	@Size.List({ @Size(min = 1, message = "Tem que cadastrar pelo menos um endereço"),
			@Size(max = 3, message = "A quantidade máxima de endeços é {max} endereços") })
	private Set<Endereco> enderecos;

	/** The salario. */
	@Min(value = 1, message = "O salário do funcionário não pode ser negativo")
	private BigDecimal salario;

	/** The data contratacao. */
	@NotNull(message = "A data de contratação do funcionario não deve estar nula")
	@Past(message = "A data de contratação não pode ser maior que a data atual")
	private LocalDate dataContratacao;

	/** The data salario. */
	@Future(message = "A data do salario deve ser maior que a data atual")
	@NotNull(message = "A data do salário do funcionario não deve estar nula")
	private LocalDate dataSalario;

	/**
	 * Instantiates a new funcionario.
	 *
	 * @param cpf     the cpf
	 * @param nome    the nome
	 * @param salario the salario
	 */
	public Funcionario(String cpf, String nome, BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setSalario(salario);
	}

	/**
	 * Instantiates a new funcionario.
	 *
	 * @param cpf      the cpf
	 * @param nome     the nome
	 * @param idade    the idade
	 * @param telefone the telefone
	 * @param endereco the endereco
	 * @param salario  the salario
	 */
	public Funcionario(String cpf, String nome, int idade, @Valid Set<Telefone> telefone, @Valid Set<Endereco> endereco,
			BigDecimal salario) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setIdade(idade);
		this.setTelefones(telefone);
		this.setEnderecos(endereco);
		this.setSalario(salario);
	}

	/**
	 * Instantiates a new funcionario.
	 */
	public Funcionario() {

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

	private void validaCpfNulloOuVazio(String cpf) {
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException(CPF_VAZIO);
		}
	}

	private void validaCpfComTamanhoMenor(String cpf) {
		if (cpf.length() < CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_PEQUENO_DEMAIS);
		}
	}

	private void validaCpfComTamanhoMaior(String cpf) {
		if (cpf.length() > CPF_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_CPF_GRANDE_DEMAIS);
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.validaIdade(idade);
		this.idade = idade;
	}
	
	private void validaIdade(int idade) {
		if (idade < IDADE_MINIMA) {
			throw new IllegalArgumentException(IDADE_MINIMA_MENSAGEM);
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

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.validaSalario(salario);
		this.salario = salario;
	}
	
	private void validaSalario(BigDecimal salario) {
		if (salario.doubleValue() < SALARIO_MINIMO) {
			throw new IllegalArgumentException(SALARIO_MINIMO_MENSAGEM);
		}
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(LocalDate dataPagamento) {
		this.dataContratacao = dataPagamento;
	}

	public LocalDate getDataSalario() {
		return dataSalario;
	}

	public void setDataSalario(LocalDate dataSalario) {
		this.dataSalario = dataSalario;
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

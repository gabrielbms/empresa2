package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.CNPJ_SIZE;
import static br.com.contmatic.util.Constantes.CNPJ_VAZIO;
import static br.com.contmatic.util.Constantes.ENDERECO_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.PRODUTO_VAZIO;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_CNPJ_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;
import static br.com.contmatic.util.Constantes.TELEFONE_VAZIO;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Fornecedor.
 * 
 * @author gabriel.santos
 */
public class Fornecedor {

	/** The cnpj. */
	@CNPJ(message = "O CNPJ do funcionario está inválido")
	@NotBlank(message = "O campo CNPJ não pode estar nulo")
	private String cnpj;

	/** The nome. */
	@NotBlank(message = "O campo nome não pode estar vazio")
	@Pattern(regexp = RegexType.NOME, message = "O nome do fornecedor está incorreto")
	@Size(min = 2, max = 100, message = "O nome mínimo é de {min} caracteres e no máximo de {max} caracteres")
	private String nome;

	/** The produto. */
	@NotBlank(message = "O campo produto não pode estar nulo")
	@Length(min = 2, max = 80, message = "Tamanho do produto invalido")
	@Pattern(regexp = RegexType.NOME, message = "O nome do produto está incorreto")
	private Set<Produto> produto;

	/** The telefones. */
	@Valid
	@NotEmpty(message = "O telefone do fornecedor não pode ser vazio")
	@Size.List({ @Size(min = 1, message = "os telefones do fornecedor não devem ser menor que um"),
			@Size(max = 3, message = "O máximo de telefones que podem ser salvo totaliza {max} telefones") })
	private Set<Telefone> telefones;

	/** The enderecos. */
	@Valid
	@NotEmpty(message = "O endereço da empresa está vazio")
	@Size.List({ @Size(min = 1, message = "Tem que cadastrar pelo menos um endereço"),
			@Size(max = 3, message = "A quantidade máxima de endeços é {max} endereços") })
	private Set<Endereco> enderecos;

	/**
	 * Instantiates a new fornecedor.
	 *
	 * @param cnpj the cnpj
	 * @param nome the nome
	 */
	public Fornecedor(String cnpj, String nome) {
		this.setCnpj(cnpj);
		this.setNome(nome);
	}
	
	/**
	 * Instantiates a new fornecedor.
	 *
	 * @param cnpj the cnpj
	 * @param nome the nome
	 * @param telefone the telefones
	 * @param produto the produto
	 * @param endereco the enderecos
	 */
	public Fornecedor(String cnpj, String nome, Set<Telefone> telefone, Set<Produto> produto, Set<Endereco> endereco) {
		this.setCnpj(cnpj);
		this.setNome(nome);
		this.setTelefones(telefone);
		this.setProduto(produto);
		this.setEnderecos(endereco);
	}

	/**
	 * Instantiates a new fornecedor.
	 */
	public Fornecedor() {
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

	public Set<Produto> getProduto() {
		return produto;
	}

	public void setProduto(Set<Produto> produto) {
		this.validaProdutoNullo(produto);
		this.produto = produto;
	}

	private void validaProdutoNullo(Set<Produto> produto) {
		if (produto == null) {
			throw new IllegalArgumentException(PRODUTO_VAZIO);
		}
	}

	public @Valid Set<Telefone> getTelefone() {
		return telefones;
	}
	
	public void setTelefones(Set<Telefone> telefone) {
		Preconditions.checkArgument(telefone.size() < 2, "Somente pode possuir um telefone");
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
		Preconditions.checkArgument(endereco.size() < 2, "Somente pode possuir um endereco");
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

package br.com.contmatic.endereco;

import static br.com.contmatic.util.Constantes.BAIRRO_INCORRETO;
import static br.com.contmatic.util.Constantes.BAIRRO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.BAIRRO_MIN_SIZE;
import static br.com.contmatic.util.Constantes.CEP_INCORRETO;
import static br.com.contmatic.util.Constantes.CEP_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_INCORRETO;
import static br.com.contmatic.util.Constantes.CIDADE_MAX_SIZE;
import static br.com.contmatic.util.Constantes.CIDADE_MIN_SIZE;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_INCORRETO;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_MAX_SIZE;
import static br.com.contmatic.util.Constantes.COMPLEMENTO_MIN_SIZE;
import static br.com.contmatic.util.Constantes.ESTADO_VAZIO;
import static br.com.contmatic.util.Constantes.NUMERO_INCORRETO;
import static br.com.contmatic.util.Constantes.NUMERO_MINIMO;
import static br.com.contmatic.util.Constantes.RUA_INCORRETO;
import static br.com.contmatic.util.Constantes.RUA_MAX_SIZE;
import static br.com.contmatic.util.Constantes.RUA_MIN_SIZE;
import static br.com.contmatic.util.RegexType.LETRAS_NUMEROS;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.util.RegexType;

/**
 * The Class Endereco.
 * 
 * @author gabriel.santos
 */
public class Endereco {

	/** The cep. */
	@NotBlank(message = "O campo cep não pode estar nulo")
	@Pattern(regexp = RegexType.CEP, message = "O CEP do endereço está incorreto")
	private String cep;

	/** The rua. */
	@Size(min = 2, max = 80)
	@NotBlank(message = "O campo rua não pode estar nulo")
	@Pattern(regexp = LETRAS_NUMEROS, message = "A rua do endereço está incorreto")
	private String rua;

	/** The numero. */
	@NotBlank(message = "O campo numero não pode estar nulo")
	private Integer numero;

	/** The complemento. */
	@Size(min = 2, max = 80)
	@NotBlank(message = "O campo complemento não pode estar nulo")
	@Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "O complemento do endereço está incorreto")
	private String complemento;

	/** The bairro. */
	@Size(min = 2, max = 40)
	@NotBlank(message = "O campo bairro não pode estar nulo")
	@Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "O bairro do endereço está incorreto")
	private String bairro;

	/** The cidade. */
	@Size(min = 2, max = 40)
	@NotBlank(message = "O campo cidade não pode estar nulo")
	@Pattern(regexp = RegexType.LETRAS_NUMEROS, message = "A cidade do endereço está incorreto")
	private String cidade;

	/** The estado. */
	@NotBlank(message = "O campo estado não pode estar nulo")
	private EstadoType estado;

	/**
	 * Instantiates a new endereco.
	 *
	 * @param cep    the cep
	 * @param numero the numero
	 */
	public Endereco(String cep, Integer numero) {
		this.setCep(cep);
		this.setNumero(numero);
	}
	
	/**
	 * Instantiates a new endereco.
	 *
	 * @param cep         the cep
	 * @param rua         the rua
	 * @param numero      the numero
	 * @param complemento the complemento
	 * @param bairro      the bairro
	 * @param cidade      the cidade
	 * @param estado      the estado
	 */
	public Endereco(String cep, String rua, Integer numero, String complemento, String bairro, String cidade,
			EstadoType estado) {
		this.setCep(cep);
		this.setRua(rua);
		this.setNumero(numero);
		this.setComplemento(complemento);
		this.setBairro(bairro);
		this.setCidade(cidade);
		this.setEstado(estado);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.validaCepIncorreto(cep);
		this.cep = cep;
	}

	private void validaCepIncorreto(String cep) {
		if (cep == null || cep.trim().isEmpty() || cep.length() < CEP_SIZE || cep.length() > CEP_SIZE) {
			throw new IllegalArgumentException(CEP_INCORRETO);
		}
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.validaRuaIncorreto(rua);
		this.rua = rua;
	}

	private void validaRuaIncorreto(String rua) {
		if (rua == null || rua.trim().isEmpty() || rua.length() < RUA_MIN_SIZE || rua.length() > RUA_MAX_SIZE) {
			throw new IllegalArgumentException(RUA_INCORRETO);
		}
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.validaNumeroIncorreto(numero);
		this.numero = numero;
	}

	private void validaNumeroIncorreto(Integer numero) {
		if (numero == null || numero < NUMERO_MINIMO) {
			throw new IllegalArgumentException(NUMERO_INCORRETO);
		}
	}
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.validaComplementoIncorreto(complemento);
		this.complemento = complemento;
	}

	private void validaComplementoIncorreto(String complemento) {
		if (complemento == null || complemento.trim().isEmpty() || complemento.length() < COMPLEMENTO_MIN_SIZE
				|| complemento.length() > COMPLEMENTO_MAX_SIZE) {
			throw new IllegalArgumentException(COMPLEMENTO_INCORRETO);
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.validaBairroIncorreto(bairro);
		this.bairro = bairro;
	}

	private void validaBairroIncorreto(String bairro) {
		if (bairro == null || bairro.trim().isEmpty() || bairro.length() < BAIRRO_MIN_SIZE
				|| bairro.length() > BAIRRO_MAX_SIZE) {
			throw new IllegalArgumentException(BAIRRO_INCORRETO);
		}
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.validaCidadeIncorreto(cidade);
		this.cidade = cidade;
	}

	private void validaCidadeIncorreto(String cidade) {
		if (cidade == null || cidade.trim().isEmpty() || cidade.length() < CIDADE_MIN_SIZE
				|| cidade.length() > CIDADE_MAX_SIZE) {
			throw new IllegalArgumentException(CIDADE_INCORRETO);
		}
	}

	public EstadoType getEstado() {
		return estado;
	}

	public void setEstado(EstadoType estado) {
		this.estadoVazio(estado);
		this.estado = estado;
	}

	public void estadoVazio(EstadoType estado) {
		if (estado == null) {
			throw new IllegalArgumentException(ESTADO_VAZIO);
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

package br.com.contmatic.empresa;

import static br.com.contmatic.util.Constantes.ID_MINIMO;
import static br.com.contmatic.util.Constantes.ID_VAZIO;
import static br.com.contmatic.util.Constantes.NOME_MAX_SIZE;
import static br.com.contmatic.util.Constantes.NOME_MIN_SIZE;
import static br.com.contmatic.util.Constantes.NOME_VAZIO;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO;
import static br.com.contmatic.util.Constantes.PRECO_MINIMO_MENSAGEM;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA;
import static br.com.contmatic.util.Constantes.QUANTIDADE_MINIMA_MENSAGEM;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_GRANDE_DEMAIS;
import static br.com.contmatic.util.Constantes.TAMANHO_DO_NOME_PEQUENO_DEMAIS;

import java.math.BigDecimal;

import javax.validation.constraints.Pattern;

import br.com.contmatic.util.Constantes;
import br.com.contmatic.util.RegexType;

public class Produto {

	private Integer id;

	@Pattern(regexp = RegexType.LETRAS, message = Constantes.NOME_INVALIDO)
	private String nome;

	private Integer quantidade;

	private BigDecimal preco;

	public Produto(Integer id, String nome) {
		super();
		this.setId(id);		
		this.setNome(nome);
	}

	public Produto(Integer id, String nome, Integer quantidade, BigDecimal preco) {
		this.setId(id);
		this.setNome(nome);
		this.setQuantidade(quantidade);
		this.setPreco(preco);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.validaIdIncorreto(id);
		this.id = id;
	}

	private void validaIdIncorreto(Integer id) {
		if (id == null || id.doubleValue() < ID_MINIMO) {
			throw new IllegalArgumentException(ID_VAZIO);
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
		this.validaNomePequenoDemais(nome);
		this.validaNomeGrandeDemais(nome);
	}

	private void validaNomeGrandeDemais(String nome) {
		if (nome.length() > NOME_MAX_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_GRANDE_DEMAIS);
		}
	}

	private void validaNomePequenoDemais(String nome) {
		if (nome.length() < NOME_MIN_SIZE) {
			throw new IllegalArgumentException(TAMANHO_DO_NOME_PEQUENO_DEMAIS);
		}
	}

	private void validaNomeNulloOuVazio(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException(NOME_VAZIO);
		}
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.validaQuantidadeIncorreta(quantidade);
		this.quantidade = quantidade;
	}

	private void validaQuantidadeIncorreta(Integer quantidade) {
		if (quantidade < QUANTIDADE_MINIMA) {
			throw new IllegalArgumentException(QUANTIDADE_MINIMA_MENSAGEM);
		}
	}


	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.validaPrecoIncorreto(preco);
		this.preco = preco;
	}

	private void validaPrecoIncorreto(BigDecimal preco) {
		if (preco.doubleValue() < PRECO_MINIMO) {
			throw new IllegalArgumentException(PRECO_MINIMO_MENSAGEM);
		}
	}


	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		if (this.id != 0) {
			sb.append("id= ").append(this.id);
		}
		if (this.nome != null) {
			sb.append(" nome= ").append(this.nome);
		}
		if (this.quantidade != 0) {
			sb.append(" quantidade= ").append(this.quantidade);
		}
		if (this.preco != BigDecimal.valueOf(0)) {
			sb.append(" preço= ").append(this.preco);
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

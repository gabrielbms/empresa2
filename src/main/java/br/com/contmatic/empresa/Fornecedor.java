package br.com.contmatic.empresa;

import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Fornecedor.
 */
public class Fornecedor {

    /** The cnpj. */
    @CNPJ(message = "O CNPJ do funcionario está inválido")
    @NotBlank(message = "O campo CNPJ não pode estar nulo")
    private String cnpj;

    /** The nome. */
    @Length(min = 2, max = 80, message = "Tamanho do nome invalido")
    @NotBlank(message = "O campo nome não pode estar nulo")
    @Pattern(regexp = RegexType.LETRAS, message = "O nome do fornecedor está incorreto")
    private String nome;

    /** The telefones. */
    @Valid
    @NotEmpty(message = "O campo telefone não pode estar nulo")
    private Set<Telefone> telefones;

    /** The produto. */
    @Length(min = 2, max = 50, message = "Tamanho do produto invalido")
    @NotBlank(message = "O campo produto não pode estar nulo")
    private String produto;

    /** The enderecos. */
    @Valid
    @NotEmpty(message = "O campo endereco não pode estar nulo")
    private Set<Endereco> enderecos;

    /**
     * Instantiates a new fornecedor.
     *
     * @param cnpj the cnpj
     * @param nome the nome
     */
    public Fornecedor(String cnpj, String nome) {
        this.cnpj = cnpj;
        this.nome = nome;
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
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    public void setTelefones(Set<Telefone> telefone) {
        Preconditions.checkArgument(telefone.size() < 2, "Somente pode possuir um telefone");
        this.telefones = telefone;
    }

    public void setEnderecos(Set<Endereco> endereco) {
        Preconditions.checkArgument(endereco.size() < 2, "Somente pode possuir um endereco");
        this.enderecos = endereco;
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

}

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
    @Pattern(regexp = "\\d{2}.?\\d{3}.?\\d{3}/?\\d{4}-?\\d{2}")
    @NotBlank(message = "O campo CNPJ não pode estar nulo")
    @Length(min = 17, max = 19)
    private String cnpj;

    /** The nome. */
    @Pattern(regexp = RegexType.NOME, message = "O nome do fornecedor está incorreto")
    @Pattern(regexp = "^[a-zA-Z0-9_ ]*$")
    @NotBlank(message = "O campo nome não pode estar nulo")
    @Length(min = 2, max = 40)
    private String nome;

    /** The telefones. */
    @NotEmpty(message = "O campo telefone não pode estar nulo")
    @Valid
    private Set<Telefone> telefones;

    /** The produto. */
    @NotBlank(message = "O campo produto não pode estar nulo")
    @Length(min = 2, max = 50)
    private String produto;

    /** The enderecos. */
    @NotEmpty(message = "O campo endereco não pode estar nulo")
    @Valid
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

    /**
     * Gets the cnpj.
     *
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Sets the cnpj.
     *
     * @param cnpj the new cnpj
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Gets the nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the telefone.
     *
     * @return the telefone
     */
    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    /**
     * Gets the produto.
     *
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * Sets the produto.
     *
     * @param produto the new produto
     */
    public void setProduto(String produto) {
        this.produto = produto;
    }

    /**
     * Gets the endereco.
     *
     * @return the endereco
     */
    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    /**
     * Sets the telefones.
     *
     * @param telefone the new telefones
     */
    public void setTelefones(Set<Telefone> telefone) {
        Preconditions.checkArgument(telefone.size() < 2, "Somente pode possuir um telefone");
        this.telefones = telefone;
    }

    /**
     * Sets the enderecos.
     *
     * @param endereco the new enderecos
     */
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

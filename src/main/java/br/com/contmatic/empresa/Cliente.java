package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.br.CPF;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Cliente.
 */
public class Cliente {

    /** The cpf. */
    @Length(min = 11, max = 11)
    @CPF(message = "O CPF do funcionario está inválido")
    @NotNull(message = "O campo CPF não pode estar nulo")
    private String cpf;

    /** The nome. */
    @Length(min = 3, max = 70)
    @NotBlank(message = "O campo nome não pode estar vazio")
    @Pattern(regexp = RegexType.LETRAS, message = "O nome do cliente está incorreto")    
    private String nome;

    /** The email. */
    @Size(min = 5, max = 100)
    @Email(message = "O email do funcionario está invalido")
    @NotBlank(message = "O campo e-mail não pode estar vazio")
    private String email;

    /** The telefones. */
    @Valid
    @NotEmpty(message = "O telefone do funcionario não pode ser vazio")
    private Set<Telefone> telefones;

    /** The boleto. */
    @Range(min = (long) 1.00, max = (long) 9999.00)
    @NotEmpty(message = "O campo boleto não pode estar nulo")    
    private BigDecimal boleto;

    /** The informacao inutil. */
    @Null
    private String informacaoInutil;

    /**
     * Instantiates a new cliente.
     *
     * @param cpf the cpf
     * @param nome the nome
     * @param telefone the telefone
     * @param boleto the boleto
     */
    public Cliente(String cpf, String nome, @Valid Set<Telefone> telefone, BigDecimal boleto) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefones = telefone;
        this.boleto = boleto;
    }

    /**
     * Instantiates a new cliente.
     */
    public Cliente() {

    }
    
    /**
     * Gets the cpf.
     *
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Sets the cpf.
     *
     * @param setCpf the new cpf
     */
    public void setCpf(String setCpf) {

        Preconditions.checkArgument(!(setCpf == null || setCpf.trim().isEmpty()), "Dados não preenchidos corretamente");
        this.cpf = setCpf;
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
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Sets the telefones.
     *
     * @param telefone the new telefones
     */
    public void setTelefones(Set<Telefone> telefone) {
        Preconditions.checkArgument(telefone.size() < 2, "Somente pode possuir um telefone");
        this.telefones = telefone;
    }

    /**
     * Gets the boleto.
     *
     * @return the boleto
     */
    public BigDecimal getBoleto() {
        return boleto;
    }

    /**
     * Sets the boleto.
     *
     * @param boleto the new boleto
     */
    public void setBoleto(BigDecimal boleto) {
        this.boleto = boleto;
    }

    /**
     * Gets the informacao inutil.
     *
     * @return the informacao inutil
     */
    public String getInformacaoInutil() {
        return informacaoInutil;
    }

    /**
     * Sets the informacao inutil.
     *
     * @param informacaoInutil the new informacao inutil
     */
    public void setInformacaoInutil(String informacaoInutil) {
        this.informacaoInutil = informacaoInutil;
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

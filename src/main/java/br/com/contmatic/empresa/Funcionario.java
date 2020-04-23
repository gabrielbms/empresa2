package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.br.CPF;
import org.joda.time.LocalDate;

import com.google.common.base.Preconditions;

import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.RegexType;

/**
 * The Class Funcionario.
 */
public class Funcionario {

    /** The cpf. */
    @CPF(message = "O CPF do funcionario está inválido")
    @NotBlank(message = "O campo CPF não pode estar nulo")
    private String cpf;

    /** The nome. */
    @Size(min = 3, max = 20)
    @NotBlank(message = "O campo nome não pode estar nulo")
    @Pattern(regexp = RegexType.LETRAS, message = "O nome do funcionario está incorreto")    
    private String nome;

    /** The idade. */
    @NotEmpty
    @Min(value = 1, message = "A idade do funcionario não pode ser menor que 1")
    private Integer idade;

    /** The telefones. */
    @Valid
    @NotBlank(message = "O campo telefone não pode estar nulo")
    private Set<Telefone> telefones;

    /** The enderecos. */
    @Valid
    @NotBlank(message = "O campo endereco não pode estar nulo")
    private Set<Endereco> enderecos;

    /** The salario. */
    @NotBlank(message = "O campo salario não pode estar nulo")
    private BigDecimal salario;

    /** The data contratacao. */
    @NotBlank(message = "O campo data de contratação não pode estar nulo")
    private LocalDate dataContratacao;

    /** The data salario. */
    @Future
    @NotBlank(message = "O campo data de salario não pode estar nulo")
    private LocalDate dataSalario;

    /**
     * Instantiates a new funcionario.
     *
     * @param cpf the cpf
     * @param nome the nome
     * @param salario the salario
     */
    public Funcionario(String cpf, String nome, BigDecimal salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    /**
     * Instantiates a new funcionario.
     *
     * @param cpf the cpf
     * @param nome the nome
     * @param idade the idade
     * @param telefone the telefone
     * @param endereco the endereco
     * @param salario the salario
     */
    public Funcionario(String cpf, String nome, int idade, @Valid Set<Telefone> telefone, @Valid Set<Endereco> endereco, BigDecimal salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.telefones = telefone;
        this.enderecos = endereco;
        this.salario = salario;
    }

    /**
     * Instantiates a new funcionario.
     */
    public Funcionario() {

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
     * @param cpf the new cpf
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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
     * Gets the idade.
     *
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * Sets the idade.
     *
     * @param idade the new idade
     */
    public void setIdade(int idade) {
        this.idade = idade;
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
     * Gets the endereco.
     *
     * @return the endereco
     */
    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    /**
     * Gets the salario.
     *
     * @return the salario
     */
    public BigDecimal getSalario() {
        return salario;
    }

    /**
     * Sets the salario.
     *
     * @param salario the new salario
     */
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    /**
     * Gets the data contratacao.
     *
     * @return the data contratacao
     */
    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    /**
     * Sets the data contratacao.
     *
     * @param dataPagamento the new data contratacao
     */
    public void setDataContratacao(LocalDate dataPagamento) {
        this.dataContratacao = dataPagamento;
    }

    /**
     * Gets the data salario.
     *
     * @return the data salario
     */
    public LocalDate getDataSalario() {
        return dataSalario;
    }

    /**
     * Sets the data salario.
     *
     * @param dataSalario the new data salario
     */
    public void setDataSalario(LocalDate dataSalario) {
        this.dataSalario = dataSalario;
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

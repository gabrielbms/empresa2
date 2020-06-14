package br.com.contmatic.empresa;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
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
    @Length(min = 11, max = 11)
    @CPF(message = "O CPF do funcionario está inválido")
    @NotBlank(message = "O campo CPF não pode estar nulo")
    private String cpf;

    /** The nome. */
    @Length(min = 2, max = 60)
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
    @Range(min = 1, message = "O campo salário não pode ser negativo")
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public @Valid Set<Telefone> getTelefone() {
        return telefones;
    }

    public @Valid Set<Endereco> getEndereco() {
        return enderecos;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
    	if (salario.doubleValue() >= 1) {
    		this.salario = salario;
    	} else {
    		throw new IllegalArgumentException("salario não pode ser negativo");
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

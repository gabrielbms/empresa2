package br.com.contmatic.telefone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import br.com.contmatic.util.RegexType;

/**
 * The Class Telefone.
 */
public class Telefone {

    /** The ddd. */
    @NotBlank(message = "O campo DDD não pode estar nulo")
    private TelefoneDDD ddd;
  
    /** The numero. */
    @Size(min = 8, max = 10)
    @NotBlank(message = "O campo número não pode estar nulo")
    @Pattern(regexp = RegexType.NUMEROS, message = "O campo Numero não pode estar invalido")
    private String numero;
    
    /** The tipo telefone. */
    @NotBlank(message = "O campo telefone não pode estar nulo")
    private TipoTelefone tipoTelefone;
    
    /**
     * Instantiates a new telefone.
     */
    public Telefone () {

    }
    
    /**
     * Instantiates a new telefone.
     *
     * @param ddd the ddd
     * @param numero the numero
     * @param telefone the telefone
     */
    public Telefone (TelefoneDDD ddd, String numero, TipoTelefone telefone) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipoTelefone = telefone;
    }

    /**
     * Gets the ddd.
     *
     * @return the ddd
     */
    public TelefoneDDD getDdd() {
        return ddd;
    }

    /**
     * Gets the numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the numero.
     *
     * @param numero the new numero
     */
    public void setNumero(String numero) {
            this.numero = numero;
    }

    /**
     * Gets the telefone.
     *
     * @return the telefone
     */
    public TipoTelefone getTelefone() {
        return tipoTelefone;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.MULTI_LINE_STYLE);
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
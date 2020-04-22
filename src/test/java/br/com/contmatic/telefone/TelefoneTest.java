package br.com.contmatic.telefone;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class TelefoneTest.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TelefoneTest {

    /** The telefone. */
    private static Telefone telefone;
    
    /** The telefone DDD. */
    private static TelefoneDDD telefoneDDD;
    
    /** The tipo telefone. */
    private static TipoTelefone tipoTelefone;
    
    /** The validator. */
    private Validator validator;

    /**
     * Inicio dos testes.
     */
    @BeforeClass
    public static void InicioDosTestes() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
        System.out.println("Iniciamos os testes na classe telefone");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        telefone = Fixture.from(Telefone.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Telefone>> constraintViolations = validator.validate(telefone);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Nao deve aceitar cliente sem cpf nome telefone boleto.
     */
    @Test
    public void nao_deve_aceitar_cliente_sem_cpf_nome_telefone_boleto() {
        Telefone telefone = new Telefone();
        Set<ConstraintViolation<Telefone>> restricoes = validator.validate(telefone);
        assertThat(restricoes, Matchers.hasSize(0));
    }

    /**
     * Deve passar na validacao com cpf nome telefone boleto informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cpf_nome_telefone_boleto_informados() {
        telefone = Fixture.from(Telefone.class).gimme("valido");
        Set<ConstraintViolation<Telefone>> restricoes = validator.validate(telefone);
        assertThat(restricoes, empty());
    }

    /**
     * Nao deve aceitar ddd nulo.
     */
    @Test
    public void nao_deve_aceitar_ddd_nulo() {
        assertNotNull(telefone.getDdd());
    }

    /**
     * Nao deve aceitar numero nulo.
     */
    @Test
    public void nao_deve_aceitar_numero_nulo() {
        assertNotNull(telefone.getNumero());
    }

    /**
     * Nao deve aceitar telefone nulo.
     */
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        assertNotNull(telefone.getTelefone());
    }

    /**
     * Deve testar o get ddd esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getDdd_esta_funcionando_corretamente() {
        telefoneDDD = TelefoneDDD.valueOf("DDD11");
        assertTrue(telefoneDDD.getComplemento().equals("São Paulo – SP") | telefoneDDD.getDdd() == 11);
    }

    /**
     * Deve testar o get ddd esta funcionando corretameante.
     */
    @Test
    public void deve_testar_o_getDdd_esta_funcionando_corretameante() {
        tipoTelefone = TipoTelefone.CELULAR;
        assertTrue(tipoTelefone.getDescricao().equals("Celular") | tipoTelefone.getTamanho() == 9);
    }

    /**
     * Deve testar o get numero esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNumero_esta_funcionando_corretamente() {
        telefone.setNumero("927219389");
        assertThat(telefone.getNumero(), containsString("927219389"));
    }

    /**
     * Nao deve aceitar espacos em branco no numero.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_numero() {
        assertFalse(telefone.getNumero().trim().isEmpty());
    }

    /**
     * Deve retornar false no hash code com um endereco de numero null.
     */
    @Test
    public void deve_retornar_false_no_hashCode_com_um_endereco_de_numero_null() {
        Telefone telefone2 = new Telefone(null, null, null);
        assertFalse(telefone.hashCode() == telefone2.hashCode());
    }

    /**
     * Deve retornar true no equals comparando dois enderecos de cep null.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_dois_enderecos_de_cep_null() {
        Telefone telefone1 = new Telefone(null, null, null);
        Telefone telefone2 = new Telefone(null, null, null);
        assertTrue(telefone1.equals(telefone2));
    }

    /**
     * Deve validar ddd annotations.
     */
    @Test
    public void deve_validar_ddd_annotations() {
        Telefone cadastroValidator = Fixture.from(Telefone.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getDdd()));
    }

    /**
     * Deve validar numero annotations.
     */
    @Test
    public void deve_validar_numero_annotations() {
        Telefone cadastroValidator = Fixture.from(Telefone.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNumero()));
    }

    /**
     * Deve validar tipo telefone annotations.
     */
    @Test
    public void deve_validar_tipo_telefone_annotations() {
        Telefone cadastroValidator = Fixture.from(Telefone.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {

    }

    /**
     * Teste no to string.
     */
    @AfterClass
    public static void teste_no_toString() {
        System.out.println(telefone);
        System.out.println("Finalizamos os testes na classe endereco\n");

    }

}

package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.math.BigDecimal;
import java.util.HashSet;
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

import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class ClienteTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class ClienteTest {
	
    /** The cliente. */
    private static Cliente cliente;

    /** The validator. */
    private Validator validator;

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Nao deve aceitar cpf nulo.
     */
    @Test
    public void nao_deve_aceitar_cpf_nulo() {
        assertNotNull(cliente.getCpf());
    }

    /**
     * Nao deve aceitar nome nulo.
     */
    @Test
    public void nao_deve_aceitar_nome_nulo() {
        assertNotNull(cliente.getNome());
    }

    /**
     * Nao deve aceitar email nulo.
     */
    @Test
    public void nao_deve_aceitar_email_nulo() {
        assertNotNull(cliente.getEmail());
    }

    /**
     * Nao deve aceitar telefone nulo.
     */
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        assertNotNull(cliente.getTelefone());
    }

    /**
     * Nao deve aceitar boleto nulo.
     */
    @Test
    public void nao_deve_aceitar_boleto_nulo() {
        assertNotNull(cliente.getBoleto());
    }

    /**
     * Deve testar o get cpf esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setCpf_esta_funcionando_corretamente() {
        cliente.setCpf("437.018.888-18");
        assertThat(cliente.getCpf(), containsString("437.018.888-18"));
    }

    /**
     * Deve testar o get nome esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setNome_esta_funcionando_corretamente() {
        cliente.setNome("Gabriel");
        assertThat(cliente.getNome(), containsString("Gabriel"));
    }

    /**
     * Deve testar o get email esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setEmail_esta_funcionando_corretamente() {
        cliente.setEmail("bueno@hotmail.com.br");
        assertThat(cliente.getEmail(), containsString("bueno@hotmail.com.br"));
    }

    /**
     * Deve testar o get telefone esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setTelefone_esta_funcionando_corretamente() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        assertTrue(cliente.equals(cliente));
    }

    /**
     * Deve testar o get boleto esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setBoleto_esta_funcionando_corretamente() {
        cliente.setBoleto(BigDecimal.valueOf(250.00));
        assertThat(cliente.getBoleto(), is(BigDecimal.valueOf(250.00)));
    }

    /**
     * Nao deve aceitar espacos em branco no cpf.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
        assertFalse(cliente.getCpf().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no nome.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_nome() {
        assertFalse(cliente.getNome().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no email.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_email() {
        assertFalse(cliente.getEmail().trim().isEmpty());
    }

    /**
     * Deve retornar true no hash code com clientes iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_clientes_iguais() {
        Cliente cliente01 = cliente;
        assertTrue(cliente.hashCode() == cliente01.hashCode());
    }

    /**
     * Deve retornar true no equals com clientes iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_clientes_iguais() {
        Cliente cliente01 = cliente;
        assertTrue(cliente.equals(cliente01) & cliente01.equals(cliente));
    }

    /**
     * Deve retornar false no equals com um cliente de cpf null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_cliente_de_cpf_null() {
        Cliente cliente2 = null;
        assertFalse(cliente.equals(cliente2));
    }

    /**
     * Deve retornar true no equals comparando um cliente com ele mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_um_cliente_com_ele_mesmo() {
        assertTrue(cliente.equals(cliente));
    }

    /**
     * Deve retornar false no equals comparando um cliente com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_um_cliente_com_null() {
        assertFalse(cliente.equals(null));
    }

    /**
     * Deve retornar false no equals com clientes de cpf diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_clientes_de_cpf_diferentes() {
        Cliente cliente1 = Fixture.from(Cliente.class).gimme("valido");
        cliente1.setCpf("12345678912");
        Cliente cliente2 = Fixture.from(Cliente.class).gimme("valido");
        cliente2.setCpf("98765432198");
        assertFalse(cliente2.equals(cliente1));
    }

    /**
     * Deve retornar false no equals com clientes e um dado aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_clientes_e_um_dado_aleatorio() {
        assertFalse(cliente.equals(new Object()));
    }

    /**
     * To string deve retornar null.
     */
    @Test
    public void toString_deve_retornar_null() {
        Cliente clienteNull = new Cliente(null, null, null, null);
        assertThat(clienteNull.toString(), containsString("null"));
    }

    /**
     * To string deve retornar valores preenchidos.
     */
    @Test
    public void toString_deve_retornar_valores_preenchidos() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        assertThat(cliente.toString(), is(cliente.toString()));
    }

    /**
     * Deve validar cpf annotations.
     */
    @Test
    public void deve_validar_cpf_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCpf()));
    }

    /**
     * Deve validar nome annotations.
     */
    @Test
    public void deve_validar_nome_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
    }

    /**
     * Deve validar email annotations.
     */
    @Test
    public void deve_validar_email_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEmail()));
    }

    /**
     * Deve validar telefones annotations.
     */
    @Test
    public void deve_validar_telefones_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }

    /**
     * Deve validar boletos annotations.
     */
    @Test
    public void deve_validar_boletos_annotations() {
        Cliente cadastroValidator = Fixture.from(Cliente.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getBoleto()));
    }
    
    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Nao deve aceitar nome curto.
     */
    @Test
    public void nao_deve_aceitar_nome_curto() {
        Cliente cliente = new Cliente();
        cliente.setNome("Gabriel");
        Set<ConstraintViolation<Cliente>> violations = validator.validate(cliente);
        assertFalse(violations.isEmpty());
    }

    /**
     * Nao deve aceitar cliente sem cpf nome telefone boleto.
     */
    @Test
    public void nao_deve_aceitar_cliente_sem_cpf_nome_telefone_boleto() {
        Cliente cliente = new Cliente();
        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
        assertThat(restricoes, Matchers.hasSize(3));
    }

    /**
     * Deve passar na validacao com cpf nome telefone boleto informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cpf_nome_telefone_boleto_informados() {
        cliente = Fixture.from(Cliente.class).gimme("valido");
        Set<ConstraintViolation<Cliente>> restricoes = validator.validate(cliente);
        assertThat(restricoes, empty());
    }
    
    /**
     * Deve testar o exception do set telefones.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        cliente.setTelefones(telefone);
    }
    
    /**
     * Deve testar o set telefones.
     */
    @Test
    public void deve_testar_o_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        cliente.setTelefones(telefone);
        assertTrue(cliente.equals(cliente));
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {

    }

    /**
     * Tear down after class.
     */
    @AfterClass
    public static void TearDownAfterClass() {
    }
}

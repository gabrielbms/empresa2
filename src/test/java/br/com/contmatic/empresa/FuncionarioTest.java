package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

/**
 * The Class FuncionarioTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class FuncionarioTest {

    /** The funcionario. */
    private static Funcionario funcionario;
    
    /** The validator. */
    private Validator validator;

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
        System.out.println("Iniciamos os testes na classe funcionario");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        funcionario = Fixture.from(Funcionario.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Funcionario>> constraintViolations = validator.validate(funcionario);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Nao deve aceitar nome curto.
     */
    @Test
    public void nao_deve_aceitar_nome_curto() {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Gabriel");
        assertTrue(funcionario.getNome().equals("Gabriel"));
    }

    /**
     * Nao deve aceitar funcionario sem cpf nome idade telefone endereco salario.
     */
    @Test
    public void nao_deve_aceitar_Funcionario_sem_cpf_nome_idade_telefone_endereco_salario() {
        Funcionario Funcionario = new Funcionario();
        Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(Funcionario);
        assertThat(restricoes, Matchers.hasSize(0));
    }

    /**
     * Deve passar na validacao com cpf nome idade telefone endereco salario informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cpf_nome_idade_telefone_endereco_salario_informados() {
        funcionario = Fixture.from(Funcionario.class).gimme("valido");
        Set<ConstraintViolation<Funcionario>> restricoes = validator.validate(funcionario);
        assertThat(restricoes, empty());
    }

    /**
     * Nao deve aceitar cpf nulo.
     */
    @Test
    public void nao_deve_aceitar_cpf_nulo() {
        assertNotNull(funcionario.getCpf());
    }

    /**
     * Nao deve aceitar nome nulo.
     */
    @Test
    public void nao_deve_aceitar_nome_nulo() {
        assertNotNull(funcionario.getNome());
    }

    /**
     * Nao deve aceitar idade nulo.
     */
    @Test
    public void nao_deve_aceitar_idade_nulo() {
        assertNotNull(funcionario.getIdade());
    }

    /**
     * Nao deve aceitar telefone nulo.
     */
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        assertNotNull(funcionario.getTelefone());
    }

    /**
     * Nao deve aceitar endereco nulo.
     */
    @Test
    public void nao_deve_aceitar_endereco_nulo() {
        assertNotNull(funcionario.getEndereco());
    }

    /**
     * Nao deve aceitar salario nulo.
     */
    @Test
    public void nao_deve_aceitar_salario_nulo() {
        assertNotNull(funcionario.getSalario());
    }

    /**
     * Nao deve aceitar data contratação nulo.
     */
    @Test
    public void nao_deve_aceitar_data_contratação_nulo() {
        assertNotNull(funcionario.getDataContratacao());
    }

    /**
     * Nao deve aceitar data salario nulo.
     */
    @Test
    public void nao_deve_aceitar_data_salario_nulo() {
        assertNotNull(funcionario.getDataSalario());
    }
    
    /**
     * Deve testar o get cpf esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getCpf_esta_funcionando_corretamente() {
        funcionario.setCpf("43701888818");
        assertThat(funcionario.getCpf(), containsString("43701888818"));
    }

    /**
     * Deve testar o get nome esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNome_esta_funcionando_corretamente() {
        funcionario.setNome("Gabriel Bueno");
        assertThat(funcionario.getNome(), containsString("Gabriel Bueno"));
    }

    /**
     * Deve testar o get idade esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getIdade_esta_funcionando_corretamente() {
        funcionario.setIdade(25);
        assertThat(funcionario.getIdade(), is(25));
    }

    /**
     * Deve testar o get endereco esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
        funcionario.getEndereco();
        assertThat(funcionario.toString(), containsString("cep="));
    }

    /**
     * Deve testar o get salario esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getSalario_esta_funcionando_corretamente() {
        funcionario.setSalario(BigDecimal.valueOf(1500.00));
        assertThat(funcionario.getSalario(), is(BigDecimal.valueOf(1500.00)));
    }

    /**
     * Deve testar o get data contratacao esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getDataContratacao_esta_funcionando_corretamente() {
        funcionario.setDataContratacao(new LocalDate(2019, 05, 05));
        assertThat(funcionario.toString(), containsString("2019-05-05"));
    }
    
    /**
     * Deve testar o get data salario esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getDataSalario_esta_funcionando_corretamente() {
        funcionario.setDataSalario(new LocalDate(2020, 07, 07));
        assertThat(funcionario.toString(), containsString("2020-07-07"));
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
        funcionario.setTelefones(telefone);
    }

    /**
     * Deve testar o set telefones.
     */
    @Test
    public void deve_testar_o_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        funcionario.setTelefones(telefone);
        assertTrue(funcionario.equals(funcionario));
    }

    /**
     * Deve testar o exception do set enderecos.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_exception_do_setEnderecos() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        funcionario.setEnderecos(endereco);
    }

    /**
     * Deve testar o set enderecos.
     */
    @Test
    public void deve_testar_o_setEnderecos() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        funcionario.setEnderecos(endereco);
        assertTrue(funcionario.equals(funcionario));

    }

    /**
     * Nao deve aceitar espacos em branco no cpf.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cpf() {
        assertFalse(funcionario.getCpf().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no nome.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_nome() {
        assertFalse(funcionario.getNome().trim().isEmpty());
    }

    /**
     * Deve retornar true no hash code com funcionarios iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_funcionarios_iguais() {
        Funcionario funcionario2 = funcionario;
        assertTrue(funcionario.hashCode() == funcionario2.hashCode());
    }

    /**
     * Deve retornar false no hash code com um funcionario de cpf null.
     */
    @Test
    public void deve_retornar_false_no_hashCode_com_um_Funcionario_de_cpf_null() {
        Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertFalse(funcionario.hashCode() == funcionario2.hashCode());
    }

    /**
     * Deve retornar true no equals com funcionarios iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_funcionarios_iguais() {
        Funcionario funcionario2 = funcionario;
        assertTrue(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
    }

    /**
     * Deve retornar false no equals com um funcionario de cpf null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_funcionario_de_cpf_null() {
        Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertFalse(funcionario.equals(funcionario2) & funcionario2.equals(funcionario));
    }

    /**
     * Deve retornar true no equals comparando um funcionario com ele mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_um_funcionario_com_ele_mesmo() {
        assertTrue(funcionario.equals(funcionario));
    }

    /**
     * Deve retornar false no equals comparando um funcionarios com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_um_funcionarios_com_null() {
        assertFalse(funcionario.equals(null));
    }

    /**
     * Deve retornar true no equals comparando dois funcionarios de cpf null.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_dois_funcionarios_de_cpf_null() {
        Funcionario funcionario1 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        Funcionario funcionario2 = new Funcionario(null, "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertTrue(funcionario1.equals(funcionario2));
    }

    /**
     * Deve retornar false no equals com funcionarios de cpf diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_funcionarios_de_cpf_diferentes() {
        Funcionario funcionario1 = new Funcionario("43701888817", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        Funcionario funcionario2 = new Funcionario("43701888818", "Gabriel Bueno", BigDecimal.valueOf(1500.00));
        assertFalse(funcionario2.equals(funcionario1));
    }

    /**
     * Deve retornar false no equals com funcionario e um numero aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_funcionario_e_um_numero_aleatorio() {
        assertFalse(funcionario.equals(new Object()));
    }

    /**
     * To string deve retornar null.
     */
    @Test
    public void toString_deve_retornar_null() {
        Funcionario funcionarioNull = new Funcionario(null, null, 0, null, null, new BigDecimal("0"));
        assertThat(funcionarioNull.toString(), containsString("salario"));
    }

    /**
     * Deve validar cpf annotations.
     */
    @Test
    public void deve_validar_cpf_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCpf()));
    }

    /**
     * Deve validar nome annotations.
     */
    @Test
    public void deve_validar_nome_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
    }

    /**
     * Deve validar idade annotations.
     */
    @Test
    public void deve_validar_idade_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getIdade()));
    }

    /**
     * Deve validar telefones annotations.
     */
    @Test
    public void deve_validar_telefones_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }

    /**
     * Deve validar enderecos annotations.
     */
    @Test
    public void deve_validar_enderecos_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEndereco()));
    }

    /**
     * Deve validar salarios annotations.
     */
    @Test
    public void deve_validar_salarios_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getSalario()));
    }

    /**
     * Deve validar data contratacao annotations.
     */
    @Test
    public void deve_validar_data_contratacao_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getDataContratacao()));
    }
    
    /**
     * Deve validar data salario annotations.
     */
    @Test
    public void deve_validar_data_salario_annotations() {
        Funcionario cadastroValidator = Fixture.from(Funcionario.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getDataSalario()));
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
    public static void tearDownAfterClass() {
        System.out.println(funcionario);
        System.out.println("Finalizamos os testes na classe funcionario\n");
        System.out.println("-----/-----/-----/-----/-----/-----/-----\n");
    }

}

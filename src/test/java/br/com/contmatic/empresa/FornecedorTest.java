package br.com.contmatic.empresa;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.runners.MethodSorters.NAME_ASCENDING;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;;

/**
 * The Class FornecedorTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class FornecedorTest {

    /** The fornecedor. */
    private static Fornecedor fornecedor;
    
    /** The validator. */
    private Validator validator;

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
        System.out.println("Iniciamos os testes na classe fornecedor");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        fornecedor = Fixture.from(Fornecedor.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Fornecedor>> constraintViolations = validator.validate(fornecedor);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Nao deve aceitar nome curto.
     */
    @Test
    public void nao_deve_aceitar_nome_curto() {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome("GB Computadores");
        Set<ConstraintViolation<Fornecedor>> violations = validator.validate(fornecedor);
        assertFalse(violations.isEmpty());
    }

    /**
     * Nao deve aceitar fornecedor sem cnpj nome telefone produto endereco.
     */
    @Test
    public void nao_deve_aceitar_Fornecedor_sem_cnpj_nome_telefone_produto_endereco() {
        Fornecedor Fornecedor = new Fornecedor();
        Set<ConstraintViolation<Fornecedor>> restricoes = validator.validate(Fornecedor);
        assertThat(restricoes, Matchers.hasSize(3));
    }

    /**
     * Deve passar na validacao com cnpj nome telefone produto endereco informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cnpj_nome_telefone_produto_endereco_informados() {
        fornecedor = Fixture.from(Fornecedor.class).gimme("valido");
        Set<ConstraintViolation<Fornecedor>> restricoes = validator.validate(fornecedor);
        assertThat(restricoes, empty());
    }

    /**
     * Nao deve aceitar cnpj nulo.
     */
    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        assertNotNull(fornecedor.getCnpj());
    }

    /**
     * Nao deve aceitar nome nulo.
     */
    @Test //
    public void nao_deve_aceitar_nome_nulo() {
        fornecedor.setNome("CA peças LTDA");
        assertNotNull(fornecedor.getNome());
    }

    /**
     * Nao deve aceitar telefone nulo.
     */
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        assertNotNull(fornecedor.getTelefone());
    }

    /**
     * Nao deve aceitar endereco nulo.
     */
    @Test
    public void nao_deve_aceitar_endereco_nulo() {
        assertNotNull(fornecedor.getEndereco());
    }

    /**
     * Nao deve aceitar produto nulo.
     */
    @Test
    public void nao_deve_aceitar_produto_nulo() {
        assertNotNull(fornecedor.getProduto());
    }

    /**
     * Deve testar o get cpf esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getCpf_esta_funcionando_corretamente() {
        fornecedor.setCnpj("97904702000131");
        assertThat(fornecedor.getCnpj(), containsString("97904702000131"));
    }

    /**
     * Deve testar o get nome esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNome_esta_funcionando_corretamente() {
        Fornecedor fornecedor1 = new Fornecedor(null, "CA peças LTDA");
        assertThat(fornecedor1.getNome(), containsString("CA peças LTDA"));
    }

    /**
     * Deve testar o get endereco esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getEndereco_esta_funcionando_corretamente() {
        fornecedor.getEndereco();
        assertThat(fornecedor.toString(), containsString("cep="));
    }

    /**
     * Deve testar o get produto esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getProduto_esta_funcionando_corretamente() {
        fornecedor.setProduto("5 placas mães");
        assertThat(fornecedor.getProduto(), is("5 placas mães"));
    }

    /**
     * Deve testar o set telefones.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        fornecedor.setTelefones(telefone);
    }

    /**
     * Deve testar o set telefoness.
     */
    @Test
    public void deve_testar_o_setTelefoness() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        fornecedor.setTelefones(telefone);
        assertTrue(fornecedor.equals(fornecedor));
    }

    /**
     * Deve testar o set enderecos.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deve_testar_o_setEnderecos() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        fornecedor.setEnderecos(endereco);
    }

    /**
     * Deve testar o set enderecoss.
     */
    @Test
    public void deve_testar_o_setEnderecoss() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        fornecedor.setEnderecos(endereco);
        assertTrue(fornecedor.equals(fornecedor));

    }

    /**
     * Nao deve aceitar espacos em branco no cnpj.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cnpj() {
        assertFalse(fornecedor.getCnpj().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no nome.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_nome() {
        assertFalse(fornecedor.getNome().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no produto.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_produto() {
        assertFalse(fornecedor.getProduto().trim().isEmpty());
    }

    /**
     * Deve retornar true no hash code com fornecedor iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_fornecedor_iguais() {
        Fornecedor fornecedor2 = fornecedor;
        assertTrue(fornecedor.hashCode() == fornecedor2.hashCode());
    }

    /**
     * Deve retornar false no hash code com uma fornecedor de cnpj null.
     */
    @Test
    public void deve_retornar_false_no_hashCode_com_uma_fornecedor_de_cnpj_null() {
        Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
        assertFalse(fornecedor.hashCode() == fornecedor2.hashCode());
    }

    /**
     * Deve retornar true no equals com fornecedores iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_fornecedores_iguais() {
        Fornecedor fornecedor2 = fornecedor;
        assertTrue(fornecedor.equals(fornecedor2) & fornecedor2.equals(fornecedor));
    }

    /**
     * Deve retornar false no equals com um fornecedor de cnpj null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_fornecedor_de_cnpj_null() {
        Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
        assertFalse(fornecedor.equals(fornecedor2) & fornecedor2.equals(fornecedor));
    }

    /**
     * Deve retornar true no equals comparando um fornecedor com ela mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_um_fornecedor_com_ela_mesmo() {
        assertTrue(fornecedor.equals(fornecedor));
    }

    /**
     * Deve retornar false no equals comparando um fornecedor com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_um_fornecedor_com_null() {
        assertFalse(fornecedor.equals(null));
    }

    /**
     * Deve retornar true no equals comparando dois fornecedores de cnpj null.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_dois_fornecedores_de_cnpj_null() {
        Fornecedor fornecedor1 = new Fornecedor(null, "CA peças LTDA");
        Fornecedor fornecedor2 = new Fornecedor(null, "CA peças LTDA");
        assertTrue(fornecedor1.equals(fornecedor2));
    }

    /**
     * Deve retornar false no equals com fornecedores de cnpj diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_fornecedores_de_cnpj_diferentes() {
        Fornecedor fornecedor1 = new Fornecedor("97904702000131", "CA peças LTDA");
        Fornecedor fornecedor2 = new Fornecedor("97904702000132", "CA peças LTDA");
        assertFalse(fornecedor2.equals(fornecedor1));
    }

    /**
     * Deve retornar false no equals com a fornecedor e um objeto aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_a_fornecedor_e_um_objeto_aleatorio() {
        assertFalse(fornecedor.equals(new Object()));
    }

    /**
     * To string deve retornar vazia.
     */
    @Test
    public void toString_deve_retornar_vazia() {
        Fornecedor fornecedorNull = new Fornecedor(null, null);
        assertThat(fornecedorNull.toString(), containsString(""));
    }

    /**
     * To string deve retornar valores preenchidos.
     */
    @Test
    public void toString_deve_retornar_valores_preenchidos() {
        Fornecedor fornecedorPreenchido = new Fornecedor("97904702000131", "CA peças LTDA");
        assertThat(fornecedorPreenchido.toString(), is(fornecedorPreenchido.toString()));
    }

    /**
     * Deve validar cnpj annotations.
     */
    @Test
    public void deve_validar_cnpj_annotations() {
        Fornecedor cadastroValidator = Fixture.from(Fornecedor.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCnpj()));
    }

    /**
     * Deve validar nome annotations.
     */
    @Test
    public void deve_validar_nome_annotations() {
        Fornecedor cadastroValidator = Fixture.from(Fornecedor.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
    }

    /**
     * Deve validar telefones annotations.
     */
    @Test
    public void deve_validar_telefones_annotations() {
        Fornecedor cadastroValidator = Fixture.from(Fornecedor.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }

    /**
     * Deve validar produto annotations.
     */
    @Test
    public void deve_validar_produto_annotations() {
        Fornecedor cadastroValidator = Fixture.from(Fornecedor.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getProduto()));
    }

    /**
     * Deve validar enderecos annotations.
     */
    @Test
    public void deve_validar_enderecos_annotations() {
        Fornecedor cadastroValidator = Fixture.from(Fornecedor.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEndereco()));
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
        System.out.println(fornecedor);
        System.out.println("Finalizamos os testes na classe funcionario\n");
        System.out.println("-----/-----/-----/-----/-----/-----/-----\n");
    }

}

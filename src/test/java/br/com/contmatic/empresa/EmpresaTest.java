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

import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.util.Annotations;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;;

/**
 * The Class EmpresaTest.
 */
@FixMethodOrder(NAME_ASCENDING)
public class EmpresaTest {

    /** The empresa. */
    private static Empresa empresa;
    
    /** The validator. */
    private Validator validator;

    /**
     * Set up before class.
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        FixtureFactoryLoader.loadTemplates("br.com.contmatic.util");
        System.out.println("Iniciamos os testes na classe empresa");
    }

    /**
     * Set up.
     */
    @Before
    public void setUp() {
        empresa = Fixture.from(Empresa.class).gimme("valido");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Deve gerar dados validos.
     */
    @Test
    public void deve_gerar_dados_validos() {
        Set<ConstraintViolation<Empresa>> constraintViolations = validator.validate(empresa);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Nao deve aceitar nome curto.
     */
    @Test
    public void nao_deve_aceitar_nome_curto() {
        Empresa Empresa = new Empresa();
        Empresa.setNome("GB Computadores");
        Set<ConstraintViolation<Empresa>> violations = validator.validate(Empresa);
        assertFalse(violations.isEmpty());
    }

    /**
     * Nao deve aceitar empresa sem cnpj nome telefone endereco.
     */
    @Test
    public void nao_deve_aceitar_empresa_sem_cnpj_nome_telefone_endereco() {
        Empresa Empresa = new Empresa();
        Set<ConstraintViolation<Empresa>> restricoes = validator.validate(Empresa);
        assertThat(restricoes, Matchers.hasSize(3));
    }

    /**
     * Deve passar na validacao com cnpj nome site telefone endereco informados.
     */
    @Test
    public void deve_passar_na_validacao_com_cnpj_nome_site_telefone_endereco_informados() {
        empresa = Fixture.from(Empresa.class).gimme("valido");
        Set<ConstraintViolation<Empresa>> restricoes = validator.validate(empresa);
        assertThat(restricoes, empty());
    }

    /**
     * Nao deve aceitar cnpj nulo.
     */
    @Test
    public void nao_deve_aceitar_cnpj_nulo() {
        assertNotNull(empresa.getCnpj());
    }

    /**
     * Nao deve aceitar nome nulo.
     */
    @Test
    public void nao_deve_aceitar_nome_nulo() {
        empresa.setNome("GB Conserto de computadores");
        assertNotNull(empresa.getNome());
    }

    /**
     * Nao deve aceitar site nulo.
     */
    @Test
    public void nao_deve_aceitar_site_nulo() {
        empresa.setSite("gbconsertos.net");
        assertNotNull(empresa.getNome());
    }

    /**
     * Nao deve aceitar telefone nulo.
     */
    @Test
    public void nao_deve_aceitar_telefone_nulo() {
        assertNotNull(empresa.getTelefone());
    }

    /**
     * Nao deve aceitar endereco nulo.
     */
    @Test
    public void nao_deve_aceitar_endereco_nulo() {
        assertNotNull(empresa.getEndereco());
    }

    /**
     * Deve testar o get cnpj esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getCnpj_esta_funcionando_corretamente() {
        empresa.setCnpj("35667373000103");
        assertThat(empresa.getCnpj(), containsString("35667373000103"));
    }

    /**
     * Deve testar o get nome esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_getNome_esta_funcionando_corretamente() {
        empresa.setNome("GB computadores");
        assertThat(empresa.getNome(), containsString("GB computadores"));
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
        empresa.setTelefones(telefone);
    }

    /**
     * Deve testar o set telefones.
     */
    @Test
    public void deve_testar_o_setTelefones() {
        Set<Telefone> telefone = new HashSet<>();
        telefone.add(Fixture.from(Telefone.class).gimme("valido"));
        empresa.setTelefones(telefone);
        assertTrue(telefone.equals(telefone));
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
        empresa.setEnderecos(endereco);
    }

    /**
     * Deve testar o set enderecos.
     */
    @Test
    public void deve_testar_o_setEnderecos() {
        Set<Endereco> endereco = new HashSet<>();
        endereco.add(Fixture.from(Endereco.class).gimme("valido"));
        empresa.setEnderecos(endereco);
        assertTrue(endereco.equals(endereco));
    }

    /**
     * Deve testar o set endereco esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setEndereco_esta_funcionando_corretamente() {
        empresa.getEndereco();
        assertThat(empresa.toString(), containsString("cep="));
    }

    /**
     * Deve testar o set site esta funcionando corretamente.
     */
    @Test
    public void deve_testar_o_setSite_esta_funcionando_corretamente() {
        empresa.getSite();
        assertThat(empresa.toString(), containsString("www."));
    }

    /**
     * Nao deve aceitar espacos em branco no cnpj.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_cnpj() {
        assertFalse(empresa.getCnpj().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no nome.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_nome() {
        assertFalse(empresa.getNome().trim().isEmpty());
    }

    /**
     * Nao deve aceitar espacos em branco no site.
     */
    @Test
    public void nao_deve_aceitar_espacos_em_branco_no_site() {
        assertFalse(empresa.getSite().trim().isEmpty());
    }

    /**
     * Deve retornar true no hash code com empresas iguais.
     */
    @Test
    public void deve_retornar_true_no_hashCode_com_empresas_iguais() {
        Empresa empresa01 = empresa;
        assertTrue(empresa.hashCode() == empresa01.hashCode());
    }

    /**
     * Deve retornar true no equals com empresas iguais.
     */
    @Test
    public void deve_retornar_true_no_equals_com_Empresas_iguais() {
        Empresa empresa01 = empresa;
        assertTrue(empresa.equals(empresa01) & empresa01.equals(empresa));
    }

    /**
     * Deve retornar false no equals com um empresa de cnpj null.
     */
    @Test
    public void deve_retornar_false_no_equals_com_um_Empresa_de_cnpj_null() {
        Empresa empresa1 = Fixture.from(Empresa.class).gimme("valido");
        Empresa empresa2 = null;
        assertFalse(empresa1.equals(empresa2));
    }

    /**
     * Deve retornar true no equals comparando uma empresa com ela mesmo.
     */
    @Test
    public void deve_retornar_true_no_equals_comparando_uma_empresa_com_ela_mesmo() {
        assertTrue(empresa.equals(empresa));
    }

    /**
     * Deve retornar false no equals comparando uma empresa com null.
     */
    @Test
    public void deve_retornar_false_no_equals_comparando_uma_empresa_com_null() {
        assertFalse(empresa.equals(null));
    }

    /**
     * Deve retornar false no equals com empresas de cnpj diferentes.
     */
    @Test
    public void deve_retornar_false_no_equals_com_Empresas_de_cnpj_diferentes() {
        Empresa empresa1 = Fixture.from(Empresa.class).gimme("valido");
        empresa1.setCnpj("36.621.217/0001-67");
        Empresa empresa2 = Fixture.from(Empresa.class).gimme("valido");
        empresa1.setCnpj("36.621.217/0001-68");
        assertFalse(empresa2.equals(empresa1));
    }

    /**
     * Deve retornar false no equals com a empresa e um objeto aleatorio.
     */
    @Test
    public void deve_retornar_false_no_equals_com_a_empresa_e_um_objeto_aleatorio() {
        assertFalse(empresa.equals(new Object()));
    }

    /**
     * To string deve retornar null.
     */
    @Test
    public void toString_deve_retornar_null() {
        Empresa empresaNull = new Empresa(null, null, null, null);
        assertThat(empresaNull.toString(), containsString(""));
    }

    /**
     * To string deve retornar valores preenchidos.
     */
    @Test
    public void toString_deve_retornar_valores_preenchidos() {
        empresa = Fixture.from(Empresa.class).gimme("valido");
        assertThat(empresa.toString(), is(empresa.toString()));
    }

    /**
     * Deve validar cnpj annotations.
     */
    @Test
    public void deve_validar_cnpj_annotations() {
        Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getCnpj()));
    }

    /**
     * Deve validar nome annotations.
     */
    @Test
    public void deve_validar_nome_annotations() {
        Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getNome()));
    }

    /**
     * Deve validar site annotations.
     */
    @Test
    public void deve_validar_site_annotations() {
        Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getSite()));
    }

    /**
     * Deve validar telefones annotations.
     */
    @Test
    public void deve_validar_telefones_annotations() {
        Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getTelefone()));
    }

    /**
     * Deve validar enderecos annotations.
     */
    @Test
    public void deve_validar_enderecos_annotations() {
        Empresa cadastroValidator = Fixture.from(Empresa.class).gimme("valido");
        assertFalse(Annotations.MensagemErroAnnotation(cadastroValidator.getEndereco()));
    }

    /**
     * Tear down.
     */
    @After
    public void TearDown() {

    }

    /**
     * Tear down after class.
     */
    @AfterClass
    public static void TearDownAfterClass() {
        System.out.println(empresa);
        System.out.println("Finalizamos os testes na classe empresa\n");
        System.out.println("-----/-----/-----/-----/-----/-----/-----\n");
    }

}

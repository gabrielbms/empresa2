package br.com.contmatic.util;

import java.math.BigDecimal;
import java.util.Random;

import org.joda.time.LocalDate;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.Estado;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDD;
import br.com.contmatic.telefone.TipoTelefone;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * A factory for creating EmpresaFixture objects.
 */
public class EmpresaFixtureFactory implements TemplateLoader {

    /**
     * Load.
     */
    @Override
    public void load() {
        Fixture.of(Cliente.class).addTemplate("valido", new Rule() {
            {
                add("cpf", random("339.655.101-00", "012.835.133-07", "924.421.582-99", "816.454.121-48"));
                add("nome", random("Gabriel", "Bueno"));
                add("email", random("gabriel@hotmail.com", "bueno@gmail.com", "julia@bol.com.br"));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("boleto", BigDecimal.valueOf(250.00));
                add("informacaoInutil", (null));
            }
        });

        Fixture.of(Empresa.class).addTemplate("valido", new Rule() {
            {
                add("cnpj", random("21.298.596/0001-28", "71.388.515/0001-59", "13.934.077/0001-80"));
                add("nome", random("Gabriel", "Bueno"));
                add("site", random("http://www.gbconsertos.com.br", "http://www.gbconsertos.com"));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("enderecos", has(1).of(Endereco.class, "valido"));
            }
        });

        Fixture.of(Fornecedor.class).addTemplate("valido", new Rule() {
            {
                add("cnpj", random("36.621.217/0001-66", "59.660.254/0001-89", "81.398.148/0001-28"));
                add("nome", random("Gabriel", "Gustavo"));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("produto", random("5 placas maes", "2 processador intel I3"));
                add("enderecos", has(1).of(Endereco.class, "valido"));
            }
        });

        Fixture.of(Funcionario.class).addTemplate("valido", new Rule() {
            {
                add("cpf", random("339.655.101-00", "012.835.133-07", "924.421.582-99", "816.454.121-48"));
                add("nome", random("Gabriel", "Gustavo"));
                add("idade", random(02, 05, 10, 15, 20));
                add("telefones", has(1).of(Telefone.class, "valido"));
                add("enderecos", has(1).of(Endereco.class, "valido"));
                add("salario", BigDecimal.valueOf(2500.00));
                add("dataContratacao", random(new LocalDate(2019, 05, 05)));
                add("dataSalario", random(new LocalDate(2020, 05, 05), new LocalDate(2020, 06, 06), new LocalDate(2020, 07, 07)));

            }
        });

        Fixture.of(Endereco.class).addTemplate("valido", new Rule() {
            {
                add("cep", random("08121-019", "04570-050", "02243-140", "02243-140"));
                add("rua", random("Rua Padre Estevão Pernet", "Av Conselheiro Carrão"));
                add("numero", random(02, 05, 10, 15, 20));
                add("complemento", random("Apartamento", "SN"));
                add("bairro", random("Tatuapé", "Belém", "Carrão"));
                add("cidade", "São Paulo");
                add("estado", (Estado.values()[new Random().nextInt(27)]));
            }
        });

        Fixture.of(Telefone.class).addTemplate("valido", new Rule() {
            {
                add("ddd", (TelefoneDDD.values()[new Random().nextInt(66)]));
                add("numero", random("906257327", "918556091", "918556091", "68700598", "84792823", "32005976"));
                add("tipoTelefone", (TipoTelefone.values()[new Random().nextInt(2)]));
            }
        });

    }
}

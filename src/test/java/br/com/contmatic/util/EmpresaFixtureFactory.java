package br.com.contmatic.util;

import java.math.BigDecimal;
import java.util.Random;

import org.joda.time.LocalDate;

import br.com.contmatic.empresa.Cliente;
import br.com.contmatic.empresa.Empresa;
import br.com.contmatic.empresa.Fornecedor;
import br.com.contmatic.empresa.Funcionario;
import br.com.contmatic.empresa.Produto;
import br.com.contmatic.endereco.Endereco;
import br.com.contmatic.endereco.EstadoType;
import br.com.contmatic.telefone.Telefone;
import br.com.contmatic.telefone.TelefoneDDDType;
import br.com.contmatic.telefone.TelefoneType;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

/**
 * A factory for creating EmpresaFixture objects.
 * 
 * @author gabriel.santos
 */
public class EmpresaFixtureFactory implements TemplateLoader {

	/**
	 * Load.
	 */
	@Override
	public void load() {
		Fixture.of(Cliente.class).addTemplate("valido", new Rule() {
			{
				add("cpf", random("39609538878", "53268874878", "43503105840", "80790840847"));
				add("nome", random("Gabriel Bueno", "Gustavo Manhani", "Guilherme Santos"));
				add("email", random("gabriel@hotmail.com", "bueno@gmail.com", "manhani@bol.com.br"));
				add("telefones", has(1).of(Telefone.class, "valido"));
				add("boleto", BigDecimal.valueOf(200.00));
			}
		});

		Fixture.of(Empresa.class).addTemplate("valido", new Rule() {
			{
				add("cnpj", random("21298596000128", "00682547000114", "43783294000188", "95635374000190"));
				add("nome", random("GB suporte de TI", "Bueno Developers", "Santos Company"));
				add("site", random("http://www.gbconsertos.com.br", "www.buenodevs.com", "www.companysantos.com.br"));
				add("telefones", has(1).of(Telefone.class, "valido"));
				add("enderecos", has(1).of(Endereco.class, "valido"));
			}
		});

		Fixture.of(Fornecedor.class).addTemplate("valido", new Rule() {
			{
				add("cnpj", random("16790259000113", "74134048000129", "87449010000197"));
				add("nome", random("Intel HD Graphics", "Amd Ryzen Series", "Geforce Shadow"));
				add("telefones", has(1).of(Telefone.class, "valido"));
				add("produto",  has(1).of(Produto.class, "valido"));
				add("enderecos", has(1).of(Endereco.class, "valido"));
			}
		});

		Fixture.of(Funcionario.class).addTemplate("valido", new Rule() {
			{
				add("cpf", random("23177895805", "70413374807", "12642295850", "71183950802"));
				add("nome", random("Gabriel Bueno", "Lucas Sadboy", "Vinicius Santos"));
				add("idade", random(20, 25, 30, 35, 40));
				add("telefones", has(1).of(Telefone.class, "valido"));
				add("enderecos", has(1).of(Endereco.class, "valido"));
				add("salario", random(BigDecimal.valueOf(2500.00), BigDecimal.valueOf(3000.00), BigDecimal.valueOf(3500.00), BigDecimal.valueOf(4000.00)));
				add("dataContratacao", random(new LocalDate(2019, 05, 05), new LocalDate(2019, 06, 06), new LocalDate(2019, 07, 07), new LocalDate(2019, 04, 04)));
				add("dataSalario", random(new LocalDate(2021, 05, 05), new LocalDate(2021, 06, 06), new LocalDate(2021, 07, 07), new LocalDate(2021, 04, 04)));
			}
		});
		
		Fixture.of(Produto.class).addTemplate("valido", new Rule() {
			{
				add("id", random(1, 2, 3, 4, 5, 6));
				add("nome", random("Ryzen 5 2600", "Intel i5 7200k", "Geforce 1060TI", "HD 1TB Barracuda Seagate"));
				add("quantidade", random(1, 2, 3, 4, 5, 6));
				add("preco", random(BigDecimal.valueOf(500.00), BigDecimal.valueOf(1000.00), BigDecimal.valueOf(1500.00)));

			}
		});

		Fixture.of(Endereco.class).addTemplate("valido", new Rule() {
			{
				add("cep", random("08121019", "04570050", "02243140", "02243140"));
				add("rua", random("Rua Padre Estevão Pernet", "Av Conselheiro Carrão", "Av Paulista", "Rua São João"));
				add("numero", random(02, 05, 10, 15, 20));
				add("complemento", random("Apartamento", "SN", "Nada Consta", "Bloco 04"));
				add("bairro", random("Tatuapé", "Belém", "Carrão"));
				add("cidade", "São Paulo");
				add("estado", (EstadoType.values()[new Random().nextInt(27)]));
			}
		});

		Fixture.of(Telefone.class).addTemplate("valido", new Rule() {
			{
				add("ddd", (TelefoneDDDType.values()[new Random().nextInt(66)]));
				add("numero", random("987451245", "987456987", "987124536", "21242526", "27284515", "32541527"));
				add("tipoTelefone", (TelefoneType.values()[new Random().nextInt(2)]));
			}
		});

	}
}

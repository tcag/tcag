package vendaDeApartamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import transferir.TransferenciaDeArquivos;

public class Venda {

	protected String dataDaVenda;
	protected double entrada;
	protected double fgts;
	protected int parcelas;
	protected String msErro;

	Imovel im = new Imovel();

	public Venda() {
		super();
	}

	public Venda(String dataDaVenda, double entrada, double fgts, int parcelas, Imovel im) {
		super();
		this.dataDaVenda = dataDaVenda;
		this.entrada = entrada;
		this.fgts = fgts;
		this.parcelas = parcelas;
		this.im = im;
	}

	public String getDataDaVenda() {
		return dataDaVenda;
	}

	public void setDataDaVenda(String dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}

	public double getEntrada() {
		return entrada;
	}

	public void setEntrada(double entrada) {
		this.entrada = entrada;
	}

	public double getFgts() {
		return fgts;
	}

	public void setFgts(double fgts) {
		this.fgts = fgts;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

//LOCALIZAR FUNCIONARIO E CLIENTE. SE RESULTADO VERDADEIRO EFETUA VENDA.
	public boolean vendaApt(String cFuncionario, String cCliente, String cVenda, String nomeProcurado) {

		try {
			BufferedReader brFuncionario = new BufferedReader(new FileReader(cFuncionario));
			BufferedReader brCliente = new BufferedReader(new FileReader(cCliente));
			BufferedWriter bwCadastro = new BufferedWriter(new FileWriter(cVenda, true));
			// LOOP PARA LOCALIZAR O NOME DO FUNCIONARIO DENTRO DO ARQUIVO
			while (brFuncionario.ready()) {
				String linha = brFuncionario.readLine();
				String arrayFuncionario[] = linha.split("&");

				String nomeCompleto = arrayFuncionario[0];
				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {

					String cpf = arrayFuncionario[1];
					String rg = arrayFuncionario[2];
					String dataDeNascimento = arrayFuncionario[3];
					String endereco = arrayFuncionario[4];
					String bairro = arrayFuncionario[5];
					String cidade = arrayFuncionario[6];
					String uf = arrayFuncionario[7];
					String nrTelefone = arrayFuncionario[8];
					String dataDeCadastro = arrayFuncionario[9];
					String funcao = arrayFuncionario[10];
					double salario = Double.parseDouble(arrayFuncionario[11]);
					String tipoDeFicha = arrayFuncionario[12];

					bwCadastro.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco + "&"
							+ bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&" + funcao
							+ "&" + salario + "&" + tipoDeFicha + "&" + im.getNomeDoCondominio() + "&" + im.getBloco()
							+ "&" + im.getApartamento() + "&" + im.getValorDoApartamento() + "&" + dataDaVenda + "&"
							+ entrada + "&" + fgts + "&" + parcelas + "&" + im.getSituacao());

					bwCadastro.newLine();
					bwCadastro.close();
					brCliente.close();
					brFuncionario.close();
					return true;
				}

			}
			// LOOP PARA LOCALIZAR O NOME DO CLIENTE DENTRO DO ARQUIVO
			while (brCliente.ready()) {
				String linha = brCliente.readLine();
				String arrayCliente[] = linha.split("&");

				String nomeCompleto = arrayCliente[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {

					String cpf = arrayCliente[1];
					String rg = arrayCliente[2];
					String dataDeNascimento = arrayCliente[3];
					String endereco = arrayCliente[4];
					String bairro = arrayCliente[5];
					String cidade = arrayCliente[6];
					String uf = arrayCliente[7];
					String nrTelefone = arrayCliente[8];
					String dataDeCadastro = arrayCliente[9];
					String funcao = arrayCliente[10];
					double salario = Double.parseDouble(arrayCliente[11]);
					String tipoDeFicha = arrayCliente[12];

					bwCadastro.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco + "&"
							+ bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&" + funcao
							+ "&" + salario + "&" + tipoDeFicha + "&" + im.getNomeDoCondominio() + "&" + im.bloco + "&"
							+ im.getApartamento() + "&" + im.getValorDoApartamento() + "&" + dataDaVenda + "&" + entrada
							+ "&" + fgts + "&" + parcelas + "&" + im.getSituacao());

					bwCadastro.newLine();
					bwCadastro.close();
					brCliente.close();
					brFuncionario.close();
					return true;
				}
			}

			brCliente.close();
			brFuncionario.close();
			bwCadastro.close();

		} catch (

		Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;
	}

	// LIBERAR DESCONTO NO APARTAMENTO VENDIDO
	public boolean liberarDesconto(String cVenda, String nomeProcurado, int desconto) {
		try {
			BufferedReader brVenda = new BufferedReader(new FileReader(cVenda));
			String arquivoTemp = cVenda.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			while (brVenda.ready()) {
				String linha = brVenda.readLine().trim();
				String arrayVenda[] = linha.trim().split("&");

				String nomeCompleto = arrayVenda[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					String cpf = arrayVenda[1];
					String rg = arrayVenda[2];
					String dataDeNascimento = arrayVenda[3];
					String endereco = arrayVenda[4];
					String bairro = arrayVenda[5];
					String cidade = arrayVenda[6];
					String uf = arrayVenda[7];
					String nrTelefone = arrayVenda[8];
					String dataDeCadastro = arrayVenda[9];
					String funcao = arrayVenda[10];
					double salario = Double.parseDouble(arrayVenda[11]);
					String tipoDeFicha = arrayVenda[12];
					String nomeDoCondominio = arrayVenda[13];
					int bloco = Integer.parseInt(arrayVenda[14]);
					int apartamento = Integer.parseInt(arrayVenda[15]);
					double valorDoApartamento = Double.parseDouble(arrayVenda[16]);
					String dataDaVenda = arrayVenda[17];
					double entrada = Double.parseDouble(arrayVenda[18]);
					double fgts = Double.parseDouble(arrayVenda[19]);
					int parcelas = Integer.parseInt(arrayVenda[20]);
					String situacao = arrayVenda[21];

					double ajuste = valorDoApartamento * desconto / 100;
					valorDoApartamento = valorDoApartamento - ajuste;

					bwTemporario.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco
							+ "&" + bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
							+ funcao + "&" + salario + "&" + tipoDeFicha + "&" + nomeDoCondominio + "&" + bloco + "&"
							+ apartamento + "&" + valorDoApartamento + "&" + dataDaVenda + "&" + entrada + "&" + fgts
							+ "&" + parcelas + "&" + situacao);

					bwTemporario.newLine();

				} else {
					bwTemporario.write(linha);
					bwTemporario.newLine();
				}
			}
			bwTemporario.close();
			brVenda.close();

			// transferirArquivo(arquivoTemp, cVenda);
			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cVenda);

		} catch (

		Exception e) {

			this.msErro = "Erro de Exception:" + e.toString();

		}
		return false;
	}

	// REPROVAR VENDA - METODO CHAMADO DENTRO DE FINACIMENTO
	public boolean reprovarVenda(String cVenda, String nomeProcurado) {
		try {
			BufferedReader brVenda = new BufferedReader(new FileReader(cVenda));
			String arquivoTemp = cVenda.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			while (brVenda.ready()) {
				String linha = brVenda.readLine().trim();
				String arrayVenda[] = linha.trim().split("&");

				String nomeCompleto = arrayVenda[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					String cpf = arrayVenda[1];
					String rg = arrayVenda[2];
					String dataDeNascimento = arrayVenda[3];
					String endereco = arrayVenda[4];
					String bairro = arrayVenda[5];
					String cidade = arrayVenda[6];
					String uf = arrayVenda[7];
					String nrTelefone = arrayVenda[8];
					String dataDeCadastro = arrayVenda[9];
					String funcao = arrayVenda[10];
					double salario = Double.parseDouble(arrayVenda[11]);
					String tipoDeFicha = arrayVenda[12];
					String nomeDoCondominio = arrayVenda[13];
					int bloco = Integer.parseInt(arrayVenda[14]);
					int apartamento = Integer.parseInt(arrayVenda[15]);
					double valorDoApartamento = Double.parseDouble(arrayVenda[16]);
					String dataDaVenda = arrayVenda[17];
					double entrada = Double.parseDouble(arrayVenda[18]);
					double fgts = Double.parseDouble(arrayVenda[19]);
					int parcelas = Integer.parseInt(arrayVenda[20]);
					String situacao = arrayVenda[21];

					situacao = "VENDA REPROVADA";

					bwTemporario.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco
							+ "&" + bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
							+ funcao + "&" + salario + "&" + tipoDeFicha + "&" + nomeDoCondominio + "&" + bloco + "&"
							+ apartamento + "&" + valorDoApartamento + "&" + dataDaVenda + "&" + entrada + "&" + fgts
							+ "&" + parcelas + "&" + situacao);

					bwTemporario.newLine();

				} else {
					bwTemporario.write(linha);
					bwTemporario.newLine();
				}
			}
			bwTemporario.close();
			brVenda.close();

			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cVenda);

		} catch (

		Exception e) {

			this.msErro = "Erro de Exception:" + e.toString();

		}
		return false;
	}

}

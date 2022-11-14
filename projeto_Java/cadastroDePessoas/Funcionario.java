package cadastroDePessoas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import transferir.TransferenciaDeArquivos;

public class Funcionario extends Pessoa {
	protected String departamento;
	protected String grupoDePagamento;

	public Funcionario() {
		super();
	}

	public Funcionario(String nomeCompleto, String cpf, String rg, Date dataDeNascimento, String endereco,
			String bairro, String cidade, String uf, String nrTelefone, Date dataDeCadastro, String funcao,
			double salario, String tipoDeFicha, String departamento, String grupoDePagamento) {
		super(nomeCompleto, cpf, rg, dataDeNascimento, endereco, bairro, cidade, uf, nrTelefone, dataDeCadastro, funcao,
				salario, tipoDeFicha);
		this.departamento = departamento;
		this.grupoDePagamento = grupoDePagamento;
	}

	// CADASTRO DE FUNCIONARIO
	public boolean cadasatraFC(String cFuncionario) {
		try {

			if (nomeCompleto.isEmpty()) {
				this.msErro = "O campo nome está vazio";
				return false;
			} else if (cpf.isEmpty()) {
				this.msErro = "O campo CPF está vazio";
				return false;
			} else if (rg.isEmpty()) {
				this.msErro = "O campo RG está vazio";
				return false;
			}  else if (endereco.isEmpty()) {
				this.msErro = "O campo endereço está vazio";
				return false;
			} else if (bairro.isEmpty()) {
				this.msErro = "O campo bairro está vazio";
				return false;
			} else if (cidade.isEmpty()) {
				this.msErro = "O campo cidade está vazio";
				return false;
			} else if (uf.isEmpty()) {
				this.msErro = "O campo UF está vazio";
				return false;
			} else if (nrTelefone.isEmpty()) {
				this.msErro = "O campo número de telefone está vazio";
				return false;
			}  else if (funcao.isEmpty()) {
				this.msErro = "O campo função está vazio";
			} else if (salario <= 0) {
				this.msErro = "O campo salário de telefone está vazio";
				return false;
			} else if (tipoDeFicha.isEmpty()) {
				this.msErro = "O campo tipo de ficha está vazio";
				return false;
			} else if (departamento.isEmpty()) {
				this.msErro = "O campo departamento está vazio";
				return false;
			} else if (grupoDePagamento.isEmpty()) {
				this.msErro = "O campo grupo de pagamento está vazio";
				return false;
			} else {

				BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(cFuncionario, true));
				bwTemporario.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco + "&"
						+ bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&" + funcao
						+ "&" + salario + "&" + tipoDeFicha + "&" + departamento + "&" + grupoDePagamento);
				bwTemporario.newLine();
				bwTemporario.close();
				return true;
			}
		} catch (

		Exception e) {

			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

	// AUMENTAR SALÁRIO
	@Override
	public boolean editarSalario(String cFuncionario, String nomeProcurado, int novoSalario) {
		try {
			BufferedReader brFuncioario = new BufferedReader(new FileReader(cFuncionario));
			String arquivoTemp = cFuncionario.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			while (brFuncioario.ready()) {
				String linha = brFuncioario.readLine();
				String arrayFuncionario[] = linha.split("&");
				this.nomeCompleto = arrayFuncionario[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {

					this.cpf = arrayFuncionario[1];
					this.rg = arrayFuncionario[2];
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					this.dataDeNascimento = formato.parse(arrayFuncionario[3]);
					this.endereco = arrayFuncionario[4];
					this.bairro = arrayFuncionario[5];
					this.cidade = arrayFuncionario[6];
					this.uf = arrayFuncionario[7];
					this.nrTelefone = arrayFuncionario[8];
					Date date = new Date();
					this.dataDeCadastro = date;
					this.funcao = arrayFuncionario[10];
					this.salario = Double.parseDouble(arrayFuncionario[11]);
					this.tipoDeFicha = arrayFuncionario[12];
					this.departamento = arrayFuncionario[13];
					this.grupoDePagamento = arrayFuncionario[14];

					double ajuste = salario * novoSalario / 100;

					salario = ajuste + salario;
					bwTemporario.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco
							+ "&" + bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
							+ funcao + "&" + salario + "&" + tipoDeFicha + "&" + departamento + "&" + grupoDePagamento);
					bwTemporario.newLine();
				} else {
					bwTemporario.write(linha);
					bwTemporario.newLine();

				}
			}
			brFuncioario.close();
			bwTemporario.close();

			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cFuncionario);

		} catch (

		Exception e) {

			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

	// LOCALIZAR E EDITAR O NOME NO ARQUIVO
	public boolean editarNomeFuncionario(String cFuncionario, String nomeProcurado, String novoNome) {
		try {
			BufferedReader brFuncioario = new BufferedReader(new FileReader(cFuncionario));
			String arquivoTemp = cFuncionario.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			while (brFuncioario.ready()) {
				String linha = brFuncioario.readLine();
				String arrayFuncionario[] = linha.split("&");
				this.nomeCompleto = arrayFuncionario[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {

					this.cpf = arrayFuncionario[1];
					this.rg = arrayFuncionario[2];
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					this.dataDeNascimento = formato.parse(arrayFuncionario[3]);
					this.endereco = arrayFuncionario[4];
					this.bairro = arrayFuncionario[5];
					this.cidade = arrayFuncionario[6];
					this.uf = arrayFuncionario[7];
					this.nrTelefone = arrayFuncionario[8];
					Date date = new Date();
					this.dataDeCadastro = date;
					this.funcao = arrayFuncionario[10];
					this.salario = Double.parseDouble(arrayFuncionario[11]);
					this.tipoDeFicha = arrayFuncionario[12];
					this.departamento = arrayFuncionario[13];
					this.grupoDePagamento = arrayFuncionario[14];

					this.nomeCompleto = novoNome;

					bwTemporario.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco
							+ "&" + bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
							+ funcao + "&" + salario + "&" + tipoDeFicha + "&" + departamento + "&" + grupoDePagamento);
					bwTemporario.newLine();
				} else {
					bwTemporario.write(linha);
					bwTemporario.newLine();

				}

			}

			bwTemporario.close();
			brFuncioario.close();

			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cFuncionario);

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

	// LOCALIZAR E EXCLUIR A LINHA REFERENTE O NOME NO ARQUIVO
	public boolean excluirNome(String cFuncionario, String nomeProcurado) {
		try {
			BufferedReader brFuncioario = new BufferedReader(new FileReader(cFuncionario));
			String arquivoTemp = cFuncionario.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			while (brFuncioario.ready()) {

				String linha = brFuncioario.readLine();
				String arrayFuncionario[] = linha.split("&");

				this.nomeCompleto = arrayFuncionario[0];

				if (!nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					bwTemporario.write(linha);
					bwTemporario.newLine();
				}

			}
			brFuncioario.close();
			bwTemporario.close();

			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cFuncionario);

		} catch (

		Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;
	}

}

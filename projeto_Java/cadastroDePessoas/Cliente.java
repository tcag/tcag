package cadastroDePessoas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import transferir.TransferenciaDeArquivos;

public class Cliente extends Pessoa {

	public Cliente() {
		super();

	}

	public Cliente(String nomeCompleto, String cpf, String rg, Date dataDeNascimento, String endereco, String bairro,
			String cidade, String uf, String nrTelefone, Date dataDeCadastro, String funcao, double salario,
			String tipoDeFicha) {
		super(nomeCompleto, cpf, rg, dataDeNascimento, endereco, bairro, cidade, uf, nrTelefone, dataDeCadastro, funcao,
				salario, tipoDeFicha);

	}

//CADASTRAR CLIENTE
	public boolean cadasatraCL(String cCliente) {
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
				this.msErro = "O campo númeo de telefone está vazio";
				return false;
			}  else if (funcao.isEmpty()) {
				this.msErro = "O campo funcao está vazio";
				return false;
			} else if (salario <= 0) {
				this.msErro = "O campo salário está vazio";
				return false;
			} else if (tipoDeFicha.isEmpty()) {
				this.msErro = "O campo tipo de ficha está vazio";
				return false;
			} else {

				BufferedWriter bwCadastro = new BufferedWriter(new FileWriter(cCliente, true));
				bwCadastro.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco + "&"
						+ bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&" + funcao
						+ "&" + salario + "&" + tipoDeFicha);
				bwCadastro.newLine();
				bwCadastro.close();
				return true;
			}
		} catch (Exception e) {
			
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

	// LOCALIZAR E EDITAR O NOME NO ARQUIVO
	public boolean editarNomeCliente(String cCliente, String nomeProcurado, String novoNome) {
		try {
			BufferedReader brCliente = new BufferedReader(new FileReader(cCliente));
			String arquivoTemp = cCliente.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			while (brCliente.ready()) {
				String linha = brCliente.readLine();
				String arrayCliente[] = linha.split("&");
				this.nomeCompleto = arrayCliente[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					this.cpf = arrayCliente[1];
					this.rg = arrayCliente[2];
					SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
					this.dataDeNascimento = formato.parse(arrayCliente[3]);
					this.endereco = arrayCliente[4];
					this.bairro = arrayCliente[5];
					this.cidade = arrayCliente[6];
					this.uf = arrayCliente[7];
					this.nrTelefone = arrayCliente[8];
					Date date = new Date();
					this.dataDeCadastro = date;
					this.funcao = arrayCliente[10];
					this.salario = Double.parseDouble(arrayCliente[11]);
					this.tipoDeFicha = arrayCliente[12];

					this.nomeCompleto = novoNome;

					bwTemporario.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco
							+ "&" + bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
							+ funcao + "&" + salario + "&" + tipoDeFicha);
					bwTemporario.newLine();
				} else {
					bwTemporario.write(linha);
					bwTemporario.newLine();
				}
			}

			bwTemporario.close();
			brCliente.close();

			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cCliente);

		} catch (Exception e) {
			
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

	// LOCALIZAR E EXCLUIR A LINHA REFERENTE O NOME NO ARQUIVO
	public boolean excluirNome(String cCliente, String nomeProcurado) {
		try {
			BufferedReader brCliente = new BufferedReader(new FileReader(cCliente));
			String arquivoTemp = cCliente.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));
			while (brCliente.ready()) {
				String linha = brCliente.readLine();
				String arrayCliente[] = linha.split("&");
				this.nomeCompleto = arrayCliente[0];

				if (!nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					bwTemporario.write(linha);
					bwTemporario.newLine();
				}
			}
			brCliente.close();
			bwTemporario.close();

			TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cCliente);

		} catch (

		Exception e) {
			e.printStackTrace();
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

}

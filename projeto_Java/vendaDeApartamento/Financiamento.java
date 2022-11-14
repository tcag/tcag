package vendaDeApartamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import transferir.TransferenciaDeArquivos;

public class Financiamento {
	protected double valorParcela;
	protected double juros;
	protected double total;
	protected String msErro;
	
	Venda venda=new Venda();
	
	public Financiamento() {
		super();
	}

	public Financiamento(double valorParcela, double juros, double total) {
		super();
		this.valorParcela = valorParcela;
		this.juros = juros;
		this.total = total;

	}

	public double getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(double valorParcela) {
		this.valorParcela = valorParcela;
	}

	public double getJuros() {
		return juros;
	}

	public void setJuros(double juros) {
		this.juros = juros;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean gerarFinanciamento(String cVenda, String cFinanciamento, String cImovel, String nomeProcurado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cVenda));
			BufferedWriter bw = new BufferedWriter(new FileWriter(cFinanciamento, true));

			while (br.ready()) {
				String linha = br.readLine().trim();
				String arrayvF[] = linha.trim().split("&");

				String nomeCompleto = arrayvF[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					String cpf = arrayvF[1];
					String rg = arrayvF[2];
					String dataDeNascimento = arrayvF[3];
					String endereco = arrayvF[4];
					String bairro = arrayvF[5];
					String cidade = arrayvF[6];
					String uf = arrayvF[7];
					String nrTelefone = arrayvF[8];
					String dataDeCadastro = arrayvF[9];
					String funcao = arrayvF[10];
					double salario = Double.parseDouble(arrayvF[11]);
					String tipoDeFicha = arrayvF[12];
					String nomeDoCondominio = arrayvF[13];
					int bloco = Integer.parseInt(arrayvF[14]);
					int apartamento = Integer.parseInt(arrayvF[15]);
					double valorDoApartamento = Double.parseDouble(arrayvF[16]);
					String dataDaVenda = arrayvF[17];
					double entrada = Double.parseDouble(arrayvF[18]);
					double fgts = Double.parseDouble(arrayvF[19]);
					int parcelas = Integer.parseInt(arrayvF[20]);
					String situacao = arrayvF[21];

					double valor = valorDoApartamento - entrada - fgts;
					this.valorParcela = valor / parcelas;
					double total1 = 0;
					double valor1 = 0;

					if (parcelas == 300) {
						juros = 15;
						double novaParcela = valorParcela * juros / 100;
						valor1 = valorParcela + valorParcela + novaParcela;
						total1 = valorParcela * parcelas;
					} else if (parcelas == 400) {
						juros = 30;
						double novaParcela = valorParcela * juros / 100;
						valor1 = valorParcela + valorParcela + novaParcela;
						total1 = valorParcela * parcelas;
					} else if (parcelas == 500) {
						juros = 45;
						double novaParcela = valorParcela * juros / 100;
						valor1 = valorParcela + valorParcela + novaParcela;
						total1 = valorParcela * parcelas;
					}
					this.valorParcela = valor1;
					this.total = total1;

					double media = salario * 30 / 100;

					// PARA CANCELAR A VENDA DO APT
					int cancelarApt = Integer.parseInt(arrayvF[15]);

					if (valorParcela > media) {

						situacao = "REPROVADO";

						bw.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco + "&"
								+ bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
								+ funcao + "&" + salario + "&" + tipoDeFicha + "&" + nomeDoCondominio + "&" + bloco
								+ "&" + apartamento + "&" + valorDoApartamento + "&" + dataDaVenda + "&" + entrada + "&"
								+ fgts + "&" + parcelas + "&" + valorParcela + "&" + juros + "&" + total + "&"
								+ situacao);
						bw.newLine();
						bw.close();
						if (situacao.equalsIgnoreCase("REPROVADO")) {
							BufferedReader brImovel = new BufferedReader(new FileReader(cImovel));
							String arquivoTemp = cImovel.replace(".txt", "temp.txt");
							BufferedWriter bwImovel = new BufferedWriter(new FileWriter(arquivoTemp));

							while (brImovel.ready()) {
								String linhaImovel = brImovel.readLine();
								String arrayI[] = linhaImovel.split("&");

								String imCondominio = arrayI[0];
								int imBloco = Integer.parseInt(arrayI[1]);
								int imApartamento = Integer.parseInt(arrayI[2]);
								double imValorApt = Double.parseDouble(arrayI[3]);
								String imSituacao = arrayI[4];

								if (imApartamento == cancelarApt) {
									imSituacao = "DISPONIVEL";
									bwImovel.write(imCondominio + "&" + imBloco + "&" + imApartamento + "&" + imValorApt
											+ "&" + imSituacao);
									venda.reprovarVenda(cVenda, nomeCompleto);
								} else {
									bwImovel.write(linhaImovel);
									bwImovel.newLine();

								}

							}
							bwImovel.close();
							brImovel.close();
							TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cImovel);
						}

					} else if (valorParcela <= media) {
						situacao = "APROVADO";

						bw.write(nomeCompleto + "&" + cpf + "&" + rg + "&" + dataDeNascimento + "&" + endereco + "&"
								+ bairro + "&" + cidade + "&" + uf + "&" + nrTelefone + "&" + dataDeCadastro + "&"
								+ funcao + "&" + salario + "&" + tipoDeFicha + "&" + nomeDoCondominio + "&" + bloco
								+ "&" + apartamento + "&" + valorDoApartamento + "&" + dataDaVenda + "&" + entrada + "&"
								+ fgts + "&" + parcelas + "&" + valorParcela + "&" + juros + "&" + total + "&"
								+ situacao);

						bw.newLine();
						bw.close();
					} else {
						bw.write(linha);
						bw.newLine();

					}

				}
			}

			br.close();

		} catch (Exception e) {

			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;
	}

	public boolean relatorio(String cFinanciamento, String nomeProcurado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cFinanciamento));

			while (br.ready()) {
				String linha = br.readLine().trim();
				String arrayvF[] = linha.trim().split("&");

				String nomeCompleto = arrayvF[0];

				if (nomeCompleto.equalsIgnoreCase(nomeProcurado)) {
					String cpf = arrayvF[1];
					String rg = arrayvF[2];
					String dataDeNascimento = arrayvF[3];
					double salario = Double.parseDouble(arrayvF[11]);
					String nomeDoCondominio = arrayvF[13];
					int bloco = Integer.parseInt(arrayvF[14]);
					int apartamento = Integer.parseInt(arrayvF[15]);
					double valorDoApartamento = Double.parseDouble(arrayvF[16]);
					String dataDaVenda = arrayvF[17];
					double entrada = Double.parseDouble(arrayvF[18]);
					double fgts = Double.parseDouble(arrayvF[19]);
					int parcelas = Integer.parseInt(arrayvF[20]);
					double valorParcela = Double.parseDouble(arrayvF[21]);
					String situacao = arrayvF[24];
					System.out.println("\t FINANCIAMENTO");
					System.out.println("-------------------------------------");
					System.out.println("Nome Completo: " + nomeCompleto);
					System.out.println("CPF: " + cpf);
					System.out.println("RG: " + rg);
					System.out.println("Data de Nascimento: " + dataDeNascimento);
					System.out.println("Salario: " + salario);
					System.out.println("Condominio: " + nomeDoCondominio);
					System.out.println("Bloco: " + bloco);
					System.out.println("Apartamento: " + apartamento);
					System.out.printf("Valor do Apartamento R$: %-10.2f \n", valorDoApartamento);
					System.out.println("Data da Venda: " + dataDaVenda);
					System.out.printf("Valor de Entrada:R$: %.2f \n", entrada);
					System.out.printf("Valor de FGTS: R$: %.2f \n", fgts);
					System.out.println("Parcela: " + parcelas + " vezes");
					System.out.printf("Valor da parcela R$: %.2f \n", valorParcela);
					System.out.printf("Situação:%10s \n", situacao);
					System.out.println("-------------------------------------");
				}
			}

			br.close();

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;
	}

}

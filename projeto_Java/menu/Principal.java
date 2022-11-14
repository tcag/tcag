package menu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import cadastroDePessoas.*; //importando todas as classes de cadastroDePessoas
import vendaDeApartamento.*; //importando todas as classes de vendaDeapartamento


public class Principal {

	public static void main(String[] args) throws Exception {
		Scanner ler = new Scanner(System.in);
		// CAMINHO DE ONDE OS ARQUIVOS ARMAZENADOS
		String cPrincipal = "C:\\Users\\Tiago\\Desktop\\projetoFinal\\";
		//CAMINHOS CONCACTENADO COM O NOME DO ARQUIVO
		String cFuncao = cPrincipal+"funcao.txt";
		String cFuncionario = cPrincipal+"funcionario.txt";
		String cCliente = cPrincipal+"cliente.txt";
		String cVenda = cPrincipal+"venda.txt";
		String cImovel = cPrincipal+"imovel.txt";
		String cFinanciamento = cPrincipal+"financiamento.txt";

		String op;
		String auxiliar;
		String funcao = "";
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		do {
			menu();
			op = ler.nextLine().trim();

			// TODOS OS CASES TEM UM METODO LOCALIZAR DENTRO DE PESSOA PARA VALIDAR SE O
			// FUNCIONARIO OU CLINTE JÁ ESTÃO CADASTRADOS
			// CASO NÃO ESTEJA CADASTRADO, FINALIZAMOS O CASE E APRESENTAMOS O MENU

			switch (op) {
			case "1": {
				System.out.println("TELA DE CADASTRO\n");

				System.out.println("Digite o seu nome completo: ");
				String nomeCompleto = ler.nextLine().trim().toUpperCase();

				System.out.println("Digite o número de CPF: ");
				String cpf = ler.nextLine().trim();

				System.out.println("Digite o número de RG: ");
				String rg = ler.nextLine().trim();

				System.out.println("Digite a data de nascimento: ");
				Date dataDeNascimento = formato.parse(ler.nextLine());

				System.out.println("Digite o endereço: ");
				String endereco = ler.nextLine().trim().toUpperCase();

				System.out.println("Digite o bairro: ");
				String bairro = ler.nextLine().trim().toUpperCase();

				System.out.println("Digite a cidade: ");
				String cidade = ler.nextLine().trim().toUpperCase();

				System.out.println("Digite o seu UF: ");
				String uf = ler.nextLine().trim().toUpperCase();

				System.out.println("Digite o seu número de telefone: ");
				String nrTelefone = ler.nextLine().trim().toUpperCase();

				System.out.println("Entre com a data de cadastro ");
				Date date = new Date();
				Date dataDeCadastro = date;

				System.out.println("\nSelecione a função abaixo: ");
				// MENU SELEÇÃO FUNÇÃO
				Pessoa pe = new Pessoa();
				pe.listarFuncao(cFuncao);

				System.out.println("\n A função encontra-se na lista?");

				// CONFIRMAR SE A FUNÇÃO ESTÁ LISTA
				do {
					selecionar();// metodo sim ou não
					auxiliar = ler.nextLine().toLowerCase().trim();

					switch (auxiliar) {
					// SE SIM ! SELICIONAMOS A OPÇÃO REFERENTE A FUNÇÃO
					case "s": {
						System.out.println("Digite a opção referente a função");
						String aux = ler.nextLine().trim();
						pe.selecionarFuncao(cFuncao, aux);
						funcao = pe.getFuncao();
						break;
					}
					// SE NÃO ! INICIAMOS O METODO CADASTRAR FUNÇÃO PARA SEGUIR COM O CASTRO DO
					// FUNCIONARIO OU CLIENTE
					case "n": {
						System.out.println("CADASTRAR FUNÇÃO");
						System.out.println("Digite a função");
						funcao = ler.nextLine();
						pe = new Pessoa();
						pe.cadastrarFuncao(cFuncao, funcao);
						break;
					}
					default:
						System.out.println("Escolha SIM ou NÃO\n");
						break;
					}
				} while (!auxiliar.equalsIgnoreCase("s") && (!auxiliar.equalsIgnoreCase("n")));

				// PARA NÃO OCORRER ERRO QUANDO O USUARIO DEIXAR O CAMPO EM BARNCO.
				// É FEITO UMA VALIDAÇÃO EM STRING

				System.out.println("Digite o valor do salario: ");
				String sSalario = ler.nextLine();
				double salario = 0;
				if (!sSalario.isEmpty()) {
					salario = Double.parseDouble(sSalario);
				}
				// AQUI SELICONAMOS O TIPO DE PESSOA A SER CADASTRADA
				System.out.println("Escolha o tipo de ficha: ");
				String tipoDeFicha = "";

				do {
					tFicha();
					auxiliar = ler.nextLine();

					switch (auxiliar) {
					case "1": {
						tipoDeFicha = "FUNCIONARIO";
						break;
					}
					case "2": {
						tipoDeFicha = "CLIENTE";
						break;
					}

					default: {
						System.out.println("Opção inválida");

					}
					}

				} while (!auxiliar.equalsIgnoreCase("1") && (!auxiliar.equalsIgnoreCase("2")));

				// CASO FUNCIONARIO ENTRAMOS NO IF PARA FINALIZAR O CADASTRO COM MAIS OPÇÕES
				if (tipoDeFicha.equalsIgnoreCase("FUNCIONARIO")) {

					System.out.println("\nDigite o seu departamento: ");
					String departamento = ler.nextLine().trim().toUpperCase();
					System.out.println("Escolha o grupo de pagamento: ");
					String grupoDePagamento = "";
					do {
						gPagamento();
						auxiliar = ler.nextLine().trim();

						switch (auxiliar) {
						case "1": {
							grupoDePagamento = "QUINZENAL";

							break;
						}
						case "2": {
							grupoDePagamento = "MENSAL";

							break;
						}

						default: {
							System.out.println("Opção inválida");
							break;
						}
						}

					} while (!auxiliar.equalsIgnoreCase("1") && (!auxiliar.equalsIgnoreCase("2")));
					Funcionario fc = new Funcionario();
					fc = new Funcionario(nomeCompleto, cpf, rg, dataDeNascimento, endereco, bairro, cidade, uf,
							nrTelefone, dataDeCadastro, funcao, salario, tipoDeFicha, departamento, grupoDePagamento);
					if (fc.cadasatraFC(cFuncionario)) {
						System.out.println("Funcionário cadastrado com sucesso!!!");
					} else {
						System.out.println(fc.msErro);
					}
					break;

				}
				// CASO CLIENTE FINALIZAMOS COM O CADASTRO
				else {
					Cliente cl = new Cliente();
					cl = new Cliente(nomeCompleto, cpf, rg, dataDeNascimento, endereco, bairro, cidade, uf, nrTelefone,
							dataDeCadastro, funcao, salario, tipoDeFicha);
					
					if (cl.cadasatraCL(cCliente)) {
						System.out.println("Cliente cadastrado com sucesso!!!");
					} else {
						System.out.println(cl.msErro);
					}
					break;
				}

			}

			// AUMENTO DE SALARIO DO FUNCIONARIO
			case "2": {
				System.out.println("AUMENTAR SALÁRIO DO FUNCIONARIO\n");

				System.out.println("Digite o nome para aumento: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizar(cFuncionario, nomeProcurado)) {
					System.out.println("Informe percentual para aumento %:");
					int novoSalario = Integer.parseInt(ler.nextLine());
					Funcionario fc = new Funcionario();
					fc = new Funcionario();
					fc.editarSalario(cFuncionario, nomeProcurado, novoSalario);
					System.out.println("Aumento efetuado com sucesso!!!");

				} else {
					System.out.println(pe.msErro);
				}

				break;

			}
			// SUBSTITUIR O NOME COMPLETO DO FUNCIONARIO
			case "3": {
				System.out.println("EDITAR NOME DO FUNCIONÁRIO");

				System.out.println("Digite o nome a ser procurado: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarCliente(cFuncionario, nomeProcurado)) {
					System.out.println("\nDigite o novo nome completo:");
					String novoNome = ler.nextLine();
					Funcionario fc = new Funcionario();
					fc.editarNomeFuncionario(cFuncionario, nomeProcurado, novoNome);
					System.out.println("\nRegistro alterado com sucesso!");
					System.out.println("\nO nome foi substituido para: " + novoNome);
				} else {
					System.out.println(pe.msErro);
				}

				break;
			}
			// SUBSTITUIR O NOME COMPLETO DO CLIENTE
			case "4": {
				System.out.println("EDITAR NOME DO CLIENTE");

				System.out.println("Digite o nome a ser procurado: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarCliente(cCliente, nomeProcurado)) {
					System.out.println("\nDigite o novo nome completo:");
					String novoNome = ler.nextLine();
					Cliente cl = new Cliente();
					cl = new Cliente();
					cl.editarNomeCliente(cCliente, nomeProcurado, novoNome);

					System.out.println("\nRegistro alterado com sucesso!");
					System.out.println("\nO nome foi substituido para: " + novoNome);

				} else {
					System.out.println(pe.msErro);
				}

				break;
			}
			// DELETAR O CADASTRO DE UMA PESSOA TIPO FUNCIONARIO
			case "5": {
				System.out.println("DELETAR FICHA DO FUNCIONÁRIO");

				System.out.println("Digite o nome para exluir: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarCliente(cFuncionario, nomeProcurado)) {

					do {
						selecionar();
						auxiliar = ler.nextLine();
						switch (auxiliar) {
						// SE SIM ! DELETAMOS A FICHA
						case "s": {
							Funcionario fc = new Funcionario();
							fc.excluirNome(cFuncionario, nomeProcurado);
							System.out.println("Funcionario deletado com sucesso!!!");
							break;
						}
						// SE NÃO ! FECHAMOS A OPÇÃO E VOLTAMOS PARA O MENU PRINCIPAL
						case "n": {
							System.out.println("\nFuncionario não foi deletado");
							System.out.println("Voltando para menu principal");
							break;
						}
						default:
							System.out.println("Escolha SIM ou NÃO\n");
							break;
						}
					} while (!auxiliar.equalsIgnoreCase("s") && (!auxiliar.equalsIgnoreCase("n")));

				} else {
					System.out.println(pe.msErro);
				}

				break;

			}
			// DELETAR O CADASTRO DE UMA PESSOA TIPO CLINTE
			case "6": {
				System.out.println("DELETAR FICHA CLIENTE");

				System.out.println("Digite o nome a ser excluido: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarCliente(cCliente, nomeProcurado)) {
					do {
						selecionar();
						auxiliar = ler.nextLine();
						switch (auxiliar) {
						// SE SIM ! DELETAMOS A FICHA
						case "s": {
							
							Cliente cl = new Cliente();
							cl.excluirNome(cCliente, nomeProcurado);
							System.out.println("Cliente deletado com sucesso!!!");
							break;
						}
						// SE NÃO ! FECHAMOS A OPÇÃO E VOLTAMOS PARA O MENU PRINCIPAL
						case "n": {
							System.out.println("\nFuncionario não foi deletado");
							System.out.println("Voltando para menu principal");
							break;
						}
						default:
							System.out.println("Escolha SIM ou NÃO\n");
							break;
						}
					} while (!auxiliar.equalsIgnoreCase("s") && (!auxiliar.equalsIgnoreCase("n")));

				} else {
					System.out.println(pe.msErro);
				}

				break;

			}
			// CADASTRAR FUNÇÃO
			case "7": {
				System.out.println("CADASTRAR FUNÇÃO");

				System.out.println("Digite a função");
				funcao = ler.nextLine().toUpperCase().trim();
				Pessoa pe = new Pessoa();
				pe.cadastrarFuncao(cFuncao, funcao);

				System.out.println("Função cadastrada com sucesso!!!");
				break;

			}
			case "8": {
				System.out.println("EFETUAR UMA VENDA DE APARTAMENTO");
				System.out.println("Digite o nome a ser localizado: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizar(cFuncionario, cCliente, nomeProcurado)) {
					System.out.println("Digite o nome do condominio");
					String condominioProcurado = ler.nextLine().trim().toUpperCase();
					Imovel im = new Imovel();
					if (im.localizarCondominio(cImovel, condominioProcurado)) {
						String nomeDoCondominio = condominioProcurado;

						System.out.println("Digite o numero do bloco");
						int bloco = Integer.parseInt(ler.nextLine().trim().toUpperCase());

						System.out.println("Digite o numero do apartamento");
						String nrApt = ler.nextLine().trim().toUpperCase();

						if (im.selecionarApt(cImovel, nrApt)) {
							int apartamento = Integer.parseInt(nrApt); // APARTAMENTO VERIFICADO EM ESTOQUE
							double valorDoApartamento = im.getValorDoApartamento();// VALOR CADASTRADO DO APARAMENTO

							System.out.println("Informe a data da venda");
							String dataDaVenda = ler.nextLine().trim().toUpperCase();

							System.out.println("Informe o valor de entrada R$: ");
							double entrada = Double.parseDouble(ler.nextLine().trim().toUpperCase());

							System.out.println("Informe o valor do FGTS R$:");
							double fgts = Double.parseDouble(ler.nextLine().trim().toUpperCase());

							System.out.println("Escolha a quantidade de parcelas");
							int parcelas = 0;
							do {
								quantParcelas();
								auxiliar = ler.nextLine().trim().toUpperCase();
								switch (auxiliar) {
								case "1": {
									parcelas = 300;
									break;
								}
								case "2": {
									parcelas = 400;
									break;
								}
								case "3": {
									parcelas = 500;
									break;
								}

								default:
									System.out.println("Escolha umas das opções acima");
								}
							} while (!auxiliar.equalsIgnoreCase("1")
									&& (!auxiliar.equalsIgnoreCase("2") && (!auxiliar.equalsIgnoreCase("3"))));

							String situacao = "VENDIDO";
							im = new Imovel(nomeDoCondominio, bloco, apartamento, valorDoApartamento, situacao);
							Venda vd = new Venda();
							vd = new Venda(dataDaVenda, entrada, fgts, parcelas, im);
							vd.vendaApt(cFuncionario, cCliente, cVenda, nomeProcurado);
							System.out.println("Venda pré-cadastrada com sucesso!");

						} else {
							System.out.println(im.msErro);
						}
					} else {
						System.out.println(im.msErro);
					}
				} else {
					System.out.println(pe.msErro);

				}
				break;
			}
			// APLICAR DESCONTO PARA VENDA DE APARTAMENTO
			case "9": {
				System.out.println("APLICAR DESCONTO DE VENDA NO APARTAMENTO");
				System.out.println("Digite o nome a ser procurado: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarVenda(cVenda, nomeProcurado)) {
					System.out.println("Informo o percentual de desconto %:");
					int desconto = Integer.parseInt(ler.nextLine());
					Venda vd = new Venda();
					vd.liberarDesconto(cVenda, nomeProcurado, desconto);
					System.out.println("Desconto aplicado com sucesso!!!");

				} else {
					System.out.println(pe.msErro);
				}

				break;
			}

			// GERAR FINANCIAMENTO PARA APROVAÇÃO
			case "10": {
				System.out.println("GERAR FINANCIMENTO");

				System.out.println("Digite o nome a ser procurado: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarVenda(cVenda, nomeProcurado)) {
					Financiamento fin = new Financiamento();
					fin.gerarFinanciamento(cVenda, cFinanciamento, cImovel, nomeProcurado);
					System.out.println("Financiamento gerado");


				} else {
					System.out.println(pe.msErro);
				}

				break;
			}

			// RELATORIO D VENDA POR CLIENTE
			case "11": {
				System.out.println("RELATORIO DE VENDA POR CLIENTE");
				System.out.println("Digite o nome a ser procurado: ");
				String nomeProcurado = ler.nextLine();
				Pessoa pe = new Pessoa();
				if (pe.localizarFinanciamento(cFinanciamento, nomeProcurado)) {
					Financiamento fin = new Financiamento();
					fin.relatorio(cFinanciamento, nomeProcurado);

				} else {
					System.out.println(pe.msErro);
				}

				break;
			}

			// CADASTRAR UM CONDOMINIO COM APT
			case "12": {

				System.out.println("CADASTRAR IMÓVEL PARA VENDA");

				System.out.println("Digite o nome do Condominio");
				String nomeDoCondominio = ler.nextLine();

				System.out.println("Digite o numero do bloco:");
				int bloco = Integer.parseInt(ler.nextLine());

				System.out.println("Digite a quantidade de apartamentos");
				int apartamento = Integer.parseInt(ler.nextLine());

				System.out.println("Informe o valor de venda");
				double valorDoApartamento = Double.parseDouble(ler.nextLine());

				String situacao = "DISPONIVEL";
				Imovel im = new Imovel();
				im = new Imovel(nomeDoCondominio, bloco, apartamento, valorDoApartamento, situacao);
				im.gerarBloco(cImovel, apartamento);

				System.out.println("Condominio cadastrado com sucesso!!!");

				break;
			}

			case "0": {
				System.out.println("Saindo do sistema");
				break;
			}

			default: {
				System.out.println("Opção inválida");
				break;
			}
			}

		} while (!op.equalsIgnoreCase("0"));
		ler.close();

	}

	protected static void menu() {
		System.out.println("\n\t\tMenu Principal\n");
		System.out.println("Escolha uma das opções abaixo:\n");
		System.out.println("\t 1 - CADASTRAR PESSOA ");
		System.out.println("\t 2 - AUMENTAR SALÁRIO FUNCIONARIO ");
		System.out.println("\t 3 - EDITAR O NOME FUNCIONARIO");
		System.out.println("\t 4 - EDITAR O NOME CLINTE");
		System.out.println("\t 5 - EXCLUIR UMA FICHA FUNCIONARIO ");
		System.out.println("\t 6 - EXCLUIR UMA FICHA CLINTE ");
		System.out.println("\t 7 - CADASTRAR FUNÇÃO");
		System.out.println("\t 8 - EFETUAR UMA VENDA ");
		System.out.println("\t 9 - APLICAR DESCONTO NA VENDA DO APARTAMENTO ");
		System.out.println("\t 10 - GERAR FINANCIMENTO ");
		System.out.println("\t 11 - RELATORIO DE VENDA POR CLIENTE");
		System.out.println("\t 12 - CADASTRAR CONDOMINIO");
		System.out.println("\t 0 - SAIR");
	}

	protected static void gPagamento() {

		System.out.println("\t 1 QUINZENAL");
		System.out.println("\t 2 MENSAL ");

	}

	protected static void tFicha() {

		System.out.println("\t 1 FUNCIONARIO");
		System.out.println("\t 2 CLIENTE ");

	}

	public static void selecionar() {
		System.out.println("ESCOLHA UMA DAS OPÇÕES ABAIXO:\n ");
		System.out.println("S - para Sim");
		System.out.println("N - para Não");

	}

	public static void quantParcelas() {
		System.out.println("EM QUANTAS PARCELAS?");
		System.out.println("1 - 300 PARCELAS");
		System.out.println("2 - 400 PARCELAS");
		System.out.println("3 - 500 PARCELAS");
	}

}

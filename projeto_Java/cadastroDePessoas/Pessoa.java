package cadastroDePessoas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;

public class Pessoa {
	protected String nomeCompleto;
	protected String cpf;
	protected String rg;
	protected Date dataDeNascimento;
	protected String endereco;
	protected String bairro;
	protected String cidade;
	protected String uf;
	protected String nrTelefone;
	protected Date dataDeCadastro;
	protected String funcao;
	protected double salario;
	protected String tipoDeFicha;

	public String msErro;

	public Pessoa() {
		super();
	}

	public Pessoa(String nomeCompleto, String cpf, String rg, Date dataDeNascimento, String endereco, String bairro,
			String cidade, String uf, String nrTelefone, Date dataDeCadastro, String funcao, double salario,
			String tipoDeFicha) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.rg = rg;
		this.dataDeNascimento = dataDeNascimento; 
		this.endereco = endereco;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
		this.nrTelefone = nrTelefone;
		this.dataDeCadastro = dataDeCadastro;
		this.funcao = funcao;
		this.salario = salario;
		this.tipoDeFicha = tipoDeFicha;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Date dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Date getDataDeCadastro() {
		return dataDeCadastro;
	}

	public void setDataDeCadastro(Date dataDeCadastro) {
		this.dataDeCadastro = dataDeCadastro;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTipoDeFicha() {
		return tipoDeFicha;
	}

	public void setTipoDeFicha(String tipoDeFicha) {
		this.tipoDeFicha = tipoDeFicha;
	}

	// METODO CADASTRAR FUNÇÕES
	public void cadastrarFuncao(String cFuncao, String funcao) {
		try {
			BufferedReader brSequencia = new BufferedReader(new FileReader(cFuncao));
			
			int cont = 0;
			int sequencia;

			while (brSequencia.ready()) {
				brSequencia.readLine();
				cont++;
			}
			sequencia = cont + 1;
			brSequencia.close();
			BufferedWriter bwFuncao = new BufferedWriter(new FileWriter(cFuncao, true));
			bwFuncao.write(sequencia + "&" + funcao);
			bwFuncao.newLine();

			bwFuncao.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	// METODO LISTAR FUNÇÕES
	public void listarFuncao(String cFuncao) {
		try {
			BufferedReader brFuncao = new BufferedReader(new FileReader(cFuncao));

			while (brFuncao.ready()) {
				String linha = brFuncao.readLine();
				String arrayF[] = linha.split("&");

				int sequencia = Integer.parseInt(arrayF[0]);
				this.funcao = arrayF[1];

				System.out.println(sequencia + "-" + funcao);
			}

			brFuncao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// METODO SELECIONAR A FUNÇÃO DO ARQUIVO
	public boolean selecionarFuncao(String cFuncao, String aux) {
		try {
			BufferedReader brFuncao = new BufferedReader(new FileReader(cFuncao));
			int cont = 0;
			while (brFuncao.ready()) {
				String linha = brFuncao.readLine();
				String arrayF[] = linha.split("&");

				String sequencia = arrayF[0];

				if (aux.equalsIgnoreCase(sequencia)) {
					this.funcao = arrayF[1];
					System.out.println(this.funcao);
				}
				cont++;
			}

			brFuncao.close();
			if (cont == 0) {
				this.msErro = "Função não cadastrada";
				return false;
			}

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;

	}

//METODO LOCALIZAR FUNCIONARIO
	public boolean localizar(String cFuncionario, String nomeProcurado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cFuncionario));
			int cont = 0;
			while (br.ready()) {
				String linha = br.readLine();
				String arrayNome[] = linha.split("&");

				if (arrayNome[0].equalsIgnoreCase(nomeProcurado)) {
					cont++;
				}

			}
			br.close();

			if (cont == 0) {
				this.msErro = "Funcionário não localizado";
				return false;
			}

		} catch (Exception e) {
			
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;

	}

	// METODO LOCALIZAR CLIENTE
	public boolean localizarCliente(String cCliente, String nomeProcurado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cCliente));
			int cont = 0;
			while (br.ready()) {
				String linha = br.readLine();
				String arrayNome[] = linha.split("&");

				if (arrayNome[0].equalsIgnoreCase(nomeProcurado)) {
					cont++;
				}

			}
			br.close();

			if (cont == 0) {
				this.msErro = "Clinte não localizado";
				return false;
			}

		} catch (Exception e) {
			
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;

	}

	// METODO LOCALIZAR CADASTRO DE TODAS AS FICHAS (FUNCIONARIO E CLIENTE)
	public boolean localizar(String cFuncionario, String cCliente, String nomeProcurado) {
		try {
			BufferedReader brF = new BufferedReader(new FileReader(cFuncionario));
			BufferedReader brC = new BufferedReader(new FileReader(cCliente));
			int cont = 0;
			while (brF.ready()) {
				String linha = brF.readLine();
				String arrayNome[] = linha.split("&");

				if (arrayNome[0].equalsIgnoreCase(nomeProcurado)) {
					cont++;
				}

			}
			brF.close();
			while (brC.ready()) {
				String linha = brC.readLine();
				String arrayNome[] = linha.split("&");

				if (arrayNome[0].equalsIgnoreCase(nomeProcurado)) {
					cont++;
				}

			}
			brC.close();

			if (cont == 0) {
				this.msErro = "Clinte não localizado";
				return false;
			}

		} catch (Exception e) {
			
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;

	}

	// METODO LOCALIZAR CLIENTE DENTRO DO ARQUIVO VENDA
	public boolean localizarVenda(String cVenda, String nomeProcurado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cVenda));
			int cont = 0;
			while (br.ready()) {
				String linha = br.readLine();
				String arrayNome[] = linha.split("&");

				if (arrayNome[0].equalsIgnoreCase(nomeProcurado)) {
					cont++;
				}

			}
			br.close();

			if (cont == 0) {
				this.msErro = "Pré-Venda não localizada";
				return false;
			}

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;

	}
	
	// METODO LOCALIZAR CLIENTE DENTRO DO ARQUIVO VENDA
		public boolean localizarFinanciamento(String cFinanciamento, String nomeProcurado) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(cFinanciamento));
				int cont = 0;
				while (br.ready()) {
					String linha = br.readLine();
					String arrayNome[] = linha.split("&");

					if (arrayNome[0].equalsIgnoreCase(nomeProcurado)) {
						cont++;
					}

				}
				br.close();

				if (cont == 0) {
					this.msErro = "Finaciamento não foi localizada";
					return false;
				}

			} catch (Exception e) {
				this.msErro = "Erro de Exception:" + e.toString();
			}
			return true;

		}


	// METODO AUMENTO DE SALARIO FUNCIONARIO
	protected boolean editarSalario(String cFuncionario, String nomeProcurado, int novoSalario) {

		double ajuste = salario * novoSalario / 100;
		salario = ajuste + salario;
		return true;
	}

}

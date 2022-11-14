package vendaDeApartamento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import transferir.TransferenciaDeArquivos;

public class Imovel {
	protected String nomeDoCondominio;
	protected int bloco;
	protected int apartamento;
	protected double valorDoApartamento;
	protected String situacao;
	public String msErro;

	public Imovel() {
		super();
	}

	public Imovel(String nomeDoCondominio, int bloco, int apartamento, double valorDoApartamento, String situacao) {
		super();
		this.nomeDoCondominio = nomeDoCondominio;
		this.bloco = bloco;
		this.apartamento = apartamento;
		this.valorDoApartamento = valorDoApartamento;
		this.situacao = situacao;
	}

	public String getNomeDoCondominio() {
		return nomeDoCondominio;
	}

	public void setNomeDoCondominio(String nomeDoCondominio) {
		this.nomeDoCondominio = nomeDoCondominio;
	}

	public int getBloco() {
		return bloco;
	}

	public void setBloco(int bloco) {
		this.bloco = bloco;
	}

	public int getApartamento() {
		return apartamento;
	}

	public void setApartamento(int apartamento) {
		this.apartamento = apartamento;
	}

	public double getValorDoApartamento() {
		return valorDoApartamento;
	}

	public void setValorDoApartamento(double valorDoApartamento) {
		this.valorDoApartamento = valorDoApartamento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public boolean gerarBloco(String cImovel, int gerarApt) {
		try {
			BufferedWriter brImovel = new BufferedWriter(new FileWriter(cImovel, true));

			for (int i = 0; i < gerarApt; i++) {
				this.apartamento = 100 + i + 1;

				brImovel.write(
						nomeDoCondominio + "&" + bloco + "&" + apartamento + "&" + valorDoApartamento + "&" + situacao);
				brImovel.newLine();
			}

			brImovel.close();

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

	// METODO LOCALIZAR CONDOMINIO
	public boolean localizarCondominio(String cImovel, String condominioProcurado) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(cImovel));
			int cont = 0;
			while (br.ready()) {
				String linha = br.readLine();
				String arrayCondominio[] = linha.trim().split("&");

				this.nomeDoCondominio = arrayCondominio[0];

				if (nomeDoCondominio.equalsIgnoreCase(condominioProcurado)) {
					this.bloco = Integer.parseInt(arrayCondominio[1]);
					this.apartamento = Integer.parseInt(arrayCondominio[2]);
					this.situacao = arrayCondominio[4];
					if (situacao.equalsIgnoreCase("DISPONIVEL")) {
						System.out.println();
						System.out.println("-----LISTA DE APARTAMENTOS-----");
						System.out.println("CONDOMINIO   BL   APT   SITUAÇÃO");
						System.out.println(nomeDoCondominio + " - " + bloco + " - " + apartamento + " - " + situacao);
						System.out.println("--------------------------------");
						System.out.println();
						cont++;
					}
				}

			}

			br.close();

			if (cont == 0) {
				this.msErro = "Condominio não localizado";
				return false;
			}

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return true;

	}

	// METO
	public boolean listarApartamentoDisponivel(String cImovel) {
		try {
			BufferedReader brImovel = new BufferedReader(new FileReader(cImovel));

			while (brImovel.ready()) {
				String linha = brImovel.readLine();
				String arrayI[] = linha.split("&");

				this.nomeDoCondominio = arrayI[0];
				this.bloco = Integer.parseInt(arrayI[1]);
				this.apartamento = Integer.parseInt(arrayI[2]);
				this.situacao = arrayI[3];

				System.out.println(nomeDoCondominio + "-" + bloco + "-" + apartamento + "-" + situacao);
			}

			brImovel.close();
		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;
	}

//METODO SELECIONAR APARTAMENTO
	public boolean selecionarApt(String cImovel, String nrApt) {
		try {
			BufferedReader brImovel = new BufferedReader(new FileReader(cImovel));
			String arquivoTemp = cImovel.replace(".txt", "temp.txt");
			BufferedWriter bwTemporario = new BufferedWriter(new FileWriter(arquivoTemp));

			int cont = 0;
			while (brImovel.ready()) {
				String linha = brImovel.readLine();
				String arrayF[] = linha.trim().split("&");

				String apt = arrayF[2];
				this.nomeDoCondominio = arrayF[0];
				this.bloco = Integer.parseInt(arrayF[1]);
				this.apartamento = Integer.parseInt(arrayF[2]);
				this.valorDoApartamento = Double.parseDouble(arrayF[3]);
				this.situacao = arrayF[4];

				if (nrApt.equalsIgnoreCase(apt) && (situacao.equalsIgnoreCase("DISPONIVEL"))) {
					this.situacao = "VENDIDO";
					bwTemporario.write(nomeDoCondominio + "&" + bloco + "&" + apartamento + "&" + valorDoApartamento
							+ "&" + situacao);
					bwTemporario.newLine();
					cont++;

				} else {
					bwTemporario.write(linha);
					bwTemporario.newLine();

				}

			}

			bwTemporario.close();
			brImovel.close();

			if (cont == 0) {
				this.msErro = " Apartamento não localizado ou vendido";
				return false;
			} else {
				TransferenciaDeArquivos.transferirArquivo(arquivoTemp, cImovel);
				return true;
			}

		} catch (Exception e) {
			this.msErro = "Erro de Exception:" + e.toString();
		}
		return false;

	}

}

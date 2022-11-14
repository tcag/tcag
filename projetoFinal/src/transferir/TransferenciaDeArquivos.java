package transferir;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class TransferenciaDeArquivos {

	public static void transferirArquivo(String arquivoOrigem, String arquivoDestino) {
		try {
			BufferedReader arquivo1 = new BufferedReader(new FileReader(arquivoOrigem));
			BufferedWriter arquivo2 = new BufferedWriter(new FileWriter(arquivoDestino));

			while (arquivo1.ready()) {
				String linha = arquivo1.readLine();
				arquivo2.write(linha);
				arquivo2.newLine();
			}
			arquivo1.close();
			arquivo2.close();

			File excluir = new File(arquivoOrigem);
			excluir.delete();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

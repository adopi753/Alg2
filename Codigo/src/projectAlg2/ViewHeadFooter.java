package projectAlg2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewHeadFooter {
	ViewMessageAndLine mens = new ViewMessageAndLine();
	
	public Boolean project(boolean d) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		if (d == false) {
			System.out.println("\nSistema iniciado em: " + format.format(data));
		} else {
			System.out.println("\nSistema finalizado em: " + format.format(data));
		}
		mens.escrever("==============IDALG MATERIAIS DE CONSTRUǇÃO===============");
		mens.escrever("=                                                        =");
		mens.escrever("= ----------SISTEMA DE GERENCIAMENTO DE ESTOQUE--------- =");
		mens.escrever("=                                                        =");
		mens.escrever("=            Desenvolvido por: In�cio | Denis            =");
		mens.escreverLinhaDupla();
		return d;
	}
}
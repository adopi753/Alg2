package projectAlg2;

public class ViewMessageAndLine {
	public void escreverDadoInvNumero() {
		escrever("\n----------------------DADO INV¡LIDO-----------------------");
		escrever("- Permitido apenas n˙mero(s). E deve ser maior que zero  -");
		escrever("----------------------------------------------------------\n");		
	}
	
	public void escreverDadoInvNumeroDecimal() {
		escrever("\n----------------------DADO INV¡ÅLIDO-----------------------");
		escrever("- Permitido apenas n˙mero(s). E deve ser maior que zero  -");
		escrever("- Para n˙meros decimais usar ','. Ex.: 10,60             -");
		escrever("----------------------------------------------------------\n");
	}
	
	public void inserirCodProd() {
		escrever1("Digite o cÛdigo do produto: ");		
	}
	
	public void escreverLinhaSimples() {
		escrever("----------------------------------------------------------");
	}
	
	public void escreverLinhaDupla() {
		escrever("==========================================================");
	}	
	
	
	public void escrever(String msg) {
		System.out.println(msg);
	}
	
	public void escrever1(String msg) {
		System.out.print(msg);
	}

	public void formatar(String msg, Object... args) {
		System.out.printf(msg, args);
	}

}
package projectAlg2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ViewMenuHome {
	static Scanner leia = new Scanner(System.in);
	
	public void menu() {
		ViewMessageAndLine mens = new ViewMessageAndLine();
		Process process = new Process();
		QueryReport queryRep = new QueryReport();
		StoreAll store = new StoreAll();		

		store.indexNul = 0;
		store.op = 0;
		do {
			do {
			mens.escrever("\n======================MENU PRINCIPAL======================");
			mens.escrever("= 1-  CADASTRO DE PRODUTO                                =");
			mens.escrever("= 2-  CADASTRO DE QUANTIDADE DE PRODUTO                  =");
			mens.escrever("= 3-  ALTERAR VALOR DE PRODUTO                           =");
			mens.escrever("= 4-  VENDA DE PRODUTO                                   =");
			mens.escrever("= 5-  CONSULTA DE PRODUTO POR C”DIGO                     =");
			mens.escrever("= 6-  RELAT”ìRIO DE COMPRA                                =");
			mens.escrever("= 7-  RELAT”ìRIO DE VENDA                                 =");
			mens.escrever("= 8-  RELAT”RIO DE ESTOQUE                               =");
			mens.escrever("= 9-  RELAT”ìRIO COMPLETO                                 =");
			mens.escrever("= 10- SAIR                                               =");
			mens.escreverLinhaDupla();
			
				try {
					mens.escrever1("ESCOLHA UM NUMERO NO MENU ACIMA -> ");
					store.op = leia.nextInt();
				} catch (InputMismatchException e) {
					mens.escreverDadoInvNumero();
					store.op = 0;
				}
				leia.nextLine();
			} while (store.op == 0);

			switch (store.op) {
			case 1:
				store.indexNul = process.register(store.nameProd, store.codVal, store.indexNul);
				break;
			case 2:
				process.regEntry(store.nameProd, store.codVal, store.indexNul);
				break;
			case 3:
				process.changeValues(store.nameProd, store.codVal, store.indexNul);
				break;
			case 4:
				process.regSales(store.nameProd, store.codVal, store.indexNul);
				break;
			case 5:
				queryRep.queryProdCod(store.nameProd, store.codVal, store.indexNul);
				break;
			case 6:
				queryRep.repBuy(store.nameProd, store.codVal, store.indexNul);
				break;
			case 7:
				queryRep.repSales(store.nameProd, store.codVal, store.indexNul);
				break;
			case 8:
				queryRep.repStore(store.nameProd, store.codVal, store.indexNul);
				break;
			case 9:
				queryRep.viewAll(store.nameProd, store.codVal, store.indexNul);
				break;
			case 10:
				mens.escreverLinhaDupla();
				mens.escrever("= AGRADECEMOS A PREFER äNCIA! VOLTE SEMPRE!               =");
				mens.escreverLinhaSimples();

				break;
			default:
				mens.escrever("OP«√ÉO INV¡ÅLIDA");
			}
		} while (store.op != 10);
	}

}
package projectAlg2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class QueryReport {
	static Scanner leia = new Scanner(System.in);
	ViewMessageAndLine mens = new ViewMessageAndLine();
	StoreAll storeAll = new StoreAll();
	Process process = new Process();

	public void queryProdCod(String nameProd[], double codVal[][], int indexNul) {
		storeAll.codProd = 0;
		storeAll.resp = 's';
		leia = new Scanner(System.in);

		mens.escrever("\n==============CONSULTA DE PRODUTO POR CÓ“DIGO==============");
		do {
			do {
				try {
					mens.inserirCodProd();
					storeAll.codProd = leia.nextDouble();

				} catch (InputMismatchException e) {
					mens.escreverDadoInvNumero();
				}
				leia.nextLine();
			} while (storeAll.codProd <= 0);

			storeAll.found = process.searchCod(nameProd, codVal, indexNul, storeAll.codProd);

			if (storeAll.found == -1) {
				mens.escrever("\n------------------CÓ“DIGO NÃƒO ENCONTRADO-------------------");
				mens.formatar("Não foi encontrado o Código %.0f%n", storeAll.codProd);
				mens.escreverLinhaDupla();

			} else {
				mens.formatar("\nCódigo: %.0f%n", codVal[1][storeAll.found]);
				mens.formatar("Produto: %s%n", nameProd[storeAll.found]);
				mens.formatar("Valor de custo: R$ %.2f%n", codVal[5][storeAll.found]);
				mens.formatar("Valor de venda: R$ %.2f%n", codVal[6][storeAll.found]);
				
				mens.formatar("\nQuantidade comprada: %.0f%n", codVal[2][storeAll.found]);
				mens.formatar("Quantidade vendida: %.0f%n", codVal[3][storeAll.found]);
				mens.formatar("Quantidade em estoque: %.0f%n", codVal[4][storeAll.found]);				

				storeAll.vlCustVenda = codVal[3][storeAll.found] * codVal[5][storeAll.found];
				mens.formatar("\nValor de custo da venda: R$ %.2f%n", storeAll.vlCustVenda);
				storeAll.vlVenda = codVal[3][storeAll.found] * codVal[6][storeAll.found];
				mens.formatar("Valor da venda: R$ %.2f%n", storeAll.vlVenda);
				storeAll.lucroVenda = storeAll.vlVenda - storeAll.vlCustVenda;
				mens.formatar("Lucro da venda: R$ %.2f%n", storeAll.lucroVenda);

				storeAll.vlTotCompra = codVal[2][storeAll.found] * codVal[5][storeAll.found];
				mens.formatar("\nValor total de compra: R$ %.2f%n", storeAll.vlTotCompra);
				storeAll.vlTotVenda = codVal[2][storeAll.found] * codVal[6][storeAll.found];
				mens.formatar("Valor total de venda: R$ %.2f%n", storeAll.vlTotVenda);
				storeAll.lucroTotCompra = storeAll.vlTotVenda - storeAll.vlTotCompra;
				mens.formatar("Lucro após venda total: R$ %.2f%n", storeAll.lucroTotCompra);

				storeAll.vlCustEst = codVal[4][storeAll.found] * codVal[5][storeAll.found];
				mens.formatar("\nValor de custo em estoque: R$ %.2f%n", storeAll.vlCustEst);
				storeAll.vlVendaEst = codVal[4][storeAll.found] * codVal[6][storeAll.found];
				mens.formatar("Valor de venda em estoque: R$ %.2f%n", storeAll.vlVendaEst);
				storeAll.vlLucroEst = storeAll.vlVendaEst - storeAll.vlCustEst;
				mens.formatar("Valor de lucro em estoque: R$ %.2f%n", storeAll.vlLucroEst);
				mens.escreverLinhaDupla();
			}

			mens.escrever1("\nDeseja pesquisar outro produto (S/N)? ");
			storeAll.resp = leia.next().charAt(0);

		} while (storeAll.resp == 's' || storeAll.resp == 'S');
	}
	
	public void repBuy(String nameProd[], double codVal[][], int indexNul) {
		storeAll.index = 0;
		storeAll.typeMov = 2;
		storeAll.indSearch = 0;
		storeAll.i = 0;
		storeAll.valSales = 0;

		mens.escrever("\n===================RELATÓ“RIO DE COMPRA====================");
		do {
			storeAll.index = process.viewMov(nameProd, codVal, indexNul, storeAll.typeMov, storeAll.indSearch);
			if (storeAll.index != -1) {
				mens.formatar("Código: %.0f%n", codVal[1][storeAll.index]);
				mens.formatar("Produto: %s%n", nameProd[storeAll.index]);
				mens.formatar("Valor de custo R$: %.2f%n", codVal[5][storeAll.index]);
				mens.formatar("Quantidade comprada: %.0f%n", codVal[2][storeAll.index]);
				mens.formatar("Valor total da compra R$: %.2f%n%n",
						codVal[2][storeAll.index] * codVal[5][storeAll.index]);
			}

			storeAll.valSales = storeAll.valSales
					+ process.calcValue(nameProd, codVal, indexNul, storeAll.typeMov, 5, storeAll.indSearch);
			storeAll.indSearch = storeAll.indSearch + 1;

			storeAll.i++;

		} while (storeAll.i < indexNul);
		mens.escrever("\n-----------------TOTALIZAÇ‡ÃƒO DAS COMPRAS------------------");
		mens.formatar("Valor total das compras R$: %.2f%n", storeAll.valSales);
		mens.escreverLinhaDupla();
	}
	
	public void repSales(String nameProd[], double codVal[][], int indexNul) {
		storeAll.typeMov = 3;
		storeAll.index = 0;
		storeAll.indSearch = 0;
		storeAll.i = 0;
		storeAll.valSales = 0;
		storeAll.costSales = 0;
		storeAll.invoicing = 0;

		mens.escrever("\n====================RELATÓ“RIO DE VENDA====================");
		do {
			storeAll.index = process.viewMov(nameProd, codVal, indexNul, storeAll.typeMov, storeAll.indSearch);
			if (storeAll.index != -1) {
				mens.formatar("Código: %.0f%n", codVal[1][storeAll.index]);
				mens.formatar("Produto: %s%n", nameProd[storeAll.index]);
				mens.formatar("Valor de venda R$: %.2f%n", codVal[6][storeAll.index]);
				mens.formatar("Quantidade vendida: %.0f%n", codVal[3][storeAll.index]);
				mens.formatar("Valor da venda R$: %.2f%n%n", codVal[3][storeAll.index] * codVal[6][storeAll.index]);
			}

			storeAll.valSales = storeAll.valSales
					+ process.calcValue(nameProd, codVal, indexNul, storeAll.typeMov, 6, storeAll.indSearch);
			storeAll.costSales = storeAll.costSales
					+ process.calcValue(nameProd, codVal, indexNul, storeAll.typeMov, 5, storeAll.indSearch);
			storeAll.indSearch = storeAll.indSearch + 1;

			storeAll.i++;
		} while (storeAll.i < indexNul);

		storeAll.invoicing = (storeAll.valSales - storeAll.costSales);

		mens.escrever("\n------------------TOTALIZAÇ‡ÃƒO DAS VENDAS------------------");
		mens.formatar("Valor total da(s) venda(s): R$ %.2f%n", storeAll.valSales);
		mens.formatar("Lucro da(s) venda(s): R$ %.2f%n", storeAll.invoicing);
		mens.escreverLinhaDupla();
	}
	
	public void repStore(String nameProd[], double codVal[][], int indexNul) {
		storeAll.typeMov = 4;
		storeAll.index = 0;
		storeAll.indSearch = 0;
		storeAll.i = 0;
		storeAll.priceCostTotal = 0;
		storeAll.valSalesTotal = 0;
		storeAll.vlCustEst = 0;
		storeAll.vlVendaEst = 0;

		mens.escrever("\n===================RELATÓ“RIO DE ESTOQUE===================");
		do {
			storeAll.index = process.viewMov(nameProd, codVal, indexNul, storeAll.typeMov, storeAll.indSearch);
			if (storeAll.index != -1) {
				mens.formatar("Código: %.0f%n", codVal[1][storeAll.index]);
				mens.formatar("Produto: %s%n", nameProd[storeAll.index]);
				mens.formatar("Quantidade estoque: %.0f%n", codVal[4][storeAll.index]);
				mens.formatar("Valor de custo: R$ %.2f%n", codVal[5][storeAll.index]);
				mens.formatar("Valor de venda: R$ %.2f%n", codVal[6][storeAll.index]);

				storeAll.vlCustEst = codVal[4][storeAll.index] * codVal[5][storeAll.index];
				mens.formatar("\nValor de custo em estoque: R$ %.2f%n", storeAll.vlCustEst);
				storeAll.vlVendaEst = codVal[4][storeAll.index] * codVal[6][storeAll.index];
				mens.formatar("Valor de venda em estoque: R$ %.2f%n%n%n", storeAll.vlVendaEst);
			}

			storeAll.priceCostTotal = storeAll.priceCostTotal
					+ process.calcValue(nameProd, codVal, indexNul, storeAll.typeMov, 5, storeAll.indSearch);
			storeAll.valSalesTotal = storeAll.valSalesTotal
					+ process.calcValue(nameProd, codVal, indexNul, storeAll.typeMov, 6, storeAll.indSearch);
			storeAll.indSearch = storeAll.indSearch + 1;

			storeAll.i++;
		} while (storeAll.i < indexNul);

		mens.escrever("------------------TOTALIZAÇ‡ÃƒO DO ESTOQUE------------------");
		mens.formatar("Valor total de custo em estoque: R$ %.2f%n", storeAll.priceCostTotal);
		mens.formatar("Valor total de venda em estoque: R$ %.2f%n", storeAll.valSalesTotal);
		mens.formatar("Lucro ao vender todo o estoque: R$ %.2f%n", (storeAll.valSalesTotal - storeAll.priceCostTotal));
		mens.escreverLinhaDupla();
	}
	
	public void viewAll(String nameProd[], double codVal[][], int indexNul) {
		storeAll.i = 0;
		storeAll.vlTotCompra = 0;
		storeAll.vlTotVenda = 0;
		storeAll.lucroTotCompra = 0;
		storeAll.vlCustVenda = 0;
		storeAll.vlVenda = 0;
		storeAll.lucroVenda = 0;

		System.out.println("\n====================RELATÓ“RIO COMPLETO====================");
		if (codVal[1][storeAll.i] <= 0) {
			mens.escrever("= Ainda sem dados para relatório!                        =");
			mens.escrever("==========================================================\n");
		} else {
			do {
				mens.formatar("Código: %.0f%n", codVal[1][storeAll.i]);
				mens.formatar("Produto: %s%n", nameProd[storeAll.i]);
				mens.formatar("Valor de custo: R$ %.2f%n", codVal[5][storeAll.i]);
				mens.formatar("Valor de venda: R$ %.2f%n", codVal[6][storeAll.i]);
				mens.formatar("\nQuantidade comprada: %.0f%n", codVal[2][storeAll.i]);
				mens.formatar("Quantidade vendida: %.0f%n", codVal[3][storeAll.i]);
				mens.formatar("Quantidade em estoque: %.0f%n", codVal[4][storeAll.i]);

				storeAll.vlTotCompra = codVal[2][storeAll.i] * codVal[5][storeAll.i];
				mens.formatar("\nValor total de compra do produto: R$ %.2f%n", storeAll.vlTotCompra);
				storeAll.vlTotVenda = codVal[2][storeAll.i] * codVal[6][storeAll.i];
				mens.formatar("Valor total de venda do produto: R$ %.2f%n", storeAll.vlTotVenda);
				storeAll.lucroTotCompra = storeAll.vlTotVenda - storeAll.vlTotCompra;
				mens.formatar("Lucro após venda total do produto: R$ %.2f%n", storeAll.lucroTotCompra);

				storeAll.vlCustVenda = codVal[3][storeAll.i] * codVal[5][storeAll.i];
				mens.formatar("\nValor de custo do produto vendido: R$ %.2f%n", storeAll.vlCustVenda);
				storeAll.vlVenda = codVal[3][storeAll.i] * codVal[6][storeAll.i];
				mens.formatar("Valor da venda do produto: R$ %.2f%n", storeAll.vlVenda);
				storeAll.lucroVenda = storeAll.vlVenda - storeAll.vlCustVenda;
				mens.formatar("Lucro da venda do produto: R$ %.2f%n", storeAll.lucroVenda);

				storeAll.vlCustEst = codVal[4][storeAll.i] * codVal[5][storeAll.i];
				mens.formatar("\nValor de custo em estoque: R$ %.2f%n", storeAll.vlCustEst);
				storeAll.vlVendaEst = codVal[4][storeAll.i] * codVal[6][storeAll.i];
				mens.formatar("Valor de venda em estoque: R$ %.2f%n", storeAll.vlVendaEst);
				storeAll.vlLucroEst = storeAll.vlVendaEst - storeAll.vlCustEst;
				mens.formatar("Valor de lucro em estoque: R$ %.2f%n%n", storeAll.vlLucroEst);

				storeAll.i++;
			} while (storeAll.i < indexNul);
			mens.escreverLinhaDupla();
		}
	}
}
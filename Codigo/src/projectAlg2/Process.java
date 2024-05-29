package projectAlg2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Process {
	static Scanner leia = new Scanner(System.in);
	ViewMessageAndLine mens = new ViewMessageAndLine();
	StoreAll storeAll = new StoreAll();

	public int register(String nameProd[], double codVal[][], int indexNul) {
		storeAll.resp = 's';
		storeAll.confirm = '0';
		storeAll.index = 0;
		storeAll.priceCost = 0;
		storeAll.valSales = 0;
		storeAll.codProd = 0;
		storeAll.nameReg = "0";
		leia = new Scanner(System.in);

		mens.escrever("\n===================CADASTRO DE PRODUTO====================");
		if (indexNul < nameProd.length) {
			mens.escrever1("Digite o nome do produto: ");
			storeAll.nameReg = leia.nextLine();

			do {
				try {
					mens.inserirCodProd();
					storeAll.codProd = leia.nextDouble();
				} catch (InputMismatchException e) {
					mens.escreverDadoInvNumero();					
				}
				leia.nextLine();
			} while (storeAll.codProd <= 0);

			storeAll.index = searchCod(nameProd, codVal, indexNul, storeAll.codProd);
			if (storeAll.index != -1) {
				mens.escrever("\nCódigo já cadastrado para o produto " + nameProd[storeAll.index]);
				mens.escreverLinhaDupla();
			} else {
				do {
					try {
						mens.escrever1("Digite o valor de custo: R$ ");
						storeAll.priceCost = leia.nextDouble();
					} catch (InputMismatchException e) {
						mens.escreverDadoInvNumeroDecimal();
					}
					leia.nextLine();
				} while (storeAll.priceCost <= 0);

				do {
					try {
						mens.escrever1("Digite o valor de venda: R$ ");
						storeAll.valSales = leia.nextDouble();
						mens.escrever1("");
					} catch (InputMismatchException e) {
						mens.escreverDadoInvNumeroDecimal();
					}
					leia.nextLine();
				} while (storeAll.valSales <= 0);
				mens.escrever1("----------------------------------------------------------");

				mens.escrever("\n\n-----------------CONFIRMAÇ‡ÃƒO DE CADASTRO------------------");
				mens.formatar("Código: %.0f%n", storeAll.codProd);
				mens.formatar("Nome: %S%n", storeAll.nameReg);
				mens.formatar("Valor de Custo: R$ %4.2f%n", storeAll.priceCost);
				mens.formatar("Valor de venda: R$ %4.2f%n", storeAll.valSales);
				mens.escreverLinhaSimples();

				mens.escrever1("Confirma o cadastro (S/N)? ");
				storeAll.confirm = leia.next().charAt(0);

				if (storeAll.confirm == 's' || storeAll.confirm == 'S') {
					execReg(nameProd, codVal, indexNul, storeAll.codProd, storeAll.nameReg, storeAll.priceCost,
							storeAll.valSales);
					indexNul = indexNul + 1;
					mens.escrever("\nProduto cadastrado com sucesso!");
					mens.escreverLinhaDupla();
				} else {
					mens.escrever("\nProduto não Cadastrado!");
					mens.escreverLinhaDupla();
				}
			}
		} else {
			mens.escrever1("Limite de cadastro atingido\n");
			mens.escreverLinhaDupla();
			storeAll.resp = 'n';
		}
		return indexNul;
	}

	public int searchCod(String nameProd[], double codVal[][], int indexNul, double searchCod) {
		int i = 0;
		while (i < indexNul && searchCod != codVal[1][i]) {
			i++;
		}
		if (searchCod != codVal[1][i]) {
			return -1;
		} else {
			return i;
		}
	}

	public void execReg(String nameProd[], double codVal[][], int index, double cod, String name, double priceCost,
			double valSales) {

		storeAll.costUn = valSales - priceCost;
		nameProd[index] = name;
		codVal[1][index] = cod;
		codVal[5][index] = priceCost;
		codVal[6][index] = valSales;
		codVal[7][index] = storeAll.costUn;
	}

	public void regEntry(String nameProd[], double codVal[][], int indexNul) {
		storeAll.codProd = 0;
		storeAll.qtEntry = 0;
		storeAll.confirm = '0';
		storeAll.resp = 's';
		leia = new Scanner(System.in);

		mens.escrever("\n============CADASTRO DE QUANTIDADE DO PRODUTO=============");
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
			storeAll.findProd = searchCod(nameProd, codVal, indexNul, storeAll.codProd);

			if (storeAll.findProd != -1) {
				do {
					try {
						mens.escrever1("Informe a quantidade de entrada para " + nameProd[storeAll.findProd] + ": ");
						storeAll.qtEntry = leia.nextDouble();
					} catch (InputMismatchException e) {
						mens.escreverDadoInvNumero();
					}
					leia.nextLine();
				} while (storeAll.qtEntry <= 0);

				mens.escrever("\n------------CONFIRMAÇ‡ÃƒO DE CADASTRO DE ENTRADA------------");
				mens.formatar("Código: %.0f%n", codVal[1][storeAll.findProd]);
				mens.formatar("Nome do produto: %S%n", nameProd[storeAll.findProd]);
				mens.formatar("Quantidade de entrada: %.0f%n", storeAll.qtEntry);
				mens.formatar("Valor total de entrada: R$ %.2f%n", (storeAll.qtEntry * codVal[5][storeAll.findProd]));
				mens.escreverLinhaSimples();

				mens.escrever1(
						"Confirma o cadastro de entrada do produto\n" + nameProd[storeAll.findProd] + " (S/N)? ");
				storeAll.confirm = leia.next().charAt(0);

				if (storeAll.confirm == 's' || storeAll.confirm == 'S') {
					execRegEntry(nameProd, codVal, storeAll.findProd, storeAll.codProd, storeAll.qtEntry);
					indexNul = indexNul + 1;
					mens.escrever("\nEntrada de produto cadastrada com sucesso!");
					mens.escreverLinhaDupla();
				} else {
					mens.escrever("\nEntrada de produto não Cadastrada!");
					mens.escreverLinhaDupla();
				}

			} else {
				mens.escrever("\n------------------CÓ“DIGO NÃƒO ENCONTRADO-------------------");
				mens.formatar("Não foi localizado o código: %.0f%n", storeAll.codProd);
				mens.escrever1(
						"Verifique se digitou o código correto." + "\nOu verifique se o produto já está cadastrado.\n");
				mens.escreverLinhaSimples();
			}
			mens.escrever("\n============CADASTRO DE QUANTIDADE DO PRODUTO=============");
			mens.escrever1("Cadastrar Entrada de outro produto (S/N)? ");
			storeAll.resp = leia.next().charAt(0);
		} while (storeAll.resp == 's' || storeAll.resp == 'S');
	}

	public void changeValues(String nameProd[], double codVal[][], int indexNul) {
		storeAll.valSales = 0;
		storeAll.codProd = 0;
		storeAll.priceCost = 0;
		storeAll.confirm = 'n';
		storeAll.str = "0";
		storeAll.index = 0;
		leia = new Scanner(System.in);

		storeAll.valSales = Double.parseDouble(storeAll.str);
		storeAll.codProd = Double.parseDouble(storeAll.str);
		storeAll.priceCost = Double.parseDouble(storeAll.str);
		leia = new Scanner(System.in);

		mens.escrever1("\n=================ALTERAR VALOR DE PRODUTO=================\n");
		do {
			try {
				mens.inserirCodProd();
				storeAll.codProd = leia.nextDouble();
			} catch (InputMismatchException e) {
				mens.escreverDadoInvNumero();
			}
			leia.nextLine();
		} while (storeAll.codProd <= 0);

		storeAll.index = searchCod(nameProd, codVal, indexNul, storeAll.codProd);

		if (storeAll.index == -1) {
			mens.escrever("\n------------------CÓ“DIGO NÃƒO ENCONTRADO-------------------");
			mens.formatar("Não foi encontrado o Código %.0f%n", storeAll.codProd);
			mens.escreverLinhaDupla();
		} else {
			mens.escrever("\n-----------------DADOS ATUAIS DO PRODUTO------------------");
			mens.formatar("Código: %.0f%n", storeAll.codProd);
			mens.formatar("Produto: %s%n", nameProd[storeAll.index]);
			mens.formatar("Valor de custo: R$ %.2f%n", codVal[5][storeAll.index]);
			mens.formatar("Valor de venda: R$ %.2f%n", codVal[6][storeAll.index]);
			mens.escreverLinhaSimples();

			mens.escrever1("\nDeseja alterar o produto (S/N)? ");
			storeAll.confirm = leia.next().charAt(0);

			if (storeAll.confirm == 's' || storeAll.confirm == 'S') {
				do {
					try {
						mens.escrever1("\nDigite o novo valor de custo: R$ ");
						storeAll.priceCost = leia.nextDouble();
					} catch (InputMismatchException e) {
						mens.escreverDadoInvNumeroDecimal();
					}
					leia.nextLine();
				} while (storeAll.priceCost <= 0);

				do {
					try {
						mens.escrever1("Digite o novo valor de venda: R$ ");
						storeAll.valSales = leia.nextDouble();
					} catch (InputMismatchException e) {
						mens.escreverDadoInvNumeroDecimal();
					}
					leia.nextLine();
				} while (storeAll.valSales <= 0);

				mens.escrever("\n---------------DADOS ATUALIZADOS DO PRODUTO---------------");
				mens.formatar("Código: %.0f%n", storeAll.codProd);
				mens.formatar("Produto: %s%n", nameProd[storeAll.index]);
				mens.formatar("Valor de custo: R$ %.2f%n", storeAll.priceCost);
				mens.formatar("Valor de venda: R$ %.2f%n", storeAll.valSales);
				mens.escreverLinhaSimples();

				mens.escrever1("Confirma alteração do produto (S/N)? ");
				storeAll.confirm = leia.next().charAt(0);

				if (storeAll.confirm == 's' || storeAll.confirm == 'S') {
					codVal[5][storeAll.index] = storeAll.priceCost;
					codVal[6][storeAll.index] = storeAll.valSales;

					mens.escrever("\n-------------------------ATENÇ‡ÃƒO!-------------------------");
					mens.escrever("Ao atualizar os valores dos produtos o relatório irá");
					mens.escrever("refletir os valores da atualização");
					mens.escreverLinhaSimples();

					mens.escrever("\nAlteração realizada com sucesso");
					mens.escreverLinhaDupla();
				}
			}
		}
	}

	public void execRegEntry(String nameProd[], double codVal[][], int indProd, double codEntry, double qt) {
		codVal[2][indProd] = codVal[2][indProd] + qt;
		codVal[4][indProd] = codVal[4][indProd] + qt;
	}

	public void regSales(String nameProd[], double codVal[][], int indexNul) {
		leia = new Scanner(System.in);
		storeAll.resp = 's';
		storeAll.confirm = '0';
		storeAll.codSales = 0;
		storeAll.qtSales = 0;
		storeAll.findProd = 0;

		mens.escrever("\n=====================VENDA DE PRODUTO=====================");
		do {
			try {
				mens.inserirCodProd();
				storeAll.codSales = leia.nextDouble();
			} catch (InputMismatchException e) {
				mens.escreverDadoInvNumero();
			}
			leia.nextLine();
		} while (storeAll.codSales <= 0);

		storeAll.findProd = searchCod(nameProd, codVal, indexNul, storeAll.codSales);

		if (storeAll.findProd != -1) {
			storeAll.qtStore = viewStore(codVal, storeAll.findProd);

			if (storeAll.qtStore <= 0.0) {
				mens.escrever("\n-----------------------SEM ESTOQUE------------------------");
				mens.escrever("No momento estamos sem estoque do produto " + nameProd[storeAll.findProd]);
				mens.escreverLinhaDupla();

			} else {
				do {
					try {
						mens.escrever1("\nDigite a quantidade de " + nameProd[storeAll.findProd] + " a ser vendida: ");
						storeAll.qtSales = leia.nextDouble();
					} catch (InputMismatchException e) {
						mens.escreverDadoInvNumero();
					}
					leia.nextLine();
				} while (storeAll.qtSales <= 0);

				if (storeAll.qtSales > storeAll.qtStore) {
					mens.escrever("\n-----------------ESTOQUE ABAIXO DA VENDA------------------");
					mens.escrever("A quantidade do produto " + nameProd[storeAll.findProd]);
					mens.formatar("em estoque é de apenas %.0f%n", storeAll.qtStore);
					mens.escrever("\nVenda cancelada!");
					mens.escreverLinhaDupla();
				}

				if (storeAll.qtSales <= storeAll.qtStore) {
					mens.escrever("\n-------------------CONFIRMAÇÃƒO DE VENDA-------------------");
					mens.formatar("Código: %.0f%n", codVal[1][storeAll.findProd]);
					mens.formatar("Produto: %s%n", nameProd[storeAll.findProd]);
					mens.formatar("Quantidade vendida: %.0f%n", storeAll.qtSales);
					mens.formatar("Valor unitário: %.2f%n", codVal[6][storeAll.findProd]);
					mens.formatar("Valor total vendido: %.2f%n", codVal[6][storeAll.findProd] * storeAll.qtSales);
					mens.escreverLinhaSimples();

					mens.escrever1("Confirma a venda (S/N)? ");
					storeAll.confirm = leia.next().charAt(0);
				}

				if (storeAll.confirm == 's' || storeAll.confirm == 'S') {
					mens.escrever("\nVenda realizada com sucesso!");
					mens.escreverLinhaDupla();
					execRegSales(codVal, storeAll.findProd, storeAll.qtSales);
				}
			}
		} else {

			mens.escrever("\n------------------CÓDIGO NÃƒO ENCONTRADO-------------------");
			mens.formatar("Não foi encontrado o Código %.0f%n", storeAll.codSales);
			mens.escreverLinhaDupla();
		}
	}

	public double viewStore(double codVal[][], int foundProd) {
		storeAll.storeViewStore = codVal[4][foundProd];
		return storeAll.storeViewStore;
	}

	public void execRegSales(double codVal[][], int indProd, double qt) {
		codVal[3][indProd] = codVal[3][indProd] + qt;
		codVal[4][indProd] = codVal[4][indProd] - qt;
	}

	public int viewMov(String nameProd[], double codVal[][], int indexNul, int typeMov, int indSearch) {
		if (codVal[typeMov][indSearch] > 0) {
			return indSearch;
		} else {
			return -1;
		}
	}

	public double calcValue(String nameProd[], double codVal[][], int indexNul, int typeMov, int cost, int indSearch) {
		storeAll.value = 0;

		storeAll.indexCalcValue = viewMov(nameProd, codVal, indexNul, typeMov, indSearch);
		if (storeAll.indexCalcValue != -1) {
			storeAll.value = storeAll.value
					+ (codVal[cost][storeAll.indexCalcValue] * codVal[typeMov][storeAll.indexCalcValue]);
		}
		return storeAll.value;
	}
}
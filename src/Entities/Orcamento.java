package Entities;

public class Orcamento {
	public String numRegiao;
	public String ton;
	public float valorPorTonelada;
	
	public void leToneladas(int linha)
	{	
		//extrai o valor por tonelada, em string ainda
		DadosCSV.getData("valor-por-ton.csv");
		ton = DadosCSV.dados[linha-1][1];			 
	}
	
	public void leRegiao(int linha)
	{
		//extrai o valor do frete, em string ainda
		DadosCSV.getData("frete-por-estado.csv");
		numRegiao = DadosCSV.dados[linha-1][1];
	}
	
	public float valorTotal(String toneladaLida, float regiaoLida)
	{
		//faz a conta para o saber o orçamento
		return (valorPorTonelada * Integer.parseInt(toneladaLida)
				+ regiaoLida);
	}
}

package Entities;

public class Orcamento {
	public static String nomeRegiao;
	public static String ton;
	public float valorPorTonelada;
	
	public static void leToneladas(int linha)
	{	
		//extrai o valor por tonelada, em string ainda
		DadosCSV.getData("valor-por-ton.csv");
		ton = DadosCSV.dados[linha-1][1];
	}
	
	public static String leRegiao(String regiao)
	{
		//extrai o valor do frete, em string ainda
		DadosCSV.getData("frete-por-estado.csv");
		int linha = DadosCSV.contaLinha("frete-por-estado.csv");
		String reg;
		for(int i = 0; i < linha; i++)
		{
			reg = DadosCSV.dados[i][0];
			
			if(regiao.equals(reg))
			{
				nomeRegiao = DadosCSV.dados[i - 1][0];
				break;
			}			
		}
		return nomeRegiao;	
	}
	
	public float valorTotal(String toneladaLida, float regiaoLida)
	{
		//faz a conta para o saber o or�amento
		return (valorPorTonelada * Integer.parseInt(toneladaLida)
				+ regiaoLida);
	}
}

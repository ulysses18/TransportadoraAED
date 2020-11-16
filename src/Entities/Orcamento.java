package Entities;

public class Orcamento {
	public static String freteRegiao;
	public static String ton;
	public static float valorPorTonelada;
	public static String diaEntrega;
	
	public static void leToneladas(int linha)
	{	
		//extrai o valor por tonelada, em string ainda
		DadosCSV.getData("valor-por-ton.csv");
		ton = DadosCSV.dados[linha-1][1];
	}
	
	public static String nomeRegiao(int indice)
	{
		//retorna o nome das regioes para exibir como escolha na "bandeja"
		DadosCSV.getData("frete-por-estado.csv");
		return DadosCSV.dados[indice][0];
	}
	
	public static String comparaRegiao(String regiao)
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
				freteRegiao = DadosCSV.dados[i][1];
				diaEntrega = DadosCSV.dados[i][2];
				break;
			}			
		}
		return freteRegiao;	
	}
	
	public static float valorTotal(String toneladaLida, float regiaoLida)
	{
		//faz a conta para o saber o or�amento
		return (valorPorTonelada * Integer.parseInt(toneladaLida)
				+ regiaoLida);
	}
	
	public static int converteDia()
	{
		//pega o primeiro caractere da string do tempo estimado
		//e converte para int
		String caractere = diaEntrega.substring(0,1);
		int diaConvertido = Integer.parseInt(caractere);
        return diaConvertido;
	}
	

}


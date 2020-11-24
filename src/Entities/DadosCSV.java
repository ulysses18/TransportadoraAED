package Entities;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class DadosCSV {

    static FileInputStream arquivo;
    static DataInputStream leitor;
    public static String[][] dados;
    public static String[] nomesColunas;

    public static int contaColuna(String planilha) {
        int caractere = 0;
        int colunas = 0;
        try {
            arquivo = new FileInputStream(planilha);
            leitor = new DataInputStream(arquivo);

            while (caractere != 10) {
                caractere = leitor.read();
                if (caractere == 59) {
                    colunas += 1;
                }
            }
            arquivo.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao existe...");
        }
        return (colunas + 1);
    }

    public static int contaLinha(String planilha) {
        int caractere = 0;
        int linhas = 0;
        try {
            arquivo = new FileInputStream(planilha);
            leitor = new DataInputStream(arquivo);

            while (caractere != -1) {
                caractere = leitor.read();
                if (caractere == 10) {
                    linhas += 1;
                }
            }
            arquivo.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao existe...");
        }
        return (linhas - 1);
    }

    public static void getData(String planilha) {
        int caractere = 0, linha = 0;
        boolean cabecalho = true;
        String buffer = "";

        int linhas = DadosCSV.contaLinha(planilha);
        int colunas = DadosCSV.contaColuna(planilha);

        dados = new String[linhas][colunas];
        nomesColunas = new String[colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                dados[i][j] = "";
            }
        }
        for (int i = 0; i < colunas; i++) {
            nomesColunas[i] = "";
        }

        try {
            arquivo = new FileInputStream(planilha);
            leitor = new DataInputStream(arquivo);

            while (caractere != -1) {
                caractere = leitor.read();

                if (caractere != 10 && caractere != -1) {
                    buffer += (char) caractere;
                }

                if (caractere == 10) {
                    if (cabecalho) {
                        nomesColunas = buffer.split(";");
                        cabecalho = false;
                        buffer = "";
                    } else {
                        dados[linha] = buffer.split(";");
                        linha++;
                        buffer = "";
                    }
                }
            }
            arquivo.close();
        } catch (IOException erro) {
            System.out.println("Arquivo nao existe...");
        }
    }
    
    public static void efetuaCadastro(String id, String nome, String tonelada)
	{
		try
		{
			FileWriter fwtr = new FileWriter("transportadoras.csv", true);
			BufferedWriter bwtr = new BufferedWriter(fwtr);
			PrintWriter pwtr = new PrintWriter(bwtr);
			
			pwtr.println(id+";"+nome);
			pwtr.flush();
			pwtr.close();
			
			FileWriter fwval = new FileWriter("transportadoras.csv", true);
			BufferedWriter bwval = new BufferedWriter(fwval);
			PrintWriter pwval = new PrintWriter(bwval);
			
			pwval.println(id+";"+tonelada);
			pwval.flush();
			pwval.close();
			
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
			
		}
		catch(Exception E)
		{
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
		}
	}

}

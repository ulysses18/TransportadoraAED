package Application;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Cadastro {
	
	JFrame telaCadastro;
	JLabel labelTitulo, labelNome, labelValorTonelada;
	JTextField nome;
	
	
	public void criaJanelaCadastro()
	{
		telaCadastro = new JFrame();              
		telaCadastro.getContentPane().setBackground(Color.DARK_GRAY);
		telaCadastro.setVisible(true);
		telaCadastro.setResizable(false);
		telaCadastro.setTitle("Cadastro de transportadora");
		telaCadastro.setBounds(250, 200, 550, 350);
		telaCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		labelTitulo = new JLabel("Insira os dados da transportadora");
		labelTitulo.setBounds(120, 0, 250, 20);
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 15));
		
		labelNome = new JLabel("Nome da transportadora: ");
		labelNome.setBounds(100, 250, 80, 20);
		labelNome.setForeground(Color.WHITE);
		
		
		telaCadastro.getContentPane().add(labelTitulo);
		telaCadastro.getContentPane().add(labelNome);
	}
	
	
}

package Application;

import java.awt.*;
import javax.swing.*;

public class Cadastro {
	
	JFrame telaCadastro;
	JLabel labelTitulo, labelNome, labelValorTonelada;
	JButton cadastrar;
	JTextField nomeTransportadora, valorPorTonelada;
	
	public void criaJanelaCadastro()
	{
		telaCadastro = new JFrame();              
		telaCadastro.getContentPane().setBackground(Color.DARK_GRAY);
		telaCadastro.setVisible(true);
		telaCadastro.setResizable(false);
		telaCadastro.setTitle("Cadastro de transportadora");
		telaCadastro.setBounds(250, 200, 550, 500);
				
		labelTitulo = new JLabel("Insira os dados da transportadora");
		labelTitulo.setBounds(120, 20, 300, 20);
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 15));
		
		labelNome = new JLabel("Nome da transportadora: ");
		labelNome.setBounds(0, 224, 200, 15);
		labelNome.setForeground(Color.WHITE);
		
		nomeTransportadora = new JTextField();
		nomeTransportadora.setBounds(200, 220, 250, 20);
		
		labelValorTonelada = new JLabel("Valor por tonelada: ");
		labelValorTonelada.setBounds(0, 254, 150, 15);
		labelValorTonelada.setForeground(Color.WHITE);
		
		valorPorTonelada = new JTextField();
		valorPorTonelada.setBounds(200, 250, 250, 20);
		
		cadastrar = new JButton();
		cadastrar.setText("Cadastrar");
		cadastrar.setBounds(200,400,150,25);
		cadastrar.setFocusable(false);
		
		telaCadastro.getContentPane().setLayout(null);
		telaCadastro.getContentPane().add(labelNome);
		telaCadastro.getContentPane().add(nomeTransportadora);
		telaCadastro.getContentPane().add(labelValorTonelada);
		telaCadastro.getContentPane().add(labelTitulo);
		telaCadastro.getContentPane().add(valorPorTonelada);
		telaCadastro.getContentPane().add(cadastrar);
		
	}
	
	
}

package Application;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;

import Entities.DadosCSV;

public class Cadastro implements ActionListener{
	
	JFrame telaCadastro;
	JLabel labelTitulo, labelID, labelNome, labelValorTonelada;
	JButton cadastrar;
	JTextField IDTransportadora, nomeTransportadora, valorPorTonelada;
	
	public void criaJanelaCadastro()
	{
		telaCadastro = new JFrame();              
		telaCadastro.getContentPane().setBackground(Color.DARK_GRAY);
		telaCadastro.setVisible(true);
		telaCadastro.setResizable(false);
		telaCadastro.setTitle("Cadastro de transportadora");
		telaCadastro.setBounds(250, 130, 550, 500);
				
		labelTitulo = new JLabel("Insira os dados da transportadora");
		labelTitulo.setBounds(150, 20, 300, 20);
		labelTitulo.setForeground(Color.WHITE);
		labelTitulo.setFont(new Font("Dialog", Font.BOLD, 17));
		
		labelID = new JLabel("ID da transportadora: ");
		labelID.setBounds(0, 190, 200, 15);
		labelID.setForeground(Color.WHITE);
		
		IDTransportadora = new JTextField();
		IDTransportadora.setBounds(200, 190, 250, 20);
		
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
		cadastrar.setForeground(Color.WHITE);
		cadastrar.setBackground(Color.ORANGE);
		cadastrar.setFocusable(false);
		cadastrar.addActionListener(this);
		
		telaCadastro.getContentPane().setLayout(null);
		telaCadastro.getContentPane().add(labelNome);
		telaCadastro.getContentPane().add(nomeTransportadora);
		telaCadastro.getContentPane().add(labelValorTonelada);
		telaCadastro.getContentPane().add(labelTitulo);
		telaCadastro.getContentPane().add(valorPorTonelada);
		telaCadastro.getContentPane().add(cadastrar);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == cadastrar)
		{
						
			String nome = nomeTransportadora.getText();
			String tonelada = valorPorTonelada.getText();		
			
			double aux = Double.valueOf(tonelada).doubleValue();
			tonelada = Double.toString(aux);
		
			String ID = DadosCSV.efetuaCadastro(nome, tonelada);
			
			Programa.inputID.addItem(ID);
			
			telaCadastro.dispose(); //apos o cadastro ser feito e o pop-up
									//confirmado, a janela de cadastro é fechada									
		}
			
	}
			
}

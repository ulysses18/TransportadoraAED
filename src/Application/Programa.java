package Application;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import Entities.DadosCSV;
//
public class Programa {

	private JFrame frmControleDeGastos;
	private JTextField inputID;
	JLabel lblBemVindo;
	JLabel labelTransportadora;
	JButton btnConsultar;
	JTable tabela;
    JScrollPane painel;
    //String[][]       dados;
	//String[]         nomesColunas;
	//FileInputStream  arquivo;
	//DataInputStream  leitor;

	// Rodar o app em memória principal.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Programa window = new Programa();
					window.frmControleDeGastos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Criar app
	public Programa() {
		initialize();
	}

	// Iniciar app.
	private void initialize() {
		// JFrame principal
		frmControleDeGastos = new JFrame();
		frmControleDeGastos.getContentPane().setFocusTraversalPolicyProvider(true);
		frmControleDeGastos.getContentPane().setForeground(Color.WHITE);
		frmControleDeGastos.getContentPane().setBackground(Color.DARK_GRAY);
		frmControleDeGastos.setBackground(Color.WHITE);
		frmControleDeGastos.setAlwaysOnTop(true);
		frmControleDeGastos.setLocation(new Point(0, 0));
		frmControleDeGastos.setBounds(new Rectangle(0, 0, 0, 0));
		frmControleDeGastos.setVisible(true);
		frmControleDeGastos.setTitle("Controle de gastos");
		frmControleDeGastos.setBounds(100, 100, 910, 522);
		frmControleDeGastos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DadosCSV.getData("transportadoras.csv");
		tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
        painel = new JScrollPane(tabela);
        painel.setBounds(123, 70, 640, 204);

		// Labels
		lblBemVindo = new JLabel("Bem vindo!");
		lblBemVindo.setBounds(395, 12, 92, 18);
		lblBemVindo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBemVindo.setForeground(Color.WHITE);

		labelTransportadora = new JLabel("ID Transportadora");
		labelTransportadora.setBounds(185, 333, 141, 15);
		labelTransportadora.setForeground(Color.WHITE);

		// InputText do ID da transportadora
		inputID = new JTextField();
		inputID.setBounds(338, 326, 221, 29);
		inputID.setColumns(10);

		// Botões
		btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setBackground(Color.ORANGE);
		btnConsultar.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnConsultar.setBounds(586, 327, 122, 27);
		btnConsultar.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent arg0) {
				// Código quando clicar no botão
				System.out.println(inputID.getText());
			}
		});


		// Adição dos componentes na tela
		frmControleDeGastos.getContentPane().setLayout(null);
		frmControleDeGastos.getContentPane().add(lblBemVindo);
		frmControleDeGastos.getContentPane().add(painel);
		frmControleDeGastos.getContentPane().add(labelTransportadora);
		frmControleDeGastos.getContentPane().add(inputID);
		frmControleDeGastos.getContentPane().add(btnConsultar);
	}

}


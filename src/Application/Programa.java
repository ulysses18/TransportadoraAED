package Application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.MatteBorder;

import Entities.DadosCSV;
//
public class Programa implements WindowListener, ActionListener{

	private JFrame frmControleDeGastos;
	private JTextField inputID;
	JLabel lblBemVindo;
	JLabel labelTransportadora;
	JButton btnConsultar, btnProximo;
	JTable tabela;
    JScrollPane painel;
    int planilha = 1;

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
		planilha = 1;
		tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
        painel = new JScrollPane(tabela);
        painel.setBounds(123, 70, 640, 204);

		// Labels
		lblBemVindo = new JLabel("Bem vindo!");
		lblBemVindo.setBounds(395, 12, 92, 18);
		lblBemVindo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBemVindo.setForeground(Color.WHITE);

		labelTransportadora = new JLabel("ID Transportadora");
		labelTransportadora.setBounds(125, 290, 141, 15);
		labelTransportadora.setForeground(Color.WHITE);

		// InputText do ID da transportadora
		inputID = new JTextField();
		inputID.setBounds(255, 285, 221, 29);
		inputID.setColumns(10);

		// Botões
		btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setBackground(Color.ORANGE);
		btnConsultar.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnConsultar.setBounds(500, 285, 122, 27);
		btnConsultar.addActionListener(this);
		/*{
			
			public void actionPerformed(ActionEvent arg0) {
				// Código quando clicar no botão
				System.out.println(inputID.getText());
				// segundo argumento = mensagem na tela
				// terceiro = título do popup
				JOptionPane.showMessageDialog(null, "Olá mundo!", "Informações", JOptionPane.QUESTION_MESSAGE);
			}
		});*/
		
		
		btnProximo = new JButton("Pr�ximo");
		btnProximo.setForeground(Color.WHITE);
		btnProximo.setBackground(Color.ORANGE);
		btnProximo.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnProximo.setBounds(770, 150, 120, 27);
		btnProximo.addActionListener(this);
		


		// Adição dos componentes na tela
		frmControleDeGastos.getContentPane().setLayout(null);
		frmControleDeGastos.getContentPane().add(lblBemVindo);
		frmControleDeGastos.getContentPane().add(painel);
		frmControleDeGastos.getContentPane().add(labelTransportadora);
		frmControleDeGastos.getContentPane().add(inputID);
		frmControleDeGastos.getContentPane().add(btnConsultar);
		frmControleDeGastos.getContentPane().add(btnProximo);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource() == btnConsultar)
		{
			System.out.println(inputID.getText());
			// segundo argumento = mensagem na tela
			// terceiro = título do popup
			JOptionPane.showMessageDialog(null, "Olá mundo!",
					"Informações", JOptionPane.QUESTION_MESSAGE);
		}
		if(evento.getSource() == btnProximo && planilha == 1)
		{
			frmControleDeGastos.getContentPane().remove(painel);
			DadosCSV.getData("frete-por-estado.csv");
			tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
			painel = new JScrollPane(tabela);
			painel.setBounds(123, 70, 640, 204);
			frmControleDeGastos.getContentPane().add(painel);
			planilha = 2;
		}
		else if(evento.getSource() == btnProximo && planilha == 2)
		{
			frmControleDeGastos.getContentPane().remove(painel);
			DadosCSV.getData("valor-por-ton.csv");
			tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
			painel = new JScrollPane(tabela);
			painel.setBounds(123, 70, 640, 204);
			frmControleDeGastos.getContentPane().add(painel);
			planilha = 3;
		}
		else if(evento.getSource() == btnProximo && planilha == 3)
		{
			frmControleDeGastos.getContentPane().remove(painel);
			DadosCSV.getData("transportadoras.csv");
			tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
			painel = new JScrollPane(tabela);
			painel.setBounds(123, 70, 640, 204);
			frmControleDeGastos.getContentPane().add(painel);
			planilha = 1;
		}
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


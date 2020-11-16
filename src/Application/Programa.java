package Application;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import Entities.DadosCSV;
import Entities.Orcamento;

public class Programa implements WindowListener, ActionListener{

	Orcamento dados = new Orcamento();
	private JFrame frmControleDeGastos;
	private JComboBox inputID, inputRegiao;
	private JTextField inputTonelada;
	JLabel lblBemVindo;
	JLabel labelTransportadora, labelRegiao, labelTonelada;
	JButton btnConsultar, btnProximo;
	JTable tabela;
    JScrollPane painel;
    LocalDate data = LocalDate.now();
    
    int planilha = 1;


	// Rodar o app em memória principal.
	public static void main(String[] args) {	
		
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			{
				try 
				{
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
		
		Locale.setDefault(new Locale("pt", "BR"));

		
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
		lblBemVindo = new JLabel("Transportadoras cadastradas");
		lblBemVindo.setBounds(395, 12, 250, 18);
		lblBemVindo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBemVindo.setForeground(Color.WHITE);

		labelTransportadora = new JLabel("ID Transportadora");
		labelTransportadora.setBounds(125, 290, 140, 20);
		labelTransportadora.setForeground(Color.WHITE);
		
		labelTonelada = new JLabel("Toneladas da carga");
		labelTonelada.setBounds(125, 290, 140, 95);
		labelTonelada.setForeground(Color.WHITE);
		
		labelRegiao = new JLabel("Regiao do destino");
		labelRegiao.setBounds(125, 290, 140, 170);
		labelRegiao.setForeground(Color.WHITE);

		// InputText do ID, Regiao e toneladas da transportadora
		inputID = new JComboBox();

		inputID.addItem("<Selecione um ID>");
		for (int i = 0; i < DadosCSV.contaLinha("transportadoras.csv"); i++) 
		{
			inputID.addItem(DadosCSV.dados[i][0]);
		}

		inputID.setBounds(255, 285, 221, 30);
		
		inputTonelada = new JTextField();
		inputTonelada.setBounds(255, 320, 221, 30);
		inputTonelada.setColumns(10);
		
		inputRegiao = new JComboBox();
		inputRegiao.setBounds(255, 355, 221, 30);

		inputRegiao.addItem("<Selecione a regiao>");
		for (int i = 0; i < DadosCSV.contaLinha("frete-por-estado.csv"); i++)
		{
			inputRegiao.addItem(Orcamento.nomeRegiao(i));
		}

		// Botões
		btnConsultar = new JButton("Consultar");
		btnConsultar.setForeground(Color.WHITE);
		btnConsultar.setBackground(Color.ORANGE);
		btnConsultar.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		btnConsultar.setBounds(500, 320, 120, 30);
		btnConsultar.addActionListener(this);
		
		btnProximo = new JButton("Proximo");
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
		frmControleDeGastos.getContentPane().add(labelRegiao);
		frmControleDeGastos.getContentPane().add(labelTonelada);
		frmControleDeGastos.getContentPane().add(inputID);
		frmControleDeGastos.getContentPane().add(inputRegiao);
		frmControleDeGastos.getContentPane().add(inputTonelada);
		frmControleDeGastos.getContentPane().add(btnConsultar);
		frmControleDeGastos.getContentPane().add(btnProximo);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if(evento.getSource() == btnConsultar)
		{			
			
			int linhaTabelaDeTon;
			float total;
			float frete;
			String regiao;
						
			//le o id da transportadora e converte para Int
			//usa esse id para saber de qual linha 
			//o valor sera extraido
			String idLido = inputID.getSelectedItem().toString();
			linhaTabelaDeTon = Integer.parseInt(idLido);
			dados.leToneladas(linhaTabelaDeTon);
		
			//le qual regiao vai ser o frete e converte para float
			//o valor correspondente
			String linhaTabelaRegiao = inputRegiao.getSelectedItem().toString();
			
			regiao = dados.comparaRegiao(linhaTabelaRegiao);
			frete = Float.parseFloat(regiao);
		
			dados.valorPorTonelada = Float.parseFloat(dados.ton);
			String toneladaLida = inputTonelada.getText();
			total = dados.valorTotal(toneladaLida, frete);
			data = data.plusDays(Orcamento.converteDia());
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String dataFormatada = data.format(formato);

			System.out.println(total);
						
			JOptionPane.showMessageDialog(null,"Valor da viagem: "
			+ "R$"+total + "\nPrevisao de chegada: " + dataFormatada,
					"Orcamento", JOptionPane.QUESTION_MESSAGE);
			
		}
		if(evento.getSource() == btnProximo && planilha == 1)
		{
			
			frmControleDeGastos.getContentPane().remove(painel);
			lblBemVindo.setText("Valor por tonelada");
			DadosCSV.getData("valor-por-ton.csv");			
			tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
			painel = new JScrollPane(tabela);
			painel.setBounds(123, 70, 640, 204);
			frmControleDeGastos.getContentPane().add(painel);
			planilha = 2;
		}
		else if(evento.getSource() == btnProximo && planilha == 2)
		{
			frmControleDeGastos.getContentPane().remove(painel);
			lblBemVindo.setText("Frete por estado");
			DadosCSV.getData("frete-por-estado.csv");
			tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
			painel = new JScrollPane(tabela);
			painel.setBounds(123, 70, 640, 204);
			frmControleDeGastos.getContentPane().add(painel);
			planilha = 3;
		}
		else if(evento.getSource() == btnProximo && planilha == 3)
		{
			frmControleDeGastos.getContentPane().remove(painel);
			lblBemVindo.setText("Transportadoras cadastradas");
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

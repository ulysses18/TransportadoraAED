package Application;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import Entities.*;
import javax.swing.border.MatteBorder;

/***
 * 
 * @author Jean Martins -> RA 675719
 * @author Ulysses Ferreira -> RA 680309
 * @author Lucas Paiva -> RA 679657
 * 
 * Este trabalho tem como objetivo, dar um maior controle sobre os gastos com fretes pelo país.
 * 
 * Os nomes das transportadoras são fictícias
 * 
 */

public class Programa implements ActionListener {

    Orcamento dados = new Orcamento();
    Cadastro cadastro = new Cadastro();
    static JFrame frmControleDeGastos;
    static JComboBox inputID;
    static JComboBox inputRegiao;
	static JTextField inputTonelada;
    JLabel lblBemVindo;
    JLabel labelTransportadora, labelRegiao, labelTonelada;
    JButton btnConsultar, btnProximo, btnAdd;
    JTable tabela;
    JScrollPane painel;
    LocalDate data = LocalDate.now();

    // Planilha ativa
    int planilha = 1;

    // Rodar o app em memória principal.
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                Programa window = new Programa();
                window.frmControleDeGastos.setVisible(true);
            } catch (Exception e) {}
        });
    }

    // Criar o app
    public Programa() {
        initialize();
    }

    // Inicializa o app
    private void initialize() {
       
        // JFrame principal
        frmControleDeGastos = new JFrame();              
        frmControleDeGastos.getContentPane().setBackground(Color.DARK_GRAY);
        frmControleDeGastos.setVisible(true);
        frmControleDeGastos.setResizable(false);
        frmControleDeGastos.setTitle("Controle de gastos");
        frmControleDeGastos.setBounds(100, 100, 910, 522);
        frmControleDeGastos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DadosCSV.getData("transportadoras.csv");
        tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
        painel = new JScrollPane(tabela);
        painel.setBounds(125, 70, 640, 185);

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

        labelRegiao = new JLabel("Regiao de destino");
        labelRegiao.setBounds(125, 290, 140, 170);
        labelRegiao.setForeground(Color.WHITE);

        // InputText do ID, Regiao e toneladas da transportadora
        inputID = new JComboBox();
        inputID.setBounds(275, 285, 221, 30);
        inputID.addItem("<Selecione um ID>");
        for (int i = 0; i < DadosCSV.contaLinha("transportadoras.csv"); i++)
        {
            inputID.addItem(DadosCSV.dados[i][0]);
        }

        inputTonelada = new JTextField();
        inputTonelada.setBounds(275, 320, 221, 30);
        inputTonelada.setColumns(10);

        inputRegiao = new JComboBox();
        inputRegiao.setBounds(275, 355, 221, 30);
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
        btnConsultar.setBounds(530, 320, 120, 30);
        btnConsultar.addActionListener(this);
        btnConsultar.setFocusable(false);

        btnProximo = new JButton("Proximo");
        btnProximo.setForeground(Color.WHITE);
        btnProximo.setBackground(Color.ORANGE);
        btnProximo.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
        btnProximo.setBounds(770, 150, 120, 27);
        btnProximo.addActionListener(this);
        
        btnAdd = new JButton("Cadastrar");
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setBackground(Color.ORANGE);
        btnAdd.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
        btnAdd.setBounds(770, 450, 120, 27);
        btnAdd.addActionListener(this);
        
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
        frmControleDeGastos.getContentPane().add(btnAdd);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Ações de clique dos botões
        if (evento.getSource() == btnConsultar) {

            int linhaTabelaDeTon;
            float total;
            float frete;
            String regiao;

            //Converte o ID da transportadora para Int
            //O número do ID é utilizado para encontrar a linha correspondente e extrair o valor
            String idLido = inputID.getSelectedItem().toString();
            linhaTabelaDeTon = Integer.parseInt(idLido);
            dados.leToneladas(linhaTabelaDeTon);

            //Converte o frete de determinada região para Float
            String linhaTabelaRegiao = inputRegiao.getSelectedItem().toString();

            regiao = dados.comparaRegiao(linhaTabelaRegiao);
            frete = Float.parseFloat(regiao);

            dados.valorPorTonelada = Float.parseFloat(dados.ton);
            String toneladaLida = inputTonelada.getText();
            total = dados.valorTotal(toneladaLida, frete);
            data = data.plusDays(Orcamento.converteDia());
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = data.format(formato);
  
            // Cria uma mensagem de diálogo na tela, descrevendo os valores e prazos
            JOptionPane.showMessageDialog(null, "Valor da viagem: "
                    + "R$" + total + "\nPrevisao de chegada: " + dataFormatada,
                    "Orcamento", JOptionPane.QUESTION_MESSAGE);
            
            data = LocalDate.now(); //Voltar a data atual,
            						//para nao acumular
        }

        if (evento.getSource() == btnProximo && planilha == 1) {

            frmControleDeGastos.getContentPane().remove(painel);
            lblBemVindo.setText("Valor por tonelada");
            DadosCSV.getData("valor-por-ton.csv");
            tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
            painel = new JScrollPane(tabela);
            painel.setBounds(125, 70, 640, 185);
            frmControleDeGastos.getContentPane().add(painel);
            planilha = 2;
            
        } else if (evento.getSource() == btnProximo && planilha == 2) {
        	
        	painel.setBounds(125, 70, 640, 103);
            frmControleDeGastos.getContentPane().remove(painel);
            lblBemVindo.setText("Frete por estado");
            DadosCSV.getData("frete-por-estado.csv");
            tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
            painel = new JScrollPane(tabela);
            painel.setBounds(125, 70, 640, 103);
            frmControleDeGastos.getContentPane().add(painel);
            planilha = 3;
            
        } else if (evento.getSource() == btnProximo && planilha == 3) {
        	
            frmControleDeGastos.getContentPane().remove(painel);
            lblBemVindo.setText("Transportadoras cadastradas");
            DadosCSV.getData("transportadoras.csv");
            tabela = new JTable(DadosCSV.dados, DadosCSV.nomesColunas);
            painel = new JScrollPane(tabela);
            painel.setBounds(125, 70, 640, 185);
            frmControleDeGastos.getContentPane().add(painel);
            planilha = 1;
            
        }
        if(evento.getSource() == btnAdd)
        {
        	cadastro.criaJanelaCadastro();
        }

    }
    
}

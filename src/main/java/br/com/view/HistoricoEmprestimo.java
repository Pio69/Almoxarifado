package br.com.view;

/*
SENAI
PSIN
MI-66
Objetivo: Visualição da tela do historico de emprestimo.
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 

Nome: Guilherme Witkosky
Alterou: Comentário do código 

*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import br.com.model.Pessoa;
import br.com.model.table.AcaoTable;
import br.com.util.XlsInsert;

public class HistoricoEmprestimo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private AcaoTable acaoTable;
	
	/* 
	 * Objetivo: Carregar os componentes presentes na tela.
	*/
	
	public HistoricoEmprestimo(Pessoa user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarProduto.class.getResource("/imagens/weg-logo.png")));
		setTitle("WEG SA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 116, 634, 343);
		contentPane.add(scrollPane);
		
		// TABELA DE PRODUTO
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		acaoTable = new AcaoTable();
		scrollPane.setViewportView(table);
		table.setModel(acaoTable);
		
		// VOLTA PARA A TELA PRINCIPAL
		JButton btnSair = new JButton("Voltar");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				FrameProduto frameProduto = new FrameProduto(user);
				frameProduto.setVisible(true);
				dispose();
				
			}
		});
		
		//Botao sair 
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.BLACK);
		btnSair.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnSair.setBounds(518, 82, 106, 23);
		contentPane.add(btnSair);
		
		// BOTAO DOWNLOAD
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				XlsInsert.createTabela();
				
			}
		});
		btnDownload.setForeground(Color.WHITE);
		btnDownload.setBackground(Color.BLACK);
		btnDownload.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnDownload.setBounds(402, 82, 106, 23);
		contentPane.add(btnDownload);

	}
}

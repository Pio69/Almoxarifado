package br.com.view;

/*
SENAI
PSIN
MI-66
Objetivo: Visualição da tela de empréstimo.
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 

Nome: Guilherme Witkosky
Alterou: Comentário do código 

*/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.List;
import br.com.dao.GenericDao;
import br.com.model.Acao;
import br.com.model.Pessoa;
import br.com.model.Produto;
import br.com.model.Usuario;
import br.com.model.table.AcaoTable;
import br.com.model.table.PessoasTable;
import br.com.model.table.ProdutoTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Emprestar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableProd;

	private ProdutoTable produtoTable;
	private JButton btnInsert;
	private JButton btnRemove;
	private GenericDao genericDao;
	
	/* 
	 * Objetivo: Carregar os componentes presentes na tela.
	*/
	
	@SuppressWarnings("unchecked")
	public Emprestar(Pessoa pessoa) {
		
		genericDao = new GenericDao();		
		final Pessoa pessoaAluguel = (Pessoa) genericDao.search(3, new Pessoa());
		
		setTitle("Home");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 99, 614, 350);
		contentPane.add(scrollPane);

		// TABLE DE Pessoa
		tableProd = new JTable();
		tableProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnRemove.setEnabled(true);

			}
		});
		
		//Tabela de produtos
		tableProd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		produtoTable = new ProdutoTable();
		tableProd.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		scrollPane.setViewportView(tableProd);
		tableProd.setModel(produtoTable);

		// CHAMA A TELA DE CADASTRO
		btnInsert = new JButton("Emprestar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				genericDao = new GenericDao();
				Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
				
				Acao acao = new Acao();
				acao.setPessoa(pessoaAluguel);
				acao.setProduto((Produto) genericDao.search(Integer.parseInt(tableProd.getValueAt(tableProd.getSelectedRow(), 0).toString()),new Produto()));
				acao.setEntregue(false);
				acao.setDataRetirada(dataAtual);
				
				genericDao.insert(acao);
				
				produtoTable = new ProdutoTable();
				tableProd.setModel(produtoTable);
				
				btnRemove.setEnabled(false);

			}
		});
		
		//Botao de inserir
		btnInsert.setForeground(new Color(0, 100, 0));
		btnInsert.setBackground(Color.WHITE);
		btnInsert.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		btnInsert.setBounds(10, 65, 121, 23);
		contentPane.add(btnInsert);

		// REMOVE A PESSOA
		btnRemove = new JButton("Voltar");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alugar alugar = new Alugar(new Pessoa());
				alugar.setVisible(true);
				dispose();
			}
		});
		
		//Botao de remover
		btnRemove.setEnabled(false);
		btnRemove.setForeground(new Color(128, 0, 0));
		btnRemove.setBackground(new Color(255, 255, 255));
		btnRemove.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		btnRemove.setBounds(141, 65, 121, 23);
		contentPane.add(btnRemove);
	}
}

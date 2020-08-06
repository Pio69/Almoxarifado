package br.com.view;

/*
SENAI
PSIN
MI-66
Objetivo: Visualição da tela home.
Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano e Vinicius Silva Sena
Data: 06/08/2020

Alterações: 

Nome: Guilherme Witkosky
Alterou: Comentário do código 

*/

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import br.com.controller.PessoaController;
import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.model.table.PessoasTable;
import java.awt.Toolkit;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePessoas;

	private PessoasTable pessoasTable;
	private JButton btnInsert;
	private JButton btnRemove;
	private JButton btnUpdate;
	private GenericDao genericDao;
	private JButton btnTeste;
	
	/* 
	 * Objetivo: Carregar os componentes presentes na tela.
	*/

	@SuppressWarnings("unchecked")
	public Home(Pessoa user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/imagens/weg-logo.png")));
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
		scrollPane.setBounds(10, 99, 614, 350);
		contentPane.add(scrollPane);

		// TABLE DE Pessoa
		tablePessoas = new JTable();
		tablePessoas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				btnRemove.setEnabled(true);
				btnUpdate.setEnabled(true);

			}
		});
		
		//Tabela pessoas
		tablePessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pessoasTable = new PessoasTable();
		tablePessoas.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		scrollPane.setViewportView(tablePessoas);
		tablePessoas.setModel(pessoasTable);

		// CHAMA A TELA DE CADASTRO
		btnInsert = new JButton("Cadastrar");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cadastrar cadastrar = new Cadastrar(user);
				cadastrar.setVisible(true);
				setVisible(false);

			}
		});
		
		//Botao para inserir
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setFocusPainted(false);
		btnInsert.setFont(new Font("3ds", Font.BOLD, 16));
		btnInsert.setBounds(10, 65, 121, 23);
		contentPane.add(btnInsert);

		// REMOVE A PESSOA
		btnRemove = new JButton("Remover");
		btnRemove.setFocusPainted(false);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				PessoaController.remove(tablePessoas, user, pessoasTable, btnUpdate, btnRemove);

			}
		});
		
		//Botao de remover
		btnRemove.setEnabled(false);
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setBackground(Color.BLACK);
		btnRemove.setFont(new Font("3ds", Font.BOLD, 16));
		btnRemove.setBounds(141, 65, 121, 23);
		contentPane.add(btnRemove);

		// CHAMA A TELA DE EDITAR
		btnUpdate = new JButton("Editar");
		btnUpdate.setFocusPainted(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Pessoa pessoa = new Pessoa();
				genericDao = new GenericDao();
				List<Pessoa> pessoas = (List<Pessoa>) genericDao.select(pessoa);
				int index = tablePessoas.getSelectedRow();

				pessoa.setId(pessoas.get(index).getId());
				pessoa.setNome(pessoas.get(index).getNome());
				pessoa.setCpf(pessoas.get(index).getCpf());
				pessoa.setEntrada(pessoas.get(index).getEntrada());
				pessoa.setSaida(pessoas.get(index).getSaida());
				pessoa.setUsuario(pessoas.get(index).getUsuario());

				Editar editar = new Editar(pessoa, user);
				editar.setVisible(true);
				setVisible(false);

			}
		});
		
		//Botao de edicao
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("3ds", Font.BOLD, 16));
		btnUpdate.setBounds(272, 65, 121, 23);
		contentPane.add(btnUpdate);

		// TESTE
		btnTeste = new JButton("Produtos");
		btnTeste.setForeground(Color.WHITE);
		btnTeste.setBackground(Color.BLACK);
		btnTeste.setFocusPainted(false);
		btnTeste.setFont(new Font("3ds", Font.BOLD, 16));
		btnTeste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameProduto frameProduto = new FrameProduto(user);
				frameProduto.setVisible(true);
				setVisible(false);
			}
		});
		btnTeste.setBounds(449, 65, 175, 23);
		contentPane.add(btnTeste);

		//Botao sair
		JButton btnSair = new JButton("Sair");
		btnSair.setFocusPainted(false);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Login login = new Login();
				login.setVisible(true);
				setVisible(false);

			}
		});
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.BLACK);
		btnSair.setFont(new Font("3ds", Font.BOLD, 16));
		btnSair.setBounds(10, 11, 74, 23);
		contentPane.add(btnSair);
		
		//Botao emprestar
		JButton btnEmprestar = new JButton("Emprestar");
		btnEmprestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alugar frame = new Alugar(user);
				frame.setVisible(true);
				setVisible(false);
			}
		});
		
		btnEmprestar.setForeground(Color.WHITE);
		btnEmprestar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEmprestar.setFocusPainted(false);
		btnEmprestar.setBackground(Color.BLACK);
		btnEmprestar.setBounds(449, 32, 175, 23);
		contentPane.add(btnEmprestar);
	}
}

package br.com.view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.model.Produto;
import br.com.model.table.ProdutoTable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameProduto extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private ProdutoTable produtoTable; 
	private JButton btnEditar;
	private JButton btnCadastrar;
	
	public FrameProduto(Pessoa user) {
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				btnEditar.setEnabled(true);
				
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		produtoTable = new ProdutoTable();
		scrollPane.setViewportView(table);
		table.setModel(produtoTable);
		
		// TELA DE EDICAO
		btnEditar = new JButton("Editar");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Produto produto = new Produto();
				GenericDao dao = new GenericDao();
				List<Produto> produtos = (List<Produto>) dao.select(produto); 
				produto = produtos.get(table.getSelectedRow());
				
				EditarProduto editar = new EditarProduto(user, produto);
				editar.setVisible(true);
				setVisible(false);
				
			}
		});
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBackground(Color.BLACK);
		btnEditar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnEditar.setBounds(10, 82, 106, 23);
		contentPane.add(btnEditar);
		
		// VOLTA PARA A TELA PRINCIPAL
		JButton btnSair = new JButton("Voltar");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Home home = new Home(user);
				home.setVisible(true);
				setVisible(false);
				
			}
		});
		btnSair.setForeground(Color.WHITE);
		btnSair.setBackground(Color.BLACK);
		btnSair.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnSair.setBounds(518, 82, 106, 23);
		contentPane.add(btnSair);
		
		// CADASTRAR PRODUTO
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastrarProduto cadastrarProduto = new CadastrarProduto(user);
				cadastrarProduto.setVisible(true);
				setVisible(false);
				
			}
		});
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setBackground(Color.BLACK); 
		btnCadastrar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnCadastrar.setBounds(126, 82, 106, 23);
		contentPane.add(btnCadastrar);
		
		// CHAMA A TELA DE HISTORICO
		JButton btnHistorico = new JButton("Historico");
		btnHistorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				HistoricoEmprestimo historicoEmprestimo = new HistoricoEmprestimo(user);
				historicoEmprestimo.setVisible(true);
				dispose();
				
			}
		});
		btnHistorico.setForeground(Color.WHITE);
		btnHistorico.setBackground(Color.BLACK);
		btnHistorico.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 14));
		btnHistorico.setBounds(242, 82, 106, 23);
		contentPane.add(btnHistorico);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Home tela = new Home(user);
				dispose();
				tela.setVisible(true);
			}
		});
	}
}

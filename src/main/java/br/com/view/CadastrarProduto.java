package br.com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.controller.ProdutoController;
import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.model.Produto;
import br.com.model.Usuario;
import java.awt.Toolkit;

public class CadastrarProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtDesc;

	/**
	 * Create the frame.
	 */
	public CadastrarProduto(Pessoa user) {
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

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home tela = new Home(user);
				dispose();
				tela.setVisible(true);
			}
		});
		btnVoltar.setBounds(46, 425, 89, 23);
		btnVoltar.setBackground(Color.BLACK);
		btnVoltar.setFont(new Font("3ds", Font.BOLD, 14));
		btnVoltar.setForeground(Color.WHITE);
		contentPane.add(btnVoltar);

		txtNome = new JTextField();
		txtNome.setBounds(183, 172, 254, 20);
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("3ds", Font.PLAIN, 14));
		txtNome.setColumns(10);
		contentPane.add(txtNome);

		txtDesc = new JTextField();
		txtDesc.setBounds(183, 246, 254, 20);
		txtDesc.setHorizontalAlignment(SwingConstants.CENTER);
		txtDesc.setFont(new Font("3ds", Font.PLAIN, 14));
		txtDesc.setColumns(10);
		contentPane.add(txtDesc);

		JLabel label = new JLabel("Nome");
		label.setBounds(183, 141, 46, 14);
		label.setFont(new Font("3ds", Font.BOLD, 15));
		contentPane.add(label);

		JLabel lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(183, 221, 73, 14);
		lblDescrio.setFont(new Font("3ds", Font.BOLD, 15));
		contentPane.add(lblDescrio);

		// CADASTRA O PRODUTO
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GenericDao genericDao = new GenericDao();
				Produto produto = new Produto();

				produto.setNomeProduto(txtNome.getText());
				produto.setDescProduto(txtDesc.getText());
				produto.setEmprestado(false);

				genericDao.insert(produto);

				ProdutoController.gerarCode(String.valueOf(genericDao.lastInsertId(produto)));

			}
		});
		btnCadastrar.setBounds(183, 313, 101, 23);
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("3ds", Font.BOLD, 14));
		btnCadastrar.setBackground(Color.BLACK);
		contentPane.add(btnCadastrar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setBounds(336, 313, 101, 23);
		btnLimpar.setFont(new Font("3ds", Font.BOLD, 14));
		btnLimpar.setBackground(Color.BLACK);
		contentPane.add(btnLimpar);
	}
}

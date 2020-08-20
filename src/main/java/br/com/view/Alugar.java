/*
*SENAI 
*PSIN
*MI-66
*Objetivo: Vizualisação do produtos alugados
*Autores: Guilherme Witkosky, Kelvin Schneider, Leonardo Pio, Rafael Adriano e Vinicius Silva Sena
*Data: 06/08/2020
*
*Alterações:
*Nome: Kelvin Schneider
*Data: 06/08/2020
*Alterou: Documentação de código
*/

package br.com.view;

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

public class Alugar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableAcao;

	private AcaoTable acaoTable;
	private JButton btnInsert;
	private GenericDao genericDao;

	Integer produto;
	private JButton btnVoltar;

	private Leitor frame;

	public void setProduto(Integer produto) {
		this.produto = produto;
	}

	/* 
	 *
	 * Objetivo: Carregar os componentes presentes na tela
	 *
	*/
	
	@SuppressWarnings("unchecked")
	public Alugar( Pessoa user) {

		genericDao = new GenericDao();

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

		// TABLE DE PESSOA
		tableAcao = new JTable();
		tableAcao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		acaoTable = new AcaoTable((user.getUsuario().getTipo().equals("Gerente")) ? true : false,
				(user.getUsuario().getTipo().equals("Gerente")) ? null : user.getId());
		tableAcao.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 12));
		scrollPane.setViewportView(tableAcao);
		tableAcao.setModel(acaoTable);

		// CHAMA A TELA DE CADASTRO
		btnInsert = new JButton("Leitor");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame = new Leitor(user);
				frame.setVisible(true);

				// Pega dados automaticamente do usuario para o aluguel do produto
				acaoTable = new AcaoTable((user.getUsuario().getTipo().equals("Gerente")) ? true : false,
						(user.getUsuario().getTipo().equals("Gerente")) ? null : user.getId());
				acaoTable.fireTableDataChanged();
				tableAcao.setModel(acaoTable);

			}
		});
		btnInsert.setForeground(Color.WHITE);
		btnInsert.setBackground(Color.BLACK);
		btnInsert.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		btnInsert.setBounds(10, 65, 121, 23);
		contentPane.add(btnInsert);

		// Botão para voltar ao menu principal
		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/* 
				 * Verifica qual a permissao do usario
				 * e encaminha para a pagina na qual tem a permissao
				 * 
				 * */
				switch (user.getUsuario().getTipo()) {
					case "Gerente":
						Home frame = new Home(user);
						frame.setVisible(true);
						dispose();
						break;
	
					case "Professor":
						Login frame2 = new Login();
						frame2.setVisible(true);
						dispose();
						break;
	
					default:
						break;
				}

			}
		});
		btnVoltar.setForeground(Color.WHITE);
		btnVoltar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 15));
		btnVoltar.setBackground(Color.BLACK);
		btnVoltar.setBounds(503, 69, 121, 23);
		contentPane.add(btnVoltar);
	}
}

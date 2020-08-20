/*SENAI 
*PSIN
*MI-66
*Objetivo: Vizualisação dos campos necessarios para a edição de Usuario
*Autores: Guilherme Witkosky, Kelvin Schneider, Leonardo Pio, Rafael Adriano e Vinicius Sena
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

import br.com.comboBoxModel.ComboBoxUfModel;
import br.com.controller.PessoaController;
import br.com.dao.GenericDaoUf;
import br.com.model.Endereco;
import br.com.model.Pessoa;
import br.com.model.UnidadeFederativa;
import br.com.model.Usuario;
import br.com.util.JSONReader;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class Editar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTextField txtEntrada;
	private JTextField txtSaida;
	private JTextField txtSenha;
	private JComboBox<String> comboBox;
	
	private JTextField txtCep;
	private JTextField txtLogradouro;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtLocalidade;
	private JComboBox cmbUF; 

	private boolean validouCep;

	/* 
	 * Objetivo: Carregar os componentes presentes na tela
	 *
	*/
	public Editar( Pessoa pessoa,  Pessoa user) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editar.class.getResource("/imagens/weg-logo.png")));
		setTitle("Editar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// CAMPOS DE TEXTO
		txtNome = new JTextField();
		txtNome.setHorizontalAlignment(SwingConstants.CENTER);
		txtNome.setFont(new Font("3ds", Font.PLAIN, 14));
		txtNome.setColumns(10);
		txtNome.setBounds(10, 36, 254, 20);
		contentPane.add(txtNome);

		txtCpf = new JTextField();
		txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
		txtCpf.setFont(new Font("3ds", Font.PLAIN, 14));
		txtCpf.setColumns(10);
		txtCpf.setBounds(10, 92, 254, 20);
		contentPane.add(txtCpf);

		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 148, 254, 20);
		contentPane.add(txtEmail);
		
		txtSenha = new JTextField();
		txtSenha.setText((String) null);
		txtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		txtSenha.setFont(new Font("3ds", Font.PLAIN, 14));
		txtSenha.setColumns(10);
		txtSenha.setBounds(10, 204, 254, 20);
		contentPane.add(txtSenha);
		
		txtEntrada = new JTextField();
		txtEntrada.setText((String) null);
		txtEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		txtEntrada.setFont(new Font("3ds", Font.PLAIN, 14));
		txtEntrada.setColumns(10);
		txtEntrada.setBounds(10, 260, 254, 20);
		contentPane.add(txtEntrada);
		
		txtSaida = new JTextField();
		txtSaida.setText((String) null);
		txtSaida.setHorizontalAlignment(SwingConstants.CENTER);
		txtSaida.setFont(new Font("3ds", Font.PLAIN, 14));
		txtSaida.setColumns(10);
		txtSaida.setBounds(10, 316, 254, 20);
		contentPane.add(txtSaida);
		
		comboBox = new JComboBox<String>();
		comboBox.setBackground(Color.WHITE);
		comboBox.setFont(new Font("3ds", Font.PLAIN, 14));
		comboBox.addItem("Professor");
		comboBox.addItem("Gerente");
		comboBox.setBounds(10, 372, 254, 20);
		contentPane.add(comboBox);

		// SETA OS VALORES PARA OS CAMPOS
		txtNome.setText(pessoa.getNome());
		txtEmail.setText(pessoa.getUsuario().getEmail());
		txtCpf.setText(pessoa.getCpf());
		txtEntrada.setText(pessoa.getEntrada());
		txtSaida.setText(pessoa.getSaida());
		txtSenha.setText(pessoa.getUsuario().getSenha());
		comboBox.setSelectedItem(pessoa.getUsuario().getTipo());

		// LABELS PARA OS CAMPOS DE TEXTO
		JLabel label = new JLabel("Nome");
		label.setFont(new Font("3ds", Font.BOLD, 15));
		label.setBounds(10, 11, 46, 14);
		contentPane.add(label);

		JLabel label_1 = new JLabel("CPF");
		label_1.setFont(new Font("3ds", Font.BOLD, 15));
		label_1.setBounds(10, 67, 46, 14);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("Email");
		label_2.setFont(new Font("3ds", Font.BOLD, 15));
		label_2.setBounds(10, 123, 46, 14);
		contentPane.add(label_2);

		//BOTAO PARA SALVAR AS ALTERACOES
		JButton btnUpdate = new JButton("Salvar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 *  inseri os dados coletados na model de usuario
				 * */
				Usuario usuario = pessoa.getUsuario();
				usuario.setEmail(txtEmail.getText());
				usuario.setSenha(txtSenha.getText());
				usuario.setTipo(comboBox.getSelectedItem().toString());
				
				Pessoa pessoas = new Pessoa();
				pessoas.setId(pessoa.getId());
				pessoas.setNome(txtNome.getText());
				pessoas.setCpf(txtCpf.getText());
				pessoas.setEntrada(txtEntrada.getText());
				pessoas.setSaida(txtSaida.getText());
				pessoas.setUsuario(usuario);

				// VERIFICA SE ACAO FOI REALIZADA PARA APAGAR OS CAMPOS
				if (PessoaController.genericFunction(pessoas, Editar.this)) {
					Home home = new Home(user);
					home.setVisible(true);
					setVisible(false);
				}

			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("3ds", Font.BOLD, 14));
		btnUpdate.setBackground(Color.BLACK);
		btnUpdate.setBounds(523, 425, 101, 23);
		contentPane.add(btnUpdate);

		// LIMPA OS CAMPOS
		JButton btnClear = new JButton("Limpar");
		btnClear.setForeground(Color.WHITE);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PessoaController.clearUpdate(txtNome, txtCpf, txtEmail, txtSenha, txtEntrada, txtSaida);
			}
		});
		btnClear.setFont(new Font("3ds", Font.BOLD, 14));
		btnClear.setBackground(Color.BLACK);
		btnClear.setBounds(412, 425, 101, 23);
		contentPane.add(btnClear);

		// RETORNA PARA A TELA PRINCIPAL
		JButton btnReturn = new JButton("Voltar");
		btnReturn.setBackground(Color.BLACK);
		btnReturn.setForeground(Color.WHITE);
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Home home = new Home(user);
				home.setVisible(true);
				setVisible(false);

			}
		});
		btnReturn.setFont(new Font("3ds", Font.BOLD, 14));
		btnReturn.setBounds(10, 425, 89, 23);
		contentPane.add(btnReturn);

		JLabel lblSenha = new JLabel("Entrada");
		lblSenha.setFont(new Font("3ds", Font.BOLD, 15));
		lblSenha.setBounds(10, 235, 59, 14);
		contentPane.add(lblSenha);

		JLabel lblSaida = new JLabel("Saida");
		lblSaida.setFont(new Font("3ds", Font.BOLD, 15));
		lblSaida.setBounds(10, 291, 59, 14);
		contentPane.add(lblSaida);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("3ds", Font.BOLD, 15));
		lblTipo.setBounds(10, 347, 59, 14);
		contentPane.add(lblTipo);

		JLabel lblSenha_1 = new JLabel("Senha");
		lblSenha_1.setFont(new Font("3ds", Font.BOLD, 15));
		lblSenha_1.setBounds(10, 179, 46, 14);
		contentPane.add(lblSenha_1);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCep.setBounds(322, 13, 64, 14);
		contentPane.add(lblCep);

		txtCep = new JTextField();
		txtCep.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (!validouCep) {

					JSONReader reader = new JSONReader();
					Endereco estado = reader.ReaderJSONViaCep(txtCep.getText().trim());

					if (estado != null) {
						txtLogradouro.setText(estado.getLogradouro());
						txtComplemento.setText(estado.getComplemento());
						txtBairro.setText(estado.getBairro());
						
						ComboBoxUfModel comboBoxUfModel = new ComboBoxUfModel();
						comboBoxUfModel.setSelectedItem(estado.getUnidadeFederativa());
						cmbUF.setModel(comboBoxUfModel);
						txtLocalidade.setText(estado.getLocalidade());
						
					}

					validouCep = true;
				}
			}
			@Override
			public void focusGained(FocusEvent arg0) {
				validouCep = false;
			}
		});
		txtCep.setHorizontalAlignment(SwingConstants.CENTER);
		txtCep.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCep.setColumns(10);
		txtCep.setBounds(322, 38, 254, 20);
		contentPane.add(txtCep);

		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLogradouro.setBounds(322, 67, 101, 14);
		contentPane.add(lblLogradouro);

		txtLogradouro = new JTextField();
		txtLogradouro.setHorizontalAlignment(SwingConstants.CENTER);
		txtLogradouro.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(322, 92, 254, 20);
		contentPane.add(txtLogradouro);

		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setFont(new Font("Dialog", Font.BOLD, 15));
		lblComplemento.setBounds(322, 123, 101, 14);
		contentPane.add(lblComplemento);

		txtComplemento = new JTextField();
		txtComplemento.setHorizontalAlignment(SwingConstants.CENTER);
		txtComplemento.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(322, 148, 254, 20);
		contentPane.add(txtComplemento);

		JLabel lblBairro = new JLabel("Bairro");
		lblBairro.setFont(new Font("Dialog", Font.BOLD, 15));
		lblBairro.setBounds(322, 179, 64, 14);
		contentPane.add(lblBairro);

		txtBairro = new JTextField();
		txtBairro.setHorizontalAlignment(SwingConstants.CENTER);
		txtBairro.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtBairro.setColumns(10);
		txtBairro.setBounds(322, 204, 254, 20);
		contentPane.add(txtBairro);

		JLabel lblUf = new JLabel("UF");
		lblUf.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUf.setBounds(322, 291, 64, 14);
		contentPane.add(lblUf);

		JLabel lblCidade = new JLabel("Localidade");
		lblCidade.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCidade.setBounds(322, 235, 101, 14);
		contentPane.add(lblCidade);

		txtLocalidade = new JTextField();
		txtLocalidade.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocalidade.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtLocalidade.setColumns(10);
		txtLocalidade.setBounds(322, 260, 254, 20);
		contentPane.add(txtLocalidade);

		cmbUF = new JComboBox();
		cmbUF.setFont(new Font("Dialog", Font.PLAIN, 14));
		cmbUF.setBackground(Color.WHITE);
		
		ComboBoxUfModel cmbUfmodel = new ComboBoxUfModel();
	
		cmbUF.setBounds(322, 316, 254, 20);
		cmbUF.setModel(cmbUfmodel);
				
		contentPane.add(cmbUF);

		validouCep = true;

	}
}

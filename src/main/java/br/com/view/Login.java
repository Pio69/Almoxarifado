package br.com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.com.controller.LoginController;
import br.com.dao.LoginDao;
import br.com.model.Pessoa;
import br.com.model.Usuario;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField pswSenha;

	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/imagens/weg-logo.png")));

		setTitle("WEG SA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/imagens/chave.png")));
		lblNewLabel_2.setBounds(139, 300, 30, 31);
		contentPane.add(lblNewLabel_2);

		pswSenha = new JPasswordField();
		pswSenha.setHorizontalAlignment(SwingConstants.CENTER);
		pswSenha.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		pswSenha.setBounds(129, 300, 366, 31);
		contentPane.add(pswSenha);

		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("3ds", Font.BOLD, 16));
		lblNewLabel_1.setBounds(129, 203, 85, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("3ds", Font.BOLD, 16));
		lblSenha.setBounds(129, 275, 85, 14);
		contentPane.add(lblSenha);
		rootPane.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(java.awt.event.KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {

					LoginDao loginDao = new LoginDao();
					Pessoa user = loginDao.login(txtEmail, pswSenha);

					if (user != null) {

						LoginController.callFrame(LoginController.getTipo(txtEmail), Login.this, user);

					}

				}

			}
		});

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setIcon(new ImageIcon(Login.class.getResource("/imagens/carta.png")));
		lblNewLabel_3.setBounds(139, 228, 30, 31);
		contentPane.add(lblNewLabel_3);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setBounds(129, 228, 366, 31);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(Login.class.getResource("/imagens/cadeado (1).png")));
		lblNewLabel_4.setBounds(55, 49, 525, 90);
		contentPane.add(lblNewLabel_4);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("3ds", Font.BOLD, 30));
		lblLogin.setBounds(55, 134, 525, 40);
		contentPane.add(lblLogin);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(55, 0, 525, 459);
		contentPane.add(panel);
		panel.setLayout(null);

		// REALIZAR LOGIN
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(75, 354, 89, 23);
		btnEntrar.setFocusPainted(false);
		panel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginDao loginDao = new LoginDao();
				Pessoa pessoa = loginDao.login(txtEmail, pswSenha);

				if (pessoa != null) {

					LoginController.callFrame(LoginController.getTipo(txtEmail), Login.this, pessoa);

				}

			}
		});
		btnEntrar.setForeground(new Color(0, 128, 0));
		btnEntrar.setBackground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 13));
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setBackground(Color.BLACK);
		btnEntrar.setFont(new Font("3ds", Font.PLAIN, 14));

		// ADICIONA A FUNCAO PARA A TECLA ENTER
		getRootPane().setDefaultButton(btnEntrar);

		// LIMPA OS CAMPOS
		JButton btnClear = new JButton("Limpar");
		btnClear.setBackground(Color.BLACK);
		btnClear.setForeground(Color.WHITE);
		btnClear.setFocusPainted(false);
		btnClear.setFont(new Font("3ds", Font.PLAIN, 14));
		btnClear.setBounds(352, 355, 89, 23);
		panel.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				LoginController.clearLogin(txtEmail, pswSenha);

			}
		});
	}

}

package br.com.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import br.com.controller.ProdutoController;
import br.com.dao.GenericDao;
import br.com.dao.GenericDaoAcao;
import br.com.model.Acao;
import br.com.model.Pessoa;
import br.com.model.Produto;
import br.com.model.table.AcaoTable;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Leitor extends javax.swing.JFrame implements Runnable, ThreadFactory {

	private WebcamPanel panel = null;
	private Webcam webcam = null;

	private static final long serialVersionUID = 6441489157408381878L;
	private Executor executor = Executors.newSingleThreadExecutor(this);
	
	Pessoa pessoa;

	public Leitor(Pessoa pessoa) {
		setResizable(false);
		initComponents();
		initWebcam();
		this.pessoa = pessoa;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		result_field = new javax.swing.JTextField();
		jSeparator1 = new javax.swing.JSeparator();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		result_field.setBorder(null);
		jPanel1.add(result_field, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 470, 20));

		jSeparator1.setForeground(new java.awt.Color(126, 167, 206));
		jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 470, 10));

		jLabel1.setForeground(new java.awt.Color(105, 105, 105));
		jLabel1.setText("Resultado");
		jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));

		jPanel2.setBackground(new java.awt.Color(250, 250, 250));
		jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
		jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 300));

		getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 380));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTextField result_field;
	// End of variables declaration//GEN-END:variables

	private void initWebcam() {
		Dimension size = WebcamResolution.QVGA.getSize();
		webcam = Webcam.getWebcams().get(0); // 0 is default webcam
		webcam.setViewSize(size);

		panel = new WebcamPanel(webcam);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);

		jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));

		executor.execute(this);
	}

	@Override
	public void run() {
		Result result = null;

		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			BufferedImage image = null;

			if (webcam.isOpen()) {
				if ((image = webcam.getImage()) == null) {
					continue;
				}
			}

			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

			try {
				result = new MultiFormatReader().decode(bitmap);
			} catch (NotFoundException e) {
				// No result...
			}

			if (result != null) {

				emprestar(result);

			/*	try {
					ProdutoController.beep();
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/

				dispose();
			}
		} while (result == null);

	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "My Thread");
		t.setDaemon(true);
		return t;
	}

	public void emprestar(Result result) {

		GenericDaoAcao genericDaoAcao = new GenericDaoAcao();

		List<Acao> acoes = (List<Acao>) genericDaoAcao.valida(new Acao(), Integer.parseInt(result.getText().toString()));

		if (acoes == null || acoes.isEmpty()) {
			System.out.println("Leitor: " + Integer.parseInt(result.getText().toString()));

			GenericDao genericDao;

			genericDao = new GenericDao();
			
			Acao acao = (Acao) genericDao.search(Integer.parseInt(result.getText().toString()), new Acao());

			Timestamp dataAtual = new Timestamp(System.currentTimeMillis());

			acao.setPessoa(pessoa);
			acao.setProduto((Produto) genericDao.search(Integer.parseInt(result.getText().toString()), new Produto()));
			acao.setEntregue(false);
			acao.setDataRetirada(dataAtual);

			System.out.println(acao);

			genericDao.insert(acao);
		} else {
			System.out.println("Leitor: " + Integer.parseInt(result.getText().toString()));

			GenericDao genericDao;

			genericDao = new GenericDao();
			
			Acao acao = (Acao) genericDao.search(Integer.parseInt(result.getText().toString()), new Acao());

			acao.setEntregue(true);

			System.out.println(acao);

			genericDao.update(acao);
		}
	}

}

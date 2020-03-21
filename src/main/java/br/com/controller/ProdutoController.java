package br.com.controller;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.JOptionPane;

import com.google.zxing.qrcode.encoder.QRCode;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.image.ImageType;

public class ProdutoController {

	public static void beep() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

		// Carrega o arquivo de áudio (não funciona com .mp3, só .wav)
		URL oUrl = new URL("http://www.soundjay.com/button/beep-06.wav");
		Clip oClip = AudioSystem.getClip();
		AudioInputStream oStream = AudioSystem.getAudioInputStream(oUrl);
		oClip.open(oStream);

		oClip.loop(0);
	}

	public static void gerarCode(String texto) {

		int size = 125; // TAMANHO DA IMAGEM
		String nomeArquivo = "F:\\Workspace\\eclipse-workspace\\Almoxarifado\\QR_Ferramentas\\"
				+ "produto_" + texto + ".png"; // CAMINHO ONDE VAI SE BAIXADO E NOME NO
																			// ARQUIVO
 
		try {

			FileOutputStream f = new FileOutputStream(nomeArquivo);
			ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(texto).to(ImageType.PNG).withSize(size, size).stream();

			f.write(out.toByteArray());
			f.close();

			JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!");
		} catch (IOException e) {
			Logger.getLogger(QRCode.class.getName()).log(Level.SEVERE, null, e);
		}

	}

}

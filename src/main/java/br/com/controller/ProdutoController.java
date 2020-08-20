/*
 * SENAI
 * PSIN
 * MI-66
 * Objetivo: Chamar o método com som para consciencia que a leitura ocorreu e gerar o qrCode
 * Autores: Leonardo Pio, Kelvin Schneider, Guilherme Witkosky, Rafael Adriano 
 * Data: 06/08/2020
 * 
 * Alterações:
 * 
 * Nome: Rafael Adriano 
 * Data: 06/08/2020
 * Alterou: Documentação de código		
 * 
 */

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
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.glxn.qrgen.image.ImageType;

public class ProdutoController {

	/* 
	 * Retorno: Void
	 * Objetivo: Reproduzir o arquivo de audio
	 *
	*/
	
	public static void beep() throws LineUnavailableException, UnsupportedAudioFileException, IOException {

		// Carrega o arquivo de áudio (não funciona com .mp3, apenas com .wav)
		URL oUrl = new URL("http://www.soundjay.com/button/beep-06.wav");
		Clip oClip = AudioSystem.getClip();
		AudioInputStream oStream = AudioSystem.getAudioInputStream(oUrl);
		oClip.open(oStream);

		oClip.loop(0);
	}

	/* 
	 * Retorno: Void
	 * Objetivo: Gerar o qrCode para a peça
	 *
	*/
	
	public static void gerarCode(String texto) {

		int size = 125; // Seta o tamanho da imagem
		
		String nomeArquivo = "";
		
		try {
		
			nomeArquivo = "C:/Almoxarifado/QR_Ferramentas/"
					+ "produto_" + texto + ".png"; // Caminho e nome da imagem que será gerada
	 
		}catch(Exception ex){
			
		}
		
			System.out.println(" 2 - " + nomeArquivo);
		
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

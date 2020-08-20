package br.com.util;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.dao.GenericDaoAcao;
import br.com.model.Acao;

public class XlsInsert {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void createTabela() {

		GenericDaoAcao daoAcao = new GenericDaoAcao();

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Hstorico");

		int rownum = 0;
		int cellnum = 0;

		Row row;
		Cell cell;

//		ESTILIZA��O DA PLANILHA
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeight((short) 400);

		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.BROWN.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		Font fonte = workbook.createFont();
		fonte.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fonte.setColor(IndexedColors.WHITE.getIndex());
		headerStyle.setFont(fonte);

//		HEADER DA PLANILHA
		row = sheet.createRow(rownum++);
		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Id");
		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Estado");
		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Data Retirada");
		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Produto");
		cell = row.createCell(cellnum++);
		cell.setCellStyle(headerStyle);
		cell.setCellValue("Pessoa");

//		POLUI O OBJETO ACAO
		List<Acao> acaoList = (List<Acao>) daoAcao.select(new Acao());
		
//		ESTILIZA O TEXTO
		CellStyle textStyle = workbook.createCellStyle();
		textStyle.setAlignment(CellStyle.ALIGN_CENTER);
		textStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

//		ADD ITENS NA TABELA
		for (Acao acao : acaoList) {
			row = sheet.createRow(rownum++);
			cellnum = 0;
			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(acao.getId());
			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(acao.isEntregue() ? "Entregue" : "Indisponível");
			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(acao.getDataRetirada());
			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(acao.getProduto().getNomeProduto());
			cell = row.createCell(cellnum++);
			cell.setCellStyle(textStyle);
			cell.setCellValue(acao.getPessoa().getNome());

		}

//		CRIA O ARQUIVO
		try {

			FileOutputStream out = new FileOutputStream(new File("C:/Almoxarifado/Planilhas/Historico.xlsx"));
			workbook.write(out);
			out.close();
			JOptionPane.showMessageDialog(null, "Aqruivo baixado com sucesso");

		} catch (FileNotFoundException e) {
			System.out.println("Erro aqui!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Erro aqui �");
			e.printStackTrace();
		}

	}
}

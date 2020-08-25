package support.tbi;

import intern.Instances;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {


    public static List<List<String>> lerPlanilhaXlsx(String path, String sheetName) {
        List<List<String>> linhas = new ArrayList<>();
        int linhaCount = 0;
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            for (Row cells : sheet) {
                List<String> celulas = new ArrayList<>();
                linhaCount++;
                Iterator<Cell> cellIterator = cells.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (String.valueOf(cell.getNumericCellValue()).length() > 0) {
                                //if (linhaCount >= linhaI && linhaCount <= linhaF) {
                                celulas.add(String.valueOf(cell.getNumericCellValue()));
                                //}
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getStringCellValue().length() > 0) {
                                //if (linhaCount >= linhaI && linhaCount <= linhaF) {
                                celulas.add(cell.getStringCellValue());
                                //}
                            }
                            break;
                    }
                }
                linhas.add(celulas);
            }
            file.close();
        } catch (Exception e) {
            Instances.getReportClassInstance().stepFail(e);
            e.printStackTrace();
        }
        return linhas;
    }


    public static List<List<String>> lerPlanilhaXlsxComEspacoEmBranco(String path, String sheetName) {
        List<List<String>> linhas = new ArrayList<>();
        int linhaCount = 0;
        try {
            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);
            for (Row cells : sheet) {
                List<String> celulas = new ArrayList<>();
                linhaCount++;
                Iterator<Cell> cellIterator = cells.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if (String.valueOf(cell.getNumericCellValue()).length() > 0) {
                                //if (linhaCount >= linhaI && linhaCount <= linhaF) {
                                celulas.add(String.valueOf(cell.getNumericCellValue()));
                                //}
                            }
                            break;
                        case Cell.CELL_TYPE_STRING:
                            if (cell.getStringCellValue().length() > 0) {
                                //if (linhaCount >= linhaI && linhaCount <= linhaF) {
                                celulas.add(cell.getStringCellValue());
                                //}
                            }
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            celulas.add("");
                            break;
                    }
                }
                linhas.add(celulas);
            }
            file.close();
        } catch (Exception e) {
            Instances.getReportClassInstance().stepFail(e);
            e.printStackTrace();
        }
        return linhas;
    }


    public static List<List<String>> lerPlanilhaXls(String path, String sheetName) {
        List<List<String>> retorno = new ArrayList<>();
        try {
            Workbook workbook = Workbook.getWorkbook(new File(path));
            Sheet sheet = workbook.getSheet(sheetName);
            int contadorDeLinhas = sheet.getRows();
            for (int i = 0; i < contadorDeLinhas; i++) {
                jxl.Cell[] linhas = sheet.getRow(i);
                List<String> dados = new ArrayList<>();
                for (jxl.Cell celula : linhas) {
                    dados.add(celula.getContents());
                }
                retorno.add(dados);
            }
            workbook.close();
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public static void escreverPlanilhaXlsx(String path, String sheetName, int linha, int coluna, String conteudo) {
        XSSFSheet sheet;
        FileInputStream file = null;
        try {
            file = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(file);
            sheet = wb.getSheet(sheetName);
            Row row = sheet.getRow(linha);
            Cell cell;
            cell = row.createCell(coluna);
            XSSFCellStyle style = wb.createCellStyle();
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            cell.setCellStyle(style);
            try {
                cell.setCellValue(conteudo);
            } catch (NumberFormatException e) {
                cell.setCellValue(conteudo);
            }
            FileOutputStream out = new FileOutputStream(path);
            wb.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //public static void escreverPlanilhaXlsx(String path, String sheetName, int linha, int coluna, String conteudo) {
    //    XSSFSheet sheet;
    //    FileInputStream file = null;
    //    try {
    //        file = new FileInputStream(path);
    //        XSSFWorkbook wb = new XSSFWorkbook(file);
    //        sheet = wb.getSheet(sheetName);
    //        //Row row = sheet.createRow(linha);
    //        Row row = sheet.getRow(linha);
    //        Cell cell;
    //        //List<List<String>> linhaAntiga = lerPlanilhaXlsx(path, sheetName);
    //        //List<String> primeiraLista = linhaAntiga.get(0);
    //        //for (String cellContent : primeiraLista) {
    //        //    System.out.println("CELL CONTENT ----------" + cellContent);
    //        //    //cell = row.createCell(primeiraLista.indexOf(cellContent));
    //        //    cell = row.getCell(primeiraLista.indexOf(cellContent));
    //        //    long num = 0;
    //        //    try{
    //        //        num = Long.parseLong(cellContent.replace(".0", ""));
    //        //        cell.setCellValue(num);
    //        //    }catch (NumberFormatException e){
    //        //        cell.setCellValue(cellContent);
    //        //    }
    //        //}
    //        cell = row.createCell(coluna);
    //        try{
    //            cell.setCellValue(conteudo);
    //        }catch (NumberFormatException e){
    //            cell.setCellValue(conteudo);
    //        }
    //        FileOutputStream out = new FileOutputStream(path);
    //        wb.write(out);
    //        out.close();
    //        System.out.println("written successfully on disk.");
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}

}

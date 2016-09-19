package com.abm.pos;

import com.abm.pos.com.abm.pos.bl.ReportManager;
import com.abm.pos.com.abm.pos.bl.SalesManager;
import com.abm.pos.com.abm.pos.dto.ReceiptDto;
import com.abm.pos.com.abm.pos.dto.reports.CommonComparisonTotalDto;
import com.abm.pos.com.abm.pos.util.SQLQueries;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@SpringBootApplication
@EnableAutoConfiguration
public class PointOfSaleApplication {

    private BaseFont bfBold;
    private BaseFont bf;
    private int pageNumber = 0;


    SalesManager salesManager = new SalesManager();


    public static void main(String[] args) throws IOException, DocumentException {

        SpringApplication.run(PointOfSaleApplication.class, args);

        PointOfSaleApplication generateInvoice = new PointOfSaleApplication();

        generateInvoice.createPDF();

    }

    private void createPDF() {

//        Document doc = new Document();
//        PdfWriter docWriter = null;
//        initializeFonts();
//        try {
//
//            java.util.List<ReceiptDto> receiptDtos = new ArrayList<>();
//
//            receiptDtos = salesManager.getReceiptDetails(10);
//
//            docWriter = PdfWriter.getInstance(doc, new FileOutputStream("/Users/asp5045/Documents/PointOfSell/src/main/resources/downloads/AddTableExample2.pdf"));
//            doc.addAuthor("betterThanZero");
//            doc.addCreationDate();
//            doc.addProducer();
//            doc.addCreator("MySampleCode.com");
//            doc.addTitle("Invoice");
//            doc.setPageSize(PageSize.LETTER);
//
//            doc.open();
//            PdfContentByte cb = docWriter.getDirectContent();
//
//            boolean beginPage = true;
//            int y = 0;
//
//            for(int i=0; i < receiptDtos.size(); i++ ){
//                if(beginPage){
//                    beginPage = false;
//                    generateLayout(doc, cb);
//                    generateHeader(doc, cb);
//                    y = 615;
//                }
//                generateDetail(doc, cb, i, y);
//                y = y - 15;
//                if(y < 50){
//                    printPageNumber(cb);
//                    doc.newPage();
//                    beginPage = true;
//                }
//            }
//            printPageNumber(cb);
//
//        }
//        catch (DocumentException dex)
//        {
//            dex.printStackTrace();
//        }
//        catch (Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        finally
//        {
//            if (doc != null)
//            {
//                doc.close();
//            }
//            if (docWriter != null)
//            {
//                docWriter.close();
//            }
//        }
//    }
//
//    private void generateLayout(Document doc, PdfContentByte cb)  {
//
//        try {
//
//            cb.setLineWidth(1f);
//
//            // Invoice Header box layout
//            cb.rectangle(150,400,250,680);
//
//            cb.stroke();
//
//            // Invoice Header box Text Headings
//
//
//            // Invoice Detail box layout
//            //cb.rectangle(20,50,550,600);
////            cb.moveTo(20,630);
////            cb.lineTo(570,630);
////            cb.moveTo(50,50);
////            cb.lineTo(50,650);
////            cb.moveTo(150,50);
////            cb.lineTo(150,650);
////            cb.moveTo(430,50);
////            cb.lineTo(430,650);
////            cb.moveTo(500,50);
////            cb.lineTo(500,650);
//            cb.stroke();
//
//            // Invoice Detail box Text Headings
////            createHeadings(cb,22,633,"Qty");
////            createHeadings(cb,52,633,"Item Number");
////            createHeadings(cb,152,633,"Item Description");
////            createHeadings(cb,432,633,"Price");
////            createHeadings(cb,502,633,"Ext Price");
//
//
//        }
//
//
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }
//
//    private void generateHeader(Document doc, PdfContentByte cb)  {
//
//        try {
//
////            createHeadings(cb,200,750,"Company Name");
////            createHeadings(cb,200,735,"Address Line 1");
////            createHeadings(cb,200,720,"Address Line 2");
////            createHeadings(cb,200,705,"City, State - ZipCode");
////            createHeadings(cb,200,690,"Country");
////
////            createHeadings(cb,482,743,"ABC0001");
////            createHeadings(cb,482,723,"123456");
////            createHeadings(cb,482,703,"09/26/2012");
//
//        }
//
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }
//
//    private void generateDetail(Document doc, PdfContentByte cb, int index, int y)  {
//        DecimalFormat df = new DecimalFormat("0.00");
//
//        try {
//
////            createContent(cb,48,y,String.valueOf(index+1),PdfContentByte.ALIGN_RIGHT);
////            createContent(cb,52,y, "ITEM" + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
////            createContent(cb,152,y, "Product Description - SIZE " + String.valueOf(index+1),PdfContentByte.ALIGN_LEFT);
////
////            double price = Double.valueOf(df.format(Math.random() * 10));
////            double extPrice = price * (index+1) ;
////            createContent(cb,498,y, df.format(price),PdfContentByte.ALIGN_RIGHT);
////            createContent(cb,568,y, df.format(extPrice),PdfContentByte.ALIGN_RIGHT);
//
//        }
//
//        catch (Exception ex){
//            ex.printStackTrace();
//        }
//
//    }
//
//    private void createHeadings(PdfContentByte cb, float x, float y, String text){
//
//
//        cb.beginText();
//        cb.setFontAndSize(bfBold, 8);
//        cb.setTextMatrix(x,y);
//        cb.showText(text.trim());
//        cb.endText();
//
//    }
//
//    private void printPageNumber(PdfContentByte cb){
//
//
//        cb.beginText();
//        cb.setFontAndSize(bfBold, 8);
//        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "Page No. " + (pageNumber+1), 570 , 25, 0);
//        cb.endText();
//
//        pageNumber++;
//
//    }
//
//    private void createContent(PdfContentByte cb, float x, float y, String text, int align){
//
//
//        cb.beginText();
//        cb.setFontAndSize(bf, 8);
//        cb.showTextAligned(align, text.trim(), x , y, 0);
//        cb.endText();
//
//    }
//
//    private void initializeFonts(){
//
//
//        try {
//            bfBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//            bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
//
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    }}









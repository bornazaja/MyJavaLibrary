package com.bzaja.myjavalibrary.pdf;

import com.bzaja.myjavalibrary.util.LanguageFormat;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.bzaja.myjavalibrary.util.LocalDateUtils;
import com.bzaja.myjavalibrary.util.PropertyInfoDto;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenericBeanReportPdf {

    public static <T> void create(String title, List<T> list, List<PropertyInfoDto> propertiesInfo, String path, LanguageFormat languageFormat) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();
            addContent(document, title, list, propertiesInfo, languageFormat);
            document.close();
        } catch (FileNotFoundException | DocumentException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    private static <T> void addContent(Document document, String title, List<T> list, List<PropertyInfoDto> propertiesInfo, LanguageFormat languageFormat) throws DocumentException {
        String newTitle = String.format("%s (%s)", title, LocalDateUtils.format(LocalDate.now(), LocalDateUtils.getLocalDatePattern(languageFormat)));
        Paragraph titleParagraph = PdfUtils.createParagraph(newTitle, PdfFonts.LARGE_BOLD, Element.ALIGN_CENTER);

        PdfCellConfiguration headerPdfCellConfiguration = new PdfCellConfiguration(PdfFonts.SMALL_BOLD, BaseColor.WHITE, PdfCellBorderStyle.BOX, BaseColor.BLACK, Element.ALIGN_CENTER);
        PdfCellConfiguration dataPdfCellConfiguration = new PdfCellConfiguration(PdfFonts.SMALL_NORMAL, BaseColor.WHITE, PdfCellBorderStyle.BOX, BaseColor.BLACK, Element.ALIGN_CENTER);

        PdfTableListBeanConfiguration pdfTableListBeanConfiguration = new PdfTableListBeanConfiguration(Element.ALIGN_CENTER, 100f, headerPdfCellConfiguration, dataPdfCellConfiguration, null);

        PdfPTable pdfPTable = PdfTableMaker.make(pdfTableListBeanConfiguration, list, propertiesInfo, new ArrayList<>(), languageFormat);

        document.add(titleParagraph);
        document.add(PdfUtils.createNewLineByParagraph());
        document.add(pdfPTable);
    }
}

package ru.kotovsvyatoslav.algorithms.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ReadDocxFile {

    @Autowired
    RestTemplate restTemplate;

    public void read() {
        try {
            FileInputStream fis = new FileInputStream("file.docx");
            XWPFDocument docxFile = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphs = docxFile.getParagraphs();
            List<XWPFTable> tables = docxFile.getTables();
            XWPFTable table =   tables.get(0);
            String st = table.getRow(0).getCell(0).getText();
            System.out.println("from table -> " + st);

            Map<String, String> header = new HashMap<>();
            header.put("Authorization","OAuth 00d33b90f3804de193df30f27f499cd9");

            ResponseEntity response = restTemplate.getForEntity("https://cloud-api.yandex.net/v1/disk/resources/download?path=ya-disk-public%3A%2F%2FrFv21zFxOVGbtuC8eUeJXoazUZdxUUTcF01nJiMZE326f81O1DnAUBeLoMT4L6NXq%2FJ6bpmRyOJonT3VoXnDag%3D%3D&",
                    String.class, header);
            System.out.println(response);
            System.out.println(docxFile.getBodyElements());
            String s = "";
            for (XWPFParagraph p : paragraphs) {
                System.out.println(p.getText());
                s = s + p;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

}

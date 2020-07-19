package com.springboottutorial.spring_boot_tutorial.util;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.impl.upcean.EAN8Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.*;

public class BarCodeGen {
    public static void main(String[] args) throws IOException {
        /*Code39Bean bean = new Code39Bean();
        final int dpi = 150;
//Configure the barcode generator
        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)); //makes the narrow bar
        //width exactly one pixel
        bean.setWideFactor(3);
        bean.doQuietZone(false);
//Open output file
        File outputFile = new File("D:\\barcode image\\out.png");
        OutputStream out = new FileOutputStream(outputFile);
        try {
            //Set up the canvas provider for monochrome PNG output
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            //Generate the barcode
            bean.generateBarcode(canvas, "Zahangir");
            //Signal end of generation
            canvas.finish();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }*/

        createBarCode128("0129287342");
        //return outputFile;
    }
    public static void createBarCode128(String fileName) {
        try {
            Code128Bean bean = new Code128Bean();
            final int dpi = 160;
            bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi));

            bean.doQuietZone(false);
            File outputFile = new File("D:\\barcode image\\out.png");
            if(!outputFile.exists()){
                outputFile.mkdir();
            }
            FileOutputStream out = new FileOutputStream(outputFile);

            BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                    out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            bean.generateBarcode(canvas, fileName);
            canvas.finish();

            System.out.println("Bar Code is generated successfully…");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
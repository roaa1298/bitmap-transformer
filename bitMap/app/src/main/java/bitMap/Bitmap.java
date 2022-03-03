package bitMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.file.Files;

public class Bitmap{
    private String inputFile;
    private String outputFile;
    private String transform;

    public Bitmap(String inputFile, String outputFile, String transform) {

        this.inputFile = "./src/main/resources/"+ inputFile + ".bmp";
        this.outputFile = "./src/main/resources/"+ outputFile + ".bmp";
        try {
            File in = new File(inputFile);
            BufferedImage img = ImageIO.read(in);

        if (transform.equals("BlackWhite")){
            BlackAndWhite(img);
        }else if (transform.equals("InvertImage")){
            InvertImage(img);
        } else if (transform.equals("DarkenImage")){
            DarkenImage(img);
        }
        else {
            System.out.println("The method you requested does not exist. Please use either BlackWhite, InvertImage, or  DarkenImage.");
        }

        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public BufferedImage InvertImage(BufferedImage img){

            BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

            Graphics2D graphic = res.createGraphics();
            graphic.drawImage(img, 0, img.getHeight(), img.getWidth(), 0, 0, 0, img.getWidth(), img.getHeight(), null);
            graphic.dispose();

        try {
            File out = new File(outputFile);
            ImageIO.write(res, "bmp", out);
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;

    }

    public  BufferedImage BlackAndWhite(BufferedImage img) {

            BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_BYTE_BINARY);

            Graphics2D graphic = res.createGraphics();
            graphic.drawImage(img, 0, 0, Color.WHITE, null);
            graphic.dispose();

            try {
                File out = new File(outputFile);
                ImageIO.write(res, "bmp", out);
            }catch (IOException e){
                e.printStackTrace();
            }


        return res;
    }

    public BufferedImage DarkenImage(BufferedImage img) {
        BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i=0; i < img.getWidth(); i++){
            for( int j=0; j < img.getHeight(); j++){
                Color c = new Color(img.getRGB(i, j));
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                int a = c.getAlpha();
                int gr = (r + g + b) / 3;

                Color grayColor = new Color (gr, gr, gr, a);
                res.setRGB(i, j, grayColor.getRGB());
            }
        }
        try {
            File out = new File(outputFile);
            ImageIO.write(res, "bmp", out);
        }catch (IOException e){
            e.printStackTrace();
        }
        return res;

    }


}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barcode.reader;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.datamatrix.DataMatrixWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Joshua
 */
public class BarcodeReader {

    /**
     * @param args the command line arguments
     */
    static Scanner stdin = new Scanner(System.in);
    public static String menu = "Select an option: \n"
            + "C: Create a new code\n"
            + "N: Create new read\n"
            + "O: Look at a previous read\n"
            + "X: Exit program\n";
    public static String codeMenu = "Select a code to create: \n"
            + "D: DataMatrix\n"
            + "Q: QR Code\n";
    public static ArrayList reads = new ArrayList();
    static Map hintMap = new HashMap();

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        start(startTime);
    }

    private static void printMenu() {
        System.out.println(menu);
    }

    private static String getSelection() {
        String input = stdin.nextLine();
        return input;
    }

    private static void start(long startTime) {
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        boolean cont = true;
        while (cont) {
            try {
                selectionLoop(startTime);
                System.out.println("Continue? Y/N");
                String input = getSelection();
                if (input != null && (input.equals("N") || input.equals("n"))) {
                    cont = false;
                }
            } catch (IOException | NotFoundException ex) {
                System.out.println("Something went wrong.");
                Logger.getLogger(BarcodeReader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriterException ex) {
                Logger.getLogger(BarcodeReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        exit(startTime);
    }

    private static String selectionLoop(long startTime) throws IOException, NotFoundException, UnsupportedEncodingException, WriterException {
        boolean notSelectedYet = true;
        String choice = "";
        while (notSelectedYet) {
            printMenu();
            String selection = getSelection();
            switch (selection) {
                case "C":
                    createNewCode();
                    notSelectedYet = false;
                    continue;
                case "N":
                    createNewRead();
                    notSelectedYet = false;
                    continue;
                case "O":
                    openPreviousReads();
                    notSelectedYet = false;
                    continue;
                case "X":
                    exit(startTime);
                    notSelectedYet = false;
                    continue;
                default:
                    System.out.println("That is not a valid input.");
            }
        }
        return choice;
    }

    private static void exit(long startTime) {
        stdin.close();
        NumberFormat formatter = new DecimalFormat("#0.00000");
        long endTime = System.currentTimeMillis();
        System.out.println("Program finished in " + formatter.format((endTime
                - startTime) / 1000d) + " seconds.");
        System.exit(0);
    }

    private static void exitWithoutTime() {
        stdin.close();
        System.exit(0);
    }

    private static String createNewRead() throws IOException, NotFoundException {
        String filePath = "Data/Image1.bmp";
        Map hintMap = new HashMap();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new 
        BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(filePath)))));
        Result codeResult = new MultiFormatReader().decode(binaryBitmap, hintMap);
        System.out.println("Code read!");
        reads.add(codeResult.getText());
        return codeResult.getText();
    }

    private static void openPreviousReads() {
        System.out.println(reads);
    }

    private static void createNewQRCode(String qrCodeData, String filePath, int 
            width, int height) 
            throws UnsupportedEncodingException, WriterException, IOException {
        String charset = "UTF-8";
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, width, height, hintMap);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
    }

    private static void createNewCode() throws WriterException, IOException {
        printCodeMenu();
        String selection = getSelection();
        String data;
        String filePath;
        switch(selection){
            case "D":
                System.out.println("What would you like to encode?");
                data = getSelection();
                System.out.println("What would you like to name it?");
                filePath = getSelection();
                filePath = ensureBitmap(filePath);
                createNewDataMatrix(data, filePath, 200, 200);
                break;
            case "Q":
                System.out.println("What would you like to encode?");
                data = getSelection();
                System.out.println("What would you like to name it?");
                filePath = getSelection();
                filePath = ensureBitmap(filePath);
                createNewQRCode(data, filePath, 200, 200);
                break;
            default: 
                System.out.println("Invalid option.\n");
        }
    }

    private static void printCodeMenu() {
        System.out.println(codeMenu);
    }

    private static void createNewDataMatrix(String data, String filePath, 
            int width, int height) throws WriterException, UnsupportedEncodingException, IOException {
        String charset = "UTF-8";
        BitMatrix matrix = new DataMatrixWriter().encode(
                new String(data.getBytes(charset), charset),
                BarcodeFormat.DATA_MATRIX, width, height);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
    }

    private static String ensureBitmap(String filePath) {
        if(!filePath.endsWith(".bmp")){
            filePath += ".bmp";
        }
        return filePath;
    }

}

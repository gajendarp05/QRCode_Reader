import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.qrcode.QRCodeMultiReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeMain {
    private static void decodeQRCode(File qrCodeimage) throws IOException {
        // BufferImage handle and manipulate the image data
        BufferedImage bufferedImage = ImageIO.read(qrCodeimage);
        LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
        // HybridBinarizer: It is designed for high frequency images of barcodes with black data on white backgrounds
        // Convert luminance data to 1 bit data.
        // BinaryBitmap: to represent 1 bit data.
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

        try {
            Result[] result = new QRCodeMultiReader().decodeMultiple(bitmap);
            for (Result i: result) {
                // It will print decoded text for all QR Code in an image.
                System.out.println(i.getText());
            }
        } catch (NotFoundException e) {
            System.out.println("There is no QR code in the image");
        }
    }

    public static void main(String[] args) {
        // Reading from file and giving File file instance
        try {
            File file = new File("multi_qrcode_1.png");
            decodeQRCode(file);
        } catch (IOException e) {
            System.out.println("Could not decode QR Code, IOException :: " + e.getMessage());
        }
    }
}

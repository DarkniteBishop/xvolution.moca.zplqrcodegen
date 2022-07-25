package net.xvolution;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.EncodeHintType;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Diego Marquez
 */
public class QRCodeGenerator {
    
    public BufferedImage createQR(String value, int width, int height) throws WriterException {
        BitMatrix matrix;
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 0);
        
        Writer writer = new QRCodeWriter();
        matrix = writer.encode(value, BarcodeFormat.QR_CODE, width, height, hints);
        matrix.rotate90();
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
                image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
            }
        }
        
        return image;        
    }    
}

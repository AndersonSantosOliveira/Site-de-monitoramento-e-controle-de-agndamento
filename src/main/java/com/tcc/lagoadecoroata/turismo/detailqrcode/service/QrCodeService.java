package com.tcc.lagoadecoroata.turismo.detailqrcode.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tcc.lagoadecoroata.turismo.detailqrcode.dto.QrCodeResponseDto;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QrCodeService {

    public QrCodeResponseDto gerarQrCode(String codigoReserva) {
        try {


            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(codigoReserva, BarcodeFormat.QR_CODE, 250, 250);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            ImageIO.write(bufferedImage, "PNG", outputStream);

            byte[] qrCode = outputStream.toByteArray();
            return new QrCodeResponseDto(codigoReserva, qrCode);
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Erro ao gerar QR Code.", e);
        }
    }
}

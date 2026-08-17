package com.tcc.lagoadecoroata.turismo.detailemail.service;

import com.tcc.lagoadecoroata.turismo.detailemail.dtos.ConfirmacaoAgendamentoEmailDto;
import com.tcc.lagoadecoroata.turismo.detailemail.dtos.EmailRequestDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarConfirmacaoAgendamento(String destinatario, ConfirmacaoAgendamentoEmailDto dto) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(
                message,
                true
        );

        helper.setFrom("lagoacoroata@gmail.com");
        helper.setTo(destinatario);
        helper.setSubject("Confirmação de agendamento - Lagoa de Coroatá");

        String conteudo = """
            <html>
                <body>
                    <h2>Agendamento confirmado!</h2>

                    <p>Olá, <strong>%s</strong>!</p>

                    <p>
                        Seu agendamento foi confirmado com sucesso.
                    </p>

                    <h3>Detalhes do agendamento</h3>

                    <p>
                        <strong>Código da reserva:</strong> %s<br>
                        <strong>Data:</strong> %s<br>
                        <strong>Quantidade de pessoas:</strong> %d
                    </p>

                    <h3>QR Code da reserva</h3>

                    <p>
                        Apresente este QR Code no momento do check-in:
                    </p>

                    <img src="cid:qrcode"
                         alt="QR Code da reserva"
                         width="300"
                         height="300">              
                    <p>
                        Obrigado por realizar seu agendamento conosco!
                    </p>
                </body>
            </html>
            """.formatted(
                dto.nomeVisitante(),
                dto.codigoReserva(),
                dto.data(),
                dto.quantidadePessoas()
        );

        // true = interpretar o conteúdo como HTML
        helper.setText(conteudo, true);

        // Adiciona o QR Code como imagem dentro do e-mail
        helper.addInline(
                "qrcode",
                new ByteArrayResource(dto.qrCode()),
                "image/png"
        );

        javaMailSender.send(message);
    }
}

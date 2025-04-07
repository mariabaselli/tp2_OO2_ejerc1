package ar.unrn.persistencia;

import ar.unrn.concurso.NotificarRegistro;
import io.github.cdimascio.dotenv.Dotenv;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class CorreoElectronico implements NotificarRegistro {
    private String from;

    public CorreoElectronico(String from) {
        this.from = from;
    }

    // configure SMTP details

    @Override
    public void enviarMensaje(String destinatario, String asunto, String mensaje) {
        // Create the email
        Email email = EmailBuilder.startingBlank()
                .from("Remitente Prueba", from)
                .to("Destinatario Prueba", destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        Dotenv dotenv = Dotenv.load();
        // Obtener el usuario y la contraseña desde las variables de entorno
        String username = dotenv.get("MAILTRAP_USER");
        String password = dotenv.get("MAILTRAP_PASSWORD");

        // Create the mailer object with SMTP server settings
        Mailer mailer = MailerBuilder
                .withSMTPServer("sandbox.smtp.mailtrap.io", 587, username, password)
                .withTransportStrategy(TransportStrategy.SMTP)
                .buildMailer();

        // Send the email
        mailer.sendMail(email);

    }
}



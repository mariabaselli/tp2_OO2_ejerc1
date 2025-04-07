package ar.unrn.concurso;

public interface NotificarRegistro {
    void enviarMensaje(String destinatario, String asunto, String mensaje);
}

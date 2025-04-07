package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    private Participante jose;
    private Concurso unConcurso;
    private LocalDate fechaInscripcionParticipante;
    private LocalDate fechaInicioConcurso;
    private LocalDate fechaFinConcurso;
    private String destinatario;
    private String asunto;
    private String mensaje;
    private FakeCorreoElectronico fakeCorreoElectronico;
    private FakeRegistroInscriptos fakeRegistroInscriptos;


    @BeforeEach
    public void inicializarVariables() {
        this.jose = Participante.nuevoParticipante("joseperez01", "Jose Perez");
        this.fechaInicioConcurso = LocalDate.now();
        this.fechaFinConcurso = LocalDate.now().plusDays(60);
        this.fechaInscripcionParticipante = LocalDate.now().plusDays(5);
        this.fakeCorreoElectronico = new FakeCorreoElectronico();
        this.fakeRegistroInscriptos = new FakeRegistroInscriptos("");

        this.unConcurso = Concurso.nuevoConcurso("01a", "Un Concurso", fechaInicioConcurso, fechaFinConcurso,
                fakeRegistroInscriptos, fakeCorreoElectronico);

        this.destinatario = "destinatarioprueba@prueba.com";
        this.asunto = "Confirmación inscripción";
        this.mensaje = "Hemos registrado su inscripción al concurso";

    }

    @Test
    public void verificarDatosEmail() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante, destinatario, asunto, mensaje);
        assertEquals(destinatario + asunto + mensaje, fakeCorreoElectronico.obtenerDatosEmail());
    }

    @Test
    public void verificarRegistroInscriptosEnArchivo() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante, destinatario, asunto, mensaje);
        String esperado = fechaInscripcionParticipante.toString() + ", " + jose.obtenerId() + ", " + unConcurso.obtenerID();
        assertEquals(esperado, fakeRegistroInscriptos.obtenerDatosInscripcion());
    }

    @Test
    public void verificarRegistroInscriptosEnBaseDeDatos() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante, destinatario, asunto, mensaje);
        String esperado = fechaInscripcionParticipante.toString() + ", " + jose.obtenerId() + ", " + unConcurso.obtenerID();
        assertEquals(esperado, fakeRegistroInscriptos.obtenerDatosInscripcion());
    }

    @Test
    public void inscribirAlConcurso() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante, destinatario, asunto, mensaje);
        assertEquals(1, unConcurso.cantidadInscriptos());
    }

    @Test
    public void VerificarInscripcionPrimerDia() {
        assertTrue(unConcurso.inscribirPrimerDia(fechaInicioConcurso));
    }

    @Test
    public void inscribirConFechaFueraDeRango() {
        LocalDate otraFechaInscripcion = fechaFinConcurso.plusDays(1);
        Exception exception = assertThrows(RuntimeException.class, () -> unConcurso.inscribirAConFecha(jose,
                otraFechaInscripcion, destinatario, asunto, mensaje));
        assertEquals(Concurso.ERROR_FECHA_INSCRIPCION, exception.getMessage());
    }

    @Test
    public void verificarParticipanteNoInscripto() {
        assertFalse(unConcurso.estaInscripto(jose));
    }

    @Test
    public void verificarSumaPuntosObtenidos() {
        unConcurso.inscribirAConFecha(jose, fechaInicioConcurso, destinatario, asunto, mensaje);
        assertEquals(10, jose.obtenerPuntos());
    }

    @Test
    public void inscribirYaInscripto() {
        unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante, destinatario, asunto, mensaje);
        Exception exception = assertThrows(RuntimeException.class, () ->
                unConcurso.inscribirAConFecha(jose, fechaInscripcionParticipante, destinatario, asunto, mensaje));
        assertEquals(1, unConcurso.cantidadInscriptos());
        assertEquals(Concurso.ERROR_PARTICIPANTE_YA_INSCRIPTO, exception.getMessage());
    }

    @Test
    public void verificarDNIParticipante() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Participante.nuevoParticipante("", "Ramon"));
        assertEquals(Participante.ERROR_ID_PARTICIPANTE, exception.getMessage());
    }

    @Test
    public void verificarNombreParticipante() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> Participante.nuevoParticipante("35896451", ""));
        assertEquals(Participante.ERROR_NOMBRE_PARTICIPANTE, exception.getMessage());
    }

    @Test

    public void inscribirOtroParticipante() {
        Participante ramon = Participante.nuevoParticipante("ramongonzalez", "Ramon Gonzalez");
        unConcurso.inscribirAConFecha(ramon, LocalDate.now(), destinatario, asunto, mensaje);
        assertEquals(1, unConcurso.cantidadInscriptos());
    }
}
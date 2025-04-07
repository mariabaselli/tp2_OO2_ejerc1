package ar.unrn.concurso;

import ar.unrn.persistencia.ArchivoDeInscriptos;
import ar.unrn.persistencia.CorreoElectronico;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // ArchivoDeInscriptos registro = new ConcursoDAOJDBC();
        ArchivoDeInscriptos archivo = new ArchivoDeInscriptos("C:\\Users\\Maria\\Documents\\universidad\\2025\\Objetos 2\\Tp's\\Tp2 registroInscriptos.txt");
        NotificarRegistro servicio = new CorreoElectronico("prueba@prueba.com");
        Concurso unConcurso = Concurso.nuevoConcurso("01a", "Un Concurso",
                LocalDate.now(), LocalDate.now().plusDays(60),
                archivo, servicio);
        Participante jose = Participante.nuevoParticipante("joseperez01", "Jose Perez");
        unConcurso.inscribirAConFecha(jose, LocalDate.now().plusDays(5), "prueba@prueba.com",
                "Inscripción Concurso", "Hola, te has inscripto al concurso");

    }
}

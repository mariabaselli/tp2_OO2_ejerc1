package ar.unrn.concurso;

import java.time.LocalDate;

public interface RegistroDeInscriptos {

    public void registrarInscripto(LocalDate fechaInscripcion, String idConcursante, String idConcurso);
}



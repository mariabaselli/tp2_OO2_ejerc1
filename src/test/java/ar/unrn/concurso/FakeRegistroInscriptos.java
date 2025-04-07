package ar.unrn.concurso;

import java.time.LocalDate;

public class FakeRegistroInscriptos implements RegistroDeInscriptos {
    String registro;


    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, String idConcursante, String idConcurso) {
        this.registro = fechaInscripcion.toString() + ", " + idConcursante + ", " + idConcurso;

    }

    public Boolean startWith(String start) {
        return registro.startsWith(start);
    }


}

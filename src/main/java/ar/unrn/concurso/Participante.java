package ar.unrn.concurso;

import java.util.Objects;

public class Participante {

    static String ERROR_ID_PARTICIPANTE = "El ID del participante no es correcto";
    static String ERROR_NOMBRE_PARTICIPANTE = "El nombre del participante no es correcto";
    //------------------------------- ATRIBUTOS -------------------------------
    private int puntosObtenidos;
    private String idParticipante;
    private String nombre;


    //----------------------------- CONSTRUCTORES -----------------------------
    private Participante(String idParticipante, String nombre) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
    }

    //-------------------------------- MÉTODOS --------------------------------
    static Participante nuevoParticipante(String id, String nombre) {
        assertValidarID(id);
        assertNombreEsValido(nombre);
        return new Participante(id, nombre);
    }

    private static void assertValidarID(String ID) {
        if (ID.isBlank() || ID == null) {
            throw new IllegalArgumentException(ERROR_ID_PARTICIPANTE);
        }
    }

    private static void assertNombreEsValido(String nombre) {
        if (nombre.isBlank() || nombre == null) {
            throw new IllegalArgumentException(ERROR_NOMBRE_PARTICIPANTE);
        }
    }

    void sumarPuntos(int puntos) {
        this.puntosObtenidos += puntos;
    }

    int obtenerPuntos() {
        return this.puntosObtenidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return idParticipante == that.idParticipante;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idParticipante);
    }

    public String obtenerId() {
        return this.idParticipante;
    }
}

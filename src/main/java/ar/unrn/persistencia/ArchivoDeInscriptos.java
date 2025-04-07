package ar.unrn.persistencia;

import ar.unrn.concurso.RegistroDeInscriptos;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class ArchivoDeInscriptos implements RegistroDeInscriptos {
    private String filePath;

    public ArchivoDeInscriptos(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, String idConcursante, String idConcurso) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String linea = fechaInscripcion.format(formatter) + "," + idConcursante + "," + idConcurso;
        final Path path = Paths.get(this.filePath);
        try {
            Files.write(path, Arrays.asList(linea), StandardCharsets.UTF_16BE,
                    Files.exists(path) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




package ar.unrn.persistencia;

import ar.unrn.concurso.RegistroDeInscriptos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ConcursoDAOJDBC implements RegistroDeInscriptos {
    private static final String queryRegistrarInscriptos = "INSERT INTO registro_inscriptos(fecha_inscripcion, id_participante, id_concurso) VALUES (?, ?, ?)";

    @Override
    public void registrarInscripto(LocalDate fechaInscripcion, String idConcursante, String idConcurso) {
        try {
            Connection conn = ConnectionManager
                    .getConnection();

            PreparedStatement statement = conn
                    .prepareStatement(queryRegistrarInscriptos);

            statement.setDate(1, Date.valueOf(fechaInscripcion));
            statement.setString(2, idConcursante);
            statement.setString(3, idConcurso);

            int cantidad = statement.executeUpdate();
            if (cantidad < 0) {
                throw new RuntimeException("Error al crear el registro");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

}


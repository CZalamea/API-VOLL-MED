package med.voll.api.domain.pacientes;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(@NotNull Long id, String nombre, String documento, String telefono, DatosDireccion direccion)  {
}

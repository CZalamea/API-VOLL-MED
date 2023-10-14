package med.voll.api.domain.pacientes;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.direccion.Direccion;


@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
@Entity(name = "Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private Boolean activo;

    @Embedded
    private Direccion direccion;

    public Paciente (DatosRegistroPaciente datos){
        this.activo = true;
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.documento = datos.documento();
        this.telefono = datos.telefono();
        this.direccion = new Direccion(datos.direccion());
    }

    public void actualizarDatosPacientes(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre()!=null){
            this.nombre = datosActualizarPaciente.nombre();
        } if (datosActualizarPaciente.documento()!=null){
            this.documento = datosActualizarPaciente.documento();
        }if (datosActualizarPaciente.telefono()!=null){
            this.telefono = datosActualizarPaciente.telefono();
        }if (datosActualizarPaciente.direccion()!=null){
            this.direccion = direccion.actualizarDatos(datosActualizarPaciente.direccion());
        }
    }

    public void desactivarPaciente() {

        this.activo = false;
    }

    public void activarPaciente(){
        this.activo = true;
    }
}

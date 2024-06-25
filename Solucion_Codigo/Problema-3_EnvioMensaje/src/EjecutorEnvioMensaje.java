
import java.util.ArrayList;
import java.util.List;

public class EjecutorEnvioMensaje {

    public static void main(String[] args) {
        Contacto remitente = new Contacto("999999999", "Juan Perez");
        List<Contacto> destinatarios = new ArrayList<>();
        destinatarios.add(new Contacto("987654321", "Maria Lopez"));
        destinatarios.add(new Contacto("555666777", "Carlos Díaz"));
        Mensaje sms = new SMS(remitente, destinatarios, "Hola, ¿cómo están?");
        Mensaje mms = new MMS(remitente, destinatarios, "imagen.jpg");

        sms.enviarMensaje();
        sms.visualizarMensaje();

        mms.enviarMensaje();
        mms.visualizarMensaje();
    }

}

class Contacto {
    public String numero;
    public String nombre;

    public Contacto(String numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Contacto{" + "numero='" + numero + '\'' + ", nombre='" + nombre + '\'' + '}';
    }
}

class Mensaje {
    public Contacto remitente;
    public List<Contacto> destinatarios;

    public Mensaje(Contacto remitente, List<Contacto> destinatarios) {
        this.remitente = remitente;
        this.destinatarios = destinatarios;
    }

    public void enviarMensaje() {}

    public void visualizarMensaje() {}
}

class SMS extends Mensaje {
    public String texto;

    public SMS(Contacto remitente, List<Contacto> destinatarios, String texto) {
        super(remitente, destinatarios);
        this.texto = texto;
    }

    @Override
    public void enviarMensaje() {
        for (Contacto destinatario : destinatarios) {
            System.out.println("Enviando SMS de " + remitente.numero + " a " + destinatario.numero + ": " + texto);
        }
    }

    @Override
    public void visualizarMensaje() {
        for (Contacto destinatario : destinatarios) {
            System.out.println("SMS de " + remitente + " a " + destinatario + ": " + texto);
        }
    }
}

class MMS extends Mensaje {
    public String nombreFichero;

    public MMS(Contacto remitente, List<Contacto> destinatarios, String nombreFichero) {
        super(remitente, destinatarios);
        this.nombreFichero = nombreFichero;
    }

    @Override
    public void enviarMensaje() {
        for (Contacto destinatario : destinatarios) {
            System.out.println("Enviando MMS de " + remitente.numero + " a " + destinatario.numero + " con fichero: " + nombreFichero);
        }
    }

    @Override
    public void visualizarMensaje() {
        for (Contacto destinatario : destinatarios) {
            System.out.println("MMS de " + remitente + " a " + destinatario + " con fichero: " + nombreFichero);
        }
    }
}

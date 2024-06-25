
import java.util.ArrayList;
import java.util.List;

public class ManejoTeatro {

    public static void main(String[] args) {
        Teatro teatro = new Teatro();
        teatro.venderEntrada("Principal", "Juan Perez", "Normal");
        teatro.venderEntrada("Central", "Maria Lopez", "Reducida");
        teatro.venderEntrada("PalcoB", "Ana Garcia", "Abonado");
        teatro.consultarEntrada(2);
        teatro.consultarEntrada(4);
    }
}

class Teatro {

    public List<Zona> zonas = new ArrayList<>();

    public Teatro() {
        zonas.add(new Zona("Principal", 200, 25.0, 17.5));
        zonas.add(new Zona("PalcoB", 40, 70.0, 40.0));
        zonas.add(new Zona("Central", 400, 20.0, 14.0));
        zonas.add(new Zona("Lateral", 100, 15.5, 10.0));
    }

    public void venderEntrada(String zona, String comprador, String tipo) {
        Zona z = buscarZona(zona);
        if (z == null) {
            System.out.println("Zona no encontrada.");
            return;
        }
        if (!z.disponibilidad()) {
            System.out.println("No hay localidades disponibles en esta zona.");
            return;
        }

        Entrada e;
        switch (tipo) {
            case "Normal":
                e = new EntradaNormal(z, comprador);
                break;
            case "Reducida":
                e = new EntradaReducida(z, comprador);
                break;
            case "Abonado":
                e = new EntradaAbonado(z, comprador);
                break;
            default:
                System.out.println("Tipo de entrada no válido.");
                return;
        }
        z.venderEntrada(e);
        System.out.println("Entrada vendida: " + e.getId() + " Precio: " + e.getPrecio());
    }

    public void consultarEntrada(int identificador) {
        for (Zona z : zonas) {
            Entrada e = z.buscarEntrada(identificador);
            if (e != null) {
                System.out.println("Entrada encontrada: ID " + e.getId() + ", Zona: " + z.getNombre() + ", Comprador: " + e.getNomComprador() + ", Precio: " + e.getPrecio());
                return;
            }
        }
        System.out.println("Entrada no encontrada.");
    }

    private Zona buscarZona(String nombre) {
        for (Zona z : zonas) {
            if (z.getNombre().equals(nombre)) {
                return z;
            }
        }
        return null;
    }
}

class Zona {

    private String nombre;
    private int numLocalidades;
    private double precioNormal;
    private double precioAbonado;
    private int localidadesVendidas = 0;
    private List<Entrada> entradas = new ArrayList<>();

    public Zona(String nombre, int numLocalidades, double precioNormal, double precioAbonado) {
        this.nombre = nombre;
        this.numLocalidades = numLocalidades;
        this.precioNormal = precioNormal;
        this.precioAbonado = precioAbonado;
    }

    public boolean disponibilidad() {
        return numLocalidades > localidadesVendidas;
    }

    public void venderEntrada(Entrada entrada) {
        entradas.add(entrada);
        localidadesVendidas++;
    }

    public Entrada buscarEntrada(int id) {
        for (Entrada e : entradas) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioNormal() {
        return precioNormal;
    }

    public double getPrecioAbonado() {
        return precioAbonado;
    }
}

class Entrada {

    private static int contador = 1;
    private int id;
    private String nomComprador;
    protected Zona zona;
    protected double precio;

    public Entrada(Zona zona, String nomComprador, double precio) {
        this.id = contador++;
        this.zona = zona;
        this.nomComprador = nomComprador;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNomComprador() {
        return nomComprador;
    }

    public double getPrecio() {
        return precio;
    }
}

class EntradaNormal extends Entrada {

    public EntradaNormal(Zona zona, String nomComprador) {
        super(zona, nomComprador, zona.getPrecioNormal());
    }
}

class EntradaReducida extends Entrada {

    private static final double DESCUENTO = 0.15;

    public EntradaReducida(Zona zona, String nomComprador) {
        super(zona, nomComprador, zona.getPrecioNormal() * (1 - DESCUENTO));
    }
}

class EntradaAbonado extends Entrada {

    public EntradaAbonado(Zona zona, String nomComprador) {
        super(zona, nomComprador, zona.getPrecioAbonado());
    }
}

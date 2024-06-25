
import java.util.ArrayList;
import java.util.List;

public class EjecutorTrabajador {

    public static void main(String[] args) {
        Jefe jefe = new Jefe("Carlos", "Perez", "Lauro Guerrero", "12345678A", 5000);

        FijoMensual fijoMensual = new FijoMensual("Ana", "Lopez", "Av. Eloy Alfaro", "87654321B", jefe, 3000);
        Comisionista comisionista = new Comisionista("Luis", "Gomez", "Luciano Coral", "23456789C", jefe, 0.1);
        PorHoras porHoras = new PorHoras("Marta", "Diaz", "Argentina", "34567890D", jefe, 15);

        jefe.DarAltaTrabajador(fijoMensual);
        jefe.DarAltaTrabajador(comisionista);
        jefe.DarAltaTrabajador(porHoras);

        jefe.fijarHoras(porHoras, 45);
        jefe.fijarVentas(comisionista, 50000);

        fijoMensual.calcularSalario();
        comisionista.calcularSalario();
        porHoras.calcularSalario();

        System.out.println("Datos de Ana (FijoMensual):");
        fijoMensual.mostrarDatos();
        System.out.println();

        System.out.println("Datos de Luis (Comisionista):");
        comisionista.mostrarDatos();
        System.out.println();

        System.out.println("Datos de Marta (PorHoras):");
        porHoras.mostrarDatos();
    }
}

class Jefe {

    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public double salario;
    public List<Trabajador> empleados;

    public Jefe(String nombre, String apellidos, String direccion, String dni, double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.salario = salario;
        this.empleados = new ArrayList<>();
    }

    public void DarAltaTrabajador(Trabajador trabajador) {
        empleados.add(trabajador);
        trabajador.jefe = this;
    }

    public void fijarHoras(PorHoras trabajador, double horas) {
        trabajador.horasTrabajadas = horas;
    }

    public void fijarVentas(Comisionista trabajador, double ventas) {
        trabajador.ventasRealizadas = ventas;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Direccion: " + direccion);
        System.out.println("DNI: " + dni);
        System.out.println("Salario: " + salario);
    }
}

class Trabajador {

    public String nombre;
    public String apellidos;
    public String direccion;
    public String dni;
    public double salario;
    public Jefe jefe;

    public Trabajador(String nombre, String apellidos, String direccion, String dni, Jefe jefe) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.dni = dni;
        this.jefe = jefe;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellidos: " + apellidos);
        System.out.println("Direccion: " + direccion);
        System.out.println("DNI: " + dni);
        System.out.println("Salario: " + salario);
        if (jefe != null) {
            System.out.println("Jefe:");
            jefe.mostrarDatos();
        }
    }

    public void calcularSalario() {
        // This method will be overridden in subclasses
    }
}

class FijoMensual extends Trabajador {

    public double salarioMensual;

    public FijoMensual(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double salarioMensual) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.salarioMensual = salarioMensual;
    }

    @Override
    public void calcularSalario() {
        this.salario = this.salarioMensual;
    }
}

class Comisionista extends Trabajador {

    public double porcentajeComision;
    public double ventasRealizadas;

    public Comisionista(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double porcentajeComision) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.porcentajeComision = porcentajeComision;
        this.ventasRealizadas = 0;
    }

    @Override
    public void calcularSalario() {
        this.salario = this.ventasRealizadas * this.porcentajeComision;
    }
}

class PorHoras extends Trabajador {

    public double precioHora;
    public double horasTrabajadas;

    public PorHoras(String nombre, String apellidos, String direccion, String dni, Jefe jefe, double precioHora) {
        super(nombre, apellidos, direccion, dni, jefe);
        this.precioHora = precioHora;
        this.horasTrabajadas = 0;
    }

    @Override
    public void calcularSalario() {
        if (horasTrabajadas <= 40) {
            this.salario = horasTrabajadas * precioHora;
        } else {
            this.salario = (40 * precioHora) + ((horasTrabajadas - 40) * (precioHora * 1.5));
        }
    }
}

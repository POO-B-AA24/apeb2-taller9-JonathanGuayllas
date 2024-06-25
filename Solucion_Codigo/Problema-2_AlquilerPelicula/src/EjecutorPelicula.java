
import java.util.ArrayList;
import java.util.Arrays;

public class EjecutorPelicula {

    public static void main(String[] args) {
        Dvd soporteDvd = new Dvd(4.5);
        Vhs soporteVhs = new Vhs("Cinta estandar");
        ArrayList<Pelicula> listaPeliculas = new ArrayList<Pelicula>(Arrays.asList(
                new Pelicula("Intensamente", "Ricardo", "2024", "Español", soporteDvd),
                new Pelicula("JuegoTronos", "Daniel", "2000","Español", soporteVhs),
                new Pelicula("Matrix", "Steven", "2024", "Español", soporteDvd)));
        for(Pelicula peli : listaPeliculas){
        System.out.println(peli);
        }
    }
    
}

class Pelicula {

    public String titulo;
    public String autor;
    public String anioEdicion;
    public String idioma;
    public double costoAlquiler;
    public Soporte soporte;

    public Pelicula(String titulo, String autor, String anioEdicion, String idioma, Soporte soporte) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioEdicion = anioEdicion;
        this.idioma = idioma;
        this.soporte = soporte;
    }

    public void calcularCostoAlquiler() {
    }

    @Override
    public String toString() {
        return "Pelicula{" + "titulo=" + titulo + ", autor=" + autor + ", anioEdicion=" + anioEdicion + ", idioma=" + idioma + ", costoAlquiler=" + costoAlquiler + ", soporte=" + soporte + '}';
    }

}

class Soporte {
}

class Dvd extends Soporte {

    public double capacidad;

    public Dvd(double capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return "Dvd{" + "capacidad=" + capacidad + '}';
    }

}

class Vhs extends Soporte {

    public String tipoCintaMag;

    public Vhs(String tipoCintaMag) {
        this.tipoCintaMag = tipoCintaMag;

    }

    @Override
    public String toString() {
        return "Vhs{" + "tipoCintaMag=" + tipoCintaMag + '}';
    }

}

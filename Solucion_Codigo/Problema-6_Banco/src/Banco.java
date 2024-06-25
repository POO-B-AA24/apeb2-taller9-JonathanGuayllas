public class Banco {
    public static void main(String[] args) {
        CuentaCheques cuentaCheques = new CuentaCheques(123, "Juan Perez");
        CuentaAhorros cuentaAhorros = new CuentaAhorros(456, "Maria Lopez");
        CuentaPlatino cuentaPlatino = new CuentaPlatino(789, "Ana Garcia");

        cuentaCheques.depositar(1000);
        cuentaCheques.retirar(1500);
        System.out.println("Cliente: " + cuentaCheques.obtenerNombreCliente() + ", Balance Cuenta Cheques: " + cuentaCheques.obtenerBalance());

        cuentaAhorros.depositar(2000);
        cuentaAhorros.retirar(500);
        cuentaAhorros.calcularInteres();
        System.out.println("Cliente: " + cuentaAhorros.obtenerNombreCliente() + ", Balance Cuenta Ahorros: " + cuentaAhorros.obtenerBalance());

        cuentaPlatino.depositar(5000);
        cuentaPlatino.retirar(1000);
        cuentaPlatino.calcularInteres();
        System.out.println("Cliente: " + cuentaPlatino.obtenerNombreCliente() + ", Balance Cuenta Platino: " + cuentaPlatino.obtenerBalance());
    }
}

class Cuenta {
    public int numeroCuenta;
    public String nombreCliente;
    public double balance;

    public Cuenta(int numeroCuenta, String nombreCliente) {
        this.numeroCuenta = numeroCuenta;
        this.nombreCliente = nombreCliente;
        this.balance = 0.0;
    }

    public void depositar(double cantidad) {
        balance += cantidad;
    }

    public void retirar(double cantidad) {
        balance -= cantidad;
    }

    public double getBalance() {
        return balance;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }
}

class CuentaCheques extends Cuenta {
    public CuentaCheques(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double cantidad) {
        balance -= cantidad; 
    }
}

class CuentaAhorros extends Cuenta {
    public static final double INTERES = 0.02;

    public CuentaAhorros(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double cantidad) {
        if (balance >= cantidad) {
            balance -= cantidad; 
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void calcularInteres() {
        balance += balance * INTERES;
    }
}

class CuentaPlatino extends Cuenta {
    public static final double INTERES = 0.10;

    public CuentaPlatino(int numeroCuenta, String nombreCliente) {
        super(numeroCuenta, nombreCliente);
    }

    @Override
    public void retirar(double cantidad) {
        balance -= cantidad; 
    }

    public void calcularInteres() {
        balance += balance * INTERES;
    }
}

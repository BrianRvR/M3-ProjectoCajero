/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author alumne
 */
public class Banco {

    private String nombre;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

   public Banco(String nombre, String nombreArchivo) throws FileNotFoundException {
        this.nombre = nombre;
        this.clientes = ArchivosClientes.leerClientes("src/main/java/com/mycompany/csv/clientes_cuentas.csv");
        this.cuentas = new ArrayList<>();

        // Crear la lista de cuentas a partir de la lista de clientes
        for (Cliente cliente : this.clientes) {
            this.cuentas.addAll(cliente.getCuentas());
        }
    }

    
    public Cliente login(String usuario, String password) {
    // Buscar cliente en la lista de clientes del banco
    for (Cliente cliente : clientes) {
        if (cliente.getNombreUsuario().equals(usuario) && cliente.getPassword().equals(password)) {
            return cliente;
            }
        }
    // Si no se encuentra un cliente con ese usuario y contraseña, retornar null
        return null;
    }
    
    
    
    
    public void retirarDeCuenta(String nombreUsuario, String password, int idCuenta, double monto) throws IOException {
        // Buscar al cliente
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getNombreUsuario().equals(nombreUsuario) && c.getPassword().equals(password)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        // Buscar la cuenta
        Cuenta cuenta = cliente.buscarCuenta(idCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        if (monto <= 0) {
            throw new IllegalArgumentException("Monto a retirar debe ser mayor que cero");
        }

        // Hacer el retiro
        cuenta.retirar(monto);

        // Actualizar el saldo de la cuenta en el archivo CSV
        actualizarInfoClienteCSV(clientes, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
    }
    
    
    public void ingresarACuenta(String nombreUsuario, String password, int idCuenta, double monto) throws IOException {
        // Buscar al cliente
        Cliente cliente = null;
        for (Cliente c : clientes) {
            if (c.getNombreUsuario().equals(nombreUsuario) && c.getPassword().equals(password)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado");
        }

        // Buscar la cuenta
        Cuenta cuenta = cliente.buscarCuenta(idCuenta);
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no encontrada");
        }

        if (monto <= 0) {
            throw new IllegalArgumentException("Monto a ingresar debe ser mayor que cero");
        }

        // Hacer el ingreso
        cuenta.ingresar(monto);

        // Actualizar el saldo de la cuenta en el archivo CSV
        actualizarInfoClienteCSV(clientes, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
    }
    
    
    
    
    public List<Cliente> getClientes() {
    return this.clientes;
    }
    
            
   
    
    
    public void cambiarContraseñaCliente(Cliente cliente, String contrasenaAntigua, String nuevaContrasena, String archivoClientes) throws IOException {
        // Verificar que el cliente exista en la lista de clientes
        if (!clientes.contains(cliente)) {
            // Manejar caso de cliente no existente
            return;
        }

        // Verificar que la contraseña antigua sea correcta
        if (!cliente.getPassword().equals(contrasenaAntigua)) {
            // Manejar caso de contraseña antigua incorrecta
            return;
        }
        
        // Cambiar la contraseña del cliente
        cliente.setContrasena(nuevaContrasena);

        // Crear una lista de un solo elemento que contenga al cliente modificado
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);

        // Actualizar la información del cliente en el archivo CSV
        actualizarInfoClienteCSV (listaClientes, archivoClientes);
    }
    
    
    public static void actualizarInfoClienteCSV(List<Cliente> clientes, String archivoClientes) throws IOException {
        // Leer todo el archivo CSV en una lista de String
        List<String> lineas = Files.readAllLines(Paths.get(archivoClientes));
        System.out.println("Archivo leído correctamente. Número de líneas: " + lineas.size());

        // Buscar y actualizar las líneas correspondientes a cada cliente
        for (Cliente cliente : clientes) {
            String usuario = cliente.getNombreUsuario();
            String contraseñaNueva = cliente.getPassword();

            for (int i = 1; i < lineas.size() && i < 10000; i++) { // Limitar la cantidad de líneas a 10000
                String[] partes = lineas.get(i).split(";");
                String usuarioActual = partes[0];
                String contraseñaActual = partes[1];

                if (usuario.equals(usuarioActual)) {
                    // Actualizar la contraseña
                    if (!contraseñaNueva.equals(contraseñaActual)) {
                        partes[1] = contraseñaNueva;
                        lineas.set(i, String.join(";", partes));
                        System.out.println("Línea actualizada: " + lineas.get(i));
                    }
                }
            }
        }

        // Escribir todas las líneas actualizadas en el archivo CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoClientes))) {
            for (String linea : lineas) {
                writer.println(linea);
                System.out.println("Línea escrita en el archivo: " + linea);
            }
            writer.flush(); // Escribir cualquier cambio pendiente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void actualizarInfoCuentaCSV(Cuenta cuenta, String archivoCuentas) throws IOException {
        // Leer todo el archivo CSV en una lista de String
        List<String> lineas = Files.readAllLines(Paths.get(archivoCuentas));
        System.out.println("Archivo leído correctamente. Número de líneas: " + lineas.size());

        // Buscar y actualizar la línea correspondiente a la cuenta
        int idCuenta = cuenta.getIdCuenta();
        for (int i = 1; i < lineas.size() && i < 10000; i++) { // Limitar la cantidad de líneas a 10000
            String[] partes = lineas.get(i).split(";");
            int idActual = Integer.parseInt(partes[2]);

            if (idCuenta == idActual) {
                // Actualizar el saldo
                double saldoNuevo = cuenta.getSaldoActual();
                partes[4] = String.format(Locale.US, "%.2f", saldoNuevo).replace(",", ".");
                lineas.set(i, String.join(";", partes));
                System.out.println("Línea actualizada: " + lineas.get(i));
                break;
            }
        }

        // Escribir todas las líneas actualizadas en el archivo CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivoCuentas))) {
            for (String linea : lineas) {
                writer.println(linea);
                System.out.println("Línea escrita en el archivo: " + linea); // Agregar esta línea para depuración
            }
            writer.flush(); // Escribir cualquier cambio pendiente
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void transferirEntreCuentas(Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto) throws IOException {
    if (cuentaOrigen instanceof CuentaCorriente && cuentaDestino instanceof CuentaCorriente) {
        Cliente clienteOrigen = null;
        Cliente clienteDestino = null;

        // Buscar el cliente de la cuenta origen
        for (Cliente cliente : clientes) {
            if (cliente.buscarCuentaPorId(cuentaOrigen.getIdCuenta()) != null) {
                clienteOrigen = cliente;
                break;
            }
        }

        // Buscar el cliente de la cuenta destino
        for (Cliente cliente : clientes) {
            if (cliente.buscarCuentaPorId(cuentaDestino.getIdCuenta()) != null) {
                clienteDestino = cliente;
                break;
            }
        }

        // Verificar que los clientes y las cuentas existan
        if (clienteOrigen == null || clienteDestino == null) {
            throw new IllegalArgumentException("Cliente o cuenta no encontrado");
        }

        // Transferir el monto de la cuenta origen a la cuenta destino
        cuentaOrigen.transferir(cuentaDestino, monto);

        // Actualizar la información de la cuenta origen en el archivo CSV
        actualizarInfoCuentaCSV(cuentaOrigen, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");
        
        // Actualizar la información de la cuenta destino en el archivo CSV
        actualizarInfoCuentaCSV(cuentaDestino, "src/main/java/com/mycompany/csv/clientes_cuentas.csv");

        // Agregar el movimiento a la lista de movimientos del cliente de la cuenta origen
        Movimiento movimientoOrigen = new Movimiento("transferencia", -monto, "Transferencia a cuenta " + cuentaDestino.getIdCuenta());
        clienteOrigen.agregarMovimientoACuenta(movimientoOrigen, cuentaOrigen);

        // Agregar el movimiento a la lista de movimientos del cliente de la cuenta destino
        Movimiento movimientoDestino = new Movimiento("transferencia", monto, "Transferencia desde cuenta " + cuentaOrigen.getIdCuenta());
        clienteDestino.agregarMovimientoACuenta(movimientoDestino, cuentaDestino);

        // Actualizar la información de los clientes en el archivo CSV
        actualizarInfoClienteCSV(clientes, "src/main/java/com/mycompany/csv/clientes.csv");

    } else {
        throw new IllegalArgumentException("Solo se permiten transferencias entre cuentas corrientes");
    }
}

   



    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
        
    }

    public Cliente buscarCliente(String nombreUsuario) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombreUsuario().equals(nombreUsuario)) {
                return cliente;
            }
        }
        return null;
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }
    
    public Cliente buscarClientePorCredenciales(String nombreUsuario, String password) {
    // Buscar al cliente correspondiente en la lista de clientes
    for (Cliente cliente : clientes) {
        if (cliente.getNombreUsuario().equals(nombreUsuario) && cliente.getPassword().equals(password)) {
            return cliente;
        }
    }

    // Si no se encuentra un cliente con las credenciales ingresadas, retornar null
    return null;
}

    public Cliente buscarClienteConCuenta(int idCuenta) {
     for (Cliente cliente : clientes) {
         for (Cuenta cuenta : cliente.getCuentas()) {
             if (cuenta.getIdCuenta() == idCuenta) {
                 return cliente;
             }
         }
     }
     return null;
 }

    public Cuenta buscarCuentaPorId(int idCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getIdCuenta() == idCuenta) {
                return cuenta;
            }
        }
        return null;
    }
    public Cuenta buscarCuentaPorTipo(String tipo) {
    for (Cliente cliente : clientes) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getTipoCuenta().equalsIgnoreCase(tipo)) {
                return cuenta;
            }
        }
    }
    return null;
    }



    
    
    



    public void agregarClienteConCuentas(String nombreUsuario, String password, List<Cuenta> nuevasCuentas) {
    Cliente cliente = new Cliente(nombreUsuario, password);
    clientes.add(cliente);
    for (Cuenta cuenta : nuevasCuentas) {
        cliente.agregarCuenta(cuenta);
    }
}


}



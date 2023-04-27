/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumne
 */
public class Banco {

    private String nombre;
    private List<Cliente> clientes;
    private List<Cuenta> cuentas;

    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.cuentas = new ArrayList<>();
        
        crearClientesConCuentas(); // Crear clientes y cuentas dinámicamente

        
        
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
    for (Cliente cliente : clientes) {
        if (cliente.getNombreUsuario().equals(nombreUsuario) && cliente.getPassword().equals(password)) {
            return cliente;
        }
    }
    return null;
}

    public Cliente buscarClienteConCuenta(String idCuenta) {
    for (Cliente cliente : clientes) {
        for (Cuenta cuenta : cliente.getCuentas()) {
            if (cuenta.getIdCuenta().equals(idCuenta)) {
                return cliente;
            }
        }
    }
    return null;
    }

    
    
    public void transferir(String idCuentaOrigen, String idCuentaDestino, double monto) {
    // Buscar el cliente de origen con el id de cuenta proporcionado
    Cliente clienteOrigen = buscarClienteConCuenta(idCuentaOrigen);
    // Buscar el cliente de destino con el id de cuenta proporcionado
    Cliente clienteDestino = buscarClienteConCuenta(idCuentaDestino);

    // Si ambos clientes existen
    if (clienteOrigen != null && clienteDestino != null) {
        // Busca la cuenta de origen del cliente
        Cuenta cuentaOrigen = clienteOrigen.buscarCuenta(idCuentaOrigen);
        // Busca la cuenta de destino del cliente
        Cuenta cuentaDestino = clienteDestino.buscarCuenta(idCuentaDestino);

        // Si ambas cuentas existen
        if (cuentaOrigen != null && cuentaDestino != null) {
            // Si ambas cuentas son del tipo "corriente"
            if (cuentaOrigen.getTipoCuenta().equals("corriente") && cuentaDestino.getTipoCuenta().equals("corriente")) {
                // Realizo la tranferencia
                cuentaOrigen.transferir(cuentaDestino, monto);
                System.out.println("Transferencia realizada con éxito.");
            } else {
                System.out.println("Tipo de cuenta no válido para transferencia.");
                // Detener la ejecución de la función porque no se me ocurre de otra manera
                return;
            }
        } else {
            System.out.println("Cuenta origen o cuenta destino no encontrada.");
            // Detener la ejecución de la función 
            return;
        }
    } else {
        System.out.println("Cuenta origen o cuenta destino no encontrada.");
        // Detener la ejecución de la función
        return;
    }
}


    

    public void crearClientesConCuentas() {
    // Crear clientes y cuentas dinámicamente
        Cliente cliente1 = new Cliente("usuario1", "password1");
        Cuenta cuenta1 = new Cuenta("001", "corriente", 1000);
        Cuenta cuenta2 = new Cuenta("002", "ahorro", 5000);
        cliente1.agregarCuenta(cuenta1);
        cliente1.agregarCuenta(cuenta2);
        agregarCliente(cliente1);

        Cliente cliente2 = new Cliente("usuario2", "password2");
        Cuenta cuenta3 = new Cuenta("003", "corriente", 2000);
        Cuenta cuenta4 = new Cuenta("004", "ahorro", 10000);
        cliente2.agregarCuenta(cuenta3);
        cliente2.agregarCuenta(cuenta4);
        agregarCliente(cliente2);
    }

    public void agregarClienteConCuentas(String nombreUsuario, String password, List<Cuenta> nuevasCuentas) {
    Cliente cliente = new Cliente(nombreUsuario, password);
    clientes.add(cliente);
    for (Cuenta cuenta : nuevasCuentas) {
        cliente.agregarCuenta(cuenta);
    }
}


}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author brian
 */
public class ArchivosClientes {
    
        private static Cliente buscarCliente(List<Cliente> clientes, String nombreUsuario) {
            for (Cliente cliente : clientes) {
                if (cliente.getNombreUsuario().equals(nombreUsuario)) {
                    return cliente;
                }
            }
            return null;
        }
        public static List<Cliente> leerClientes(String rutaArchivo) throws FileNotFoundException {
            File archivo = new File(rutaArchivo);
            Scanner scanner;

            try {
                scanner = new Scanner(archivo);
            } catch (FileNotFoundException e) {
                System.out.println("ERROR: No se encontró el archivo en la ruta especificada.");
                throw e;
            }

            List<Cliente> clientes = new ArrayList<>();

            // Ignorar la primera línea, que contiene los encabezados del archivo CSV
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                System.out.println("Leyendo línea del archivo: " + linea);
                String[] datos = linea.split(";");

                if (datos.length >= 5) { // Verificar que el arreglo datos tenga al menos 5 elementos
                    String nombreUsuario = datos[0];
                    String contrasena = datos[1];

                    String numeroCuentaStr = datos[2];
                    int numeroCuenta = 0;
                    if (!numeroCuentaStr.equals("corriente") && !numeroCuentaStr.equals("ahorro")) {
                        numeroCuenta = Integer.parseInt(numeroCuentaStr.replaceAll("\\D+",""));
                    }

                    String tipoCuenta = datos[3];
                    String saldoStr = datos[4];
                    double saldo = Double.parseDouble(saldoStr.replace(".", "").replace(",", "."));

                    // Buscar si ya existe el cliente en la lista de clientes
                    Cliente cliente = buscarCliente(clientes, nombreUsuario);

                    // Si el cliente no existe, crearlo y agregarlo a la lista de clientes
                    if (cliente == null) {
                        cliente = new Cliente(nombreUsuario, contrasena);
                        clientes.add(cliente);
                    }

                    // Crear la cuenta y agregarla al cliente
                    if (tipoCuenta.equals("corriente") || tipoCuenta.equals("ahorro")) {
                        Cuenta cuenta = new Cuenta(numeroCuenta, tipoCuenta, saldo);
                        cliente.agregarCuenta(cuenta);
                    }
                }
            }

            scanner.close();

            return clientes;
        }
}
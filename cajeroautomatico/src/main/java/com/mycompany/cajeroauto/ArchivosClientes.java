/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cajeroauto;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author brian
 */
public class ArchivosClientes {
        
    public static List<Cliente> leerClientes(String rutaArchivo) throws FileNotFoundException, ParseException {
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

                int numeroCuenta = Integer.parseInt(datos[2]);
                String tipoCuenta = datos[3];
                String saldoStr = datos[4];
                double saldo = 0;
                if (tipoCuenta.equals("corriente") || tipoCuenta.equals("ahorro")) {
                    saldo = Double.parseDouble(saldoStr.replace(",", "."));
                    // Crear la cuenta y agregarla al cliente
                    Cuenta cuenta = new Cuenta(numeroCuenta, tipoCuenta, saldo);
                    // Buscar si ya existe el cliente en la lista de clientes
                    Cliente cliente = buscarCliente(clientes, nombreUsuario);
                    // Si el cliente no existe, crearlo y agregarlo a la lista de clientes
                    if (cliente == null) {
                        cliente = new Cliente(nombreUsuario, contrasena);
                        clientes.add(cliente);
                    }
                    cliente.agregarCuenta(cuenta);
                } else {
                    System.out.println("ERROR: El tipo de cuenta " + tipoCuenta + " no es válido.");
                }
            }
        }

        scanner.close();

        return clientes;
    }

    private static Cliente buscarCliente(List<Cliente> clientes, String nombreUsuario) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombreUsuario().equals(nombreUsuario)) {
                return cliente;
            }
        }
        return null;
    }
}
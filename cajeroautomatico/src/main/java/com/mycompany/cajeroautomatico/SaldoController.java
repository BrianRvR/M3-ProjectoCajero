package com.mycompany.cajeroautomatico;

public class SaldoController {

    private Cuenta cuenta; // Atributo para almacenar la instancia de la cuenta

    // Método para setear la instancia de la cuenta
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        actualizarSaldo(); // actualiza el saldo de la cuenta en la vista
    }

    // Método para actualizar el saldo en la vista
    private void actualizarSaldo() {
        // Aquí puedes acceder al atributo 'cuenta' para obtener el saldo y actualizar la vista
    }

}

package com.example.tfinal_paulodomingues

class Contacto(val nome: String, val morada: String, val telefone: String, val email: String) {
    override fun toString(): String {
        return "$nome ($telefone)"
    }

}
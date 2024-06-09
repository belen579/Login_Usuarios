package com.example.ex_terceraev.ui.Data

data class Usuario(

    var usuario: String,
    var email:String,
    var contraseña: String,
    var seleccionado: Boolean

) {

}


var listausuarios= mutableListOf<Usuario>(
    Usuario("belen","belen@iesteis.gal", "abcd", false),
    Usuario("bastos","bastos@iesteis.gal", "abcdef", false),
    Usuario("alex","Alex@iesteis.gal", "123456", false),
    Usuario("maria","Maria@iesteis.gal", "123456", false)




)

fun getListaclassUsuario(): MutableList<Usuario> {
    return listausuarios
}
package com.example.ex_terceraev.ui.Screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registrarse(navController: NavController, viewModel: LoginViewModel){

    var context= LocalContext.current

    Scaffold(
        topBar = { TopAppBar(title = { "Validacion" }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor= MaterialTheme.colorScheme.onPrimary),

            actions= {
                Text(text ="Nuevos Usuarios")
                Text(text ="Num ${viewModel.listausuarios.size}")
            }
        )}){ Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(modifier = Modifier
            .padding(top = 30.dp)
            .align(Alignment.CenterHorizontally)
        ){
            Text(text = "Registrarse")
        }
        Spacer (modifier = Modifier.padding(10.dp))

        TextField(value = viewModel.usuario, onValueChange = {it -> viewModel.ObtenerUsuario(it)},
            label = { Text("Usuario") })

        Spacer (modifier = Modifier.padding(10.dp))
        TextField(value = viewModel.email, onValueChange = {it -> viewModel.setemail(it)},

            label = { Text("Email") })

        TextField(value = viewModel.password, onValueChange = {it -> viewModel.ObtenerPassword(it)},
            // visualTransformation = if(viewModel.banderapassword) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Password") })

        Spacer (modifier = Modifier.padding(10.dp))
        TextField(value = viewModel.password2, onValueChange = {it -> viewModel.setpassword2(it)},
            //   visualTransformation = if(viewModel.banderapassword) VisualTransformation.None else PasswordVisualTransformation(),
            label = { Text("Password") })

        Button(onClick = {
            if(viewModel.comprobarcontraseñaregistro(viewModel.password, context)&& viewModel.comprobarcontraseñaregistro(viewModel.password2, context)
                && viewModel.comprobarregistro(viewModel.usuario, context) && viewModel.comprobarsiestaenlalistaregistro(viewModel.usuario, viewModel.email, context)
                ){


               // viewModel.showDialog

                //    Toast.makeText(context, "Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show()




                    viewModel.Guardarusuario(
                        Usuario(viewModel.usuario,viewModel.email, viewModel.password, false)
                    )

                    navController.navigate(route = Screens.listadousuarios.route)
                    viewModel.showAlertDialog(context, "Alerta", "Usuario Registrado Correctamente")

                }





               // viewModel.onConvert()




        },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        ) {
            Text(text = "alta")
        }



    }


    }



}




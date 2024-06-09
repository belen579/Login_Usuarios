package com.example.ex_terceraev.ui.Screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel

@Composable
fun loginlistado(viewModel: LoginViewModel, navigation: NavController) {
    var context = LocalContext.current
    val scrollState = rememberLazyListState()

    if (!viewModel.banderacambio) {


        Column(
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = viewModel.email,
                    onValueChange = { it -> viewModel.setemail(it) },
                    label = { Text("Usuario") })

                Spacer(modifier = Modifier.padding(10.dp))

                TextField(value = viewModel.password,
                    onValueChange = { it -> viewModel.ObtenerPassword(it) },
                    visualTransformation = if (viewModel.banderapassword) VisualTransformation.None else PasswordVisualTransformation(),
                    label = { Text("Password") })

                Spacer(modifier = Modifier.padding(10.dp))

                Button(onClick = {
                    if (viewModel.comprobarcontraseña(viewModel.password, context)
                        && viewModel.comprobarcontraseñaentrelalista(viewModel.email, viewModel.password, context)) {
                      //  viewModel.Guardarusuario(Usuario(viewModel.usuario, viewModel.password, false))
                     //   viewModel.onConvert()


                        navigation.navigate(route = Screens.registro.route)

                    }
                }, modifier = Modifier
                    .align(Alignment.CenterHorizontally)

                ) {
                    Text(text = "Entrar")
                }

                Box(modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(start = 10.dp)
                    .align(Alignment.Start)
                    .clickable { viewModel.showpassword() }){
                    Text(text = "Ver contraseña")
                }

                Box(modifier = Modifier
                    .padding(top = 30.dp)
                    .padding(end = 40.dp)
                    .align(Alignment.End)
                    .clickable {
                        navigation.navigate(route = Screens.registro.route)
                        //  viewModel.Guardarusuario(
                        //    Usuario(viewModel.usuario, viewModel.password)
                        //  )
                    }){
                    Text(text = "Registrarse")
                }

            }

        }

    }

    if(viewModel.banderacambio){



    }
}



@SuppressLint("SuspiciousIndentation")
@Composable
fun checkboxusuario(usuario: Usuario, viewModel: LoginViewModel) {

    var checkedstate by rememberSaveable { mutableStateOf(usuario.seleccionado) }
 var context = LocalContext.current

    Card (modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable { viewModel.showAlertDialog(context, "usuario", "${usuario.usuario}") },
        shape = RoundedCornerShape(8.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Checkbox(checked = checkedstate, onCheckedChange = {isChecked ->
                checkedstate= isChecked

                if(isChecked){
                    viewModel.toggleSeleccion(usuario)
                }else{
                    viewModel.toggleSeleccion(usuario)
                }
            }


            )
            Column {
                Text(text = "${usuario.usuario}")
                Text(text = "${usuario.contraseña}")

            }
            Image(painter =painterResource(id= android.R.drawable.ic_menu_edit), contentDescription = "", modifier = Modifier.clickable {
                Toast.makeText(context, " Ha clicado en Editar", Toast.LENGTH_SHORT).show()

            })
            IconButton(onClick = {
                viewModel.borrarusuario(usuario)}) {
                Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Black)
            }


            Spacer(modifier = Modifier.weight(1f))
            
            Image(painter =painterResource(id= android.R.drawable.ic_delete), contentDescription = "", modifier = Modifier.clickable {
                Toast.makeText(context, " Ha clicado en eliminar", Toast.LENGTH_SHORT).show()

            })
            IconButton(onClick = { viewModel.añadirusuario(usuario)
               }) {
                Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
            }

        }


        }
    }


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun presentacionScafold(viewModel: LoginViewModel, navigation: NavController){

    var checkstate by rememberSaveable {
        mutableStateOf(false)
    }

   Scaffold(
      topBar = { TopAppBar(title = { "Validacion" }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor= MaterialTheme.colorScheme.primary),

      actions= {
          Text(text = "Validacion")
        /*
          IconButton(onClick = { viewModel.onConvert() }) {
              //  navController.navigate(route= Screens.confirmacion.route)}) {
              Icon(Icons.Default.Refresh, contentDescription = null, tint = Color.Black)
          }
          IconButton(onClick = { viewModel }) {
              //  navController.navigate(route= Screens.confirmacion.route)}) {
              Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
          }
          IconButton(onClick = { viewModel }) {
              //  navController.navigate(route= Screens.confirmacion.route)}) {
              Icon(Icons.Default.Delete, contentDescription = null, tint = Color.Black)
          }

          Checkbox(checked = viewModel.checkedall, onCheckedChange = { ischecked ->
              viewModel.checkedall = ischecked

              if (ischecked) {
                  viewModel.seleccionartodosloscheck()

              } else {
                  viewModel.cleartodosloscheck()
              }

              navigation.navigate(route = Screens.activityunica.route)


          })*/


      }







      )},


       bottomBar = {
           if(viewModel.banderacambio){
               BottomAppBar(
                   modifier = Modifier.fillMaxWidth(),

                   ) {
                   Text(text = "Numero de usuarios ${viewModel.listausuarios.size}")
               }

           }

       }

   ) {
       loginlistado(viewModel,navigation)
   }
}


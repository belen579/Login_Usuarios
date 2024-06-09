package com.example.ex_terceraev.ui.Screens

import android.R
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ex_terceraev.ui.Data.Usuario
import com.example.ex_terceraev.ui.Navigation.Screens
import com.example.ex_terceraev.ui.viewmodel.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun listadousuarios(viewModel: LoginViewModel, navController: NavController){
    val scrollState = rememberLazyListState()
    var context = LocalContext.current
    Scaffold(
        topBar = { TopAppBar(title = { "Validacion" }, colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor= MaterialTheme.colorScheme.onPrimary),

            actions= {
                Row(modifier= Modifier.align(alignment = Alignment.CenterVertically)) {
                    Text(text ="Listado de Usuarios")
                    IconButton(onClick = {

                        if(viewModel.mostrarinfo){
                            Toast.makeText(context,"Datos ${viewModel.nombreusuario}", Toast.LENGTH_SHORT).show()
                        }


                    }) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = Color.Black)
                    }
                }

                //Text(text ="Num ${viewModel.listausuarios.size}")
            }
        )
        }){
        Column(
            modifier= Modifier.padding(top =70.dp)

        ) {
            Column {
                Text(text = "Usuarios")
            }


            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 8.dp),
                state = scrollState
            ) {

                items(viewModel.listausuarios){

                        usuario   ->
                    checkboxusuario2(usuario, viewModel,

                        navController
                    )

                }


            }
        }

    }


}


@SuppressLint("SuspiciousIndentation")
@Composable
fun checkboxusuario2(usuario: Usuario, viewModel: LoginViewModel,
                    navController: NavController) {

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
        ) {Column {
            Text(text = "${usuario.usuario}")
            Text(text = "${usuario.email}")

        }

            Checkbox(checked = checkedstate, onCheckedChange = {isChecked ->
                checkedstate= isChecked

                if(isChecked){
                    viewModel.toggleSeleccion(usuario)
                  //  Toast.makeText(context, "${usuario.usuario}", Toast.LENGTH_SHORT).show()
                    viewModel.mostrarinfoconvert("${usuario.usuario}, ${usuario.email}")
                }else{
                    viewModel.toggleSeleccion(usuario)
                }
            }


            )





            Spacer(modifier = Modifier.weight(1f))

            Image(painter = painterResource(id= R.drawable.ic_delete), contentDescription = "", modifier = Modifier.clickable {
                Toast.makeText(context, " Ha clicado en eliminar", Toast.LENGTH_SHORT).show()
                viewModel.borrarusuario(usuario)

            })
            IconButton(onClick = {

                usuario.usuario= "cambiado"
                usuario.email="cambio@teis.gal"


                navController.navigate(route= Screens.listadousuarios.route)

            }) {
                Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Black)
            }

        }


    }
}


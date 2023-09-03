package com.example.laboratorio6application

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.laboratorio6application.ui.theme.Laboratorio6ApplicationTheme
import java.nio.file.WatchEvent

var usersList = mutableListOf<User>()
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createUsers()
        setContent {
            Laboratorio6ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Login()
                }
            }
        }
    }
}
fun createUsers(){
    usersList.clear()
    usersList.add(User("Admin","123"))
    usersList.add(User("Gerax","Gerax@!7"))
    usersList.add(User("VEVEVEeeee","Veee"))
}

fun verifyLogin(user:String,password:String):Boolean{
    return User(user, password) in usersList
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login() {
    var textUser by remember {
            mutableStateOf("")
    }
    var textPass by remember {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()
    val maxSizeUser = 10
    val maxSizePassword = 8
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_login),
            contentDescription =null,
            Modifier
                .size(150.dp)
                .padding(PaddingValues(vertical = 20.dp)))

        TextField(value = textUser,
            onValueChange = { if(it.length <= maxSizeUser) textUser = it },
            label = { Text(text = "Ingrese su usuario")},
            modifier = Modifier.padding(PaddingValues(vertical = 20.dp)))
        TextField(value = textPass,
            onValueChange = { (if(it.length <= maxSizePassword)textPass = it )},
            label = { Text(text = "Ingrese su contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(PaddingValues(vertical = 20.dp)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Button(onClick = {
            if(verifyLogin(textUser, textPass)){
                context.startActivity(Intent(context,MiGaleriaActivity::class.java))
                activity?.finish()
            }else{
                println("Entro en error")
                Toast.makeText(context,"Error en las credenciales",Toast.LENGTH_LONG).show()
            }
        }) {
            Text(text = "Ingresar")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Laboratorio6ApplicationTheme {
        Login()
    }
}
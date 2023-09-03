package com.example.laboratorio6application

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio6application.ui.theme.Laboratorio6ApplicationTheme

var imageList =  mutableListOf<ImageAttr>()
var pos = 0

class MiGaleriaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createImageList()
        setContent {
            Laboratorio6ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GaleriaActivity()
                }
            }
        }
    }
}

fun createImageList(){
    imageList.clear()
    imageList.add(ImageAttr(R.drawable.ic_jjkposter,"Jujutsu Kaisen","Gege Akutami (2018)"))
    imageList.add(ImageAttr(R.drawable.ic_jojoposter,"JoJo's Bizarre Adventure","Hirohiko Araki (1987)"))
    imageList.add(ImageAttr(R.drawable.ic_snkposter,"Shingeki no Kyojin","Hajime Isayama (2009)"))
    imageList.add(ImageAttr(R.drawable.ic_liwposter,"Kaguya-sama: Love Is War","Aka Akasaka (2015)"))
    imageList.add(ImageAttr(R.drawable.ic_dnposter,"Death Note","Tsugumi Ōba (2003)"))
    imageList.add(ImageAttr(R.drawable.ic_yourposter,"Shigatsu wa Kimi no Uso","Naoshi Arakawa (2016)"))
    imageList.add(ImageAttr(R.drawable.ic_monsterposter,"Monster","Naoki Urasawa (1991)"))
    imageList.add(ImageAttr(R.drawable.ic_opposter,"One punch Man","One (2009)"))
    imageList.add(ImageAttr(R.drawable.ic_vsposter,"Vinland Saga"," Makoto Yukimura (2005)"))
    imageList.add(ImageAttr(R.drawable.ic_psposter,"Psycho-Pass","Hikaru Miyoshi (2014)"))
}

fun interactWithImage(move:Int): ImageAttr{
    var imageAttrTemp: ImageAttr
    if((pos+move) < 0){
        pos = imageList.size-1
        imageAttrTemp = imageList[pos]
    }else if ((pos+move) == imageList.size){
        pos = 0
        imageAttrTemp = imageList[pos]
    }else{
        pos+=move
        imageAttrTemp = imageList[pos]
    }
    return imageAttrTemp
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GaleriaActivity(){
    var showImage by remember {
        mutableStateOf(imageList[0].image)
    }
    var upperText by remember {
        mutableStateOf(imageList[0].upperText)
    }
    var lowerText by remember {
        mutableStateOf(imageList[0].lowerText)
    }
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    Column(modifier = Modifier.fillMaxWidth().verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(title = { Text(text = "Galeria") }, colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Gray
        ),
            actions = { IconButton(onClick = {
                context.startActivity(Intent(context,MainActivity::class.java))
                activity?.finish()
            }) {
                Icon(painter = painterResource(id = R.drawable.baseline_logout_24), contentDescription = "")
        }}
        )

        Card(shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier
                .padding(10.dp)
                .padding(vertical = 10.dp)) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(showImage),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .width(250.dp)
                        .height(500.dp))
            }

        }

        Card(modifier = Modifier, shape = RoundedCornerShape(0.dp)) {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = upperText, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = lowerText, fontSize = 20.sp)
            }
        }

        Row(modifier = Modifier.padding(vertical = 20.dp)) {
            Button(onClick = {
                var tempImageAttr = interactWithImage(-1)
                showImage = tempImageAttr.image
                upperText = tempImageAttr.upperText
                lowerText = tempImageAttr.lowerText
            }, modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(text = "Anterior")
            }
            Button(onClick = {
                var tempImageAttr = interactWithImage(1)
                showImage = tempImageAttr.image
                upperText = tempImageAttr.upperText
                lowerText = tempImageAttr.lowerText
            }, modifier = Modifier.padding(horizontal = 10.dp)) {
                Text(text = "Siguiente")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MiGaleriaPreview() {
    Laboratorio6ApplicationTheme {
        GaleriaActivity()
    }
}
package com.seda.woof_compose_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.seda.woof_compose_app.data.Datasource
import com.seda.woof_compose_app.model.Dog
import com.seda.woof_compose_app.ui.theme.Woof_Compose_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Woof_Compose_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WoofApp()
                }
            }
        }
    }
}

@Composable
fun WoofApp() {
    Scaffold(topBar = {
        WoofTopAppBar()
    })
    {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)){
            items(Datasource.dogs){ dogs->
                DogItem(dog = dogs)

            }
    }


   }
}
@Composable
fun DogItem(dog: Dog,modifier: Modifier=Modifier){
    Card(modifier = modifier.padding(8.dp), elevation = 4.dp) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .padding(8.dp)
            .background(MaterialTheme.colors.surface)) {
            DogIcon(dogIcon = dog.imageResourceId)
            DogInformation(dogName = dog.name,dog.age )
    }

}

}

@Composable()
fun DogIcon(@DrawableRes dogIcon:Int, modifier: Modifier=Modifier){
    Image(painter = painterResource(id = dogIcon), contentDescription ="",
        modifier= modifier
            .clip(RoundedCornerShape(50))
            .padding(8.dp)
            .size(64.dp) , contentScale = ContentScale.Crop)

}

@Composable
fun DogInformation(@StringRes dogName:Int, dogAge: Int, modifier: Modifier=Modifier){
    Column() {
        Text(text = stringResource(id = dogName),modifier =modifier.padding(8.dp), color = MaterialTheme.colors.onSurface)
        
Text(text = stringResource(R.string.years_old,dogAge), color = MaterialTheme.colors.onSurface )
    }

}
@Composable
fun WoofTopAppBar(modifier: Modifier = Modifier) {
    Row(modifier = modifier.background(color = MaterialTheme.colors.primary).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(R.drawable.ic_woof_logo),
            contentDescription = null, modifier = Modifier.size(64.dp).padding(8.dp)
        )
        Text(
            text = stringResource(R.string.app_name)
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Woof_Compose_AppTheme {
     WoofApp( )
    }
}
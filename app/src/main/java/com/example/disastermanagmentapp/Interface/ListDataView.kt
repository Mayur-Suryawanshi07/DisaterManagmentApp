package com.example.disastermanagmentapp.Interface

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disastermanagmentapp.R

@Composable
fun listOfDisaster(Country:String , Desc:String) {
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(20.dp),
    ) {
        Row (
            modifier=Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Image(painter = painterResource(id = R.drawable.cyclone),
                contentDescription = "Cyclone",
                Modifier.size(100.dp))
            Column(
                modifier = Modifier.padding(6.dp),
            ) {
                Text(text = Country, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.padding(5.dp))

                Text(text = Desc,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Light)


            }
        }
    }
}

@Composable
fun disaterUi():MutableList<Disaster> {
    val listOfitems= mutableListOf<Disaster>()
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))
    listOfitems.add(Disaster("India","Disaster is very serious here and kinda of hard"))

    return listOfitems
    
}

data class Disaster(
    val country:String,
    val desc: String
)
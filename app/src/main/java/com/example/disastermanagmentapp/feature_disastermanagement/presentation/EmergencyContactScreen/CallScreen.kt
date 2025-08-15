package com.example.disastermanagmentapp.feature_disastermanagement.presentation.EmergencyContactScreen

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract.Contacts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.disastermanagmentapp.R

@Preview(showSystemUi = true)
@Composable
fun EmergencyCallList(modifier: Modifier = Modifier) {
    val items = listOf(
        EmergencyItems(R.drawable.police, "Call Police"),
        EmergencyItems(R.drawable.docter, "Call Doctor"),
        EmergencyItems(R.drawable.ambulance, "Call Ambulance"),
        EmergencyItems(R.drawable.fire, "Call Fire Service"),
        EmergencyItems(R.drawable.weater_forecast, "Check Weather"),
        EmergencyItems(R.drawable.first_aid, "First Aid Instruction"),
        EmergencyItems(R.drawable.tips_logo, "Safety tips"),
        EmergencyItems(R.drawable.national_agency_logo, "National Disaster Management Authority"),
        EmergencyItems(R.drawable.report_logo2, "Report A Disaster")

    )

    Scaffold (modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp)
    ){
        val state = rememberLazyGridState()
        Column(
            modifier = Modifier.padding(bottom = 80.dp)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "Emergency Call List",
                color = Color.Red,
                fontSize = 20.sp,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            )
            LazyVerticalGrid(
                state=state,
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)

            ) {
                itemsIndexed(items) { _, item ->
                    EmergencyItemCard(item.Img, item.Name) // Call the composable function
                }
            }
        }
    }
}

@Composable
fun EmergencyItemCard(img: Int, name: String) { // Composable function for each item
    val context= LocalContext.current
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(4.dp),
        onClick = {
            when (name) {
                "Call Police", "Call Ambulance", "Call Fire Service" ->
                    makeCall(context, getPhoneNumber(name))
                "Medical tips" ->{
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.ready.gov/home-fires"))
                    context.startActivity(intent)

                }
                "Check Weather" ->{
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=current+weather&oq=current+weather+&gs_lcrp=EgZjaHJvbWUyBggAEEUYOdIBBzI1NGowajeoAg-wAgE&client=ms-android-xiaomi-rvo2b&sourceid=chrome-mobile&ie=UTF-8"))
                    context.startActivity(intent)

                }
                "First Aid Instruction" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.verywellhealth.com/basic-first-aid-procedures-1298578"))
                    context.startActivity(intent)

                }
                "Safety tips" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ddma.delhi.gov.in/ddma/safety-tips-1"))
                    context.startActivity(intent)
                }
                "Call Doctor"->{
                    val getNumber= Intent(Intent.ACTION_VIEW, Uri.parse(Contacts.CONTENT_URI.toString()))
                    context.startActivity(getNumber)
                }
                "Report A Disaster" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ndma.gov.in/Response/Emergency-Operations-Center"))
                    context.startActivity(intent)
                }
                "National Disaster Management Authority" ->{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ndma.gov.in/"))
                    context.startActivity(intent)
                }

            }

        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = name,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.FillHeight
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

private fun getPhoneNumber(name: String): String =
    when (name) {
        "Call Police" -> "100" // Replace with actual number
        "Call Doctor" -> " "
        "Call Ambulance" -> "102"
        "Call Fire Service" -> "101"
        else -> "" // Or throw IllegalArgumentException("Invalid emergency contact: $name")
    }

private fun makeCall(context: Context, phoneNumber: String) {
    if (phoneNumber.isNotEmpty()) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
        context.startActivity(intent)
    }
}

data class EmergencyItems(
    val Img: Int,
    val Name: String
)

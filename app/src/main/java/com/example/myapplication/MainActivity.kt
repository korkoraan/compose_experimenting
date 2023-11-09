package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }

    }
}

val reviews = listOf(
    ReviewData("Gabe Newell", R.drawable.profile_pic_1,
        "I hate this game", "February 14 2019"),
    ReviewData("YOLOPlayer2007", R.drawable.profile_pic_2,
        "Best game ever!!", "November 15 2022"),
    ReviewData("Ernest Halimov", R.drawable.profile_pic_3,
        "Never played it, must be good tho", "February 14 2019"),
)
@Composable
fun Main() {
    Box {
        Column (modifier = Modifier.background(color = Color.Black)) {
            Box(modifier = Modifier.fillMaxWidth()) {
                TopImage()
            }
            Card (
                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.screen_background)),
                modifier = Modifier.fillMaxHeight()
            )
            {
                LazyColumn(modifier = Modifier.padding(top = 75.dp)) {
                    item {
                        Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)) {
                            Tags("MULTIPLAYER", "CLICKER", "PSYCHOLOGICAL HORROR", "COMMUNITY")
                        }
                    }
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 15.dp)
                        ) {
                            Description()
                        }
                    }

                    val ids = arrayOf(R.drawable.selector_img_1, R.drawable.selector_img_2,
                        R.drawable.selector_img_3, R.drawable.selector_img_4)
                    item {
                        Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(horizontal = 10.dp)
                    )
                        { ImageSelector(ids = ids) }
                    }

                    item { Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)) { ReviewAndRatingsSection()} }

                    items(reviews) {
                        review -> Review(data = review)
                    }

                    item {
                        Box( contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)) {
                            InstallBtn()
                        }
                    }
                }
            }
        }
        Box (modifier = Modifier.offset(x = 15.dp, y = 100.dp)) { LogoSection() }
    }
}

@Composable
fun TopImage() {
    Image (
        painter = painterResource(id = R.drawable.top_image),
        contentDescription = stringResource(id = R.string.top_image_content_desc),
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LogoSection() {
    Row {
        OutlinedCard (
            modifier = Modifier
                .size(width = 88.dp, height = 95.dp)
                .shadow(elevation = 100.dp, spotColor = Color.Black)
        )
        {
            Image (
                painter = painterResource(id = R.drawable.dota_logo),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
            )
        }
        Column (
            modifier = Modifier
                .offset(x = 10.dp)
                .align(Alignment.Bottom)
        ){
            Text(text ="DoTA 2", color = Color.White)
            Row {
                Box(modifier = Modifier.size(height = 30.dp, width = 75.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.rating),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = "70M",
                    color = Color.LightGray,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .offset(x = 5.dp)
                )
            }
        }
    }
}

@Composable
fun Tags(vararg tags: String) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        items(tags) {
            tag -> Card (
                colors = CardDefaults.cardColors(containerColor = colorResource(R.color.tag_background).copy(alpha = 0.24f)),
                modifier = Modifier.clip(RoundedCornerShape(22.dp))
            ){
                Text(
                    text = tag,
                    color = colorResource(id = R.color.tag_text),
                    fontSize = 10.sp,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp, 5.dp))
            }
        }
    }
}

@Composable
fun Description() {
        Text(
            text = stringResource(id = R.string.text),
            color = colorResource(id = R.color.text),
            fontSize = 12.sp,
            lineHeight = 19.sp
        )
}

@Composable
fun SelectorItem(preview_id: Int) {
    Box(contentAlignment = Alignment.Center) {
        Card(shape = RoundedCornerShape(5.dp), modifier = Modifier.size(240.dp, 128.dp)) {
            Image(
                painter = painterResource(id = preview_id),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )
        }
        Box(modifier = Modifier.size(40.dp, 40.dp)) {
            Image(
                painter = painterResource(id = R.drawable.play_button),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun ImageSelector(ids: Array<Int>) {
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(ids) {
            id -> SelectorItem(id)
        }
    }
}

@Composable
fun InstallBtn() {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.install_btn)),
        modifier = Modifier
            .size(width = 327.dp, height = 70.dp)
    ) {
        Text(stringResource(id = R.string.install_btn_text), color = colorResource(id = R.color.install_btn_text))
    }
}

data class ReviewData(
    val name: String,
    val idPicture: Int,
    val text: String,
    val date: String,
)

@Composable
fun Review(data: ReviewData) {
    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp)
    ) {
       Row {
            Image(
                painter =  painterResource(id = data.idPicture),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
           Column (
               modifier = Modifier.padding(horizontal = 10.dp)
           ){
               Text(text = data.name, color = Color.White)
               Text(text = data.date, color = Color.LightGray, fontSize = 10.sp)
           }
       }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "\"" + data.text + "\"",
                color = colorResource(id = R.color.text),
                fontSize = 15.sp,
                modifier = Modifier
            )
        }
        Divider(
            color = colorResource(id = R.color.divider),
            modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp)
        )
    }
}

@Composable
fun ReviewAndRatingsSection() {
    Column {
        Text(text = "Review & Ratings", color = Color.White)
        Row {
            Text(text = "4.9", color = Color.White, fontSize = 40.sp)
            Column (
                modifier = Modifier
                    .offset(x = 10.dp, y = 0.dp)
                    .align(Alignment.Bottom)
            ) {
                Box(modifier = Modifier.size(height = 30.dp, width = 75.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.rating),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds
                    )
                }
                Text(
                    text = "70M Reviews",
                    color = colorResource(id = R.color.text),
                    fontSize = 11.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Main()
    }
}

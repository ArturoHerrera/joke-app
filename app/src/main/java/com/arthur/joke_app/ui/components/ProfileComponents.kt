package com.arthur.joke_app.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.arthur.joke_app.R
import com.arthur.joke_app.ui.theme.QuickSand
import com.arthur.joke_app.ui.theme.SuperWhite
import com.arthur.joke_app.utils.ExtFunctions.gradientBackground
import kotlin.random.Random

@Composable
fun HomeProfileDrawer(
    userDisplayName: String,
    userPhotoUrl:String,
    onLogOut: () -> Unit
){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground(
                listOf(
                    Color(
                        Random.nextInt(1, 256),
                        Random.nextInt(1, 256),
                        Random.nextInt(1, 256)
                    ), Color(
                        Random.nextInt(1, 256),
                        Random.nextInt(1, 256),
                        Random.nextInt(1, 256)
                    )
                ),
                angle = 45f
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = stringResource(id = R.string.profile_welcome),
                fontFamily = QuickSand,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp,
                style = MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        color = colorResource(id = R.color.black),
                        offset = Offset(x = 2f, y = 4f),
                        blurRadius = 0.1f
                    )
                ),
                color = SuperWhite
            )
            Spacer(modifier = Modifier.padding(24.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userPhotoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_user_placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Black, CircleShape)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = userDisplayName,
                fontFamily = QuickSand,
                fontWeight = FontWeight.Black,
                fontSize = 32.sp,
                style = MaterialTheme.typography.h4.copy(
                    shadow = Shadow(
                        color = colorResource(id = R.color.black),
                        offset = Offset(x = 2f, y = 4f),
                        blurRadius = 0.1f
                    )
                ),
                color = SuperWhite
            )
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth().padding(vertical = 32.dp, horizontal = 16.dp)
        ){
            OutlinedButton(
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                    disabledContentColor = Color.LightGray
                ),
                elevation = ButtonDefaults.elevation(8.dp),
                shape = RoundedCornerShape(6.dp),
                onClick = onLogOut
            ) {
                Image(
                    imageVector = Icons.Filled.Logout,
                    contentDescription = null
                )
                Text(
                    text = stringResource(id = R.string.profile_logout),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 6.dp),
                    fontFamily = QuickSand,
                    fontWeight = FontWeight.Black,
                    fontSize = 18.sp
                )
            }
        }
    }

}
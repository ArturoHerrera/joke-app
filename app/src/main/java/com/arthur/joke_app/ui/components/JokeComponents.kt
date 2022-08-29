package com.arthur.joke_app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arthur.joke_app.R
import com.arthur.joke_app.data.model.JokeType
import com.arthur.joke_app.data.remote.dto.JokeResponse
import com.arthur.joke_app.ui.theme.QuickSand
import com.arthur.joke_app.ui.theme.SuperWhite

@Composable
fun JokeComponent(
    joke: JokeResponse
) {
    when (joke.type) {
        JokeType.single.name -> JokeSingleComponent(joke = joke)
        JokeType.twopart.name -> JokeTwoPartsComponent(joke = joke)
    }
}

@Composable
fun JokeSingleComponent(
    joke: JokeResponse
) {
    joke.joke?.let { safeJoke ->
        Text(
            text = safeJoke,
            style = MaterialTheme.typography.h5.copy(
                shadow = Shadow(
                    color = colorResource(id = R.color.black),
                    offset = Offset(x = 2f, y = 4f),
                    blurRadius = 0.1f
                )
            ),
            fontFamily = QuickSand,
            color = SuperWhite,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun JokeTwoPartsComponent(
    joke: JokeResponse
) {
    joke.setup?.let { safeJokeSetup ->
        Text(
            text = safeJokeSetup,
            style = MaterialTheme.typography.h5.copy(
                shadow = Shadow(
                    color = colorResource(id = R.color.black),
                    offset = Offset(x = 2f, y = 4f),
                    blurRadius = 0.1f
                )
            ),
            fontFamily = QuickSand,
            color = SuperWhite,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
    Spacer(modifier = Modifier.padding(16.dp))
    joke.delivery?.let { safeJokeDelivery ->
        Text(
            text = safeJokeDelivery,
            style = MaterialTheme.typography.h5.copy(
                shadow = Shadow(
                    color = colorResource(id = R.color.black),
                    offset = Offset(x = 2f, y = 4f),
                    blurRadius = 0.1f
                )
            ),
            fontFamily = QuickSand,
            color = SuperWhite,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun ShareJokeComponent(
    joke: JokeResponse,
    onShareClicked: (joke: JokeResponse) -> Unit
) {
    Spacer(modifier = Modifier.padding(16.dp))
    Divider(
        color = SuperWhite,
        modifier = Modifier
            .fillMaxWidth()
            .width(1.dp)
    )
    Spacer(modifier = Modifier.padding(16.dp))
    Icon(
        imageVector = Icons.Filled.Share,
        contentDescription = null,
        tint = SuperWhite,
        modifier = Modifier
            .requiredSize(35.dp)
            .fillMaxWidth()
            .clickable { onShareClicked(joke) }
    )
}
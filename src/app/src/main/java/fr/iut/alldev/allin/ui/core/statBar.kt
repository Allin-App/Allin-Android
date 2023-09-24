package fr.iut.alldev.allin.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.ui.theme.AllInTheme

@Composable
fun StatBar(
    percentage: Float
) {
    val radius100percent = if(percentage==1f) 50 else 0
    val radius0percent = if(percentage==0f) 50 else 0
    Box(
        Modifier.padding(horizontal = 9.dp)
    ){
        Row(
            Modifier.align(Alignment.Center)
        ){
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth(percentage)
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = 50,
                            bottomLeftPercent = 50,
                            topRightPercent = radius100percent,
                            bottomRightPercent = radius100percent
                        )
                    )
                    .background(AllInTheme.colors.allIn_Bar1stGradient)

            ){
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 15.dp),
                    text = "OUI",
                    style = AllInTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    fontSize = 25.sp,
                    color = Color.White.copy(alpha = 0.3f),
                )
            }
            if(percentage!=0f && percentage!=1f) {
                Spacer(modifier = Modifier.width(15.dp))
            }
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .fillMaxWidth()
                    .clip(
                        AbsoluteRoundedCornerShape(
                            topLeftPercent = radius0percent,
                            bottomLeftPercent = radius0percent,
                            topRightPercent = 50,
                            bottomRightPercent = 50
                        )
                    )
                    .background(AllInTheme.colors.allIn_Bar2ndGradient)
            )
        }
        Box(
            Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        ) {
            when (percentage) {
                0f -> {
                    Icon(
                        painter = painterResource(id = R.drawable.fire_solid),
                        tint = AllInTheme.colors.allIn_BarPink,
                        contentDescription = null,
                        modifier = Modifier
                            .size(32.dp)
                    )
                }
                1f -> {
                    Icon(
                        painter = painterResource(id = R.drawable.fire_solid),
                        tint = AllInTheme.colors.allIn_BarPurple,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .size(32.dp)
                    )
                }
                else -> {
                    Row {
                        Spacer(modifier = Modifier.fillMaxWidth(percentage))
                        Image(
                            painter = painterResource(id = R.drawable.bar_flame),
                            contentDescription = null,
                            modifier = Modifier
                                .size(32.dp)
                                .offset(x = (-9).dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun StatBar0Preview() {
    AllInTheme {
        StatBar(percentage = 0f)
    }
}

@Preview
@Composable
private fun StatBar33Preview() {
    AllInTheme {
        StatBar(percentage = 0.33f)
    }
}

@Preview
@Composable
private fun StatBar50Preview() {
    AllInTheme {
        StatBar(percentage = 0.5f)
    }
}

@Preview
@Composable
private fun StatBar66Preview() {
    AllInTheme {
        StatBar(percentage = 0.66f)
    }
}

@Preview
@Composable
private fun StatBar100Preview() {
    AllInTheme {
        StatBar(percentage = 1f)
    }
}
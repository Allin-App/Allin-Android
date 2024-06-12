package fr.iut.alldev.allin.ui.core.bet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.iut.alldev.allin.R
import fr.iut.alldev.allin.theme.AllInTheme

@Composable
fun AllInEmptyView(
    image: Painter,
    text: String,
    subtext: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .alpha(.35f)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(.5f)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = AllInTheme.typography.h2,
            color = AllInTheme.colors.onMainSurface,
            fontSize = 16.sp
        )

        subtext?.let {
            Text(
                text = subtext,
                textAlign = TextAlign.Center,
                style = AllInTheme.typography.p1,
                color = AllInTheme.colors.onBackground2,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllInEmptyViewPreview() {
    AllInTheme {
        AllInEmptyView(
            text = "C'est un peu vide par ici",
            subtext = "Ajoutez des amis pour les afficher dans le classement, et voir qui est le meilleur !",
            image = painterResource(id = R.drawable.eyes),
            modifier = Modifier.fillMaxSize()
        )
    }
}
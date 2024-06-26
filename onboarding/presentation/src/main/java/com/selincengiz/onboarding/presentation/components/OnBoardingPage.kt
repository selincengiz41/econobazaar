package com.selincengiz.onboarding.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.selincengiz.onboarding.presentation.Page
import com.selincengiz.onboarding.presentation.pages


@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    Column(modifier = modifier) {

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = page.title),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = com.selincengiz.core.common.R.color.display_small)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = page.description),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = com.selincengiz.core.common.R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun OnBoardingPagePreview() {
    OnBoardingPage(page = pages[0])
}

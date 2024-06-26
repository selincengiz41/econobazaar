package com.selincengiz.login.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LoginScreen(viewModel: LoginViewModel, navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.selincengiz.core.common.R.raw.logov))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    val loginState by viewModel.state.collectAsState()

    LaunchedEffect(loginState) {
        if (loginState == LoginState.Success) {
            navController.navigate(com.selincengiz.home.presentation.R.id.home_nav_graph)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = com.selincengiz.core.common.R.color.gray50))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier.size(128.dp)) {
            LottieAnimation(
                composition = composition,
                progress = { progress }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = com.selincengiz.core.common.R.string.login_title),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = com.selincengiz.core.common.R.color.display_small)
        )

        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = {
                Text(
                    stringResource(id = com.selincengiz.core.common.R.string.email),
                    color = colorResource(id = com.selincengiz.core.common.R.color.gray900)
                )
            }, modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colorResource(id = com.selincengiz.core.common.R.color.gray900),
                focusedBorderColor = colorResource(id = com.selincengiz.core.common.R.color.gray900),
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text(
                    stringResource(id = com.selincengiz.core.common.R.string.password),
                    color = colorResource(id = com.selincengiz.core.common.R.color.gray900)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = colorResource(id = com.selincengiz.core.common.R.color.gray900),
                focusedBorderColor = colorResource(id = com.selincengiz.core.common.R.color.gray900),
                unfocusedBorderColor = Color.Gray
            )
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                viewModel.onEvent(LoginEvent.Logined(userName, password))
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = com.selincengiz.core.common.R.color.gray900))
        ) {
            Text(
                stringResource(id = com.selincengiz.core.common.R.string.login),
                color = colorResource(id = com.selincengiz.core.common.R.color.white)
            )
        }
    }
}
package com.seo4d696b75.android.splashscreendemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.seo4d696b75.android.splashscreendemo.ui.theme.SplashScreenDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Activityが描画可能になってもスプラッシュ画面を強制的に1秒間表示する
        var isSplashShown = true
        splashScreen.setKeepOnScreenCondition { isSplashShown }
        lifecycleScope.launch {
            delay(1000L)
            isSplashShown = false
        }

        setContent {
            SplashScreenDemoTheme {
                MainScreen(
                    modifier = Modifier.fillMaxSize(),
                    onRestart = {
                        val intent = Intent(this, MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        }
                        startActivity(intent)
                    },
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    onRestart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            MainSection(
                onRestart = onRestart,
            )
        }
    }
}

@Composable
fun MainSection(
    onRestart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Hello Splash Activity!",
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onRestart) {
            Text(text = "Restart")
        }
    }
}

@Preview
@Composable
private fun PreviewMainScreen() {
    MainScreen(
        onRestart = {},
    )
}
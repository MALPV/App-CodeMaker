package cl.inventionchile.codemaker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import cl.inventionchile.codemaker.ui.theme.CodeMakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodeMakerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                }
            }
        }
    }
}
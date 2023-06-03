package com.example.sarajevotransitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sarajevotransitapp.ui.theme.theme.SarajevoTransitAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*setContent {
            SarajevoTransitAppTheme {
                staApp()
                }*/
            }
        }


/*@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SarajevoTransitAppTheme {
        Greeting("Android")
    }
}*/
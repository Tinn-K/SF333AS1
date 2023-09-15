package th.ac.tu.dome.tj.sf333_as1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import th.ac.tu.dome.tj.sf333_as1.ui.theme.SF333_AS1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SF333_AS1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Scaffold(topBar = { TopAppBar() }) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(modifier = Modifier.padding(innerPadding)) {
                Text(
                    "Try to guess the number I'm thinking of from 1 - 1000!",

                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 1.em

                )

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth().padding(bottom=100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GuessTextField()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val rnds by remember { mutableStateOf((0..1000).random()) }
    var count by remember { mutableStateOf(0) }
    var guessText by remember { mutableStateOf("") }

    fun guess(number: Int) {
        if (number == rnds) {
            guessText = "Congrats, you got it in $count tries."
            return
        }

        guessText = if (number > rnds) {
            "Hint: It's lower!"
        } else {
            "Hint: It's higher!"
        }

        count++
    }

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        placeholder = { Text(text = "Your Guess", textAlign = TextAlign.Center) },
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.NumberPassword
        ),

        )
    Text(guessText)
    Button(onClick = { guess(text.text.toInt()) }) {
        Text("Play Again")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SF333_AS1Theme {
        App()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row {
                Text(
                    "Number Guessing Game",
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold
                )
            }
        }, modifier = modifier, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )

    )
}
package ru.ilimurzin.daysuntilnewyear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ru.ilimurzin.daysuntilnewyear.ui.theme.Theme
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Counter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    val now = LocalDateTime.now()
    val newYear = LocalDateTime.of(now.year + 1, 1, 1, 0, 0) // Next January 1st 00:00
    val daysUntilNewYear = ChronoUnit.DAYS.between(now, newYear)

    if (daysUntilNewYear > 0) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = daysUntilNewYear.toString(),
                fontSize = 48.sp,
            )
            Text(
                text = "days until New Year",
                fontSize = 24.sp,
            )
        }
    } else {
        val hoursUntilNewYear = ChronoUnit.HOURS.between(now, newYear)

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = hoursUntilNewYear.toString(),
                fontSize = 48.sp,
            )
            Text(
                text = "hours until New Year",
                fontSize = 24.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Theme {
        Counter()
    }
}

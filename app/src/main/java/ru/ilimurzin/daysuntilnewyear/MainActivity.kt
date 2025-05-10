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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
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
                    NowCounter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun NowCounter(
    modifier: Modifier = Modifier,
) {
    var now by remember { mutableStateOf(LocalDateTime.now()) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            now = LocalDateTime.now()
        }
    }

    Countdown(
        now = now,
        modifier = modifier,
    )
}

@Composable
fun Countdown(
    now: LocalDateTime,
    modifier: Modifier = Modifier,
) {
    if (now.dayOfYear == 1) {
        return Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "New Year!",
                fontSize = 48.sp,
            )
        }
    }

    val newYear = LocalDateTime.of(now.year + 1, 1, 1, 0, 0) // Next January 1st 00:00
    val daysUntilNewYear = ChronoUnit.DAYS.between(now, newYear)
    val hoursUntilNewYear = ChronoUnit.HOURS.between(now, newYear)
    val minutesUntilNewYear = ChronoUnit.MINUTES.between(now, newYear)
    val secondsUntilNewYear = ChronoUnit.SECONDS.between(now, newYear)

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (daysUntilNewYear > 0) {
            CountdownUnit(
                daysUntilNewYear.toString(),
                if (daysUntilNewYear == 1L) "day" else "days",
            )
        }

        if (hoursUntilNewYear > 0) {
            val hours = secondsUntilNewYear % 86400 / 3600

            CountdownUnit(
                hours.toString(),
                if (hours == 1L) "hour" else "hours",
            )
        }

        if (minutesUntilNewYear > 0) {
            val minutes = secondsUntilNewYear % 3600 / 60

            CountdownUnit(
                minutes.toString(),
                if (minutes == 1L) "minute" else "minutes",
            )
        }

        val seconds = secondsUntilNewYear % 60

        CountdownUnit(
            seconds.toString(),
            if (seconds == 1L) "second" else "seconds",
        )

        Text(
            text = " until New Year",
            fontSize = 24.sp,
        )
    }
}

@Composable
fun CountdownUnit(
    number: String,
    label: String,
) {
    Text(
        text = number,
        fontSize = 48.sp,
    )
    Text(
        text = label,
        fontSize = 24.sp,
        modifier = Modifier.padding(bottom = 24.dp),
    )
}

@Preview(showBackground = true)
@Composable
fun NowPreview() {
    Theme {
        Countdown(LocalDateTime.now())
    }
}

@Preview(showBackground = true)
@Composable
fun TwoDaysBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 29, 23, 0, 33))
    }
}

@Preview(showBackground = true)
@Composable
fun OneDayBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 30, 23, 0, 33))
    }
}

@Preview(showBackground = true)
@Composable
fun TwoHoursBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 22, 0, 33))
    }
}

@Preview(showBackground = true)
@Composable
fun OneHourBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun TwoMinutesBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 58))
    }
}

@Preview(showBackground = true)
@Composable
fun OneMinuteBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59))
    }
}

@Preview(showBackground = true)
@Composable
fun LessThanAMinuteBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59, 43))
    }
}

@Preview(showBackground = true)
@Composable
fun BetweenOneAndTwoSecondsBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59, 58, 1))
    }
}

@Preview(showBackground = true)
@Composable
fun LessThanASecondBeforePreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59, 59, 1))
    }
}

@Preview(showBackground = true)
@Composable
fun NewYearPreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 1, 1, 0, 0, 33))
    }
}

@Preview(showBackground = true)
@Composable
fun January1stPreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 1, 1, 14, 50, 33))
    }
}

@Preview(showBackground = true)
@Composable
fun January2ndPreview() {
    Theme {
        Countdown(LocalDateTime.of(LocalDateTime.now().year, 1, 2, 14, 50, 33))
    }
}

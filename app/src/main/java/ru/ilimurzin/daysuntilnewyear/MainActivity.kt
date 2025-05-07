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
                        now = LocalDateTime.now(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Counter(
    now: LocalDateTime,
    modifier: Modifier = Modifier,
) {
    val newYear = LocalDateTime.of(now.year + 1, 1, 1, 0, 0) // Next January 1st 00:00
    val daysUntilNewYear = ChronoUnit.DAYS.between(now, newYear)

    if (daysUntilNewYear == 365L) {
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

    val number: String
    val description: String

    if (daysUntilNewYear > 0) {
        number = daysUntilNewYear.toString()
        description = if (daysUntilNewYear == 1L) "day" else "days"
    } else {
        val hoursUntilNewYear = ChronoUnit.HOURS.between(now, newYear)

        if (hoursUntilNewYear > 0) {
            number = hoursUntilNewYear.toString()
            description = if (hoursUntilNewYear == 1L) "hour" else "hours"
        } else {
            val minutesUntilNewYear = ChronoUnit.MINUTES.between(now, newYear)

            if (minutesUntilNewYear > 0) {
                number = minutesUntilNewYear.toString()
                description = if (minutesUntilNewYear == 1L) "minute" else "minutes"
            } else {
                val secondsUntilNewYear = ChronoUnit.SECONDS.between(now, newYear)

                number = secondsUntilNewYear.toString()
                description = if (secondsUntilNewYear == 1L) "second" else "seconds"
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = number,
            fontSize = 48.sp,
        )
        Text(
            text = description,
            fontSize = 24.sp,
        )
        Text(
            text = " until New Year",
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NowPreview() {
    Theme {
        Counter(LocalDateTime.now())
    }
}

@Preview(showBackground = true)
@Composable
fun TwoDaysBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 29, 23, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun OneDayBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 30, 23, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun TwoHoursBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 22, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun OneHourBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun TwoMinutesBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 58))
    }
}

@Preview(showBackground = true)
@Composable
fun OneMinuteBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59))
    }
}

@Preview(showBackground = true)
@Composable
fun TwoSecondsBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59, 58))
    }
}

@Preview(showBackground = true)
@Composable
fun OneSecondBeforePreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 12, 31, 23, 59, 59))
    }
}

@Preview(showBackground = true)
@Composable
fun NewYearPreview() {
    Theme {
        Counter(LocalDateTime.of(LocalDateTime.now().year, 1, 1, 0, 0))
    }
}

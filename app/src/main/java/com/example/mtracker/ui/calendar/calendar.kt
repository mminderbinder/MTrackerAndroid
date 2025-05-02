import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mtracker.R
import java.time.LocalDate

@Composable
fun DayContent(
    day: LocalDate,
    isToday: Boolean,
    isOutOfMonth: Boolean,
    mood: Int?,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(2.dp)
            .clip(CircleShape)
            .border(
                width = 2.dp,
                color = if (isToday) MaterialTheme.colorScheme.primary else Color.Transparent,
                shape = CircleShape
            )
            .clickable(
                enabled = isEnabled && !isOutOfMonth,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        if(isOutOfMonth) {
            Text(
                text = day.dayOfMonth.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
            )
        } else if (mood != null) {
            Icon(
                painter = painterResource(id = getMoodDrawableResource(mood)),
                contentDescription = "Mood level $mood",
                modifier = Modifier.fillMaxSize(0.9f),
                tint = Color.Unspecified
            )
        } else if (isEnabled) {
            Text(
                text = day.dayOfMonth.toString(),
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.add_24),
                contentDescription = "Add mood",
                modifier = Modifier.fillMaxSize(0.7f),
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = day.dayOfMonth.toString(),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                textAlign = TextAlign.Center
            )
        }
    }
}

fun getMoodDrawableResource(mood: Int): Int {
    return when (mood) {
        -4 -> R.drawable.low4
        -3 -> R.drawable.low3
        -2 -> R.drawable.low2
        -1 -> R.drawable.low1
        0 -> R.drawable.neutral0
        1 -> R.drawable.high1
        2 -> R.drawable.high2
        3 -> R.drawable.high3
        4 -> R.drawable.high4
        else -> R.drawable.neutral0
    }
}


@Preview(showBackground = true)
@Composable
fun DayContentWithAddIconPreview() {
    Surface {
        DayContent(
            day = LocalDate.now(),
            isToday = true,
            isOutOfMonth = false,
            mood = null,
            isEnabled = true,
            onClick = {}
        )
    }
}
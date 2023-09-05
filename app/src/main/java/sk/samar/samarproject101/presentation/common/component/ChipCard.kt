package sk.samar.samarproject101.presentation.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChipCard(
    modifier: Modifier = Modifier,
    text: Int,
    isSelectedItem: (Int) -> Boolean,
    onChangeState: (Int) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(width = .4.dp, color = MaterialTheme.colorScheme.inverseSurface),
        colors = if (isSelectedItem(text)) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary) else CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Text(
            modifier = modifier
                .padding(vertical = 6.dp, horizontal = 18.dp)
                .selectable(
                    selected = isSelectedItem(text),
                    onClick = {
                        onChangeState(text)
                    },
                    role = Role.RadioButton
                ),
            text = stringResource(id = text),
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
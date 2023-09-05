package sk.samar.samarproject101.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun DropDownSystem(
    expanded: Boolean = false, list: List<Int>, onSelect: (Int?) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        DropdownMenu(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            expanded = expanded,
            onDismissRequest = {
            onSelect(null)
        }) {
            list.forEach {
                DropdownMenuItem(onClick = {
                    onSelect(it)
                }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = it),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

}
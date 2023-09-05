package sk.samar.samarproject101.presentation.explore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sk.samar.samarproject101.R

class ExploreViewModel : ViewModel() {

    var search by mutableStateOf("")
    var selectedTabList = listOf(
        R.string.Personal,
        R.string.Business,
        R.string.Merchant
    )
    var selectedTab by mutableStateOf(selectedTabList.first())


}
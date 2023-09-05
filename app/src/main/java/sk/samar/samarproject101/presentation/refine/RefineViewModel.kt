package sk.samar.samarproject101.presentation.refine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import sk.samar.samarproject101.R

class RefineViewModel : ViewModel() {

    var availabilityList = listOf(
        R.string.AvailableHeyLetUsConnect,
        R.string.AwayStayDiscreteAndWatch,
        R.string.BusyDonotDisturbWillCatchUpLater,
        R.string.SOSEmergencyNeedAssistanceHELP
    )

    var purposedList = listOf(
        R.string.Coffee,
        R.string.Business,
        R.string.Hobbies,
        R.string.Friendship,
        R.string.Movies,
        R.string.Dinning,
        R.string.Dating,
        R.string.Matrimony
    )

    var yourStatus by mutableStateOf("Hi community! I am open to new connections \"\uD83D\uDE0A\"")
    var availabilityDropDown by mutableStateOf(false)
    var selectedAvailability by mutableIntStateOf(availabilityList.first())

    var selectedPurposeLists = mutableStateMapOf<Int, Int>()


}
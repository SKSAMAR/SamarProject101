package sk.samar.samarproject101.presentation.refine

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.*
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sk.samar.samarproject101.R
import sk.samar.samarproject101.presentation.common.component.ChipCard
import sk.samar.samarproject101.presentation.explore.ExploreActivity
import sk.samar.samarproject101.presentation.ui.theme.SamarProject101Theme
import sk.samar.samarproject101.util.DropDownSystem

class RefineActivity : ComponentActivity() {

    private val viewModel by viewModels<RefineViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamarProject101Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    RefineScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Composable
    private fun RefineScreen() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.refine))
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                onBackPressedDispatcher.onBackPressed()
                            }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .rotate(90f)
                                    .size(32.dp),
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        navigationIconContentColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.surface
                    )
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.SelectYourAvailability),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .border(
                                    width = .14.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable(
                                    indication = null,
                                    interactionSource = MutableInteractionSource()
                                ) {
                                    viewModel.availabilityDropDown = !viewModel.availabilityDropDown
                                },
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 12.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = viewModel.selectedAvailability),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 14.sp,
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null
                                )
                            }
                        }
                        if (viewModel.availabilityDropDown) {
                            DropDownSystem(
                                list = viewModel.availabilityList,
                                expanded = viewModel.availabilityDropDown,
                                onSelect = {
                                    viewModel.availabilityDropDown = false
                                    it?.let {
                                        viewModel.selectedAvailability = it
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = stringResource(id = R.string.AddYourStatus),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier
                                .border(
                                    width = .14.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                value = viewModel.yourStatus,
                                onValueChange = {
                                    if (viewModel.yourStatus.length <= 250) {
                                        viewModel.yourStatus = it
                                    }
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    textColor = MaterialTheme.colorScheme.primary,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    containerColor = Color.Transparent
                                ),
                                textStyle = TextStyle(fontSize = 15.sp)
                            )
                        }


                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = stringResource(id = R.string.SelectHyperlocalDistance),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                item {
                    SeekBarWithValue()
                }

                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {

                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = stringResource(id = R.string.SelectPurpose),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        FlowRow(
                            modifier = Modifier.padding(vertical = 2.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
                            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
                            maxItemsInEachRow = 4
                        ) {
                            viewModel.purposedList.forEach { titles ->
                                ChipCard(
                                    text = titles, onChangeState = {
                                        if (viewModel.selectedPurposeLists.contains(it)) {
                                            viewModel.selectedPurposeLists.remove(it)
                                        } else {
                                            viewModel.selectedPurposeLists[it] = it
                                        }
                                    },
                                    isSelectedItem = {
                                        viewModel.selectedPurposeLists.contains(it)
                                    }
                                )
                            }
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(15.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(.45f),
                            onClick = {
                                startActivity(Intent(this@RefineActivity, ExploreActivity::class.java))
                            },
                        ) {
                            Text(text = stringResource(id = R.string.SaveAndExplore))
                        }
                    }
                }


                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }


    @Composable
    fun SeekBarWithValue() {
        var sliderPosition by remember { mutableFloatStateOf(1f) }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(sliderPosition / 109))
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(width = 30.dp, height = 30.dp),
                        painter = painterResource(id = R.drawable.tooltip),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = sliderPosition.toInt().toString(),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Slider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 0.dp),
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 1f..100f
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "1 Km",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = "100 Km",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }

        }
    }

}
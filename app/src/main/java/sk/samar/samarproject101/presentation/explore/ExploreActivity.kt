package sk.samar.samarproject101.presentation.explore

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sk.samar.samarproject101.R
import sk.samar.samarproject101.presentation.common.component.ChipCard
import sk.samar.samarproject101.presentation.ui.theme.SamarProject101Theme
import sk.samar.samarproject101.util.DropDownSystem

class ExploreActivity : ComponentActivity() {

    private val viewModel by viewModels<ExploreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SamarProject101Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface
                ) {
                    ExploreScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
    @Composable
    private fun ExploreScreen() {
        Scaffold(topBar = {
            TopAppBar(title = {
                Column(
                    modifier = Modifier.padding(horizontal = 12.dp)
                ) {
                    Text(
                        text = "${stringResource(id = R.string.Howdy)} Sk Samar !!",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(11.dp),
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null
                        )
                        Text(
                            text = " Home Sweet Home", fontSize = 11.sp
                        )
                    }
                }
            }, navigationIcon = {
                IconButton(onClick = {
                    onBackPressedDispatcher.onBackPressed()
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.Menu,
                        contentDescription = null
                    )
                }
            }, actions = {
                IconButton(onClick = {
                    onBackPressedDispatcher.onBackPressed()
                }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.FilterAlt,
                        contentDescription = null
                    )
                }
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.surface,
                actionIconContentColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.surface
            )
            )
        }) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                item {
                    TextTabsMatched(tabData = viewModel.selectedTabList, onChange = {
                        viewModel.selectedTab = it
                    })
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .weight(1f),
                                value = viewModel.search,
                                onValueChange = {
                                    viewModel.search = it
                                },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Search
                                ),
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Search,
                                        contentDescription = null
                                    )
                                },
                                trailingIcon = {
                                    if (viewModel.search.trim().isNotEmpty()) {
                                        Icon(
                                            modifier = Modifier.clickable(
                                                indication = null,
                                                interactionSource = MutableInteractionSource()
                                            ) {
                                                viewModel.search = ""
                                            },
                                            imageVector = Icons.Default.Clear,
                                            contentDescription = null
                                        )
                                    }
                                },
                                placeholder = {
                                    Text(
                                        text = stringResource(id = R.string.Search),
                                        fontSize = 10.sp
                                    )
                                },
                                textStyle = TextStyle(fontSize = 10.sp),
                                shape = RoundedCornerShape(28.dp),
                            )

                            IconButton(onClick = { }) {
                                Icon(
                                    modifier = Modifier.clickable(
                                        indication = null,
                                        interactionSource = MutableInteractionSource()
                                    ) {

                                    },
                                    imageVector = Icons.Default.FilterList,
                                    contentDescription = null
                                )
                            }
                        }


                        Spacer(modifier = Modifier.height(10.dp))



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

                items(40) {
                    CardContent()
                }


                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }


    @Composable
    private fun TextTabsMatched(
        tabData: List<Int>, onChange: (Int) -> Unit
    ) {
        if (tabData.isNotEmpty()) {
            var tabIndex by remember { mutableStateOf(0) }
            TabRow(
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.surface,
            ) {
                tabData.forEachIndexed { index, text ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                            onChange(text)
                        },
                        text = {
                            Text(
                                text = stringResource(id = text),
                                color = if (index == tabIndex) Color.White else Color.Gray
                            )
                        },
                    )
                }
            }
        }
    }


    @Composable
    private fun CardContent() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "+ INVITE",
                        textAlign = TextAlign.End,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Column(
                        modifier = Modifier.padding(start = 90.dp, top = 4.dp)
                    ) {
                        Text(
                            text = "Sk Samar",
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Kolkata | MCA", textAlign = TextAlign.End,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Within 8.8 KM",
                            textAlign = TextAlign.End,
                            fontWeight = FontWeight.Black,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))

                        Box {

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(.6f)
                                    .clip(shape = RoundedCornerShape(24.dp)),
                                thickness = 18.dp,
                                color = MaterialTheme.colorScheme.primary.copy(.2f)
                            )
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(.35f)
                                    .clip(
                                        shape = RoundedCornerShape(
                                            topStart = 24.dp,
                                            bottomStart = 24.dp
                                        )
                                    ),
                                thickness = 18.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Coffee | Business | Friendship", textAlign = TextAlign.End,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Hi community! \"\uD83D\uDE0A\"", textAlign = TextAlign.End,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }

                }
            }

            Card(
                modifier = Modifier
                    .padding(start = 1.dp, top = 15.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .size(90.dp)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "SS", fontSize = 40.sp)
                }
            }
        }
    }

}
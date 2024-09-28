package com.example.contactapp2.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.contactapp2.ContactAppViewModel
import com.example.contactapp2.database.tables.Contact
import com.example.contactapp2.presentation.navigation.AddEditScreen
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    viewModel: ContactAppViewModel
) {
    var contacts = remember {
        mutableStateOf<List<Contact>>(emptyList())
    }
    var colorBrush = Brush.linearGradient(listOf(Color.LightGray, Color.Magenta))
    LaunchedEffect(key1 = viewModel.db.contactDAO().getAllContacts()) {
        viewModel.db.contactDAO().getAllContacts().collectLatest {
            contacts.value = it
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(AddEditScreen(null)) }) {
                Icon(imageVector = Icons.Rounded.Add, null)
            }
        },

        topBar = {
            TopAppBar(
                title = { Text(text = "Contact APP") },
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn {
                items(contacts.value)
                {
                    Card(modifier = Modifier.padding(10.dp)) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(colorBrush)
                                .clickable {
                                    navController.navigate(
                                        AddEditScreen(
                                            it.id
                                        )
                                    )
                                }
                        )

                        Row(modifier = Modifier.fillMaxSize()) {
                            Text(text = it.name)
                            Text(text = it.number)
                            Text(text = it.email)
                            Spacer(modifier = Modifier.width(10.dp))

                            Icon(imageVector = Icons.Rounded.Delete, contentDescription = null,
                                modifier = Modifier.clickable { viewModel.deleteContact(listOf(it)) })
                        }
                    }
                }
            }
        }
    }
}
package com.brandyodhiambo.quench.views.screens.notification

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brandyodhiambo.quench.views.ui.theme.primaryColor

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotificationScreen() {
    Scaffold(
        backgroundColor = primaryColor,
        topBar = {
            TopAppBar(
                title = { Text(text = "Notification", color = Color.White, fontSize = 16.sp) },
                backgroundColor = primaryColor,
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn() {
                item {

                }
            }
        }
    }
}

@Preview
@Composable
fun AddReminder() {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Add New Reminder",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, tint = Color.Gray, contentDescription = null )
            }
        }
    }
}
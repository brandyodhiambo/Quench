package com.brandyodhiambo.home.presentation.component

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.brandyodhiambo.common.R
import com.brandyodhiambo.designsystem.theme.primaryColor

@Composable
fun CongratulationsDialog(
    openDialogCustom: MutableState<Boolean>,
) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painterResource(id = R.drawable.congratulations),
                    contentDescription = null,
                )
            }
            Text(
                text = "Share With Friends Now!!",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = primaryColor,
                    ),
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clickable {
                            val intent =
                                context.packageManager.getLaunchIntentForPackage("com.facebook.katana")
                            if (intent != null) {
                                context.startActivity(intent)
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Facebook is not installed",
                                        Toast.LENGTH_SHORT,
                                    )
                                    .show()
                            }
                        },

                )
                Image(
                    painter = painterResource(id = R.drawable.twitter),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(
                        color = primaryColor,
                    ),
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clickable {
                            val intent =
                                context.packageManager.getLaunchIntentForPackage("com.twitter.android")
                            if (intent != null) {
                                context.startActivity(intent)
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Twitter is not installed",
                                        Toast.LENGTH_SHORT,
                                    )
                                    .show()
                            }
                        },

                )
                Image(
                    painter = painterResource(id = R.drawable.whatsapp),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clickable {
                            val intent =
                                context.packageManager.getLaunchIntentForPackage("com.whatsapp")
                            if (intent != null) {
                                context.startActivity(intent)
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Whatsapp is not installed",
                                        Toast.LENGTH_SHORT,
                                    )
                                    .show()
                            }
                        },

                )

                Image(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clickable {
                            val sendIntent = Intent()
                            sendIntent.action = Intent.ACTION_SEND
                            sendIntent.putExtra(
                                Intent.EXTRA_TEXT,
                                "Quench App",
                            )
                            sendIntent.type = "text/plain"
                            context.startActivity(sendIntent)
                        },

                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    Text(
                        "Cancel",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
                TextButton(onClick = {
                    openDialogCustom.value = false
                }) {
                    Text(
                        "Okay",
                        fontWeight = FontWeight.ExtraBold,
                        color = primaryColor,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}

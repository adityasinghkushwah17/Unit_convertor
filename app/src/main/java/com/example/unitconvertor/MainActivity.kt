package com.example.unitconvertor

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconvertor.ui.theme.UnitconvertorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitconvertorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Unitconvertor()
                }
            }
        }
    }
}
@Composable
fun Unitconvertor(){

    var inputvalue by remember{ mutableStateOf("") }
    var outputvalue by remember{ mutableStateOf("") }
    var inputunit by remember{ mutableStateOf("Meters") }
    var outputunit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember{ mutableStateOf(false) } // for the drop down menu
    var oExpanded by remember{ mutableStateOf(false) }
    val conversionfactor = remember{ mutableStateOf(1.00) }
    val oconversionfactor = remember{ mutableStateOf(1.00) }

    fun convertunits(){
        // ? Elvis operator
        val inputvaluetodouble=inputvalue.toDoubleOrNull()?:0.0
        val result=(inputvaluetodouble*conversionfactor.value*100/oconversionfactor.value).roundToInt()/100.0
        outputvalue=result.toString()
    }
    Column (modifier= Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
       Text(text = "UnitConvertor",style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value =inputvalue , onValueChange = {
                      inputvalue=it //to present it as a string
            convertunits() //it will directly change the value if there is a change in the inputs.

        }, label = { Text(text ="Enter value" )}
            )
        Row {
            //input Box
           Box{
               //input button
               Button(onClick = { iExpanded=true }) {
                Text(text = inputunit)
                   Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down" )
               }
               DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded=false }) {
                   DropdownMenuItem(
                       text = {Text("Centimeters")},
                       onClick = {
                           iExpanded=false
                           inputunit="Centimeters"
                           conversionfactor.value=0.01
                           convertunits()

                       })
                   DropdownMenuItem(
                       text = {Text("Meters")},
                       onClick = {
                           iExpanded=false
                           inputunit="Meters"
                           conversionfactor.value=1.00
                           convertunits() })
                   DropdownMenuItem(
                       text = {Text("Feet")},
                       onClick = {
                           iExpanded=false
                           inputunit="Feet"
                           conversionfactor.value=0.3048
                           convertunits()
                       })
                   DropdownMenuItem(
                       text = {Text("Millimeters")},
                       onClick = {
                           iExpanded=false
                           inputunit="Centimeters"
                           conversionfactor.value=0.001
                           convertunits()
                       })
               }
           }
           Spacer(modifier = Modifier.width(20.dp))
            //output box
            Box{
                Button(onClick = { oExpanded=true }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription ="Arrow Down" )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            oExpanded=false
                            outputunit="Centimeters"
                            oconversionfactor.value=0.01
                            convertunits()
                        })
                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            oExpanded=false
                            outputunit="Meters"
                           oconversionfactor.value=1.00
                            convertunits()
                        })
                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpanded=false
                            outputunit="Feet"
                            oconversionfactor.value=0.3048
                            convertunits()
                        })
                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            oExpanded=false
                            outputunit="Millimeters"
                            oconversionfactor.value=0.001
                            convertunits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputvalue $outputunit ", style = MaterialTheme.typography.headlineMedium,color= Color.Cyan)
    }
}




@Preview(showBackground = true)
@Composable
fun UnitconvertorPreview(){
    Unitconvertor()
}
package com.example.gestioninventariostockpro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaCatalogo(
    navController: NavController,
    viewModel: StockViewModel,
    nombreOperario: String
) {
    var mostrarSoloCritico by remember { mutableStateOf(false) }

    val productosAMostrar = if (mostrarSoloCritico) {
        viewModel.obtenerProductosEnRiesgo()
    } else {
        viewModel.listaProductos
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("reporte") }) {
                Text("Reporte")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(text = "Operario: $nombreOperario", fontWeight = FontWeight.Bold, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Button(onClick = { mostrarSoloCritico = false }) {
                    Text("Ver Todo")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { mostrarSoloCritico = true }) {
                    Text("Stock Crítico")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(productosAMostrar) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable { navController.navigate("edicion/${producto.id}") }
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = producto.nombre, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(text = "Precio: $${producto.precio}")
                            Text(
                                text = "Stock: ${producto.stockActual}",
                                color = if (producto.stockActual < 5) Color.Red else Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}
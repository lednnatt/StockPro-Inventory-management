package com.example.gestioninventariostockpro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PantallaEdicion(
    navController: NavController,
    viewModel: StockViewModel,
    productoId: Int
) {
    val producto = viewModel.obtenerProducto(productoId)

    if (producto == null) {
        Text("Producto no encontrado")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = producto.nombre, fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = producto.descripcion, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Stock actual: ${producto.stockActual}",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(
                onClick = { viewModel.actualizarStock(producto.id, producto.stockActual - 1) },
                enabled = producto.stockActual > 0
            ) {
                Text("Restar (-1)")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { viewModel.actualizarStock(producto.id, producto.stockActual + 1) }
            ) {
                Text("Sumar (+1)")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text("Guardar y Volver")
        }
    }
}
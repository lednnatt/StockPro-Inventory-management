package com.example.gestioninventariostockpro

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class StockViewModel : ViewModel() {
    val listaProductos = mutableStateListOf(
        Producto(1, "Taladro Eléctrico", "Taladro de alta potencia, uso industrial", 45.0, 10),
        Producto(2, "Martillo", "Martillo de acero forjado", 8.5, 20),
        Producto(3, "Caja de Tornillos", "Caja con 100 tornillos surtidos", 3.0, 4),
        Producto(4, "Cinta Métrica", "Cinta métrica de 5 metros", 5.5, 2),
        Producto(5, "Guantes de Trabajo", "Par de guantes resistentes al corte", 6.0, 15),
        Producto(6, "Casco de Seguridad", "Casco certificado para construcción", 12.0, 0)
    )

    fun obtenerProducto(id: Int): Producto? {
        return listaProductos.find { it.id == id }
    }

    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = listaProductos.indexOfFirst { it.id == id }
        if (index != -1) {
            val productoActual = listaProductos[index]
            listaProductos[index] = productoActual.copy(stockActual = nuevaCantidad)
        }
    }

    fun calcularValorTotalInventario(): Double {
        var total = 0.0
        for (producto in listaProductos) {
            total += producto.precio * producto.stockActual
        }
        return total
    }

    fun obtenerProductosEnRiesgo(): List<Producto> {
        return listaProductos.filter { it.stockActual < 5 }
    }

    fun obtenerProductosConStockCero(): Int {
        return listaProductos.count { it.stockActual == 0 }
    }
}
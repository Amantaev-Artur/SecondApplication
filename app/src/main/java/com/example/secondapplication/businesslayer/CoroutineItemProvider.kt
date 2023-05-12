package com.example.secondapplication.businesslayer

import com.example.secondapplication.datalayer.IItemAccessor2
import com.example.secondapplication.objects.Item
import kotlinx.coroutines.*

class CoroutineItemProvider(private val accessor: IItemAccessor2) {
	private val scope = CoroutineScope(Dispatchers.Main)

	fun load(callback: (List<Item>) -> Unit) {
		scope.launch {
			try {
				val result = withContext(Dispatchers.IO) { accessor.items2() }
				callback(result)
			} catch (error: Throwable) {
				error.printStackTrace()
			}
		}
	}
}
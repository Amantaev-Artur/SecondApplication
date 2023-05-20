package com.example.secondapplication.businesslayer

import com.example.secondapplication.datalayer.IItemAccessor2
import com.example.secondapplication.objects.Item
import kotlinx.coroutines.*

class CoroutineItemProvider(private val accessor: IItemAccessor2) {
	private val scope = CoroutineScope(Dispatchers.Main)
	private var loadedItems = emptyList<Item>()
	private var skip = 0
	private var limit = 24

	fun load(callback: (List<Item>) -> Unit) {
		scope.launch {
			try {
//				приходится использовать subList, т.к. апи криво работает с параметром skip
				val result = withContext(Dispatchers.IO) { accessor.items2(limit).subList(skip, limit) }
				loadedItems += result
				callback(loadedItems)

				skip += result.size
				limit += result.size // приходиться наращивать из-за кривого апи
			} catch (error: Throwable) {
				error.printStackTrace()
			}
		}
	}
}
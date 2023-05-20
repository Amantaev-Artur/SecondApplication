package com.example.secondapplication.datalayer

import com.example.secondapplication.objects.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface IItemAccessor2 {
	// API docs: https://cataas.com/cat/rV1MVEh0Af2Bm4O0
	@GET("/api/cats?tags=gif")
	suspend fun items2(@Query("limit") limit: Int): List<Item>
}
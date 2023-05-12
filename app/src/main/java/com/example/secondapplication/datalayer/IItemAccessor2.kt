package com.example.secondapplication.datalayer

import com.example.secondapplication.objects.Item
import retrofit2.http.GET

interface IItemAccessor2 {
//	API docs: https://cataas.com/cat/rV1MVEh0Af2Bm4O0
	@GET("/api/cats?skip=0&limit=1000&tags=gif")
	suspend fun items2(): List<Item>
}
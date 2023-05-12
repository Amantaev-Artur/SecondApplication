package com.example.secondapplication.datalayer

import com.example.secondapplication.objects.Item
import retrofit2.http.GET

interface IItemAccessor2 {
	@GET("/api/cats?skip=0&limit=1000")
	suspend fun items2(): List<Item>
}
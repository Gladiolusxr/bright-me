package com.example.brightme.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProductResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: DataProduct,

	@field:SerializedName("success")
	val success: Boolean,

	@field:SerializedName("message")
	val message: String
)

data class DataProduct(

	@field:SerializedName("products")
	val products: List<ProductsItem>
)

data class ProductsItem(

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("category")
	val category: Category
)

data class Category(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int
)

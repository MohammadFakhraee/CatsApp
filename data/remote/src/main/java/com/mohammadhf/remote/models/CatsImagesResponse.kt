package com.mohammadhf.remote.models

import com.google.gson.annotations.SerializedName

data class CatsImagesResponse(
	@field:SerializedName("id")
	val id: String? = null,
	@field:SerializedName("url")
	val url: String? = null,
	@field:SerializedName("breeds")
	val breeds: List<BreedsItem?>
)
package com.keshav.composetutorials.model

data class BookItem(
     val authors: List<String> = emptyList(),
     val categories: List<String> = emptyList(),
    @Transient val isbn: String = "",
    @Transient val longDescription: String = "",
    @Transient val pageCount: Int = 0,
    @Transient val shortDescription: String = "",
    val status: String = "",
    val thumbnailUrl: String = "",
    val title: String = ""
)
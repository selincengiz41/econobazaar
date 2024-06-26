package com.selincengiz.detail.domain.usecases


data class GetDetailUseCase(
    val addFavorite: AddFavorite,
    val deleteFavorite: DeleteFavorite,
    val getDetail: GetDetail,
    val getFavorites: GetFavorites,
    val addCart: AddCart
)
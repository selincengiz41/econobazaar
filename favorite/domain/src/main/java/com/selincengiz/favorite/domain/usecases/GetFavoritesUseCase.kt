package com.selincengiz.favorite.domain.usecases

import com.selincengiz.core.common.UiEvent
import com.selincengiz.favorite.domain.repo.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) {
    operator fun invoke() = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(favoriteRepository.getFavorites()))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
package com.selincengiz.detail.domain.usecases

import com.selincengiz.core.common.UiEvent
import com.selincengiz.detail.domain.repo.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFavorites @Inject constructor(
    private val detailRepository: DetailRepository
) {
    operator fun invoke() = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(detailRepository.getFavorites()))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
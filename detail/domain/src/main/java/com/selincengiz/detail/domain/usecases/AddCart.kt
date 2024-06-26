package com.selincengiz.detail.domain.usecases

import com.selincengiz.core.common.UiEvent
import com.selincengiz.detail.domain.model.CartUI
import com.selincengiz.detail.domain.model.ProductUI
import com.selincengiz.detail.domain.repo.DetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddCart @Inject constructor(
    private val detailRepository: DetailRepository
) {
    operator fun invoke(cartUI: CartUI) = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(detailRepository.addToCart(cartUI)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
package com.selincengiz.cart.domain.usecases

import com.selincengiz.cart.domain.repo.CartRepository
import com.selincengiz.core.common.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
) {
    operator fun invoke(userId: Int) = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(cartRepository.getCart(userId)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
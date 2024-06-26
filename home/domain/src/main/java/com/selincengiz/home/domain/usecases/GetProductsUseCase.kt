package com.selincengiz.home.domain.usecases

import com.selincengiz.core.common.UiEvent
import com.selincengiz.home.domain.repo.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke() = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(productRepository.getProducts()))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
package com.selincengiz.search.domain.usecases

import com.selincengiz.core.common.UiEvent
import com.selincengiz.search.domain.repo.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String) = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(productRepository.searchProducts(query)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
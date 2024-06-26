package com.selincengiz.categories.domain.usecases

import com.selincengiz.categories.domain.repo.CategoryRepository
import com.selincengiz.core.common.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetCategoriesProduct @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(category:String) = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(categoryRepository.getCategoriesProduct(category)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}
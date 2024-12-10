package com.test.currency.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.currency.database.CurrencyFavorite
import com.test.currency.service.DatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val databaseService: DatabaseService
) : ViewModel() {
    val result: MutableLiveData<List<CurrencyFavorite>> = MutableLiveData()


    fun load() {
        databaseService.loadFavorites(viewModelScope) {
            viewModelScope.launch {
                result.value = it

            }
        }
    }

}
package com.test.currency.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.currency.api.CurrencyLocal
import com.test.currency.service.CurrencyService
import com.test.currency.service.DatabaseService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val service: CurrencyService,
    private val databaseService: DatabaseService
) : ViewModel() {

    val result: MutableLiveData<List<CurrencyLocal>> = MutableLiveData()
    val spinnerList: MutableLiveData<List<String>> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData(false)

    var currentName = ""
    fun loadSpinner() {
        progress.value = true
        service.loadSpinnerItems(
            viewModelScope,
            success = {
                viewModelScope.launch {
                    spinnerList.value = it
                    progress.value = false
                }

            },
            fail = {
                viewModelScope.launch {
                    progress.value = false

                }
            }
        )
    }

    fun processCurrency(currencyLocal: CurrencyLocal) {
        if (currencyLocal.isFavorites) {
            databaseService.removeFavorites(viewModelScope, currentName, currencyLocal)
        } else {
            databaseService.addFavorites(viewModelScope, currentName, currencyLocal)

        }
    }


    fun loadCurrencies(text: String) {

        progress.value = true
        service.loadCurrencies(viewModelScope,
            fail = {
                viewModelScope.launch {
                    progress.value = false

                }
            },
            source = text,
            success = { currencies ->
                viewModelScope.launch {
                    currentName = text
                    progress.value = false
                    result.value = currencies
                }

            })
    }
}
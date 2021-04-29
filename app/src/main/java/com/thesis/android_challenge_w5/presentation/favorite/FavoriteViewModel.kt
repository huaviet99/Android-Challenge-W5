package com.thesis.android_challenge_w5.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.model.Restaurant

class FavoriteViewModel : ViewModel(){
    val email = MutableLiveData<String>()
    private val restaurantList = MutableLiveData<List<Restaurant>>()

    fun fetchRestaurantList(): LiveData<List<Restaurant>>{
        val data =  RestaurantDataStore.getFavoriteRestaurantList(email.value!!).toMutableList()
        restaurantList.postValue(data)
        return restaurantList

    }
}
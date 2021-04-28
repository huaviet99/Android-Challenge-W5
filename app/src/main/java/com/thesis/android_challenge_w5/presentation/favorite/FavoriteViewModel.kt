package com.thesis.android_challenge_w5.presentation.favorite

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.data.RestaurantDataStore.getRestaurantList
import com.thesis.android_challenge_w5.model.Restaurant

class FavoriteViewModel : ViewModel(){

    private val restaurantList = MutableLiveData<List<Restaurant>>()

    fun fetchRestaurantList(): LiveData<List<Restaurant>>{
        val data =  RestaurantDataStore.getFavoriteRestaurantList("huaviet999@gmail.com").toMutableList()
        restaurantList.value = data
        return restaurantList

    }
}
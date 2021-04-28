package com.thesis.android_challenge_w5.presentation.top

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.model.Restaurant

class TopViewModel : ViewModel(){

    private val restaurantList = MutableLiveData<List<Restaurant>>()

    fun fetchRestaurantList(): LiveData<List<Restaurant>>{
        restaurantList.value = RestaurantDataStore.test("huaviet999@gmail.com")
        return restaurantList
    }
}
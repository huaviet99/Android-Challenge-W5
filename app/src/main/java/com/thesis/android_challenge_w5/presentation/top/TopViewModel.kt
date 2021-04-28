package com.thesis.android_challenge_w5.presentation.top

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.RestaurantDataStore
import com.thesis.android_challenge_w5.model.Restaurant

class TopViewModel : ViewModel(){

    private val restaurantList = MutableLiveData<List<Restaurant>>()
     val isAddFavoriteSucceed = MutableLiveData<Boolean>()
    val isRemoveFavoriteSucceed = MutableLiveData<Boolean>()

    fun fetchRestaurantList(): LiveData<List<Restaurant>>{
       val data = RestaurantDataStore.getAllRestaurantListWithFavoriteChecked("1@gmail.com")
        for(d in data){
            Log.d("TopViewModel",d.toString())
        }
        restaurantList.postValue(data)
        return restaurantList
    }

    fun addFavoriteRestaurant(restaurant: Restaurant){
        RestaurantDataStore.setAddFavoriteRestaurantCallback(object : RestaurantDataStore.AddFavoriteRestaurantCallback {
            override fun onSucceed() {
                Log.d("TopViewModel","add onSucceed")
                isAddFavoriteSucceed.value = true
            }
        })
        RestaurantDataStore.addRestaurantListByEmail("1@gmail.com",restaurant)
    }

    fun removeFavoriteRestaurant(restaurant: Restaurant){
        RestaurantDataStore.setRemoveFavoriteRestaurantCallback(object : RestaurantDataStore.RemoveFavoriteRestaurantCallback {
            override fun onSucceed() {
                Log.d("TopViewModel","remove onSucceed")

                isRemoveFavoriteSucceed.value = true
            }
        })
        RestaurantDataStore.removeRestaurantListByEmail("1@gmail.com",restaurant)
    }
}
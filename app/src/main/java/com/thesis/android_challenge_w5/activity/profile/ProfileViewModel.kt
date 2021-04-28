package com.thesis.android_challenge_w5.activity.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w5.data.UserDataStore
import com.thesis.android_challenge_w5.model.User


class ProfileViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    private val userDataStore = UserDataStore.instance

    init {
        user.value = User()
    }

    fun setupUserProfile(email: String){
        val user =  userDataStore.getUserByEmail(email)
        user?.let {
            this.user.value = user
            this.user.postValue(user)
        }

    }

    fun editFullNameUser(email: String, fullName: String) {
        userDataStore.editUser(email,UserDataStore.FULL_NAME_FIELD,fullName)
        user.value?.fullName = fullName
        user.postValue(user.value)
    }

    fun editEmailUser(oldEmail:String, email: String) {
        userDataStore.editUser(oldEmail,UserDataStore.EMAIL_FIELD,email)
        user.value?.email = email
        user.postValue(user.value)
    }

    fun editPhoneNumberUser(email: String, phoneNumber: String) {
        userDataStore.editUser(email,UserDataStore.PHONE_NUMBER_FIELD,phoneNumber)
        user.value?.phoneNumber = phoneNumber
        user.postValue(user.value)
    }
}
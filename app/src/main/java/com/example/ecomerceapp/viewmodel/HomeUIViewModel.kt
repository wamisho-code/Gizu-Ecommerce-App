package com.example.ecomerceapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.ecomerceapp.model.user_models.UserDto
import com.example.ecomerceapp.repository.AuthRepository
import com.example.ecomerceapp.repository.UserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeUIViewModel(private val useInfoRepo:
                      UserInfoRepository= UserInfoRepository(),
    private  val authRepo: AuthRepository= AuthRepository()

)
    : ViewModel() {
    private val _userDto = MutableStateFlow(UserDto())

    val userDto=_userDto.asStateFlow()
    fun getUserInfo(onResult:(Boolean,String?)->Unit){
        useInfoRepo.getUserInfo { bool, userDto,message ->
            if(bool){
                _userDto.update { currentDto->
                    currentDto.copy(
                        fullName = userDto?.fullName,
                        email = userDto?.email,
                        phoneNumber = userDto?.phoneNumber
                    )
                }
                onResult(true,null)
            }else{
                onResult(false,message)
            }
        }
    }
    fun logout(){
        authRepo.logout()
    }
}
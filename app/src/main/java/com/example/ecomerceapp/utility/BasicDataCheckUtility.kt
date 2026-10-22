package com.example.ecomerceapp.utility

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.ecomerceapp.model.LoginValidationResult
import com.example.ecomerceapp.model.SignUpValidationResult
import com.example.ecomerceapp.uistate.LoginUiState
import com.example.ecomerceapp.uistate.SignUpUiState
import com.example.ecomerceapp.viewmodel.SignUpUIViewModel

class BasicDataCheckUtility(signUpUIViewModel: SignUpUIViewModel) {


    companion object{
        fun checkForSignUpUIStateInput(signUpUiState: SignUpUiState): SignUpValidationResult{
            val fullName= signUpUiState.fullName
            val phoneNumber=signUpUiState.phoneNumber
            val email=signUpUiState.email
            val password= signUpUiState.password

            if(fullName.isEmpty()|| phoneNumber.isEmpty() ||
                email.isEmpty() || password.isEmpty()){
                return SignUpValidationResult(
                    isPhoneValid = false,
                    isPasswordStrong = false,
                    isValid = false,
                    message = "Please fill all the required credentials"
                )
            }else{

                val isValidPhone= checkPhoneFormat(phoneNumber)
                val isStrongPassword=checkPasswordStrength(password)
                //println("phoneandpassword: $isValidPhone & $isStrongPassword")
                val message= when{
                    !isValidPhone && !isStrongPassword ->
                        "Invalid phone number and weak password"
                    !isValidPhone->
                        "Invalid phone number"
                    !isStrongPassword->
                        "Password must be Must be at least 6 characters long"+
                                "\nMust contain at least one special character,"+
                                "\none lowercase letter and one uppercase letter"
                    else -> null
                }
                return SignUpValidationResult(
                    isPhoneValid = isValidPhone,
                    isPasswordStrong =isStrongPassword,
                    isValid = isValidPhone && isStrongPassword,
                    message= message
                )
            }

        }
        fun checkForLoginUiStateInput(loginUiState: LoginUiState): LoginValidationResult{
            val password:String= loginUiState.password
            val email: String= loginUiState.email
            if(password.isEmpty() || email.isEmpty()){
                return LoginValidationResult(
                    isValid = false,
                    isPasswordStrong = false,
                    message =  "Please fill all the required credentials"
                )
            }else{
                val isPasswordStrong= checkPasswordStrength(loginUiState.password)
                val message= when{
                    !isPasswordStrong->"Password must be Must be at least 6 characters long"+
                            "\nMust contain at least one special character,"+
                            "\none lowercase letter and one uppercase letter"
                    else->null
                }
                return LoginValidationResult(
                    isValid = isPasswordStrong,
                    isPasswordStrong = isPasswordStrong,
                    message = message
                )

            }
        }
        fun checkPasswordStrength(password:String): Boolean{
            // Regex Breakdown:
            // (?=.*[A-Z])    -> Must contain at least one uppercase letter
            // (?=.*[a-z])    -> Must contain at least one lowercase letter
            // (?=.*\d)       -> Must contain at least one digit (number)
            // (?=.*[@$!%*?&]) -> Must contain at least one special character (@, $, !, %, *, ?, or &)
            // .{6,}          -> Must be at least 8 characters long
            val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{6,}\$"
            return  password.matches(passwordRegex.toRegex())
        }
        fun checkPhoneFormat(phoneNumber:String): Boolean{
            // Regex breakdown:
            // ^(\\+251|251|0)? -> Optional international code (+251 or 251) OR local code (0)
            // [79]             -> The next digit must be 7 or 9 (Ethio Telecom / Safaricom mobile blocks)
            // \\d{8}$          -> Followed by exactly 8 trailing digits
            val cleanPhone = phoneNumber.replace("\\s|-".toRegex(), "")
            val ethiopianPhoneRegex = "^(\\+251|251|0)?[79]\\d{8}\$".toRegex()
            return  cleanPhone.matches(ethiopianPhoneRegex)
        }
    }




}
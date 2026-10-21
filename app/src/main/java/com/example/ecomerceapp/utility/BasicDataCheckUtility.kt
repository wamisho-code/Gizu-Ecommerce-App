package com.example.ecomerceapp.utility

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecomerceapp.model.SignUpValidationResult
import com.example.ecomerceapp.uistate.SignUpUiState
import com.example.ecomerceapp.viewmodel.SignUpUIViewModel

class BasicDataCheckUtility(signUpUIViewModel: SignUpUIViewModel) {


    companion object{
        fun checkForSignUpUIState(signUpUiState: SignUpUiState): SignUpValidationResult{
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
                // Regex breakdown:
                // ^(\\+251|251|0)? -> Optional international code (+251 or 251) OR local code (0)
                // [79]             -> The next digit must be 7 or 9 (Ethio Telecom / Safaricom mobile blocks)
                // \\d{8}$          -> Followed by exactly 8 trailing digits
                val phone= signUpUiState.phoneNumber
                val cleanPhone = phone.replace("\\s|-".toRegex(), "")
                val ethiopianPhoneRegex = "^(\\+251|251|0)?[79]\\d{8}\$".toRegex()
                val isValidPhone= cleanPhone.matches(ethiopianPhoneRegex)
                if(!isValidPhone){

                }
                // Regex Breakdown:
                // (?=.*[A-Z])    -> Must contain at least one uppercase letter
                // (?=.*[a-z])    -> Must contain at least one lowercase letter
                // (?=.*\d)       -> Must contain at least one digit (number)
                // (?=.*[@$!%*?&]) -> Must contain at least one special character (@, $, !, %, *, ?, or &)
                // .{6,}          -> Must be at least 8 characters long
                val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&]).{6,}\$"

                val isStrongPassword=password.matches(passwordRegex.toRegex())
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
    }
}
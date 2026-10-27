package com.example.ecomerceapp.utility

import com.example.ecomerceapp.R
import com.example.ecomerceapp.model.home_screen_models.SlideCardItem

class SlideCardUtility {
    companion object{
        val slideCardList= listOf<SlideCardItem>(
            SlideCardItem(
                text = "Buy without leaving home.",
                image = R.drawable.from_home

            ),
            SlideCardItem(
                text = "Great items at low prices.",
                image = R.drawable.buying

            ),
            SlideCardItem(
                text = "Find exactly what you want.",
                image = R.drawable.finding_and_buying

            ),
            SlideCardItem(
                text = "Simple ways to pay instantly.",
                image = R.drawable.payment_blue

            ),
            SlideCardItem(
                text = "Fast delivery straight to you.",
                image = R.drawable.delivery_purple

            ),
            SlideCardItem(
                text = "List your items and sell fast.",
                image = R.drawable.sell_it_easy

            ),


            )
    }
}
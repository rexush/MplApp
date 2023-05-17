package com.example.mplapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    val name: String,
    val description: String,
    val logo: String,
    val achievement: String,
    val founder: String,
    val negara: String,
    val pemain: String
) : Parcelable

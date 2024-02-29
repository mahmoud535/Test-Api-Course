package com.example.testapicourse.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName ="user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var name:String,
    var message:String,
    var imageId: Int
):Parcelable

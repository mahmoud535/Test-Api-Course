package com.example.testapicourse.presentation.adapter

import com.example.testapicourse.domain.model.User

interface OnListItemClick {
    fun onItemClick(user: User)
}
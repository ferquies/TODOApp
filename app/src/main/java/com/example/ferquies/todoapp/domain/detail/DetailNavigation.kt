package com.example.ferquies.todoapp.domain.detail

/**
 * Created by Fernando Q. Esquitino
 * Email: ferquies@gmail.com
 * Twitter: @ferquies
 * 1/21/18
 */
sealed class DetailNavigation {
    class Back : DetailNavigation()
}
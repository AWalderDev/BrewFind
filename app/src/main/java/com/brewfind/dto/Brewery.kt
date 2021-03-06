package com.brewfind.dto

data class Brewery(var name: String) {
    override fun toString(): String {
        return "$name"
    }
}
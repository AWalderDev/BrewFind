package com.brewfind.dto

data class Brewery(var name: String, var size: String, var street: String, var city: String, var state: String, var zipCode: String) {
    override fun toString(): String {
        return name
    }
}
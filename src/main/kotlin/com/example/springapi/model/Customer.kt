package com.example.springapi.model

/**
 * Customer data class.
 *
 * @property id The customer ID.
 * @property firstName The customer's first name.
 * @property lastName The customer's last name.
 */
data class Customer(
    val id: Long,
    val firstName: String,
    val lastName: String
)
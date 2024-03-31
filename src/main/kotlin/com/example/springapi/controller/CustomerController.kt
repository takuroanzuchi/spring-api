package com.example.springapi.controller

import com.example.springapi.service.CustomerService
import com.example.springapi.model.Customer
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/customers")
    fun findAll(): List<CustomerResponse> {
        return customerService.findAll().map {
            CustomerResponse(
                id = it.id,
                firstName = it.firstName,
                lastName = it.lastName
            )
        }
    }

    @GetMapping("/customers/{id}")
    fun findById(id: Long): Customer? {
        return customerService.findById(id)
    }

    @DeleteMapping("/customers/{id}")
    fun deleteById(id: Long) {
        customerService.deleteById(id)
    }

    @PutMapping("/customers")
    fun save(@RequestBody request: CustomerRequest): Customer {
        val customer = Customer(
            id = 0,
            firstName = request.firstName,
            lastName = request.lastName
        )
        return customerService.save(customer)
    }
}

/**
 * Customer 作成エンドポイント、Customer 更新エンドポイントのリクエストボディ
 *
 * @property firstName
 * @property lastName
 */
data class CustomerRequest(
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
)

/**
 * Customer 一覧取得エンドポイントのレスポンス
 *
 * @property customers
 */
data class CustomerResponse(
    @JsonProperty("id") val id: Long,
    @JsonProperty("first_name") val firstName: String,
    @JsonProperty("last_name") val lastName: String,
)
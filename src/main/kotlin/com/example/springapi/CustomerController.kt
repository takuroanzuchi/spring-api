package com.example.springapi

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(private val customerService: CustomerService) {

    @GetMapping("/customers")
    fun findAll(): List<Customer> {
        return customerService.findAll()
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
    val customers: List<Customer>,
)
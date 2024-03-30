package com.example.springapi

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

interface CustomerRepository {
    fun findAll(): List<Customer>
    fun findById(id: Long): Customer?
    fun save(customer: Customer): Customer
    fun deleteById(id: Long)
}

@Repository
class CustomerRepositoryImpl(
    val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
): CustomerRepository {
override fun findAll(): List<Customer> {
        return namedParameterJdbcTemplate.query(
            "SELECT id, first_name, last_name FROM customer"
        ) { rs, _ ->
            Customer(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name")
            )
        }
    }

    override fun findById(id: Long): Customer? {
        return namedParameterJdbcTemplate.query(
            "SELECT id, first_name, last_name FROM customer WHERE id = :id",
            mapOf("id" to id)
        ) { rs, _ ->
            Customer(
                rs.getLong("id"),
                rs.getString("first_name"),
                rs.getString("last_name")
            )
        }.firstOrNull()
    }

    override fun save(customer: Customer): Customer {
        namedParameterJdbcTemplate.update(
            "INSERT INTO customer (first_name, last_name) VALUES (:firstName, :lastName)",
            mapOf(
                "firstName" to customer.firstName,
                "lastName" to customer.lastName
            )
        )
        return customer
    }

    override fun deleteById(id: Long) {
        namedParameterJdbcTemplate.update(
            "DELETE FROM customer WHERE id = :id",
            mapOf("id" to id)
        )
    }
}
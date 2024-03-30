package com.example.springapi

import org.springframework.stereotype.Service

interface CustomerService {
    fun findAll(): List<Customer>
    fun findById(id: Long): Customer?
    fun save(customer: Customer): Customer
    fun deleteById(id: Long)
}

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
): CustomerService {
    override fun findAll(): List<Customer> {
        return customerRepository.findAll()
    }

    override fun findById(id: Long): Customer? {
        return customerRepository.findById(id)
    }

    override fun save(customer: Customer): Customer {
        return customerRepository.save(customer)
    }

    override fun deleteById(id: Long) {
        return customerRepository.deleteById(id)
    }
}
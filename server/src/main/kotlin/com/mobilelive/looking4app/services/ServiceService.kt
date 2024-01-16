package com.mobilelive.looking4app.services

import com.mobilelive.looking4app.exposed.Service
import com.mobilelive.looking4app.exposed.User
import com.mobilelive.looking4app.repository.ServiceRepository

class ServiceService(private val serviceRepository: ServiceRepository) {

    fun findAll(): List<Service> = serviceRepository.findAll()

    fun findById(id: Int): Service? = serviceRepository.findById(id)

    fun save(service: Service): Service = serviceRepository.save(service)

    fun searchByName(name: String): List<Service> = serviceRepository.searchByName(name)

    fun searchByDescription(description: String): List<Service> = serviceRepository.searchByDescription(description)


}

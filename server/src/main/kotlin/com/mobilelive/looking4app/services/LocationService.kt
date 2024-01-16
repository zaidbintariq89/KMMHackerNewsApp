package com.mobilelive.looking4app.services

import com.mobilelive.looking4app.exposed.Location
import com.mobilelive.looking4app.repository.LocationRepository


class LocationService(private val locationRepository: LocationRepository) {

    fun findAll(): List<Location> = locationRepository.findAll()

    fun findById(id: Int): Location? = locationRepository.findById(id)

    fun save(location: Location): Location = locationRepository.save(location)
}
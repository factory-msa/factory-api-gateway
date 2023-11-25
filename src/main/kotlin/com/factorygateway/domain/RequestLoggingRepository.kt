package com.factorygateway.domain

import org.springframework.data.jpa.repository.JpaRepository

interface RequestLoggingRepository: JpaRepository<RequestLoggingEntity, String>

package com.factorygateway.domain

import com.factorygateway.support.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "API_REQUEST_LOG")
class RequestLoggingEntity(

    @Id
    @Column(name = "GT_ID", nullable = false)
    var globalTransactionId: String,

    @Column(name = "URI", nullable = false)
    var uri: String,

    @Column(name = "HTTP_METHOD", nullable = false)
    var httpMethod: String,

    @Column(name = "HTTP_HEADERS", nullable = false)
    var httpHeaders: String,

    @Column(name = "BODY")
    var body: String

): BaseTimeEntity()

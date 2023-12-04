package com.factorygateway.filter

import com.factorygateway.domain.RequestLoggingEntity
import com.factorygateway.domain.RequestLoggingRepository
import com.factorygateway.support.GlobalTransactionIdGenerator.Companion.GLOBAL_TRANSACTION_ID_HEADER
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange

@Component
class CustomLoggingFilter(
    private val repository: RequestLoggingRepository
) : AbstractGatewayFilterFactory<CustomLoggingFilter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request: ServerHttpRequest = exchange.request

            val globalTransactionId = request.headers[GLOBAL_TRANSACTION_ID_HEADER]?.get(0) ?: ""
            val uri = request.uri.toString()
            val method = request.methodValue
            val headers = getHeaders(request.headers)

            // TODO: queryParams, body
            repository.save(
                RequestLoggingEntity(globalTransactionId, uri, method, headers, "")
            )

            chain.filter(exchange)
        }
    }

    private fun getHeaders(headers: HttpHeaders): String {
        return headers.entries
            .flatMap { entry -> entry.value.map { entry.key to it } }
            .joinToString(separator = "\n") { (key, value) -> "$key: $value" }
    }

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean,
    )

}

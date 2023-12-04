package com.factorygateway.filter

import com.factorygateway.support.GlobalTransactionIdGenerator
import com.factorygateway.support.GlobalTransactionIdGenerator.Companion.GLOBAL_TRANSACTION_ID_HEADER
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class CustomHeaderFilter : GlobalFilter, Ordered {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val globalTransactionId = GlobalTransactionIdGenerator.of()

        exchange.request
            .mutate()
            .header(GLOBAL_TRANSACTION_ID_HEADER, globalTransactionId)

        return chain.filter(exchange)
    }

    override fun getOrder(): Int {
        return Ordered.HIGHEST_PRECEDENCE
    }
}

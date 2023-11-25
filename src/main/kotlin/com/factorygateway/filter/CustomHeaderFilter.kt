package com.factorygateway.filter

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

@Component
class CustomHeaderFilter: GlobalFilter, Ordered {

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        val globalTransactionId = UUID.randomUUID().toString().replace("-", "")

        exchange.request
            .mutate()
            .header("GTID", globalTransactionId)

        return chain.filter(exchange)
    }

    override fun getOrder(): Int {
        return Ordered.HIGHEST_PRECEDENCE
    }
}

package com.factorygateway.support

import java.util.*

class GlobalTransactionIdGenerator {

    companion object {
        const val GLOBAL_TRANSACTION_ID_HEADER = "GLBL-TRX-ID"

        fun of(): String {
            return UUID.randomUUID().toString().replace("-", "")
        }
    }

}

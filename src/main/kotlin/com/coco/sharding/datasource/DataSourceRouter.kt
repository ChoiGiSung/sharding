package com.coco.sharding.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import java.util.LinkedHashMap

class DataSourceRouter : AbstractRoutingDataSource() {
    fun setTargetDataSources(dataSourceMap: LinkedHashMap<Any, Any>) {

    }

    override fun determineCurrentLookupKey(): Any? {
        TODO("Not yet implemented")
    }
}
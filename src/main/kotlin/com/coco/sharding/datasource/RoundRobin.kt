package com.coco.sharding.datasource

class RoundRobin<T>(private val list: MutableList<T>) {
    private var index: Int = 0

    private val iterator: Iterator<T> = object : Iterator<T> {
        override fun hasNext(): Boolean {
            return true
        }

        override fun next(): T {
            val value = list[index]
            index = (index + 1) % list.size
            return value
        }
    }

    fun next(): T {
        return iterator.next()
    }

    fun add(item: T) {
        list.add(item)
    }
}

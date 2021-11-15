package org.romeo.layer_data.data_sources.preferences

interface PreferencesDataSource<T> {
    fun save(data: T)
    fun get(): T?
}
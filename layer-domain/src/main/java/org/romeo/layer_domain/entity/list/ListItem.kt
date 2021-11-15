package org.romeo.layer_domain.entity.list

interface ListItem<T> {
    fun getViewType(): Int = 0
    fun areContentsTheSame(other: T) = compareAnnotatedFields(other, Content::class.java)
    fun areItemsTheSame(other: T) = compareAnnotatedFields(other, ListItemId::class.java)
}
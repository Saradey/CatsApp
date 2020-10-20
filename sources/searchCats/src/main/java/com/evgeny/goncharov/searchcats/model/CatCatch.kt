package com.evgeny.goncharov.searchcats.model

/**
 * Модель для отображения кота в списке искомых котов
 * @property catName имя кота
 * @property catId id кота
 */
data class CatCatch(
    val catName: String,
    val catId: String
)
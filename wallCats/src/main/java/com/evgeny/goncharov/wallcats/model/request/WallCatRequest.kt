package com.evgeny.goncharov.wallcats.model.request

import com.evgeny.goncharov.coreapi.GET_PARAM_LIMIT
import com.evgeny.goncharov.coreapi.GET_PARAM_PAGE

class WallCatRequest(
    private val limit: Int,
    private val page: Int
) {

    fun createRequest(): Map<String, Int> = hashMapOf(
        Pair(GET_PARAM_LIMIT, limit),
        Pair(GET_PARAM_PAGE, page)
    )
}
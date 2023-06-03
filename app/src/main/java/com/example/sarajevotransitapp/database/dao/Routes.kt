package com.example.sarajevotransitapp.database.dao

import com.example.sarajevotransitapp.database.entities.routes
import com.example.sarajevotransitapp.database.entities.routestops
import com.example.sarajevotransitapp.database.functions.routetypes

interface RoutesDAO {
    fun insertRoute(route: routes)
    fun updateRoute(route: routes)
    fun deleteRoute(route: routes)
    fun getRouteById(routeId: Int): routes
    fun getAllRoutes(routeTypes: routetypes): List<routes> {
        return routetypes.types_of_route()
    }

}

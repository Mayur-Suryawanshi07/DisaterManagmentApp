package com.example.disasterpreparedness.core.navigation.destination

import kotlinx.serialization.Serializable

sealed class Routes{
    @Serializable
    data object Login : Routes()

    @Serializable
    data object SignUp : Routes()

    @Serializable
    data object Disaster : Routes()

    @Serializable
    data class DisasterDetail(val disasterId: String) : Routes()

    @Serializable
    data object Contact : Routes()

    @Serializable
    data object Profile : Routes()

}

sealed class Graph{
    
    @Serializable
    data object Auth : Graph()
    
    @Serializable
    data object Main : Graph()
}


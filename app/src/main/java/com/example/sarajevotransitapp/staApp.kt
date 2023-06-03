package com.example.sarajevotransitapp

import com.example.sarajevotransitapp.ui.theme.TicketPurchase

enum class staApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = TicketPurchase.valueOf(
        backStackEntry?.destination?.route ?: TicketPurchase.Start.name
    )

}
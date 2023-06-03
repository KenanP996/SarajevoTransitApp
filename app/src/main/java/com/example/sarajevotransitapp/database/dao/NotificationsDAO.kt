package com.example.sarajevotransitapp.database.dao

import com.example.sarajevotransitapp.database.Notification


interface NotificationsDAO {
    fun insertNotification(notification: Notification)
    fun updateNotification(notification: Notification)
    fun deleteNotification(notification: Notification)
    fun getNotificationById(notificationId: Int): Notification?
    fun getAllNotifications(): List<Notification>
}

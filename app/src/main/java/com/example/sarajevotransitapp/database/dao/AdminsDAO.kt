package com.example.sarajevotransitapp.database.dao

import com.example.sarajevotransitapp.database.entities.admins

interface AdminsDAO {
        fun insertAdmin(admin: admins)
        fun updateAdmin(admin: admins)
        fun deleteAdmin(admin: admins)
        fun getAdminById(adminId: Int): admins
        fun getAllAdmins(): List<admins>
    }


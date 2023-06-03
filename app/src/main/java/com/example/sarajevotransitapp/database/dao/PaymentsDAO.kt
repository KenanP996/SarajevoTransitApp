package com.example.sarajevotransitapp.database.dao

import com.example.sarajevotransitapp.database.Payments
interface PaymentsDAO {
    fun insertPayment(payment: Payments)
    fun updatePayment(payment: Payments)
    fun deletePayment(payment: Payments)
    fun getPaymentById(paymentId: Int): Payments?
    fun getAllPayments(): List<Payments>
}

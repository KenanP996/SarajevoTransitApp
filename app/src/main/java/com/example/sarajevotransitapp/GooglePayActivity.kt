package com.example.sarajevotransitapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.wallet.*
import com.google.android.gms.common.api.Status

class GooglePayActivity : AppCompatActivity() {

    private lateinit var paymentsClient: PaymentsClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_google_pay)

        // Create a PaymentsClient instance
        paymentsClient = createPaymentsClient()
        val googlePayButton = findViewById<Button>(R.id.googlePayButton)
        googlePayButton.setOnClickListener { onGooglePayButtonClicked() }
    }

    private fun createPaymentsClient(): PaymentsClient {
        val walletOptions = Wallet.WalletOptions.Builder()
            .setEnvironment(WalletConstants.ENVIRONMENT_TEST) // Set the appropriate environment (test or production)
            .build()

        return Wallet.getPaymentsClient(this, walletOptions)
    }

    private fun getPaymentDataRequest(): PaymentDataRequest {
        // TODO: Create and return a PaymentDataRequest object with the required payment parameters
        return PaymentDataRequest.fromJson("")
    }

    private fun isReadyToPayRequest(): IsReadyToPayRequest {
        // TODO: Create and return an IsReadyToPayRequest object
        return IsReadyToPayRequest.fromJson("")
    }

    fun onGooglePayButtonClicked() {
        val paymentDataRequest = getPaymentDataRequest()

        // Check if the device supports Google Pay
        val isReadyToPayRequest = isReadyToPayRequest()

        paymentsClient.isReadyToPay(isReadyToPayRequest)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result == true) {
                        // Device supports Google Pay, show the payment sheet
                        AutoResolveHelper.resolveTask(
                            paymentsClient.loadPaymentData(paymentDataRequest),
                            this,
                            LOAD_PAYMENT_DATA_REQUEST_CODE
                        )
                    } else {
                        // Device does not support Google Pay, show an alternative payment method
                        showAlternativePaymentMethod()
                    }
                } else {
                    // Error occurred, handle the error
                    task.exception?.let { handleError(it) }
                }
            }
    }

    private fun handlePaymentSuccess(paymentData: PaymentData?) {
        // TODO: Handle successful payment
        if (paymentData != null) {
            val paymentInfo = paymentData.toJson() // Retrieve payment information for further processing
            Log.d(TAG, "Payment successful: $paymentInfo")
        }
    }

    private fun showAlternativePaymentMethod() {
        // TODO: Show an alternative payment method to the user
        Log.d(TAG, "Device does not support Google Pay")
    }

    private fun handleError(exception: Exception) {
        // TODO: Handle payment error
        Log.e(TAG, "Payment error: exception=$exception")
    }

    private fun handleError(status: Status) {
        // TODO: Handle payment error
        Log.e(TAG, "Payment error: status=$status")
    }

    companion object {
        private const val TAG = "GooglePayActivity"
        private const val LOAD_PAYMENT_DATA_REQUEST_CODE = 1234
    }

    // This method handles the result of the `loadPaymentData`.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            LOAD_PAYMENT_DATA_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val paymentData = data?.let { PaymentData.getFromIntent(it) }
                        handlePaymentSuccess(paymentData)
                    }
                    Activity.RESULT_CANCELED -> {
                        // Handle user cancellation
                    }
                    AutoResolveHelper.RESULT_ERROR -> {
                        // Handle error
                        val status = AutoResolveHelper.getStatusFromIntent(data)
                        if (status != null) {
                            handleError(status)
                        }
                    }
                    else -> {
                        // Handle any other unexpected situation
                    }
                }
            }
        }
    }
}

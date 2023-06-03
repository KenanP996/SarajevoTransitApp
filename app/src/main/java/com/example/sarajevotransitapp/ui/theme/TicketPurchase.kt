package com.example.sarajevotransitapp.ui.theme

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sarajevotransitapp.R
import com.example.sarajevotransitapp.database.functions.tickets
import com.example.sarajevotransitapp.database.functions.transports
import com.example.sarajevotransitapp.model.TicketItem

enum class TicketPurchase(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    TicketType(title = R.string.choose_type),
    MeansOfTransport(title = R.string.choose_vehicle),
    Checkout(title = R.string.order_checkout)
}

@Composable
fun TicketPurchaseAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun VehicleScreen(
    options: List<com.example.sarajevotransitapp.database.entities.tickets>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (transports) -> Unit,
    modifier: Modifier = Modifier
) {
    BaseMenuScreen(
        options = options,
        onCancelButtonClicked = onCancelButtonClicked,
        onNextButtonClicked = onNextButtonClicked,
        onSelectionChanged = onSelectionChanged as (TicketItem) -> Unit,
        modifier = modifier
    )


}

@Preview
@Composable
fun VehiclePreview(){
    VehicleScreen(
        options = tickets.tickets,
        onCancelButtonClicked = {},
        onNextButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}




package com.example.sarajevotransitapp.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sarajevotransitapp.R
import com.example.sarajevotransitapp.database.functions.tickets
import com.example.sarajevotransitapp.model.TicketItem


@Composable
fun TicketTypeScreen(
    options: List<com.example.sarajevotransitapp.database.entities.tickets>,
    onCancelButtonClicked: () -> Unit,
    onNextButtonClicked: () -> Unit,
    onSelectionChanged: (tickets) -> Unit,
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
fun TicketTypePreview(){
    TicketTypeScreen(
        options = tickets.tickets,
        onNextButtonClicked = {},
        onCancelButtonClicked = {},
        onSelectionChanged = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}

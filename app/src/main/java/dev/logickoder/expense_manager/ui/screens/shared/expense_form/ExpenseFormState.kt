package dev.logickoder.expense_manager.ui.screens.shared.expense_form

import dev.logickoder.expense_manager.data.model.DataRow
import dev.logickoder.expense_manager.ui.domain.FormState
import dev.logickoder.expense_manager.ui.domain.MutableObservableState
import dev.logickoder.expense_manager.utils.createErrorState
import dev.logickoder.expense_manager.utils.float
import dev.logickoder.expense_manager.utils.formatted
import dev.logickoder.expense_manager.utils.toText
import java.time.LocalDate

class ExpenseFormState(
    val data: DataRow? = null,
) : FormState<DataRow>() {

    val isEdit = data != null

    val merchantError = createErrorState()
    val merchant = MutableObservableState(
        initial = data?.merchant,
        update = { value: String?, _ -> value },
        output = { it ?: "" }
    )

    val totalError = createErrorState()
    val total = MutableObservableState(
        initial = data?.total,
        update = { amount: String?, _ -> amount?.float },
        output = { it?.formatted ?: "" }
    )

    val dateError = createErrorState()
    val date = MutableObservableState(
        initial = data?.date,
        update = { date: LocalDate?, _ -> date },
        output = { it?.toText() ?: "" }
    )

    val comment = MutableObservableState(
        initial = data?.comment ?: "",
        update = { comment: String, _ -> comment },
        output = { it }
    )

    val receipt = MutableObservableState(
        initial = data?.receipt,
        update = { receipt: String?, _ -> receipt },
        output = { it }
    )

    val statusError = createErrorState()
    val status = MutableObservableState(
        initial = data?.status,
        update = { status: Pair<Boolean, String>?, initial ->
            if (status?.first == true) status.second else initial
        },
        output = { it }
    )

    val showGallery = MutableObservableState(
        initial = false,
        update = { showGallery: Boolean, _ -> showGallery },
        output = { it }
    )

    override val errors = listOf(
        merchantError, totalError, dateError, statusError
    )

    override suspend fun save(): DataRow? {
        val errorMessage = "Please provide a %s"
        clearErrors()

        if (merchant.value == null)
            merchantError.emit(errorMessage.format("merchant"))
        if (total.value == null)
            totalError.emit(errorMessage.format("total"))
        if (date.value == null)
            dateError.emit(errorMessage.format("date"))
        if (status.value == null)
            statusError.emit(errorMessage.format("status"))

        return if (hasError()) {
            null
        } else DataRow(
            id = data?.id ?: 0,
            date = date.value!!,
            merchant = merchant.value!!,
            total = total.value!!,
            status = status.value!!,
            receipt = receipt.value,
            comment = comment.value,
        )
    }

    override fun clear() {
        merchant.emit(null)
        total.emit(null)
        date.emit(null)
        comment.emit("")
        receipt.emit(null)
        status.emit(null)
        clearErrors()
    }
}
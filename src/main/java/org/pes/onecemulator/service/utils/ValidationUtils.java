package org.pes.onecemulator.service.utils;

import org.pes.onecemulator.entity.AccountingEntry;

import java.util.Objects;

public final class ValidationUtils {

    public static void requireNonNull(AccountingEntry accountingEntry) {
        Objects.requireNonNull(accountingEntry,
                "AccountingEntry is null.");
        Objects.requireNonNull(accountingEntry.getId(),
                "accountingEntry.getId() is null.");
        Objects.requireNonNull(accountingEntry.getSum(),
                "accountingEntry.getSum() is null.");
        Objects.requireNonNull(accountingEntry.getCode(),
                "accountingEntry.getCode() is null.");
        Objects.requireNonNull(accountingEntry.getDocumentName(),
                "accountingEntry.getDocumentName() is null.");
        Objects.requireNonNull(accountingEntry.getDate(),
                "accountingEntry.getDate() is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest(),
                "ExpenseRequest is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getNumber(),
                "expenseRequest.getNumber() is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getConfirm(),
                "expenseRequest.getConfirm() is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getPaid(),
                "expenseRequest.getPaid() is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getCurrency(),
                "expenseRequest.getCurrency() is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getSource(),
                "expenseRequest.getSource() is null.");
        Objects.requireNonNull(accountingEntry.getExpenseRequest().getSource().getName(),
                "expenseRequest.getSource().getName() is null.");
    }
}

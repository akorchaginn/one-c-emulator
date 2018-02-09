package org.pes.onecemulator.service.utils;

import org.pes.onecemulator.entity.AccountingEntry;

public final class ValidationUtils {

    public static void validateAccountingEntryForCrmRequest(AccountingEntry object) {
        if (object == null) {
            throw new NullPointerException("AccountingEntry -> object is null.");
        }

        if (object.getId() == null) {
            throw new NullPointerException("AccountingEntry -> id is null.");
        }

        if (object.getSum() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> sum is null.");
        }

        if (object.getCode() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> code is null.");
        }

        if (object.getDocumentName() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> document name is null.");
        }

        if (object.getDate() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> date is null.");
        }

        if (object.getExpenseRequest() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request is null.");
        }

        if (object.getExpenseRequest().getNumber() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request -> number is null.");
        }

        if (object.getExpenseRequest().getConfirm() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request -> isConfirm is null.");
        }

        if (object.getExpenseRequest().getPaid() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request -> isPaid is null.");
        }

        if (object.getExpenseRequest().getCurrency() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request -> currency is null.");
        }

        if (object.getExpenseRequest().getSource() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request -> source is null.");
        }

        if (object.getExpenseRequest().getSource().getName() == null) {
            throw new NullPointerException("AccountingEntry with id:" + object.getId()
                    + " -> expense request -> source -> name is null.");
        }
    }
}

package com.company.AditionalTask;

import java.util.ArrayList;
import java.util.Objects;

public class Document {

    private ArrayList<String> documentNumber;
    private String eMail;
    private String phoneNumber;
    private boolean isValid;

    public Document(ArrayList<String> documentNumber, String eMail, String phoneNumber) {
        this.documentNumber = documentNumber;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return isValid == document.isValid && Objects.equals(documentNumber, document.documentNumber) && Objects.equals(eMail, document.eMail) && Objects.equals(phoneNumber, document.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documentNumber, eMail, phoneNumber, isValid);
    }

    public ArrayList<String> getDocumentNumber() {
        return documentNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isValid() {
        return isValid;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentNumber=" + documentNumber +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}

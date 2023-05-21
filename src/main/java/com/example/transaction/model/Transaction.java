package com.example.transaction.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable{
    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "trans_type")
    private String transType = null;

    @Column(name = "name")
    private String name=null;

    @Column(name = "entry_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date entryDate;

    @Column(name = "amount")
    private Integer amount = null;

    public Transaction() {
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transType=" + transType +
                ", entryDate=" + entryDate +
                ", amount=" + amount +
                ", name='" + name + '\'' +
                '}';
    }
    public Transaction( String transType, String name, Date entryDate, Integer amount) {
        this.transType = transType;
        this.amount = amount;
        this.entryDate = entryDate;
        this.name = name;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transType;
    }

    public void setTransactionType(String transType) {
        this.transType = transType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

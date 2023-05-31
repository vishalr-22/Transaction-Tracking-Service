package com.example.SpringBootDemo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable{
    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "transType")
    private String transType = null;

    @Column(name = "name")
    private String name=null;

    @Column(name = "entryDate")
    private String entryDate;

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
    public Transaction( String transType, String name, String entryDate, Integer amount) {
        this.transType = transType;
        this.amount = amount;
        this.entryDate = entryDate;
        this.name = name;
    }

    // public Transaction( String transType, String name, String entryDate, Integer amount) {
    //     this.transType = transType;
    //     this.amount = amount;
    //     System.out.println("kkkkkkkkkkkkkkk");
    //     try {
    //         this.entryDate =  new SimpleDateFormat("dd/MM/yyyy").parse(entryDate);
    //     } catch (ParseException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }  ;
    //     this.name = name;
    //     // SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    //     // Date parsed;
    //     // try {
    //     //     parsed = (Date) format.parse(entryDate);
    //     //     java.sql.Date edDate = new java.sql.Date(parsed.getTime());
    //     //     this.entryDate = edDate;
    //     // } catch (ParseException e) {
    //     //     // TODO Auto-generated catch block
    //     //     this.entryDate = null;   
    //     // }
    // }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

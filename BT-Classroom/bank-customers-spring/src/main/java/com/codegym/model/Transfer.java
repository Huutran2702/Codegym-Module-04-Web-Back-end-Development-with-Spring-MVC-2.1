package com.codegym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date created_at;

    private long created_by;

    @Column(columnDefinition = "tinyint(1) DEFAULT '0'")
    private int deleted;

    private Date updated_at;

    private long updated_by;

    @Column(columnDefinition = "int(11) NOT NULL")
    private int fees;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    private long fees_amount;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    private long transaction_amount;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    private long transfer_amount;

    @OneToOne
    @JoinColumn(name = "recipient_id",nullable = false)
    private Customer recipient_id;

    @OneToOne
    @JoinColumn(name = "sender_id",nullable = false)
    private Customer sender_id;

    public Transfer() {
    }

    public Transfer(long id, Date created_at, long created_by, int deleted, Date updated_at, long updated_by, int fees,
                    long fees_amount, long transaction_amount, long transfer_amount, Customer recipient_id, Customer sender_id) {
        this.id = id;
        this.created_at = created_at;
        this.created_by = created_by;
        this.deleted = deleted;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
        this.fees = fees;
        this.fees_amount = fees_amount;
        this.transaction_amount = transaction_amount;
        this.transfer_amount = transfer_amount;
        this.recipient_id = recipient_id;
        this.sender_id = sender_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(long created_by) {
        this.created_by = created_by;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public long getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(long updated_by) {
        this.updated_by = updated_by;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }

    public long getFees_amount() {
        return fees_amount;
    }

    public void setFees_amount(long fees_amount) {
        this.fees_amount = fees_amount;
    }

    public long getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(long transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public long getTransfer_amount() {
        return transfer_amount;
    }

    public void setTransfer_amount(long transfer_amount) {
        this.transfer_amount = transfer_amount;
    }

    public Customer getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(Customer recipient_id) {
        this.recipient_id = recipient_id;
    }

    public Customer getSender_id() {
        return sender_id;
    }

    public void setSender_id(Customer sender_id) {
        this.sender_id = sender_id;
    }
}

package com.codegym.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "create_at")
    private Date createdAt;

    @Column(name = "create_by")
    private long createBy;

    @Column(columnDefinition = "tinyint(1) DEFAULT '0'")
    private int deleted;

    @Column(name = "update_at")
    private Date updatedAt;

    @Column(name = "update_by")
    private long updateBy;

    @Column(columnDefinition = "int(11) NOT NULL")
    private int fees;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    private long fees_amount;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    private long transaction_amount;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    @Min(value = 50,message = "{error.transfer_amount.lt50}")
    @Max(100000000)
    @NotNull(message = "{error.transfer_amount.blank}")
    private long transfer_amount;

    @ManyToOne
    @JoinColumn(name = "recipient_id",nullable = false)
    private Customer recipient;

    @ManyToOne
    @JoinColumn(name = "sender_id",nullable = false)
    private Customer sender;

    public Transfer() {
    }

    public Transfer(long id, Date createdAt, long createBy, int deleted, Date updatedAt, long updateBy,
                    int fees, long fees_amount, long transaction_amount, long transfer_amount, Customer recipient, Customer sender) {
        this.id = id;
        this.createdAt = createdAt;
        this.createBy = createBy;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
        this.updateBy = updateBy;
        this.fees = fees;
        this.fees_amount = fees_amount;
        this.transaction_amount = transaction_amount;
        this.transfer_amount = transfer_amount;
        this.recipient = recipient;
        this.sender = sender;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
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

    public Customer getRecipient() {
        return recipient;
    }

    public void setRecipient(Customer recipient) {
        this.recipient = recipient;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }
}

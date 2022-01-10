package com.codegym.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;



@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "create_at")
    private Date createdAt;

    @Column(name = "create_by")
    private long createdBy;

    @Column(columnDefinition = "tinyint(1) DEFAULT '0'")
    private int deleted;

    @Column(name = "update_at")
    private Date updatedAt;

    @Column(name = "update_by")
    private long updateBy;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @Column(columnDefinition = "decimal(12,0)")
    @Min(value = 50,message = "{error.transaction_amount.lt50}")
    @Max(100000000)
    @NotNull(message = "{error.transaction_amount.blank}")
    private long transaction_amount;

    public Deposit() {
    }

    public Deposit(long id, Date createdAt, long createdBy, int deleted,
                   Date updatedAt, long updateBy, Customer customer, long transaction_amount) {
        this.id = id;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.deleted = deleted;
        this.updatedAt = updatedAt;
        this.updateBy = updateBy;
        this.customer = customer;
        this.transaction_amount = transaction_amount;
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

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(long transaction_amount) {
        this.transaction_amount = transaction_amount;
    }
}

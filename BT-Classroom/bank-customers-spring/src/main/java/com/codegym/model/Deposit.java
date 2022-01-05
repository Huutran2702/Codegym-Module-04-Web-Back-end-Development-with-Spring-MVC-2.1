package com.codegym.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date created_at;

    private long created_by;

    @Column(columnDefinition = "tinyint(1) DEFAULT '0'")
    private int deleted;

    private Date updated_at;

    private long updated_by;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer_id;

    @Column(columnDefinition = "decimal(12,0) NOT NULL")
    private long transaction_amount;

    public Deposit() {
    }

    public Deposit(long id, Date created_at, long created_by, int deleted, Date updated_at,
                   long updated_by, Customer customer_id, long transaction_amount) {
        this.id = id;
        this.created_at = created_at;
        this.created_by = created_by;
        this.deleted = deleted;
        this.updated_at = updated_at;
        this.updated_by = updated_by;
        this.customer_id = customer_id;
        this.transaction_amount = transaction_amount;
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

    public Customer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customer customer_id) {
        this.customer_id = customer_id;
    }

    public long getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(long transaction_amount) {
        this.transaction_amount = transaction_amount;
    }
}

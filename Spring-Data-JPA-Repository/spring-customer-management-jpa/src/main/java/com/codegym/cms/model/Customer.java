package com.codegym.cms.model;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "{error.firstName.blank}")
    @Size(min = 2,max = 15,message = "{error.firstName.outOfSize}")
    private String firstName;

    @NotBlank(message = "{error.lastName.blank}")
    @Size(min = 2,max = 15,message = "{error.lastName.outOfSize}")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    public Customer() {
    }

    public Customer( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

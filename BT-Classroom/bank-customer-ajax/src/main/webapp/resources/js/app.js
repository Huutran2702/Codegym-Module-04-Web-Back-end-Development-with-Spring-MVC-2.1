class App {

    static BASE_URL_CUSTOMER = "http://localhost:8080/customers";
    static BASE_URL_PROVINCE = "http://localhost:8091/api/provinces";

    static showDeleteConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure you want to delete the selected data ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it !',
            cancelButtonText: 'Cancel',
        })
    }

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }
}

class Customer {
    constructor(id, fullName, email, phone, address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}

class Deposit {
    constructor(id, fullName, email, phone, address) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}

$(document).ready(function() {
    $(".num-space").number(true, 0, ',', ' ');
    $(".num-point").number(true, 0, ',', '.');
    $(".num-comma").number(true, 0, ',', ',');

    $('[data-toggle="tooltip"]').tooltip();
});
let page = {
    urls: {
        getAllCustomers: App.BASE_URL_CUSTOMER,
        getCustomerById: App.BASE_URL_CUSTOMER + '/edit/',
        getDeposit: App.BASE_URL_CUSTOMER + '/deposit/',
        getWithdraw: App.BASE_URL_CUSTOMER + '/withdraw/',
        getTransfer: App.BASE_URL_CUSTOMER + '/transfer/',
        saveNew: App.BASE_URL_CUSTOMER,
        saveEdit: App.BASE_URL_CUSTOMER + '/edit/',
        doDeposit: App.BASE_URL_CUSTOMER + '/deposit/',
        doWithdraw: App.BASE_URL_CUSTOMER + '/withdraw/',
        doTransfer: App.BASE_URL_CUSTOMER + '/transfer/',
        doSuspend: App.BASE_URL_CUSTOMER + '/suspend/'
    }
}

let customer = new Customer();
let deposit = new Deposit();
let withdraw = new Withdraw();
let transfer = new Transfer();
let sender = new Sender();
let recipient = new Recipient();
let tempCustomer = jQuery.validator.format($.trim($("#tempCustomer").val()));

let addRow = () => {
    $("#tbListCustomers tbody").prepend($(tempCustomer(customer.id, customer.fullName, customer.email, customer.phone, customer.address, customer.balance)));
}

function getCustomers() {
    return $.ajax({
        type: "GET",
        url: page.urls.getAllCustomers,
    }).done((data) => {

        let str = ``;

        $.each(data, (index, item) => {
            customer = item;
            // customer.id = item.id;
            // customer.fullName = item.fullName;
            // customer.email = item.email;
            // customer.phone = item.phone;
            // customer.address = item.address;
            // customer.balance = item.balance;
            customer.balance = App.formatNumberSpace(customer.balance);
            addRow();
        });
    }).fail(function () {
        App.showErrorAlert("An error occurred. Please try again later !");
    });
}

function showCreateModal() {
    $('#modalCreateCustomer').modal('show');
}

function showUpdateModal() {
    if (customer.id == null) {
        $('#modalUpdateCustomer').modal('hide');
        $('#frmUpdateCustomer')[0].reset();
    } else {
        $.ajax({
            type: "GET",
            url: page.urls.getCustomerById + customer.id,
        }).done((resp) => {
            customer = resp;

            // assignUpdateModal();

            $("#fullNameUp").val(customer.fullName);
            $("#emailUp").val(customer.email);
            $("#phoneUp").val(customer.phone);
            $('#addressUp').val(customer.address);

            $('#modalUpdateCustomer').modal('show');
        }).fail(function () {
            App.showErrorAlert("An error occurred. Please try again later !");
        });
    }
}

function assignUpdateModal() {
    $("#upFullName").val(customer.fullName);
    $("#upEmail").val(customer.email);
    $("#upPhone").val(customer.phone);
    $('#upAddress').val(customer.address);
    $('#modalUpdateCustomer').modal('show');
}

function showDepositModal() {
    if (deposit.customerId == null) {
        $('#modalDeposit').modal('hide');
        $('#frmDeposit')[0].reset();
    } else {
        $.ajax({
            type: "GET",
            url: page.urls.getDeposit + deposit.customerId,
        }).done((resp) => {
            deposit = resp;

            $("#customerIdDep").val(deposit.customerId);
            $("#fullNameDep").val(deposit.fullName);
            $("#balanceDep").val(deposit.balance);

            $('#modalDeposit').modal('show');
        }).fail(function () {
            App.showErrorAlert("An error occurred. Please try again later !");
        });
    }
}

function showWithdrawModal() {
    if (withdraw.customerId == null) {
        $('#modalWithdraw').modal('hide');
        $('#frmWithdraw')[0].reset();
    } else {
        $.ajax({
            type: "GET",
            url: page.urls.getWithdraw + withdraw.customerId,
        }).done((resp) => {
            withdraw = resp;

            // withdraw.balance = App.formatNumberSpace(withdraw.balance);
            $("#customerIdWd").val(withdraw.customerId);
            $("#fullNameWd").val(withdraw.fullName);
            $("#balanceWd").val(withdraw.balance);

            $('#modalWithdraw').modal('show');
        }).fail(function () {
            App.showErrorAlert("An error occurred. Please try again later !");
        });
    }
}

function showTransferModal() {
    if (transfer.senderId == null) {
        $('#modalTransfer').modal('hide');
        $('#frmTransfer')[0].reset();
    } else {
        $.ajax({
            type: "GET",
            url: page.urls.getTransfer + transfer.senderId,
        }).done((resp) => {
            transfer = resp.transferDTO;

            let recipientDTOS = [];
            recipientDTOS = resp.recipientDTOS;

            let str = ``;

            $.each(recipientDTOS, (index, item) => {
                str += `<option value="${item.recipientId}">(${item.recipientId}) ${item.recipientName}</option>`;
            });

            $("#recipientIdTrf").html(str);

            $("#senderIdTrf").val(transfer.senderId);
            $("#senderNameTrf").val(transfer.senderName);
            $("#emailTrf").val(transfer.email);
            $("#balanceTrf").val(transfer.balance);

            $('#modalTransfer').modal('show');
        }).fail(function () {
            App.showErrorAlert("An error occurred. Please try again later !");
        });
    }
}

function showSuspendedModal() {
    App.showSuspendedConfirmDialog()
        .then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "POST",
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
                    url: page.urls.doSuspend + customer.id
                }).done(() => {
                    App.showSuccessAlert("Succeeded client suspension!");
                    $("#tr_" + customer.id).remove();
                }).fail(() => {
                    App.showErrorAlert("An error occurred. Please try again later!");
                });
            }
        });
}

$("#btnCreateCustomer").on("click", function () {
    customer.fullName = $("#fullName").val();
    customer.email = $("#email").val();
    customer.phone = $("#phone").val();
    customer.address = $("#address").val();

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.saveNew,
        data: JSON.stringify(customer)
    }).done((data) => {
        customer = data;
        addRow();

        App.showSuccessAlert("Successful data generation !");
        App.formatTooltip();

        $('#modalCreateCustomer').modal('hide');

    }).fail((jqXHR, textStatus, errorThrown) => {
        $("#modalCreateCustomer .modal-body .modal-alert-danger").empty();
        $("#modalCreateCustomer .modal-body .modal-alert-danger").removeClass("hide").addClass("show");

        let str = ``;
        $.each(jqXHR.responseJSON, function (key, value) {
            str += `<label id="${key}-error" class="error" for="${key}">${value}</label>`;
            $("#" + key).addClass("error");
        });
        $("#modalCreateCustomer .modal-body .modal-alert-danger").html(str);
    });
});

$("#btnUpdateCustomer").on("click", function () {
    customer.fullName = $("#fullNameUp").val();
    customer.email = $("#emailUp").val();
    customer.phone = $("#phoneUp").val();
    customer.address = $("#addressUp").val();

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.saveEdit,
        data: JSON.stringify(customer)
    }).done((resp) => {
        customer = resp;
        customer.balance = App.formatNumberSpace(customer.balance);
        let current_row = $("#tbListCustomers tbody").find('#' + $("#currentRow").val());
        let updated_row = $(tempCustomer(customer.id, customer.fullName, customer.email, customer.phone, customer.address, customer.balance))
        current_row.replaceWith(updated_row);

        $("#currentRow").val("");
        $("#frmUpdateCustomer")[0].reset();
        $("#frmUpdateCustomer").validate().resetForm();

        App.showSuccessAlert("Data update successful !");
        App.formatTooltip();

        $('#modalUpdateCustomer').modal('hide');

    }).fail((jqXHR) => {
        $("#modalUpdateCustomer .modal-body .modal-alert-danger").empty();
        $("#modalUpdateCustomer .modal-body .modal-alert-danger").removeClass("hide").addClass("show");

        let str = ``;
        $.each(jqXHR.responseJSON, function (key, value) {
            str += `<label id="${key}Up-error" class="error" for="${key}Up">${value}</label>`;
            $("#" + key + "Up").addClass("error");
        });
        $("#modalUpdateCustomer .modal-body .modal-alert-danger").html(str);
    });
});

$("#btnDepositMoney").on("click", function () {
    deposit.customerId = $("#customerIdDep").val();
    deposit.transactionAmount = $("#transactionAmountDep").val();
    deposit.balance = App.removeFormatNumberSpace(deposit.balance);

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.doDeposit,
        data: JSON.stringify(deposit)
    }).done((resp) => {
        customer = resp;

        customer.balance = App.formatNumberSpace(customer.balance);
        let current_row = $("#tbListCustomers tbody").find('#' + $("#currentRow").val());
        let updated_row = $(tempCustomer(customer.id, customer.fullName, customer.email, customer.phone, customer.address, customer.balance))
        current_row.replaceWith(updated_row);

        App.showSuccessAlert("Successful deposit transaction !");
        App.formatTooltip();

        $('#modalDeposit').modal('hide');

    }).fail((jqXHR) => {
        $("#modalDeposit .modal-body .modal-alert-danger").empty();
        $("#modalDeposit .modal-body .modal-alert-danger").removeClass("hide").addClass("show");

        let str = ``;
        $.each(jqXHR.responseJSON, function (key, value) {
            str += `<label id="${key}Dep-error" class="error" for="${key}Dep">${value}</label>`;
            $("#" + key + "Dep").addClass("error");
        });
        $("#modalDeposit .modal-body .modal-alert-danger").html(str);
    });
});

$("#btnWithdrawMoney").on("click", function () {
    withdraw.customerId = $("#customerIdWd").val();
    withdraw.transactionAmount = $("#transactionAmountWd").val();
    withdraw.balance = App.removeFormatNumberSpace(withdraw.balance);

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.doWithdraw,
        data: JSON.stringify(withdraw)
    }).done((resp) => {
        customer = resp;

        customer.balance = App.formatNumberSpace(customer.balance);
        let current_row = $("#tbListCustomers tbody").find('#' + $("#currentRow").val());
        let updated_row = $(tempCustomer(customer.id, customer.fullName, customer.email, customer.phone, customer.address, customer.balance))
        current_row.replaceWith(updated_row);

        App.showSuccessAlert("Successful withdraw transaction !");
        App.formatTooltip();

        $('#modalWithdraw').modal('hide');

    }).fail((jqXHR) => {
        $("#modalWithdraw .modal-body .modal-alert-danger").empty();
        $("#modalWithdraw .modal-body .modal-alert-danger").removeClass("hide").addClass("show");

        let str = ``;
        $.each(jqXHR.responseJSON, function (key, value) {
            str += `<label id="${key}Wd-error" class="error" for="${key}Wd">${value}</label>`;
            $("#" + key + "Wd").addClass("error");
        });
        $("#modalWithdraw .modal-body .modal-alert-danger").html(str);
    });
});

$("#btnTransferMoney").on("click", function () {
    transfer.transferAmount = App.removeFormatNumberSpace($("#transferAmountTrf").val());
    transfer.recipientId = $("#recipientIdTrf").val();

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: page.urls.doTransfer,
        data: JSON.stringify(transfer)
    }).done((resp) => {
        sender = resp.sender;
        recipient = resp.recipient;

        $("#balanceTrf").val(sender.balance);

        sender.balance = App.formatNumberSpace(sender.balance);
        let current_row_sender = $("#tbListCustomers tbody").find('#tr_' + sender.id);
        let updated_row_sender = $(tempCustomer(sender.id, sender.fullName, sender.email, sender.phone, sender.address, sender.balance))
        current_row_sender.replaceWith(updated_row_sender);

        recipient.balance = App.formatNumberSpace(recipient.balance);
        let current_row_recipient = $("#tbListCustomers tbody").find('#tr_' + recipient.id);
        let updated_row_recipient = $(tempCustomer(recipient.id, recipient.fullName, recipient.email, recipient.phone, recipient.address, recipient.balance))
        current_row_recipient.replaceWith(updated_row_recipient);

        $("#transferAmountTrf").val("");

        App.showSuccessAlert("Successful transfer transaction !");
        App.formatTooltip();

    }).fail((jqXHR) => {

        $("#modalTransfer .modal-body .modal-alert-danger").empty();
        $("#modalTransfer .modal-body .modal-alert-danger").removeClass("hide").addClass("show");

        let str = ``;
        if (jqXHR.responseJSON.message) {
            str += `<label id="message-error" class="error" for="message">${jqXHR.responseJSON.message}</label>`;
        } else {
            $.each(jqXHR.responseJSON, function (key, value) {
                str += `<label id="${key}Trf-error" class="error" for="${key}Trf">${value}</label>`;
                $("#" + key + "Trf").addClass("error");
            });
        }

        $("#modalTransfer .modal-body .modal-alert-danger").html(str);
    });
});

$("a.create-modal").on("click", function () {
    delete customer.id;
    delete customer.balance;
    showCreateModal();
});

$("#tbListCustomers").on("click", ".edit", function () {
    customer.id = $(this).data('id');
    $("#currentRow").val($(this).closest("tr").attr("id"));
    showUpdateModal();
});

$("#tbListCustomers").on("click", ".deposit", function () {
    deposit.customerId = $(this).data('id');
    $("#currentRow").val($(this).closest("tr").attr("id"));
    showDepositModal();
});

$("#tbListCustomers").on("click", ".withdraw", function () {
    withdraw.customerId = $(this).data('id');
    $("#currentRow").val($(this).closest("tr").attr("id"));
    showWithdrawModal();
});

$("#tbListCustomers").on("click", ".transfer", function () {
    transfer.senderId = $(this).data('id');
    $("#currentRow").val($(this).closest("tr").attr("id"));
    showTransferModal();
});

$("#transferAmountTrf").on("change", function () {
    let transferAmount = $(this).val();
    let fees = 10;
    let feeAmount = transferAmount * fees / 100;
    let transactionAmount = parseInt(transferAmount) + feeAmount;
    $("#transactionAmountTrf").val(transactionAmount);
});

$("#tbListCustomers").on("click", ".suspended", function () {
    customer.id = $(this).data('id');
    $("#currentRow").val($(this).closest("tr").attr("id"));
    showSuspendedModal();
});

$('#modalCreateCustomer').on('hidden.bs.modal', function () {
    $("#currentRow").val("");
    $("#modalCreateCustomer .modal-body .modal-alert-danger").empty();
    $("#modalCreateCustomer .modal-body .modal-alert-danger").removeClass("show").addClass("hide");
    $("#modalCreateCustomer input.error").removeClass("error");
    $('#frmCreateCustomer')[0].reset();
    $('#frmCreateCustomer').validate().resetForm();
});

$('#modalUpdateCustomer').on('hidden.bs.modal', function () {
    $("#currentRow").val("");
    $("#modalUpdateCustomer .modal-body .modal-alert-danger").empty();
    $("#modalUpdateCustomer .modal-body .modal-alert-danger").removeClass("show").addClass("hide");
    $("#modalUpdateCustomer input.error").removeClass("error");
    $('#frmUpdateCustomer')[0].reset();
    $('#frmUpdateCustomer').validate().resetForm();
});

$('#modalDeposit').on('hidden.bs.modal', function () {
    $("#currentRow").val("");
    $("#modalDeposit .modal-body .modal-alert-danger").empty();
    $("#modalDeposit .modal-body .modal-alert-danger").removeClass("show").addClass("hide");
    $("#modalDeposit input.error").removeClass("error");
    $('#frmDeposit')[0].reset();
    $('#frmDeposit').validate().resetForm();
});

$('#modalWithdraw').on('hidden.bs.modal', function () {
    $("#currentRow").val("");
    $("#modalWithdraw .modal-body .modal-alert-danger").empty();
    $("#modalWithdraw .modal-body .modal-alert-danger").removeClass("show").addClass("hide");
    $("#modalWithdraw input.error").removeClass("error");
    $('#frmWithdraw')[0].reset();
    $('#frmWithdraw').validate().resetForm();
});

$('#modalTransfer').on('hidden.bs.modal', function () {
    $("#currentRow").val("");
    $("#modalTransfer .modal-body .modal-alert-danger").empty();
    $("#modalTransfer .modal-body .modal-alert-danger").removeClass("show").addClass("hide");
    $("#modalTransfer input.error").removeClass("error");
    $('#frmTransfer')[0].reset();
    $('#frmTransfer').validate().resetForm();
});

$(document).ready(function () {
    getCustomers().then(function () {
        $("#frmDeposit .num-space").number(true, 0, ',', ' ');
        $("#frmWithdraw .num-space").number(true, 0, ',', ' ');
        $("#frmTransfer .num-space").number(true, 0, ',', ' ');
        $('[data-toggle="tooltip"]').tooltip();
    });
});


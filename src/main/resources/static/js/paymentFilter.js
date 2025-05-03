function filterCustomer() {
    var customerId = document.getElementById('customerIdInput').value;

    if (customerId.trim() === '') {
        return;
    }

    fetch('/payments/filter?customerId=' + customerId)
        .then(response => response.json())
        .then(data => {
            var tableBody = document.querySelector('tbody');
            tableBody.innerHTML = '';

            if (data.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="5" class="text-center">No payments found</td></tr>';
            } else {
                data.forEach(function(customer) {
                    var row = `
                        <tr>
                            <td>${customer.id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.totalAmount}</td>
                            <td>${customer.paidAmount}</td>
                            <td>${customer.pendingAmount}</td>
                        </tr>
                    `;
                    tableBody.innerHTML += row;
                });
            }
        })
        .catch(error => console.error('Error:', error));
}


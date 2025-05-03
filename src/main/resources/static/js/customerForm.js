document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("roomNumber").addEventListener("change", function () {
        var roomNumber = this.value;
        if (roomNumber) {
            fetch(`/rooms/` + roomNumber)
                .then(response => {
                    if (!response.ok) {
                        throw new Error("Room not found");
                    }
                    return response.json();
                })
                .then(data => {
                    document.getElementById("capacity").value = data.capacity;
                    document.getElementById("occupied").value = data.occupied;
                    document.getElementById("vacant").value = data.vacant;
                })
                .catch(error => {
                    console.error("Error fetching room details:", error);
                    document.getElementById("capacity").value = 0;
                    document.getElementById("occupied").value = 0;
                    document.getElementById("vacant").value = 0;
                });
        } else {
            document.getElementById("capacity").value = 0;
            document.getElementById("occupied").value = 0;
            document.getElementById("vacant").value = 0;
        }
    });

        const totalAmountInput = document.getElementById("totalAmount");
        const paidAmountInput = document.getElementById("paidAmount");
        const pendingAmountInput = document.getElementById("pendingAmount");

        function updatePendingAmount(){
            let totalAmount = parseFloat(totalAmountInput.value) || 0;
            let paidAmount = parseFloat(paidAmountInput.value) || 0;
            let pendingAmount = totalAmount - paidAmount;

            pendingAmountInput.value = pendingAmount >=0 ? pendingAmount.toFixed(2) : 0;
        }

        if(paidAmountInput){
            paidAmountInput.addEventListener("input",updatePendingAmount);
        }

        function validateFrom(){
            let phone = document.getElementById("phone").value;
            let aadhaar = document.getElementById("aadhaar").value;
            let phoneError = document.getElementById("phoneError");
            let aadhaarError = document.getElementById("aadhaarError");

            phoneError.innerHTML ="";
            aadhaarError.innerHTML = "";

            if(!/^\d{10}$/.test(phone)){
              phoneError.innerHTML("Phone number must be 10 digits");
              return false;
            }

             if(!/^\d{12}$/.test(aadhaar)){
              aadhaarError.innerHTML("Aadhaar number must be 12 digits");
              return false;
             }
           return true;
            }

});

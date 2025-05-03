    package com.example.pgmanagement.model;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotBlank;
    import jakarta.validation.constraints.Pattern;

    import java.math.BigDecimal;
    import java.time.LocalDate;




    @Entity
    @Table(name = "customer")
    public class CustomerEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @NotBlank(message = "Email is requires")
        @Email(message = "Invalid email format")
        private String email;
        @NotBlank(message = "Phone Number is Required")
        @Pattern(regexp = "^[0-9]{10}$",message = "Phone number must be 10 digits")
        private String phone;
        private String roomNumber;
        @Min(value = 0,message = "Paid amount cannot be negative")
        private int paidAmount;

        @Column(name = "pending_amount")
        private BigDecimal pendingAmount;
        @NotBlank(message = "Aadhaar number is Requires")
        @Pattern(regexp = "^[0-9]{12}$",message = "Aadhaar number must be 12 digits")
        private String AadhaarNo;
        private String address;
        private String company;
        private BigDecimal totalAmount;
        private LocalDate joinDate;
        private Boolean active;
        private LocalDate PaymentDate;

        public RoomEntity getRoom() {
            return room;
        }

        public void setRoom(RoomEntity room) {
            this.room = room;
        }

        @PrePersist
        public void setDefaultJoinDate(){
            if(joinDate == null){
                joinDate = LocalDate.now();
            }
        }

        @ManyToOne
        @JoinColumn(name = "room_id")
        private RoomEntity room;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDate getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(LocalDate joinDate) {
            this.joinDate = joinDate;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public LocalDate getPaymentDate() {
            return PaymentDate;
        }

        public void setPaymentDate(LocalDate paymentDate) {
            PaymentDate = paymentDate;
        }


        public BigDecimal getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
        }

        // Getters and Setters
        public int getPaidAmount() {
            return paidAmount;
        }

        public void setPaidAmount(int paidAmount) {
            this.paidAmount = paidAmount;
        }


        public String getAadhaarNo() {
            return AadhaarNo;
        }

        public void setAadhaarNo(String aadhaarNo) {
            this.AadhaarNo = aadhaarNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public BigDecimal getPendingAmount() {
            return pendingAmount;
        }

        public void setPendingAmount(BigDecimal pendingAmount) {
            this.pendingAmount = pendingAmount;
        }

    }

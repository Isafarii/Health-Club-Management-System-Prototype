public class Member {
    private String memberName;
    private String memberId;
    private int memberAge;
    private String paymentMethod;
    private String lastSignIn;
    private String qrCodeId; 
    private String memberEmail;
    private String expirationDate;

    // Constructor matching the file format
    public Member(String memberName, int memberAge, String memberId, String paymentMethod, String lastSignIn, 
                  String qrCodeId, String memberEmail, String expirationDate) {
        this.memberName = memberName;
        this.memberAge = memberAge;
        this.memberId = memberId;
        this.paymentMethod = paymentMethod;
        this.lastSignIn = lastSignIn;
        this.qrCodeId = qrCodeId;
        this.memberEmail = memberEmail;
        this.expirationDate = expirationDate;
    }
    
    // toString method
    public String toString() {
        return "Member Name: " + memberName +
               "\nMember Age: " + memberAge +
               "\nMember ID: " + memberId +
               "\nPayment Method: " + paymentMethod +
               "\nLast Sign In: " + lastSignIn +
               "\nQR Code ID: " + qrCodeId +
               "\nMember Email: " + memberEmail +
               "\nExpiration Date: " + expirationDate;
    }


    // Getters and Setters
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }

    public int getMemberAge() { return memberAge; }
    public void setMemberAge(int memberAge) { this.memberAge = memberAge;}

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getLastSignIn() { return lastSignIn; }
    public void setLastSignIn(String lastSignIn) { this.lastSignIn = lastSignIn; }

    public String getQrCodeId() { return qrCodeId; }
    public void setQrCodeId(String qrCodeId) { this.qrCodeId = qrCodeId; }

    public String getMemberEmail() { return memberEmail; }
    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }
}

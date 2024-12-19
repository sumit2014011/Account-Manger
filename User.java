
class User {

    private long Balance = 0L;
    private String Name;
    private String Mob_NUM;
    private String User_id;
    private String Email;
    private String pin = null;
    private String CardNumber;
    public String date;
    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.CardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setMob_NUM(String Mob_NUM) {
        this.Mob_NUM = Mob_NUM;
    }

    public void setUser_id(String User_id) {
        this.User_id = User_id;
    }

    public void setBalance(long Balance) {
        this.Balance = Balance;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getName() {
        return Name;
    }

    public long getBalance() {
        return Balance;
    }

    public String getMob_NUM() {
        return Mob_NUM;
    }

    public String getUser_id() {
        return User_id;
    }

    public String getEmail() {
        return Email;
    }
}

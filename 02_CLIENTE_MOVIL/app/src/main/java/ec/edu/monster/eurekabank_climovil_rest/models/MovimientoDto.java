package ec.edu.monster.eurekabank_climovil_rest.models;

public class MovimientoDto {
    private String account;
    private double amount;

    public MovimientoDto(String account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    // Getters y setters
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

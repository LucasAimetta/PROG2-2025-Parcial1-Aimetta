package empresa.dto;

public class nuevoPago {
    private int idContrato;
    private double amount;

    public nuevoPago(int idContrato, double amount) {
        this.idContrato = idContrato;
        this.amount = amount;
    }

    public nuevoPago() {
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

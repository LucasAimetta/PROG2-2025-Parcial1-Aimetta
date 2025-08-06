package empresa.dto;

public class NuevoPago {
    private int idContrato;
    private double amount;

    public NuevoPago(int idContrato, double amount) {
        this.idContrato = idContrato;
        this.amount = amount;
    }

    public NuevoPago() {
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

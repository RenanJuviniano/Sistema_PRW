public class Cliente {
    private String nomeCliente;
    private String emailCliente;
    private double gastos;
    private String pratoPreferido;

    public Cliente(String nomeCliente, String emailCliente) {
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.gastos = 0.0;
        this.pratoPreferido = "Prato preferido não definido";
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setPratoPreferido(String pratoPreferido) {
        this.pratoPreferido = pratoPreferido;
    }

    public double getGastos() {
        return gastos;
    }

    public void gastosRestaurantes(double valor) {
        gastos += valor;
    }

    @Override
    public String toString() {
        if (!pratoPreferido.equals("Prato preferido não definido")) {
            return "Cliente: " + nomeCliente + "," + pratoPreferido;
        } else {
            return "Cliente: " + nomeCliente;
        }
    }
}
import java.util.Arrays;
import java.util.Objects;
import java.util.HashMap;

public class Restaurante {
    private String nomeRestaurante;
    private String prato;
    private int votos;
    private HashMap<String, Cliente> clientes;
    private LivroVisitas livroVisitas;
    private int totalVisitas;
    private HashMap<String, Integer> visitasPorCliente;

    public Restaurante(String nomeRestaurante, String prato ){
        this.nomeRestaurante = nomeRestaurante;
        this.prato = prato;
        this.votos = 0;
        this.clientes = new HashMap<>();
        this.livroVisitas = new LivroVisitas();
        this.totalVisitas = 0;
        this.visitasPorCliente = new HashMap<>();
    }
    public String getPrato(){
        return prato;
    }
    public int getVotos(){
        return votos;
    }
    public String getNomeRestaurante(){
        return nomeRestaurante;
    }
    public void adicionarVoto(){
        votos++;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        Restaurante outro = (Restaurante) obj;
        return nomeRestaurante.equals(outro.nomeRestaurante);
    }
    @Override
    public int hashCode(){
        return Objects.hash(nomeRestaurante);
    }

    public void registraVisita(Cliente cliente, String data, String comentario){
        String email = cliente.getEmailCliente();

        clientes.put(email, cliente);

        totalVisitas++;

        visitasPorCliente.put(email, visitasPorCliente.getOrDefault(email, 0) + 1);

        livroVisitas.adicionarVisita(email, comentario, data);
    }

    public boolean contemCliente(String email){
        return clientes.containsKey(email);
    }

    public String[] listarClientes(){
        return clientes.keySet().toArray(new String[0]);
    }

    public String[] listarLivroVisitas(){
        return livroVisitas.listarInfoRegistradas();
    }

    public String[] listarLivroVisitas(int quantidade){
        String[] visitas = livroVisitas.infoRecentes(quantidade);
        int total = Math.min(quantidade, visitas.length);
        if(total < 0){
            total = 0;
        }
        return Arrays.copyOf(visitas, total);
    }
    public int getTotalVisitas(){
        return totalVisitas;
    }

    public int getTotalClientes(){
        return clientes.size();
    }

    public HashMap<String, Integer> getVisitasPorCliente(){
        return new HashMap<>(visitasPorCliente);
    }
}
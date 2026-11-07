import java.util.ArrayList;

public class LivroVisitas {
    private ArrayList<String> visitas;

    public LivroVisitas(){
        this.visitas = new ArrayList<>();
    }

    public void adicionarVisita(String nomeCliente, String comentarioCliente, String data){
       String visita = "Data: " + data + ", Cliente: " + nomeCliente;

       if (comentarioCliente != null && !comentarioCliente.isBlank()){
           visita += ", Comentário: " + comentarioCliente;
       }
       visitas.add(visita);
    }

    public String[] listarInfoRegistradas(){
        return visitas.toArray(new String[0]);
    }
    public String[] InfoRecentes(){
        int inicio = Math.max(0, visitas,size() - quantidade);
        String[] recentes = new String[visitas.size() - inicio];
        for(int i = inicio; i < visitas.size(); i++){
            recentes[i - inicio] = visitas.get(i);
        }
        return recentes;
    }
}

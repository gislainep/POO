package trabalho;

import java.io.*;		import javax.swing.JOptionPane;
class ProgramaCadastro {
   static void salvar(Pilha cadastro) {
       FileOutputStream fo;        ObjectOutputStream oo;
       try {
           fo = new FileOutputStream("D:\\Faculdade\\terceiro periodo\\Programacao_Orientada_a_Objeto\\aps2\\aps2\\src\\aps2.obj");
           oo = new ObjectOutputStream(fo);
           oo.writeObject(cadastro);
           oo.close();
           JOptionPane.showMessageDialog(null, "O cadastro foi salvo em arquivo.");
       } catch(IOException e) {
           JOptionPane.showMessageDialog(null,
                   "Não foi possível salvar o cadastro em arquivo!");
       }
   }
   static Pilha ler() {
       FileInputStream fi;        ObjectInputStream oi;        Pilha cadastroLido;
       try {
           fi = new FileInputStream("D:\\\\Faculdade\\\\terceiro periodo\\\\Programacao_Orientada_a_Objeto\\\\aps2\\\\aps2\\\\src\\\\aps2.obj");
           oi = new ObjectInputStream(fi);
           cadastroLido = (Pilha) oi.readObject();
           oi.close();
           JOptionPane.showMessageDialog(null, "O cadastro foi lido do arquivo.");
       } catch(Exception e) {
           JOptionPane.showMessageDialog(null,
                   "Não foi possível ler o cadastro do arquivo!");
           cadastroLido = new Pilha();
       }
       return cadastroLido;
   }
   static void inserirCliente(Pilha cadastro) {
       String resposta,marca,respostaGb,respostaCapacidade;        int mhz,gb,capacidade;        Computador com; Memoria memoria; Hd hd;
       
       marca = JOptionPane.showInputDialog("Digite a marca do HD");
       resposta = JOptionPane.showInputDialog("Digite a quantidade de Mhz da memoria");
       mhz = Integer.parseInt(resposta);
       respostaGb = JOptionPane.showInputDialog("Digite a quantidade de Gb da memoria");
       gb = Integer.parseInt(respostaGb);
       respostaCapacidade = JOptionPane.showInputDialog("Digite a capacidade do Hd"); 
       capacidade = Integer.parseInt(respostaCapacidade);
       memoria = new Memoria(mhz, gb);
       hd = new Hd(marca, capacidade);
       com = new Computador (memoria,hd);
       try {
           cadastro.inserir(com);
           JOptionPane.showMessageDialog(null, "O dados do computador foram inseridos no cadastro.");
       } catch(PilhaCheiaException e) {
           JOptionPane.showMessageDialog(null, "Cadastro cheio!!!");
       } 
   }

   static void removerCliente(Pilha cadastro) {
       Computador com; Memoria memoria ; Hd hd;
       
       try {
           com = cadastro.remover();
           JOptionPane.showMessageDialog(null,
               "Foi removido o cliente abaixo:\n" +
               "- Nome: " + memoria.getMhz() +
               "\n- Telefone: " + memoria.getGb()+
               "- Nome: " + hd.getCapacidade() +
               "\n- Telefone: " + hd.getMarca());
       } catch(PilhaVaziaException e) {
           JOptionPane.showMessageDialog(null, "Cadastro vazio!!!");
       }
   }
   public static void main(String[] args) {
       Pilha cadastro;        String opcao;
       
       cadastro = new Pilha();
       for(;;)
       {
           opcao = JOptionPane.showInputDialog("Digite a opção:\n" +
                   "1 - Salvar o cadastro no arquivo.\n" +
                   "2 - Ler um cadastro do arquivo.\n" +
                   "3 - Inserir um computador no cadastro.\n" +
                   "4 - Remover um cliente do cadastro.\n" +
                   "5 - Terminar o programa.");
           if(opcao == null || opcao.equals("5"))
               break;
           else
               if(opcao.equals("1"))
                   salvar(cadastro);
               else
                   if(opcao.equals("2"))
                       cadastro = ler();
                   else
                       if(opcao.equals("3"))
                           inserirCliente(cadastro);
                       else
                           if(opcao.equals("4"))
                               removerCliente(cadastro);
       }
   }
}

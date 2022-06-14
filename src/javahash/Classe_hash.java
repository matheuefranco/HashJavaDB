/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javahash;
import java.security.MessageDigest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mefma
 */
public class Classe_hash {
    
   
    public ArrayList<String> usuarios = new ArrayList();
    public ArrayList<String> senhas = new ArrayList();
    
    public void salvaArquivo(){
        FileWriter salva=null;
        String csvFile = "dados.csv";
        try {

            salva = new FileWriter(csvFile); 
            for(int i=0;i<usuarios.size();i++){
                 salva.append(usuarios.get(i));
                 salva.append(",");
                 salva.append(senhas.get(i));
                 salva.append("\n");
            }
             System.out.println("Dados salvos");
          }catch (Exception e) {

            System.out.println(e.getMessage()+ " Error in CsvFileWriter !!!");
        }
   
        try {
            salva.flush();
            salva.close();
        } catch (IOException ex) {
            System.out.println("Erro"+ex.getMessage());
        }          
    }
  //------------------------------------------------  
    public void carregaArquivo(){
       String csvFile = "dados.csv";
        String line = "";
        String[] user = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                 user = line.split(",");
                System.out.println("Usuario:"+user[0]+ " hash_senha= " + user[1]);
                usuarios.add(user[0]);
                senhas.add(user[1]);
            }// fim percurso no arquivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
public String makeHash(String senha){
   String resultado="";
    try {
            
            MessageDigest algorithm = null;
            algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(senha.getBytes("UTF-8")); 
            resultado=toHexFormat(messageDigest);
            System.out.println("Hash de "+senha+ "="+resultado);
        } catch (Exception ex) {
            System.out.println(ex);
        }
   return resultado;
}

private String toHexFormat(final byte[] bytes) {
  final StringBuilder sb = new StringBuilder();
  for (byte b : bytes) {
    sb.append(String.format("%02x", b));
  }
 
  return sb.toString();
}

    
}

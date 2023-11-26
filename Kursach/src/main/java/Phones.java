import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Phones {
    static HashMap<String, Vector<String>> book;
    public Phones(){
        book = new HashMap<String, Vector<String>>();
    }
    public synchronized void add(String name){
        boolean inB = false;
        Vector<String> v = new Vector<>();
        for (Map.Entry<String, Vector<String>> pair : book.entrySet()) {
            if(pair.getKey().equals(name)){
                inB = true;
                break;
            }             
        }
        if(!inB) book.put(name, v);
    }
    public synchronized void add(String name, String phone){
        boolean inB = false;
        Vector<String> v = new Vector<>();
        for (Map.Entry<String, Vector<String>> pair : book.entrySet()) {
            if(pair.getKey().equals(name)){
                inB = true;
                //v.add(phone);
                pair.getValue().add("");
                
                break;
            }             
        }

        if(!inB){
            v.add(phone);
            book.put(name, v);
        } 
    }
    public synchronized void add(String name, String bug, String desc, String person, String open ){
        boolean inB = false;
        Vector<String> v = new Vector<>();
        for (Map.Entry<String, Vector<String>> pair : book.entrySet()) {
            if(pair.getKey().equals(name)){
                inB = true;
                pair.getValue().clear();
                //v.add(phone);
                pair.getValue().add(bug);
                pair.getValue().add(desc);
                pair.getValue().add(person);
                pair.getValue().add(open);
                
                break;
            }             
        }

        if(!inB){
            v.add(bug);
            v.add(desc);
            v.add(person);
            v.add(open);
            book.put(name, v);
        } 
    }
    public synchronized void delete(String name){
        book.remove(name);
    }
    public synchronized void delete(String name, String phone){
        for (Map.Entry<String, Vector<String>> pair : book.entrySet()) {
            if(pair.getKey().equals(name)){
                
                pair.getValue().removeElement((String)phone);
                
                break;
            }             
        }
    }
    public String toStr(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Vector<String>> pair : book.entrySet()) {
                sb.append(pair.getKey() + "&");
                sb.append(pair.getValue().get(0) + "&");
                sb.append(pair.getValue().get(1) + "&");
                sb.append(pair.getValue().get(2) + "&");
                sb.append(pair.getValue().get(3) + "&\n");
            
        }

        return sb.toString();
    }

    public void saveToTextFile(){
        String str = toStr();
        try{
            Reader in = new StringReader(str);
            FileOutputStream out = new FileOutputStream("bugs.txt");
            int c = 0;
            while (( c = in.read()) >= 0) {
                out.write(c);
            }
        }
        catch(IOException err){
            System.out.println("Error in saving to text");
        }
        

    }
    public void loadFromTextFile(){
        try{
            book.clear();
            Scanner reader = new Scanner(new FileInputStream("bugs.txt"));
            while( reader.hasNextLine()){
                String str = reader.nextLine();
                String name = "";
                int start = 0;
                int count = 0;
                Vector<String> v = new Vector<String>();
                for(int i = 0; i < str.length(); i++){
                    if(str.charAt(i) == '&'){
                        if(count == 0){
                            name = str.substring(start, i);
                            count++;
                            start = i + 1;
                        }
                        else{
                            v.add(str.substring(start, i));
                            start = i + 1;

                        }
                    }
                }
                book.put(name, v);
            }  
            
        } 
        catch(IOException err){
            System.out.println("Error in loading from text");
        }
    }
    public int size(){
        return book.size();
    }
    public Vector<String> getValue(String key){
        return book.get(key);
    }

}

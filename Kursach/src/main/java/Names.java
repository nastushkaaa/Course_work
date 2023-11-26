import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Names {
    static HashMap<String, String> names;

    public Names(){
        names = new HashMap<String, String>();
    }
    public synchronized void add(String name, String password){
        names.put(name, password);
    }
    public boolean check_name(String name){
        return names.containsKey(name);
    }
    public boolean check_password(String name, String password){
        return password.equals(names.get(name));
    }
    public String toStr(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> pair : names.entrySet()) {
                sb.append(pair.getKey() + "-" + pair.getValue() + "\n");
            
        }

        return sb.toString();
    }
    public void loadFromTextFile(){
        try{
            names.clear();
            Scanner reader = new Scanner(new FileInputStream("names.txt"));
            while( reader.hasNextLine()){
                String str = reader.nextLine();
                String name = "";
                String password = "";
                for(int i = 0; i < str.length(); i++){
                    if(str.charAt(i) == '-'){
                        name = str.substring(0, i);
                        password = str.substring(i + 1);
                        continue;
                    }
                }
                names.put(name, password);
            }  
            
        } 
        
        catch(IOException err){
            System.out.println("Error in loading from text");
        }
    }
    public void saveToTextFile(){
        String str = toStr();
        try{
            Reader in = new StringReader(str);
            FileOutputStream out = new FileOutputStream("names.txt");
            int c = 0;
            while (( c = in.read()) >= 0) {
                out.write(c);
            }
        }
        catch(IOException err){
            System.out.println("Error in saving to text");
        }
        

    }
}

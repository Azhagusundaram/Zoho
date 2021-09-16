package datastructuresandoops;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Functionalities {
    private Map<Character,Integer> tempMap =new HashMap<>();
    private Map<Character,Integer> finalMap =new HashMap<>();

    public String set(char variable,int value){
        tempMap.put(variable,value);
        return "set value successfully";
    }
    public String get(char variable){
        if(tempMap.containsKey(variable)){
            return tempMap.get(variable)+"";
        }
        return "No variable name as "+variable;
    }
    public String unSet(char variable){
        tempMap.put(variable,null);
        return "unset value successfully";
    }
    public String update(char variable, int value){
        if(tempMap.containsKey(variable)){
            tempMap.put(variable,value);
            return "updated successfully";
        }
        return "No variable name as "+variable;
    }
    public int count(int value){
        Set<Character> variables= tempMap.keySet();
        int count=0;
        for(char c:variables){
            int variableValue= tempMap.get(c);
            if(variableValue==value)
                count++;
        }
        return count;
    }
    public void begin(){
        finalMap = tempMap;
        tempMap =new HashMap<>();
    }
    public void rollBack(){
        tempMap=new HashMap<>();
        tempMap.putAll(finalMap);
    }
    public void commit(){
        finalMap.putAll(tempMap);
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;


public class Cluster {
	HashMap<String,Integer> frequencies;
	String rep;
	
	
	public Cluster(String value){
		frequencies=new HashMap<String, Integer>();
		frequencies.put(value,1);
		rep=value;
		
	}
	
	public int getFrequencyOfRep(){
		return frequencies.get(rep);
	}
	
	public String getRep(){
		return rep;
	}
	
	public HashMap<String,Integer> getFrequencies(){
			return frequencies;
	}
	
	public void merge(Cluster arg){
	
		 for (Entry<String, Integer> entry : arg.getFrequencies().entrySet())
		 {
			 String key=entry.getKey();
			 int value= entry.getValue();
			 int newFrequency=0;
			 if(frequencies.containsKey(key))
				 newFrequency=value+frequencies.get(key);
			 else
				 newFrequency=value;
			 frequencies.put(key,newFrequency);
			 if(newFrequency>getFrequencyOfRep())
				 rep=key;
		 }
	}
	
	public String toString(){
		String result="";
		result+="Rep :"+rep+"\n";
		for (Entry<String, Integer> entry : frequencies.entrySet())
		 {
			result+=entry.getKey()+" : "+entry.getValue()+"\n";
			
		 }
		return result;
		
	}
	
	
	
}

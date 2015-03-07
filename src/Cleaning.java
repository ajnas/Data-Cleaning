import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;



public class Cleaning {
	private ArrayList<String> input;
	private ArrayList<Cluster> clusters;
	double disThreshold,occRel;
	boolean hasChanged;
	
	
	public Cleaning(ArrayList<String> input,double d,double e){
		this.input=input;
		this.disThreshold=d;
		this.occRel=e;
		
	}
	private void convertToUpperCase(){
		for(int i=0;i<input.size();i++)
			input.set(i, input.get(i).toUpperCase());
	}
	private void removeNonAlphaNumeric(){
		for(int i=0;i<input.size();i++){
			input.set(i,input.get(i).replaceAll("[^A-Za-z0-9]", ""));
		}
	}
	
	private void createClusters(){
		clusters=new ArrayList<Cluster>();
		for(int i=0;i<input.size();i++){
			clusters.add(new Cluster(input.get(i)));
		}
		System.out.println("Created clusters \n"+getOutput());
	}
	
	private void sortClusters(){
		Collections.sort(clusters, new Comparator<Cluster>() {
		    public int compare(Cluster cluster1, Cluster cluster2)  {
		        return (cluster2.getFrequencyOfRep()-cluster1.getFrequencyOfRep()); // The order depends on the direction of sorting.
		    }
		});
		System.out.println("After sorting \n"+getOutput());

	}
	
	private boolean isMergable(Cluster cluster1,Cluster cluster2){
		int distance=Levenshtein.distance(cluster1.getRep(),cluster2.getRep());
		float ratio=cluster1.getFrequencyOfRep()/cluster2.getFrequencyOfRep();
		
		if(distance<disThreshold){
			return true;
		}
		else
			return false;
		
	}
	
	private void mergeClusterSets(){
		for(int i=0;i<clusters.size();i++){
			for(int j=i+1;j<clusters.size();j++){
				if(isMergable(clusters.get(i),clusters.get(j)))
				{
					hasChanged=true;
					clusters.get(i).merge(clusters.get(j));
					System.out.println("Merged Cluster"+i+" and Cluster"+j+"\n");
					clusters.remove(j);
				
				}
			}
		}
		System.out.println("After Merging \n"+getOutput());

	}
	
	private void cleanClusters(){
		for(int i=0;i<clusters.size();i++){
				HashMap<String,Integer> frequencies=clusters.get(i).getFrequencies();
				for (Entry<String, Integer> entry : frequencies.entrySet())
				 {
					 String key=entry.getKey();
					 if(Levenshtein.distance(key,clusters.get(i).getRep())>disThreshold)
						 {
						 	hasChanged=true;
						 	clusters.add(new Cluster(key));
						 }
						 
				 }
				
			}
		System.out.println("After cleaning \n"+getOutput());

			
		}
	
	public void execute(){
		convertToUpperCase();
		removeNonAlphaNumeric();
		createClusters();
		hasChanged=true;
		while(hasChanged){
			hasChanged=false;
			sortClusters();
			mergeClusterSets();
			cleanClusters();
			
		}
	}
	
	public String getOutput(){
		String result="";
		result+="Output :\n";
		for(int i=0;i<clusters.size();i++){
			result+="Cluster "+(i+1)+"\n";
			result+=clusters.get(i).toString();
		}
		return result;
	}
	
	}
	
	



package Program;
import java.util.*;

public class Program {
	public static void main(String[] args) {
	    String s1 = "abbzccc", s2 = "babzzcz";
		//String s1 = "abbz", s2 = "accz";
	    System.out.println(canConvert(s1, s2));
	}

	private static boolean canConvert(String s1, String s2) {
		if(s1.length()!=s2.length())
			return false;
		if(s1.equals(s2))
			return true;
		Map<Character,Integer> map1= new HashMap<>();
		Map<Character,Integer> map2=new HashMap<>();
		for(char c:s1.toCharArray()) {
			map1.put(c,map1.getOrDefault(c,0)+1);
		}
		for(char c:s2.toCharArray()) {
			map2.put(c,map2.getOrDefault(c,0)+1);
		}
		TreeMap<Integer,TreeSet<Character>> tmap1 = new TreeMap<>();
		TreeMap<Integer,TreeSet<Character>> tmap2 = new TreeMap<>();
		for(Map.Entry<Character,Integer> entry:map1.entrySet()) {
			tmap1.putIfAbsent(entry.getValue(), new TreeSet<>());
			tmap1.get(entry.getValue()).add(entry.getKey());
		}
		for(Map.Entry<Character,Integer> entry:map2.entrySet()) {
			tmap2.putIfAbsent(entry.getValue(), new TreeSet<>());
			tmap2.get(entry.getValue()).add(entry.getKey());
		}
		Map<Character,Character> pair = new HashMap<>();
		for(Map.Entry<Integer,TreeSet<Character>> entry:tmap1.entrySet()) {

			if(!tmap2.containsKey(entry.getKey())|| tmap2.get(entry.getKey()).size() != entry.getValue().size()) {
				return false;
			}
			
			TreeSet<Character> set1=entry.getValue();
			TreeSet<Character> set2=tmap2.get(entry.getKey());
			for(char c:set1) {
				char c1=set2.pollFirst();
				if(c!=c1) {
					if(pair.containsKey(c1)&&c==pair.get(c1)) {
						pair.remove(c1);
					}
					else {
					pair.put(c,c1);
					}
				}
			}
			
			}
		return pair.isEmpty();
		
	}
}

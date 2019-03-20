package trainingdiary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class QueryResult implements Iterable<List<String>>{
	private List<Integer> keys;
	private HashMap<Integer, List<String>> rows;
	
	public QueryResult() {
		keys = new ArrayList<>();
		rows = new HashMap<>();
	}
	
	public void insertRow(int key, List<String> cols) {
		keys.add(key);
		rows.put(key, cols);
	}
	
	public List<String> getResult(int key) {
		return this.rows.get(key);
	}

	@Override
	public Iterator<List<String>> iterator() {
		Iterator<List<String>> ires = rows.values().iterator();
        return ires; 
	}
}

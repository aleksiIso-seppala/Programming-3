import java.util.Iterator;
import java.util.TreeMap;
import java.lang.Iterable;

public class ObjectNode extends Node implements Iterable<String> {
    
    private TreeMap<String, Node> nodes_;
    
    public ObjectNode(){
        this.nodes_ = new TreeMap<>();
    }
    public Node get(String key){
        
        if (!nodes_.containsKey(key)){
            return null;
        }
        return nodes_.get(key);
    }
    public void set(String key, Node node){
    
    if(nodes_.containsKey(key)){
        nodes_.put(key,node);
        return;
    }
    nodes_.put(key,node);
    }
    
    public int size(){
        return nodes_.size();
    }

    @Override
    public Iterator<String> iterator() {
        var tmp = nodes_.keySet();
        return tmp.iterator();
        
    }
}

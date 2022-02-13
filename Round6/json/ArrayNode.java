import java.util.ArrayList;
import java.util.Iterator;

public class ArrayNode extends Node implements Iterable<Node> {

    private ArrayList<Node> nodes_;
    
    public ArrayNode(){
        this.nodes_ = new ArrayList<>();
    }
    public void add(Node node){
        nodes_.add(node);
    }
    public int size(){
        return nodes_.size();
    }


    @Override
    public Iterator<Node> iterator() {
        return nodes_.iterator();
    }
    
}

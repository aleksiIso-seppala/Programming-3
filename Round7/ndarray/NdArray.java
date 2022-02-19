import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;


public class NdArray<E> extends AbstractCollection<E>{
    
    private ArrayList<Integer> dimensions;
    private ArrayList<E> array;
    
    public NdArray(Integer firstDimLen, Integer ...furtherDimLens) throws NegativeArraySizeException{
        this.dimensions = new ArrayList<>();
        
        if(firstDimLen<0){
            throw new NegativeArraySizeException("illegal dimension size "+firstDimLen+".");
        }
        
        dimensions.add(firstDimLen);
        for(int i=0;i<furtherDimLens.length;i++){
            if(furtherDimLens[i]<0){
                throw new NegativeArraySizeException("illegal dimension size "+furtherDimLens[i]+".");
            }
            dimensions.add(furtherDimLens[i]);
            
        }
        this.array = new ArrayList<>();
        for(int i=0;i<size();i++){
            array.add(null);
        }
    }
    
    @Override
    public int size(){
        int result = 1;
        for(var element : dimensions){
            result = result * element;
        }
        return result;
    }
    

    public E get(int... indices)throws IllegalArgumentException, IndexOutOfBoundsException{
        
        if(dimensions.size() != indices.length){
            throw new IllegalArgumentException
            (String.format("The array has &s dimension but &s indices were given",dimensions.size(),indices.length));
        }
        for(int i=0;i<indices.length;i++){
            if((indices[i] < 0) || (indices[i]>=dimensions.get(i))){
                throw new IndexOutOfBoundsException(String.format
                ("Illegal index %s of dimension &s of length %s.",indices[i],(i+i),dimensions.get(i)));
            }
        }

        int result = 1;
        for(var i=0;i<indices.length;i++){
            if(i==0){
                result = indices[1] + dimensions.get(1)*indices[0];     
                continue;
            }
            if(i==(indices.length-1)){
                break;
            }
            result = indices[i+1] + dimensions.get(i+1)*result;
        }
        
        return array.get(result);
    }
    
    public void set(E item, int... indices)throws IllegalArgumentException, IndexOutOfBoundsException{
        
        if(dimensions.size() != indices.length){
            throw new IllegalArgumentException
                (String.format("The array has %s dimension but %s indices were given",dimensions.size(),indices.length));
        }
        for(int i=0;i<indices.length;i++){           
            if(indices[i] < 0 || indices[i]>=dimensions.get(i)){
                throw new IndexOutOfBoundsException(String.format
        ("Illegal index %s of dimension %s of length %s.",indices[i],(i+i),dimensions.get(i)));
            }
        }
        
        //strike three
        int result = 1;
        for(var i=0;i<indices.length;i++){
            if(i==0){
                result = indices[1] + dimensions.get(1)*indices[0];     
                continue;
            }
            if(i==(indices.length-1)){
                break;
            }
            result = indices[i+1] + dimensions.get(i+1)*result;
        }
        
        array.set(result, item);
        
    }
    
    public int[] getDimensions(){
        int[] dimen = new int[dimensions.size()];
        
        int index = 0;
        for(var dim : dimensions){
            dimen[index] = dim;
            index++;
        }
        
        return dimen;
    }
    
    @Override
    public Iterator<E> iterator(){
        return array.iterator();
    }
}

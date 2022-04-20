public interface Entry<K,V,C> {

    K getKey();
    
    V getValue();
    
    C getCount();
}
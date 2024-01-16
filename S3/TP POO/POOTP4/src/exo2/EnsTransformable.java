package exo2;

public class EnsTransformable <L>{
    private Object[] ensemble;

    public EnsTransformable(L[] ensemble) {
        this.ensemble = new Object[ensemble.length];
        for ( int i = 0 ; i < ensemble.length ; i++ ){
            this.ensemble[i] = (Object)ensemble[i];
        }
    }

    @Override
    public String toString(){
        String chain ="";
        for ( int i = 0; i < ensemble.length; i++ ){
            chain += "("+ensemble.toString()+")";
        }
        return chain;
    }

    public void transformer (Transformation f){
        for ( int i = 0; i < ensemble.length; i++ ){
            ensemble[i] = (Object)(f.transforme((L)ensemble[i]));
        }
    }
}

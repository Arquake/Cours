package exo6 ;
class B {
    private int[] tab = new int[3];
    public int[] getTab(){
        return tab;
    }
    void m (int[] tab){
        this.tab = tab;
        tab[0]=tab[0]+1;
    }
    public int get (int indice){
        return tab[indice] ;
    }
}

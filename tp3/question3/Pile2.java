package question3;

public class Pile2<T> implements PileI<T>{
    /** par délégation : utilisation de la class Stack */
    private Stack<T> stk;
    /** la capacité de la pile */
    private int capacité;

    /** Création d'une pile.
     * @param taille la "taille maximale" de la pile, doit être > 0
     */
    public Pile2(int taille){
        if (taille <= 0){
            taille = CAPACITE_PAR_DEFAUT;
        }
        this.stk = new Stack<T>();
        this.capacité = taille;
    }
    
    /**
     * Constructeur par défaut.
     */
    public Pile2(){
        this(PileI.CAPACITE_PAR_DEFAUT);
    }
    
    /**
     * Ajoute un élément à la pile si elle n'est pas pleine.
     * @param o : élément ajouté.
     */
    public void empiler(T o) throws PilePleineException{
        if(estPleine()){
            throw new PilePleineException();
        }
        this.stk.push(o);
    }
    
    /**
     * Supprime le dernier élément ajouté à la pile.
     */
    public T depiler() throws PileVideException{
        if (estVide())
            throw new PileVideException();
        return this.stk.pop();
    }

    /**
     * Retourne le dernier élément ajouté de la pile.
     */
    public T sommet() throws PileVideException{
         if (estVide())
            throw new PileVideException();
        return this.stk.peek();
    }
    
    /**
     * Donne le nombre maximal d'éléments que la pile peut contenir.
     */
    public int capacite(){
         return this.capacité;
    }
    
    /**
     * Retoune le nombre d'éléments présent dans la pile.
     */
    public int taille(){
         return this.stk.size();
    }
    
    /**
     * Retourne true si la pile est vide.
     */
    public boolean estVide(){
        return this.stk.empty();
    }
    
    /**
     * Retourne true si la pile est pleine.
     */
    public boolean estPleine(){
        return this.stk.size() == this.capacité;
    }
    
    
	public boolean equals(Object o){
        if( this== o ){
            return true;
        }
        if(!(o instanceof Pile2)){
            return false;
        }
        Pile2 p1 = (Pile2)o;

        if (p1.taille() == this.taille() && p1.capacite() == this.capacite()){
            boolean estEgale = false;
            for(int i= stk.size()-1; i>=0 ; i--){
                Object temp = this.stk.get(i);
                boolean existe = false;
                for(int j = this.stk.size() - 1; j >= 0; j--){
                    if((temp == p1.stk.get(i))){
                        existe = true;
                    }
                }
                if(existe){
                    estEgale = true;
                } else{
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    
    public int hashCode(){
        return toString().hashCode();
    }
    
    public String toString(){
         String s = "[";
        for (int i = this.stk.size() - 1; i >= 0; i--) {
           s+= this.stk.get(i).toString();
           if(i >0){
               s+= ", ";
           }       
        }
        return s + "]"; 
    }
    
} 


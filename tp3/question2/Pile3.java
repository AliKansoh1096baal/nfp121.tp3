package question2;

public class Pile3 implements PileI {
    private Vector<Object> v;
    private int capacit�;
    
    /**
     * Constructeur par d�faut.
     */
    public Pile3() {
        this(PileI.CAPACITE_PAR_DEFAUT);
    }
    
    /**
     * Constructeur par initialisation.
     * @param taille : nombre d'�l�ments de la pile.
     */
    public Pile3(int taille) {
        if (taille <= 0){
            taille = CAPACITE_PAR_DEFAUT;
        }
        this.v = new Vector<Object>();
        this.capacit� = taille;
    }
    
    
    /**
     * Ajoute un �l�ment � la pile si celle-ci n'est pas pleine.
     * @param Object o : l'�l�ment ajout�.
     */
    public void empiler(Object o) throws PilePleineException {
        if(estPleine()){
            throw new PilePleineException();
        }
        this.v.add(o);
    }
    
    /**
     * Supprime le dernier �l�ment ajout� � la pile.
     */
    public Object depiler() throws PileVideException {
        if (estVide()){
            throw new PileVideException();
        }
        int d = v.lastIndexOf(sommet());    
        return this.v.remove(d);
    }
    
    
    /**
     * Retourne le dernier �l�ment ajout�.
     */
    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        return this.v.lastElement();
    }
    
    /**
     * Retourne le nombre d'�l�ments que contient la pile � cet instant.
     */
    public int taille() {
        return this.v.size();
    }
    
    /**
     * Retourne le nombre d'�l�ments que peut contenir la pile.
     */
    public int capacite() {
        return this.capacit�;
    }
    
    /**
     * M�thode retournant true si la pile est vide.
     */
    public boolean estVide() {
        return this.v.isEmpty();
    }
    
    
    /**
     * M�thode retournant true si la pile est pleine.
     */
    public boolean estPleine() {
        return this.v.size()==this.capacit�;
    }

    public String toString() {
        String s = "[";
        for (int i = this.v.size() - 1; i >= 0; i--) {
            s+= this.v.get(i).toString();
            if(i >0){
                s+= ", ";
            }

        }
        return s + "]"; 
    }

 
	public boolean equals(Object o) {
		if( this== o ){
            return true;
        }
        if(!(o instanceof Pile3)){
            return false;
        }
        Pile3 p = (Pile3) o;
        if(this.taille() == p.taille()){
            if(this.capacite() == p.capacite()){
               boolean estEgale = false; 
               for (int i = this.v.size() - 1; i >= 0; i--) {
                    Object tmp = this.v.get(i);
                    boolean existe = false;
                    for(int j = this.v.size() - 1; j >= 0; j--){
                        if((tmp == p.v.get(i))){
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
                
        }
        return false;
    }


    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

}


classe Pile4 :

public class Pile4 implements PileI, Cloneable {
    /** la liste des Maillons/Elements */
    private Maillon stk;
    /** la capacit� de la pile */
    private int capacite;
    /** le nombre */
    private int nombre;

    /**
     * Classe interne "statique" contenant chaque �l�ment de la chaine c'est une
     * proposition, vous pouvez l'ignorer !
     */
    private static class Maillon implements Cloneable {
        private Object element;
        private Maillon suivant;

        public Maillon(Object element, Maillon suivant) {
            this.element = element;
            this.suivant = suivant;
        }

        public Maillon suivant() {
            return this.suivant;
        }

        /** Teste si un suivant.
         * M�thode qui facilite le toString()
         * en testant si un suivant existe
         */
        public boolean hasNext(){
            return this.suivant != null;
        }

        public Object element() {
            return this.element;
        }

        public Object clone() throws CloneNotSupportedException {
            Maillon m = (Maillon) super.clone();
            m.element = element;
            return m;
        }
    }

    /**
     * Cr�ation d'une pile.
     * 
     * @param taille
     *            la taille de la pile, la taille doit �tre > 0
     */
    public Pile4(int taille) {
        if (taille <= 0)
            taille = CAPACITE_PAR_DEFAUT;
        this.stk = null;
        this.capacite = taille;
    }

    public Pile4() {
        this(PileI.CAPACITE_PAR_DEFAUT);
    }

    /**
     * empiler � la pile un �l�ment en param�tre.
     * @Param o
     *      objet � empiler dans la pile
     * @throw PilePleineException
     *      -si pile est pleine, ne peut pas empiler
     */
    public void empiler(Object o) throws PilePleineException {
        if (estPleine())
            throw new PilePleineException();
        stk = new Maillon (o,stk);     
        this.nombre++;            
    }

    /**
     * depiler supprime de la pile le dernier objet entr�, 
     * retourne cet objet.
     * @return Object 
     * @throw PileVideException
     *      -si pile n'a aucun �l�mnet
     */
    public Object depiler() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        Maillon tmp = this.stk;
        this.stk = this.stk.suivant;
        nombre--;
        return tmp.element;
    }

    public Object sommet() throws PileVideException {
        if (estVide())
            throw new PileVideException();
        return stk.element ;
    }

    /**
     * Effectue un test de l'�tat de la pile.
     * 
     * @return vrai si la pile est vide, faux autrement
     */
    public boolean estVide() {
        return stk == null; 
    }

    /**
     * Effectue un test de l'�tat de la pile.
     * 
     * @return vrai si la pile est pleine, faux autrement
     */
    public boolean estPleine() {
        return this.taille() >= capacite; 
    }

    /**
     * Retourne une repr�sentation en String d'une pile, contenant la
     * repr�sentation en String de chaque �l�ment.
     * 
     * @return une repr�sentation en String d'une pile
     * Utilise la m�thode hasNext() de Maillon
     */
    public String toString() {
        String s = "[";
        Maillon tmp = stk;
        while (tmp != null){
            s = s + tmp.element() ;
            tmp = tmp.suivant();
            if (tmp !=null) {s = s + ", ";}  
        }  
        return s + "]"; 
    }

   
	public boolean equals(Object o) {
		boolean b = true;
        Pile4 p1;
        if (o instanceof Pile4) {
            p1 = (Pile4)o;
            Maillon m1;
            Maillon m2;
            try{
                if (p1.taille() == this.taille() && p1.capacite() == this.capacite()){
                m1 = stk;
                m2 = p1.stk;
                    for (int i = this.nombre - 1; i >= 0; i--) {
                        if(!(m1.element() == m2.element())){
                            b = false;
                        }
                        m1 = m1.suivant();
                        m2 = m2.suivant();
                    }
                }
                else{
                    b = false;
                }
            }catch (Exception e){
                b = false;
            }
            
        }else{
            b = false;
        }
        return b;
    }


    public int capacite() {
        return this.capacite;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public int taille() {
        return nombre;
    }
}


package Model.Calcules;

import Model.Metier.Livraison;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Iterator;

public class IteratorSeq implements Iterator<Livraison> {

    private Livraison[] candidats;
    private int nbCandidats;

    /**
     * Cree un iterateur pour iterer sur l'ensemble des sommets de nonVus
     *
     * @param nonVus
     * @param livraisonCourante
     */
    public IteratorSeq(Collection<Livraison> nonVus, Livraison livraisonCourante) {
        this.candidats = new Livraison[nonVus.size()];
        nbCandidats = 0;
        for (Livraison s : nonVus){
            candidats[nbCandidats++] = s;
        }
    }

    @Override
    public boolean hasNext() {
        return nbCandidats > 0;
    }

    @Override
    public Livraison next() {
        return candidats[--nbCandidats];
    }

}

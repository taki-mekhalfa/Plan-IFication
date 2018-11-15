package Model.Calcules;

import Model.Metier.Livraison;

import javax.naming.OperationNotSupportedException;
import java.util.Collection;
import java.util.Iterator;

/**
 * Classe de l'itérateur pour iterer sur l'ensemble des sommets de nonVus.
 * @author H4104
 */
public class IteratorSeq implements Iterator<Livraison> {

    private Livraison[] candidats;
    private int nbCandidats;

    /**
     * Constructeur de la classe IteratorSeq 
     * @param nonVus correspondant la collection des livraisons non vus
     * @param livraisonCourante correspondantà la livraison en cours d'itération
     */
    public IteratorSeq(Collection<Livraison> nonVus, Livraison livraisonCourante) {
        this.candidats = new Livraison[nonVus.size()];
        nbCandidats = 0;
        for (Livraison s : nonVus){
            candidats[nbCandidats++] = s;
        }
    }

    /**
     * Méthode de test pour savoir si la livraison en cours d'itération dispose d'un successeur.
     * @return boolean correspondant au résultat de ce test
     */
    @Override
    public boolean hasNext() {
        return nbCandidats > 0;
    }

    /**
     * Méthode d'obtention de la livraison suivante dans le cadre de l'itération.
     * @return Livraison correspondant à l'élement recherché
     */
    @Override
    public Livraison next() {
        return candidats[--nbCandidats];
    }

}

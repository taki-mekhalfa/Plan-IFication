package TestUnitaire;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.*;

import Model.Planification;
import Model.Metier.*;

public class TestPlanification {
	
	private final static String cheminPetitPlan="./Documents/fichiersXML2018/petitPlan.xml";
	private final static String cheminPetitDl="./Documents/fichiersXML2018/dl-petit-6.xml";
	private static Planification planification;
	private static int nombreLivreurs=3;
	
	@Before
	public void charger() {
		planification=new Planification();
		File fichierPetitPlan=new File(cheminPetitPlan);
		File fichierPetitDl=new File(cheminPetitDl);
		planification.chargerPlan(fichierPetitPlan);
		planification.chargerDemandesDeLivraisons(fichierPetitDl);
		planification.calculerTournees(nombreLivreurs);
		//System.out.println("charger");
	}
	@Test
	public void testCalculerTournee(){
		planification.calculerTournees(nombreLivreurs);
		System.out.println("testCalculerTournee");
	}
	@Test
	public void testIsEntrepot(){
		Livraison test=planification.getDemandeLivraisons().getPointsDeLivraisons().get(0);
		planification.isEntrepot(test);
		System.out.println("testIsEntrepot");
	}
	@Test
	public void testAjouterPointDeLivraison(){
		String idPointLivraison=planification.getPlan().getNoeuds().get(2);
		int duree=60;
		Livraison livraison1=(Livraison)planification.getTournees().get(1).getHeuresDeLivraison().keySet().toArray()[1];
		Livraison livraison2=(Livraison)planification.getTournees().get(1).getHeuresDeLivraison().keySet().toArray()[2];
		assertNotEquals(livraison2,null);
		planification.ajouterPointDeLivraison(idPointLivraison, duree, livraison1, livraison2);
		System.out.println("testAjouterPointDeLivraison");
		//System.out.println("sadsdf:"+planification.livraisonsConsecutives(livraison1, livraison2));
	}
	@Test
	public void testSupprimerPointDeLivraison(){
		assertNotEquals(planification.getPlan(),null);
		List<Livraison> listeLivraisons=planification.getDemandeLivraisons().getPointsDeLivraisons();
		Livraison livraisonSupprime=listeLivraisons.get(2);
		planification.supprimerPointDeLivraison(livraisonSupprime);
		System.out.println("testSupprimerPointDeLivraison");
		
	}
	@Test
	public void testDeplacerLivraison(){
		Livraison livraisonADeplacer=planification.getDemandeLivraisons().getPointsDeLivraisons().get(3);
		System.out.println(planification.getTournees().size());
		Tournee tourneeChoisie1=planification.getTournees().get(0);
		planification.getTournees().get(1);
		int size=tourneeChoisie1.getHeuresDeLivraison().size();
		System.out.println("size="+size);
		Livraison livraison1=(Livraison)tourneeChoisie1.getHeuresDeLivraison().keySet().toArray()[0];
		Livraison livraison2=(Livraison)tourneeChoisie1.getHeuresDeLivraison().keySet().toArray()[2];
		planification.deplacerLivraison(livraisonADeplacer, livraison1, livraison2);
		System.out.println("testDeplacerLivraison");
	}
	@Test
	public void testLivraisonsConsecutives(){
		Livraison livraison1=planification.getDemandeLivraisons().getPointsDeLivraisons().get(1);
		Livraison livraison2=planification.getDemandeLivraisons().getPointsDeLivraisons().get(2);	
		Livraison livraison3=planification.getDemandeLivraisons().getPointsDeLivraisons().get(3);	
		boolean drapeauConsecutives1=planification.livraisonsConsecutives(livraison1, livraison2);
		boolean drapeauConsecutives2=planification.livraisonsConsecutives(livraison1, livraison3);
		boolean drapeauConsecutives3=planification.livraisonsConsecutives(livraison2, livraison2);
		System.out.println(drapeauConsecutives1);
		System.out.println(drapeauConsecutives2);
		System.out.println(drapeauConsecutives3);
		System.out.println("testLivraisonsConsecutives");
	}
	@Test
	public void testMAJAffichage(){
		planification.MAJAffichage();
		System.out.println("testMAJAffichage");
	}
	@Test
	public void testGetNomDeLaRue(){
		String idOrigine=planification.getPlan().getNoeuds().get(0);
		String idDestination=planification.getPlan().getSuccesseurs(idOrigine).get(0).getDestination();
		planification.getNomDeLaRue(idOrigine, idDestination);
		
		String idOrigineRueNotFound=planification.getPlan().getNoeuds().get(0);
		String idDestinationRueNotFound="5678";
		planification.getNomDeLaRue(idOrigineRueNotFound, idDestinationRueNotFound);
		System.out.println("testGetNomDeLaRue");
	}
	@Test
	public void testSupprimerDemandesLivraisons(){
		planification.supprimerDemandesLivraisons();
		System.out.println("testSupprimerDemandesLivraisons");
	}
	@Test
	public void testSupprimerPlan(){
		planification.supprimerPlan();
		System.out.println("testSupprimerPlan");
	}
	@Test
	public void testGetTournees(){
		planification.getTournees();
		System.out.println("testGetTournees");
	}
	@AfterClass
	public static void clean() {
		planification=null;
		System.out.println("clean");
	}

}

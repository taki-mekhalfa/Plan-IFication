package Test.TestsUnitaires;

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
	@BeforeClass
	public static void beforeClass() {
		System.out.println("----------------------------------------------");
		System.out.println("--------------Test Planification--------------");
		System.out.println("----------------------------------------------");
	}
	@Before
	public void charger() {
		planification=new Planification();
		File fichierPetitPlan=new File(cheminPetitPlan);
		File fichierPetitDl=new File(cheminPetitDl);
		planification.chargerPlan(fichierPetitPlan);
		planification.chargerDemandesDeLivraisons(fichierPetitDl);
		planification.calculerTournees(nombreLivreurs);
	}
	@After
	public void after() {
		System.out.println("Test reussi");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testCalculerTournees(){
		System.out.println("test Calculer Tournees:");
		planification.calculerTournees(nombreLivreurs);
		System.out.println(planification.getTournees());
		
	}
	@Test
	public void testIsEntrepot(){
		System.out.println("test IsEntrepot:");
		Livraison test=planification.getDemandeLivraisons().getPointsDeLivraisons().get(0);
		System.out.println(planification.isEntrepot(test));
		
	}
	@Test
	public void testAjouterPointDeLivraison(){
		System.out.println("test Ajouter Point DeLivraison:");
		String idPointLivraison=planification.getPlan().getNoeuds().get(2);
		int duree=60;
		Livraison livraison1=(Livraison)planification.getTournees().get(1).getHeuresDeLivraison().keySet().toArray()[1];
		Livraison livraison2=(Livraison)planification.getTournees().get(1).getHeuresDeLivraison().keySet().toArray()[2];
		assertNotEquals(livraison2,null);
		System.out.println(planification.ajouterPointDeLivraison(idPointLivraison, duree, livraison1, livraison2));
	}
	@Test
	public void testSupprimerPointDeLivraison(){
		System.out.println("test Supprimer Point DeLivraison:");
		assertNotEquals(planification.getPlan(),null);
		List<Livraison> listeLivraisons=planification.getDemandeLivraisons().getPointsDeLivraisons();
		Livraison livraisonSupprime=listeLivraisons.get(2);
		System.out.println("livraisonSupprime:"+livraisonSupprime.toString());
	}
	@Test
	public void testDeplacerLivraison(){
		System.out.println("test Deplacer Livraison:");
		Livraison livraisonADeplacer=planification.getDemandeLivraisons().getPointsDeLivraisons().get(3);
		Tournee tourneeChoisie1=planification.getTournees().get(0);
		planification.getTournees().get(1);
		int size=tourneeChoisie1.getHeuresDeLivraison().size();
		Livraison livraison1=(Livraison)tourneeChoisie1.getHeuresDeLivraison().keySet().toArray()[0];
		Livraison livraison2=(Livraison)tourneeChoisie1.getHeuresDeLivraison().keySet().toArray()[2];
		planification.deplacerLivraison(livraisonADeplacer, livraison1, livraison2);
		System.out.println("livraison a deplacer:"+livraisonADeplacer.toString());
	}
	@Test
	public void testLivraisonsConsecutives(){
		System.out.println("test Livraisons Consecutives");
		Livraison livraison1=planification.getDemandeLivraisons().getPointsDeLivraisons().get(1);
		Livraison livraison2=planification.getDemandeLivraisons().getPointsDeLivraisons().get(2);	
		System.out.println("Livraisons Consecutives:"+planification.livraisonsConsecutives(livraison1, livraison2));
	}
	@Test
	public void testGetNomDeLaRue(){
		System.out.println("test Get Nom De La Rue");
		String idOrigine=planification.getPlan().getNoeuds().get(0);
		String idDestination=planification.getPlan().getSuccesseurs(idOrigine).get(0).getDestination();
		System.out.println(planification.getNomDeLaRue(idOrigine, idDestination));	
	}
	@Test
	public void testGetNomDeLaRueNotFound(){
		System.out.println("test Get Nom De La Rue Not Found");
		String idOrigineRueNotFound=planification.getPlan().getNoeuds().get(0);
		String idDestinationRueNotFound="5678";
		System.out.println(planification.getNomDeLaRue(idOrigineRueNotFound, idDestinationRueNotFound));	
	}
	@Test
	public void testSupprimerDemandesLivraisons(){
		System.out.println("test Supprimer Demandes Livraisons");
		planification.supprimerDemandesLivraisons();
		System.out.println("DemandeLivraisons:"+planification.getDemandeLivraisons());
	}
	@Test
	public void testSupprimerPlan(){
		System.out.println("test Supprimer Plan");
		planification.supprimerPlan();
		System.out.println("Plan:"+planification.getPlan());
	}
	@AfterClass
	public static void clean() {
		planification=null;
		System.out.println(""); 
	}

}

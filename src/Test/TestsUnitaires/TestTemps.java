package Test.TestsUnitaires;

import org.junit.*;

import Model.Metier.Temps;

public class TestTemps {
	@BeforeClass
	public static void before() {
		System.out.println("----------------------------------------------");
		System.out.println("----------------Test Temps--------------------");
		System.out.println("----------------------------------------------");
	}
	@Test
	public void testTempsComparaison() {
		System.out.println("----------------------------------------------");
		System.out.println("test Temps Comparaison:");
		Temps t=new Temps(8,0,0);
		System.out.println("Temps:"+t.toString());
		System.out.println("compare to 11:00:00:"+t.compareTo(new Temps(11,0,0)));
		System.out.println("compare to 08:02:00:"+t.compareTo(new Temps(8,2,0)));
		System.out.println("compare to 08:00:03:"+t.compareTo(new Temps(8,0,3)));
		System.out.println("Test reussi");
	}
	@AfterClass
	public static void afterClass(){
		System.out.println("----------------------------------------------");
		System.out.println("");
	} 
}

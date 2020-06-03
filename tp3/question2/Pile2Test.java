package question2;

public class Pile2Test extends junit.framework.TestCase {
    private PileI p1;
    private PileI p2;
    private PileI p3;
    private PileI p4;

    protected void setUp() // throws java.lang.Exception
    {
        p1 = new question2.Pile2();
        p2 = new question2.Pile2();
    }

    public void test_Pile_capacite() {
        assertEquals(PileI.CAPACITE_PAR_DEFAUT, p1.capacite());
    }

    public void test_Pile_estPleine() throws Exception {
        PileI p = new question2.Pile2(3);
        p.empiler(3);
        assertEquals(1, p.taille());
        p.empiler(2);
        assertEquals(2, p.taille());
        p.empiler(1);
        assertEquals(3, p.taille());

        assertEquals(true, p.estPleine());
        assertEquals(p.taille(), p.capacite());
        try {
            p.empiler(0);
            fail("La pile est pleine !");
        } catch (Exception e) {
            assertTrue(e instanceof question1.PilePleineException);
        }
    }

    public void test_Pile_sommet() throws Exception {
        PileI p = new question2.Pile2(3);
        assertEquals(true, p.estVide());

        p.empiler(new Integer(3));
        assertEquals(" sommet ?? ", new Integer(3), p.sommet());
        assertEquals(1, p.taille());
        assertEquals(" depiler ?? ", new Integer(3), p.depiler());
        assertEquals(0, p.taille());
    }

    public void test_Pile_estVide() throws Exception {
        PileI p = new question2.Pile2(3);
        assertEquals(true, p.estVide());
        try {
            Object r = p.depiler();
            fail("La pile est vide !");
        } catch (Exception e) {
            assertTrue(e instanceof question1.PileVideException);
        }
    }

    public void test_Pile_toString() throws Exception {
        PileI pile1 = new question2.Pile2(3);
        assertEquals("toString incorrect ? ", "[]", pile1.toString());
        pile1.empiler(4);
        assertEquals("toString incorrect ? ", "[4]", pile1.toString());
        pile1.empiler(5);
        assertEquals("toString incorrect ? ", "[5, 4]", pile1.toString());
        pile1.empiler(3);
        assertEquals("toString incorrect ? ", "[3, 5, 4]", pile1.toString());

    }

    public void test_Pile_TailleNegative() {
        PileI p = new question2.Pile2(-3);
        assertEquals(p.CAPACITE_PAR_DEFAUT, p.capacite());
    }

    public void test_Pile_equals() throws Exception {

        p1.empiler(3);
        p1.empiler(2);
        p1.empiler(1);

        p2.empiler(3);
        p2.empiler(2);
        p2.empiler(1);

        assertTrue("égalité de deux piles ? ", p1.equals(p2));
        assertTrue("égalité de deux piles ? ", p2.equals(p1));
        assertTrue("égalité de deux piles ? ", p1.equals(p1));

        p2.empiler(1);
        assertFalse("égalité de deux piles ? ", p1.equals(p2));

    }

    public void test_equals_contenu_different() throws Exception{ 
        PileI p1 = new Pile(6); PileI p11 = new Pile(6);
        PileI p2 = new Pile2(6);PileI p22 = new Pile2(6);
        PileI p3 = new Pile3(6);PileI p33 = new Pile3(6);
        PileI p4 = new Pile4(6);PileI p44 = new Pile4(6);

        p1.empiler(3);p1.empiler(2);p1.empiler(5);
        p11.empiler("3");p11.empiler("2");p11.empiler("5");

        p2.empiler(3);p2.empiler(2);p2.empiler(5);
        p22.empiler("3");p22.empiler("2");p22.empiler("5");

        p3.empiler(3);p3.empiler(2);p3.empiler(5);
        p33.empiler("3");p33.empiler("2");p33.empiler("5");

        p4.empiler(3);p4.empiler(2);p4.empiler(5);
        p44.empiler("3");p44.empiler("2");p44.empiler("5");

        assertEquals(" equals Pile ???", p2.toString(),p22.toString());
        assertEquals(" equals Pile ???", p22.toString(),p2.toString());
        assertEquals(" equals Pile ???", p3.toString(),p33.toString());
        assertEquals(" equals Pile ???", p33.toString(),p3.toString());
        assertEquals(" equals Pile ???", p4.toString(),p44.toString());
        assertEquals(" equals Pile ???", p44.toString(),p4.toString());

        assertEquals(" equals Pile ???", p1.hashCode(), p1.hashCode());
        assertFalse(" une pile d'entiers(1,2,3) == une pile de String(\"1\",\"2\",\"3\") ???",p1.equals(p11));
    }
}

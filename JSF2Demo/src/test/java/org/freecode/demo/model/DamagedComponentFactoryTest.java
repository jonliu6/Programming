package org.freecode.demo.model;

import java.util.Collection;
import java.util.Iterator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DamagedComponentFactoryTest extends TestCase {

    private DamagedComponentFactory dcf = null;
    
    public DamagedComponentFactoryTest( String aName ) {
        super( aName );
    }
    
    public static Test suite() {
        return new TestSuite( DamagedComponentFactoryTest.class );
    }
    
    public void setUp() {
        dcf = new DamagedComponentFactory();
        dcf.initializeDamagedComponents();
        dcf.populateDamagedComponentMap();
    }
    
    public void testDamagedComponentInitialization() {
        assertTrue( dcf.getDamagedComponentMap() != null && dcf.getDamagedComponentMap().size() > 0 );
        dcf.printDamagedComponentMap();
    }
    
    public void testFindDamagedComponentByUniqueCode() {
        String singleCode = "OJU";
        assertNotNull(dcf.getDamagedComponent(singleCode));
        System.out.println("Print out from testFindDamagedComponentByUniqueCode()...");
        System.out.println(dcf.getDamagedComponent(singleCode));
        
    }
    
    public void testFindDamagedComponentByDelimitedCodes() {
        String multiCodes = "ONE;ON;ONS";
        Collection<DamagedComponent> colDC = dcf.getDamagedComponents(multiCodes, ";");
        assertNotNull(colDC);
        System.out.println("Print out from testFindDamagedComponentByDelimitedCodes()...");
        if ( colDC != null ) {
            for ( Iterator<DamagedComponent> it = colDC.iterator(); it.hasNext(); ) {
                System.out.println(it.next());
            }
        }
    }
    
    public void testFindDamagedComponentByComponentCodes() {
        String primaryCode = "O/H Neutral";
        String secondaryCode = "";
        DamagedComponent found = dcf.getDamagedComponent(primaryCode, secondaryCode);
        assertNotNull(found);
        System.out.println("Print out from testFindDamagedComponentByComponentCodes()...");
        System.out.println(found);
        primaryCode = "O/H Neutral";
        secondaryCode = "O/H Neutral/ground/isolation device";
        found = dcf.getDamagedComponent(primaryCode, secondaryCode);
        System.out.println(found);
    }
    
    public void tearDown() {
        dcf.cleanUp();
        dcf = null;
    }
}

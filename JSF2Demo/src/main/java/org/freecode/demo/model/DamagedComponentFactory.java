package org.freecode.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DamagedComponentFactory {
    private List<DamagedComponent> theDamagedComponents = null;
    private Map<String, Map<String, DamagedComponent>> theDamagedComponentMap = null; // collection for object lookups

    public void initializeDamagedComponents() {
        DamagedComponent dc1 = new DamagedComponent("OJ", "O/H Primary", "");
        DamagedComponent dc2 = new DamagedComponent("OJU", "O/H Primary", "Jumper/Riser/Drop lead/Tap");
        DamagedComponent dc3 = new DamagedComponent("ONE", "O/H Primary", "O/H Neutral/ground", Boolean.TRUE);
        DamagedComponent dc4 = new DamagedComponent("OPA", "O/H Primary", "O/H Primary conductor - al", Boolean.TRUE);
        DamagedComponent dc5 = new DamagedComponent("OPC", "O/H Primary", "O/H Primary conductor - cu", Boolean.TRUE);
        DamagedComponent dc6 = new DamagedComponent("SAU", "O/H Primary", "Automatic Splice", Boolean.TRUE);
        DamagedComponent dc7 = new DamagedComponent("SCO", "O/H Primary", "Compression Splice", Boolean.TRUE);
        DamagedComponent dc8 = new DamagedComponent("CAM", "O/H Primary", "Ampact/Wedge Connector", Boolean.TRUE);
        DamagedComponent dc9 = new DamagedComponent("CBO", "O/H Primary", "Bolted Type Connector", Boolean.TRUE);
        DamagedComponent dc10 = new DamagedComponent("CLI", "O/H Primary", "Live-line/hot tap Connector", Boolean.TRUE);
        DamagedComponent dc11 = new DamagedComponent("CST", "O/H Primary", "Stirrup Connector", Boolean.TRUE);
        DamagedComponent dc12 = new DamagedComponent("IST", "O/H Primary", "Insulator");
        
        DamagedComponent dc13 = new DamagedComponent("ON", "O/H Neutral", "");
        DamagedComponent dc14 = new DamagedComponent("ONO", "O/H Neutral", "O/H Neutral/ground/isolation device", Boolean.TRUE);
        DamagedComponent dc15 = new DamagedComponent("ONA", "O/H Neutral", "Automatic", Boolean.TRUE);
        DamagedComponent dc16 = new DamagedComponent("ONC", "O/H Neutral", "Compression", Boolean.TRUE);
        DamagedComponent dc17 = new DamagedComponent("ONB", "O/H Neutral", "Bolted Type", Boolean.TRUE);
        DamagedComponent dc18 = new DamagedComponent("ONS", "O/H Neutral", "Spool/Insulator", Boolean.TRUE);

        
        theDamagedComponents = new ArrayList<DamagedComponent>();
        
        theDamagedComponents.add(dc1);
        theDamagedComponents.add(dc2);
        theDamagedComponents.add(dc3);
        theDamagedComponents.add(dc4);
        theDamagedComponents.add(dc5);
        theDamagedComponents.add(dc6);
        theDamagedComponents.add(dc7);
        theDamagedComponents.add(dc8);
        theDamagedComponents.add(dc9);
        theDamagedComponents.add(dc10);
        theDamagedComponents.add(dc11);
        theDamagedComponents.add(dc12);
        theDamagedComponents.add(dc13);
        theDamagedComponents.add(dc14);
        theDamagedComponents.add(dc15);
        theDamagedComponents.add(dc16);
        theDamagedComponents.add(dc17);
        theDamagedComponents.add(dc18);
    }
    
    public void cleanUp() {
        if ( theDamagedComponentMap != null ) {
            theDamagedComponentMap.clear();
            theDamagedComponentMap = null;
        }
        
        if ( theDamagedComponents != null ) {
            theDamagedComponents.clear();
            theDamagedComponents = null;
        }
    }
    
    public void populateDamagedComponentMap() {
        if ( theDamagedComponents != null && theDamagedComponents.size() > 0 ) {
            for (DamagedComponent dc : theDamagedComponents ) {
                if ( dc != null ) {
                    addDamagedComponentIntoMap(dc);
                }
            }
        }
    }
    
    private void addDamagedComponentIntoMap(DamagedComponent dc) {
        if ( theDamagedComponentMap == null ) {
            theDamagedComponentMap = new HashMap<String, Map<String, DamagedComponent>>();
            Map aSubComponentMap = new HashMap<String, DamagedComponent>();
            aSubComponentMap.put(dc.getSecondaryComponent(), dc);
            theDamagedComponentMap.put(dc.getPrimaryComponent(), aSubComponentMap);
        }
        else {
            if ( theDamagedComponentMap.containsKey(dc.getPrimaryComponent()) ) {
                Map aSubComponentMap = theDamagedComponentMap.get(dc.getPrimaryComponent());
                if ( aSubComponentMap == null && dc.getSecondaryComponent() != null ) {
                    aSubComponentMap = new HashMap<String, DamagedComponent>();
                    aSubComponentMap.put(dc.getSecondaryComponent(), dc);
                }
                else {
                    if ( aSubComponentMap.containsKey(dc.getSecondaryComponent()) == false ) {
                        aSubComponentMap.put(dc.getSecondaryComponent(), dc);
                    }
                }
            }
            else {
                Map aSubComponentMap = new HashMap<String, DamagedComponent>();
                aSubComponentMap.put(dc.getSecondaryComponent(), dc);
                theDamagedComponentMap.put(dc.getPrimaryComponent(), aSubComponentMap);
            }
        }
    }
    
    public Map<String, Map<String, DamagedComponent>> getDamagedComponentMap() {
        return theDamagedComponentMap;
    }
    
    public List<DamagedComponent> getAllDamagedComponents() {
        return theDamagedComponents;
    }
    
    public Collection<String> getPrimaryCodes() {
        Collection<String> codes = null;
        if ( theDamagedComponentMap != null ) {
            codes = theDamagedComponentMap.keySet();
        }
        
        return codes;
    }
    
    /**
     * retrieve a list of secondary damaged component codes by the given primary code and the flag of equipment-failure-related
     * @param primaryCode: the primary damaged component code
     * @param relatedToEquipmentFailure: flag of if or not related to a equipment failure
     * @return a list of eligible secondary damaged component codes 
     */
    public Collection<String> getSecondaryCodes(String primaryCode, Boolean relatedToEquipmentFailure) {
        Collection<String> codes = null;
        if ( theDamagedComponentMap != null ) {
            Map<String, DamagedComponent> subMap = theDamagedComponentMap.get(primaryCode);
            if ( subMap != null ) {
                if ( Boolean.TRUE.equals(relatedToEquipmentFailure) ) {
                    codes = subMap.keySet();
                }
                else {
                    codes = new ArrayList<String>();
                    for ( String aKey: subMap.keySet() ) {
                        DamagedComponent dc = subMap.get(aKey);
                        if ( dc != null && Boolean.FALSE.equals(dc.getRelatedToEquipmentFailure()) ) {
                            codes.add(aKey);
                        }
                    }
                }
            }
        }
        
        return codes;
    }
    
    /**
     * find a damaged component by a unique 3-letter code
     * @param uniqueCode
     * @return found damaged component
     */
    public DamagedComponent getDamagedComponent(String uniqueCode) {
        DamagedComponent dc = null;
        
        if ( theDamagedComponentMap != null && theDamagedComponentMap.size() > 0 && uniqueCode != null && uniqueCode.length() > 0 ) {
            Collection<Map<String, DamagedComponent>> damagedComponentCollection = theDamagedComponentMap.values();
            if ( damagedComponentCollection != null && damagedComponentCollection.size() > 0 ) {
                for (Iterator<Map<String, DamagedComponent>> it = damagedComponentCollection.iterator(); it.hasNext();){
                    Map<String, DamagedComponent> innerMap = it.next();
                    if ( innerMap != null && innerMap.size() >0 ) {
                        Collection<DamagedComponent> damagedComponents = innerMap.values();
                        if ( damagedComponents != null && damagedComponents.size() > 0 ) {
                            for ( Iterator<DamagedComponent> it1 = damagedComponents.iterator(); it1.hasNext();) {
                                DamagedComponent aDamagedComponent = it1.next();
                                if ( aDamagedComponent != null && uniqueCode.trim().equalsIgnoreCase( aDamagedComponent.getCode() ) ) {
                                    return aDamagedComponent;
                                }
                            }
                        }
                    }
                }
            }
        } 
        
        return dc;
    }
    
    /**
     * find the damaged components by a delimited (; as delimiter) string of component codes - e.g. OJU;ONE
     * @param delimitedCodes
     * @return collection of found damaged components
     */
    public Collection<DamagedComponent> getDamagedComponents(String delimitedCodes, String delimiter) {
        Collection<DamagedComponent> damagedComponents = null;
        
        if ( delimitedCodes != null && delimitedCodes.length() > 0 && delimiter != null && delimiter.length() > 0 ) {
            String[] componentCodes = delimitedCodes.split(delimiter);
            if ( componentCodes != null && componentCodes.length > 0 ) {
                for (int i=0; i < componentCodes.length; ++i) {
                    DamagedComponent aDamagedComponent = getDamagedComponent(componentCodes[i]);
                    if ( aDamagedComponent != null ) {
                        if ( damagedComponents == null ) {
                            damagedComponents = new ArrayList<DamagedComponent>();
                        }
                        damagedComponents.add(aDamagedComponent);
                    }
                }
            }
        }
        
        return damagedComponents;
    }
    
    /**
     * find a damaged component by primary and secondary codes
     * @param primaryCode
     * @param secondaryCode
     * @return found damaged component
     */
    public DamagedComponent getDamagedComponent(String primaryCode, String secondaryCode) {
        DamagedComponent dc = null;
        if ( theDamagedComponentMap != null && theDamagedComponentMap.size() > 0 && primaryCode != null ) {
            Set<String> primaryCodes = theDamagedComponentMap.keySet();
            for (String pCode : primaryCodes) {
                if ( primaryCode.trim().equalsIgnoreCase(pCode) ) {
                    Map<String, DamagedComponent> innerMap = theDamagedComponentMap.get(pCode);
                    if ( innerMap != null && innerMap.size() > 0 ) {
                        Set<String> secondaryCodes = innerMap.keySet();
                        for (String sCode: secondaryCodes ) {
                            if ( sCode.trim().equalsIgnoreCase(secondaryCode) ) {
                                return innerMap.get(sCode); // found the DamagedComponent and return
                            }
                        }
                    }
                }
                    
            }
        }
        return dc;
    }
    
    public void printDamagedComponentMap() {
        String indentation = "";
        if ( theDamagedComponentMap != null && theDamagedComponentMap.size() > 0 ) {
            for (String priKey : theDamagedComponentMap.keySet() ) {
                indentation = "";
                System.out.println( indentation + priKey );
                Map<String, DamagedComponent> subMap = theDamagedComponentMap.get(priKey);
                if ( subMap != null && subMap.size() > 0 ) {
                    indentation = "  ";
                    for ( String secKey : subMap.keySet() ) {
                        System.out.println( indentation + secKey);
                    }
                }
            }
        }
    }
}

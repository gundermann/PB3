package helper;

import java.io.*; 

import javax.xml.parsers.*; 

import org.xml.sax.*; 
import org.xml.sax.helpers.DefaultHandler; 

import Mappe.Auszahlung;
import Mappe.Document;
import Mappe.Vertrag;
import Mappe.VertragsMappe;
import Mappe.Vertragsblatt;
import Mappe.Zuwendung;

public class SAXLesen extends DefaultHandler { 
    static final String neueZeile = System.getProperty("line.separator"); 

    static private Writer out = null; 

    private VertragsMappe mappe;
    private String eName;
    private StringBuffer textBuffer = null; 
    private Document vorgang = null;
    private Document docInVorgang = null;
    
    public VertragsMappe mappeLaden(VertragsMappe mappe){
    	
    	this.mappe=mappe;
    	
    	SAXParser saxParser; 
    	
    	try { 
    		saxParser = SAXParserFactory.newInstance().newSAXParser(); 
    		saxParser.parse(new File("xml_file.xml"), this); 
    	} catch (ParserConfigurationException pe) { 
    		pe.printStackTrace(); 
    	} catch (SAXException se) { 
    		se.printStackTrace(); 
    	} catch (IOException ie) { 
    		ie.printStackTrace(); 
    	} 
    	return mappe;
    }

    // Starttag auslesen 
    public void startElement(String namespaceURI, String localName, 
            String qName, Attributes attrs) throws SAXException { 
        textPuffer(); 
        eName = ("".equals(localName)) ? qName : localName; 

        // Erfassen der Attribute in den Starttags 
        if (attrs != null) { 
            for (int i = 0; i < attrs.getLength(); i++) { 
                String aName = attrs.getLocalName(i); 
                if ("".equals(aName)) 
                    aName = attrs.getQName(i); 
            } 
        } 
    } 

    // Schlusstags auslesen 
    public void endElement(String namespaceURI, String localName, String qName) 
            throws SAXException { 
        textPuffer(); 
        eName = ("".equals(localName)) ? qName : localName; 
        if(eName.equals("Bezeichnung")){
        	mappe.getTeilvorgaenge().add(vorgang);
        }
    } 

    // Erzeugt einen String aus den Char-Arrays und liest 
    // diesen in einen StringBuffer ein 
    public void characters(char[] buf, int offset, int len) throws SAXException { 
        String s = new String(buf, offset, len); 
        if(s.contains("\t") || s.contains("\n")){
        	s = "\n";
        }
        
//        if (s.equals(" \n\t") || s.equals("\n    ") || s.equals(" \n    ") 
//        	|| s.equals(" \n") || s.equals("\n\t\t") || s.equals("\n\t")
//        	|| s.equals("\n\t\t    ")) {
//        	s = "\n";
//		}
        else if (textBuffer == null) 
            textBuffer = new StringBuffer(s); 
        else 
            textBuffer.append(s); 
    } 

    /** ************** Hilfsmethoden ******************* */ 

    // Wandelt den StringBuffer in einen String und 
    private void textPuffer() throws SAXException { 
        if (textBuffer == null) 
            return; 
        ausgabe(eName, textBuffer.toString()); 
        textBuffer = null; 
    } 

    private void createVorgang(String value) {
//    	if(vorgang != null){
//    		mappe.getTeilvorgaenge().add(vorgang);
//    	}
    	if (value.equals("VNS-Vertrag")){
    		vorgang = new Vertrag();
    	}
    	if (value.equals("Auszahlung"))
    		vorgang = new Auszahlung();
    	vorgang.setTitel(value);
    } 

    private void createDocInVorgang() {
    	if (vorgang.getTitel().equals("VNS-Vertrag")){
    		
    		docInVorgang = new Vertragsblatt();
    		vorgang.getChildren().add(docInVorgang);
    	}
    	
    }
    
    // Ausgabe des Strings 
    private void ausgabe(String key, String value) throws SAXException { 
    	try { 
            if (out == null) 
                out = new OutputStreamWriter(System.out, "UTF8"); 
            if(key.equals("AktenzeichenA") )
            	mappe.setAzA(value); 
            if(key.equals("AktenzeichenB"))
            	mappe.setAzB(value);
            if(key.equals("BNRZD"))
            	mappe.setBnrzd(value);
            if(key.equals("Amt"))
            	mappe.setAmt(value);
            if(key.equals("FP"))
            	mappe.setFp(value);
            if(key.equals("EUC"))
            	mappe.setEuCode(value);
            if(key.equals("Erstzahlungsjahr"))
            	mappe.setErstzahlungsjahr(value);
            if(key.equals("Status"))
            	mappe.setStatus(value);
            
            //Neuer Vorgang
            if(key.equals("Bezeichnung"))
            	createVorgang(value);
            if(key.equals("VStatus")){
            	if(vorgang instanceof Vertrag){
            		((Vertrag) vorgang).setStatus(value);
            	}
            	if(vorgang instanceof Auszahlung){
            		((Auszahlung) vorgang).setStatus(value);
            	}
            }
            if(key.equals("Zuwendungssumme")){
            	if(vorgang instanceof Vertrag){
            		((Vertrag) vorgang).setZuwendung(Float.parseFloat(value));
            	}
            	if(vorgang instanceof Auszahlung){
            		((Auszahlung) vorgang).setZuwendungssumme(Float.parseFloat(value));
            	}
            }
            if(key.equals("Zahlungsbetrag")){
            	if(vorgang instanceof Auszahlung){
            		((Auszahlung) vorgang).setZahlungsbetrag(Float.parseFloat(value));
            	}
            }
            
            
            //Vertragsblatt
           	if(key.equals("Vertragsbeginn")){
            	createDocInVorgang();
            	((Vertragsblatt) docInVorgang).setVertragsbeginn(value); 
            }
            if(key.equals("Vertragslaufzeit"))
            	((Vertragsblatt) docInVorgang).setLaufzeit(Integer.parseInt(value));
            if(key.equals("Vertragsabschluss"))
            	((Vertragsblatt) docInVorgang).setVertragsabschluss(value);
            
            if(key.equals("BezugsJahr")){
            	((Vertragsblatt) docInVorgang).getZuwendungen().add(new Zuwendung());
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setBezugsJahr(Integer.parseInt(value));
            }
            if(key.equals("PC"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setPC(Integer.parseInt(value));
            if(key.equals("Beihilfesatz"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setBeihilfesatz(Float.parseFloat(value));
            if(key.equals("Vertragsflaeche"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setVertragsflaeche(Float.parseFloat(value));
            if(key.equals("Zuwendungsbetrag"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setZuwendungsbetrag(Float.parseFloat(value));
            if(key.equals("EU-ABB"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setEUABB(value);
            if(key.equals("EU-Titel"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setEUTitel(value);
            if(key.equals("Anteil-EU"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setAnteilEU(Float.parseFloat(value));
            if(key.equals("Landestitel"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setLandestitel(value);
            if(key.equals("Anteil-Land"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setAnteilLand(Float.parseFloat(value));
            if(key.equals("Titel-Sonst"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setTitelSonst(value);
            if(key.equals("Anteil-Sonst"))
            	((Vertragsblatt) docInVorgang).getLastZuwendung().setAnteilSonst(Float.parseFloat(value));
            
            
            eName = "";
            out.flush(); 
        } catch (IOException ex) { 
            throw new SAXException("Ein-/Ausgabefehler", ex); 
        } 
    }


} 
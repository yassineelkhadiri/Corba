package exo1;

import org.omg.CORBA.*;

import org.omg.PortableServer.*;

import java.io.*;

import java.lang.*;

import java.util.*;

import org.omg.CosNaming.*;

import org.omg.CosNaming.NamingContextPackage.*;


public class Serveur {
    public static void main(String[] args) throws IOException {
        ////////////////////////////////////////
        // Initialisation de l'ORB et de la POA 
        ////////////////////////////////////////
        try {
            //init ORB
            ORB orb = ORB.init(args, null);

            //init POA
            POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            poa.the_POAManager().activate();

            ////////////////////////////////////////////////////////////////
            // Instantiation de l'objet : creation de 
            // l'implementation de l'objet
            ////////////////////////////////////////////////////////////////
            calculImpl calcImpl = new calculImpl();

            ////////////////////////////////////////////
    	    //  Activation de l'objet d'implementation 
            ////////////////////////////////////////////
            org.omg.CORBA.Object calc = poa.servant_to_reference(calcImpl);
            ////////////////////////////////////////////
            //PARTIE EXO2//
            ///////////////////////////////////////////
             
            // Utiliser NamingContextExt qui est Interoperable
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            calcul href = calculHelper.narrow(calc);
            // enregistrer le servant dans le service de nommage
            String name = "exo2";
            NameComponent path[] = ncRef.to_name( name );
            ncRef.rebind(path, calc);

            ////////////////////////////////////////////////////////
	    //  Sauvegarde de la reference d'objet dans un fichier
            ///////////////////////////////////////////////////////
            // try {
            //     String calc_ref = orb.object_to_string(calc);
            //     String refFile = "calcul.ref";
            //     PrintWriter out = new PrintWriter(new FileOutputStream(refFile));
            //     out.println(calc_ref);
            //     out.close();
            // } catch (IOException ex) {
            //     System.err.println(
            //         "Impossible d'ecrire la reference dans calcul.ref");
            //     System.exit(1);
            // }


	    ////////////////////////////////////////////////////////////////
	    // Lancement de la POA  et de l'ORB : a partir de cet instant, le serveur
	    // est capable de traiter les requetes sur les objets deja
	    // actives ainsi que ceux qui le seront par la suite
	    // La methode "orb.run" est bloquante
	    ////////////////////////////////////////////////////////////////
            System.out.println("Le serveur est pret ");
            orb.run();

            System.exit(0);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}

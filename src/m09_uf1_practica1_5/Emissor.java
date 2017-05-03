package m09_uf1_practica1_5;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

public class Emissor {    

    public KeyPair generadorAleatori() {
        KeyPair keys = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            keys = keyGen.genKeyPair();
        } catch (Exception e) {
            System.err.println("Generador no disponible.");
        }
        return keys;
    }

    public byte[] signData(String fitxer, PrivateKey priv) {
        byte[] signature = null;
        FileInputStream datafis;
        BufferedInputStream bufin = null;
        byte[] buffer = new byte[1024];
        int len;
        
        try {
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initSign(priv); //Inicialitzem la firma digital a partirde l’algorisme utilitzat
            
            datafis = new FileInputStream(fitxer);
            bufin = new BufferedInputStream(datafis);
                        
            while (bufin.available() != 0) {
                len = bufin.read(buffer);
                signer.update(buffer, 0, len);//Li assignem a l’objecte firma les dades afirmar digitalment
            }  

            bufin.close();
            signature = signer.sign();//Finalment generem la firma
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }
}

package m09_uf1_practica1_5;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Receptor {

    /**
     * Leemos el fichero recibido por parametro mediante un FileInputStream y un
     * BufferedInputStream y gracias al objeto Signature validamos que el 
     * contenido que hemos recibido es el correcto verificando la firma.
     * @param fitxer
     * @param signature
     * @param pub
     * @return 
     */
    public boolean validateSignature(String fitxer, byte[] signature, PublicKey pub) {
        boolean isValid = false;
        FileInputStream datafis;
        BufferedInputStream bufin = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            Signature signer = Signature.getInstance("SHA256withRSA");
            signer.initVerify(pub);
            datafis = new FileInputStream(fitxer);
            bufin = new BufferedInputStream(datafis);
            while (bufin.available() != 0) {
                len = bufin.read(buffer);
                signer.update(buffer, 0, len);//Li assignem a l’objecte firma les dades afirmar digitalment
            }  

            bufin.close();
            isValid = signer.verify(signature);
        } catch (Exception ex) {
            System.err.println("Error validant les dades: " + ex);
        }
        return isValid;
    }

}

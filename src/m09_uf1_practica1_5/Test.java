package m09_uf1_practica1_5;

import java.security.KeyPair;

public class Test {

    public static void main(String[] args) {
        /**
         * Declaramos las variables papichulas
         */
        Emissor em = new Emissor();
        Receptor re = new Receptor();
        String res;
        KeyPair kP;
        byte[] contenido;
        //Generamos el par de claves
        kP = em.generadorAleatori();
        //Almacenamos en un array de bytes el contenido de signData que es la firma
        //digital
        contenido = em.signData("Carta.txt", kP.getPrivate());
        //Mostramos Todo correcto si el metodo signData verifica que el contenido
        //es el mismo que el enviado mediante la clave publica o Illo tu que
        // si el metodo devuelve false
        System.out.println(re.validateSignature("Carta2.txt", contenido, kP.getPublic())?"Todo correcto":"Illo tu que ase");

    }

}

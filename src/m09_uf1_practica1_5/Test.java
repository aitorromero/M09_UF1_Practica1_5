package m09_uf1_practica1_5;

import java.security.KeyPair;

public class Test {

    public static void main(String[] args) {
        Emissor em = new Emissor();
        Receptor re = new Receptor();
        String res;
        KeyPair kP;
        byte[] contenido;
        kP = em.generadorAleatori();
        
        contenido = em.signData("Carta.txt", kP.getPrivate());
        //(re.validateSignature("Carta.txt", contenido, kP.getPublic()))? "Es valido": "No es valido";
        
        System.out.println(re.validateSignature("Carta2.txt", contenido, kP.getPublic())?"Todo correcto":"Illo tu que ");
        /*
        if(re.validateSignature("Carta.txt", contenido, kP.getPublic())){
            System.out.println("Todo correcto");
        }else{
            System.out.println("Illo tu que ");
        }*/
    }

}

package teoria;
//Comentario prueba
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Entrada {
    public static Scanner entrada= new Scanner (System.in);
    public static void main(String[] args) {
        int aceptada=0;
        int n, l,potencia;
       String [] cadena_w = new String[2];
       //PUNTO 1
        HashSet<String> alfabeto = GeneraAlfabeto();
        //PUNTO 2
        System.out.println("Ingresa dos cadenas (ambas deben tener solamente simbolos del alfabeto y ser diferentes):");
       do{
        while(aceptada<2){
            cadena_w[aceptada]=entrada.next();
                if(CadenaEsValida(cadena_w[aceptada],alfabeto)){
                aceptada++;
                System.out.println("Cadena aceptada");
                }else{
                    System.out.println("Cadena invalida. Ingresala nuevamente");
                }
            }
            aceptada=0;
        }while(cadena_w[0].equals(cadena_w[1]));
        System.out.println("Las candeas son:\n"+cadena_w[0]+"\n"+cadena_w[1]);
       //PUNTO 3
        EvaluaDosCadenas(cadena_w[0],cadena_w[1]);


       //PUNTO 4
        do{
            //tiene restriccion para las combinaciones posibles
            System.out.println("Escribe el numero de palabras del lenguaje: ");
            n=entrada.nextInt();
            System.out.println("Escribe la longitud de las palabras del lenguaje: ");
            l=entrada.nextInt();
            //restriccion 
        }while(n>Math.pow(alfabeto.size(),l));
        HashSet<String>lenguaje_1=GeneraLenguajeAleatorio(alfabeto,n,l);
        HashSet<String>lenguaje_2=GeneraLenguajeAleatorio(alfabeto,n,l);
        System.out.println("El lenguaje uno es :"+lenguaje_1);
         System.out.println("El lenguaje dos es :"+lenguaje_2);
        //PUNTO 5
        HashSet<String>lenguaje_0=DiferenciaDeLenguaje(lenguaje_1,lenguaje_2);
        System.out.println("El lenguaje L0 (L1-L2) es: "+lenguaje_0);
        //PUNTO 6
        System.out.println("Se va a evaluar el punto 6 ");
        do{
            System.out.println("Teclea a que potencia se va a elevar el alfabeto: ");
            potencia=entrada.nextInt();
        }while(potencia>5||potencia<-5);
        
        System.out.println("La potencia del lenguaje es:\n "+AlfabetoPotencia(alfabeto,potencia));

        //Punto 7.A
        System.out.println("Se va a evaluar el punto 7.A ");
        System.out.println("Ingresa una cadena con las siguientes restricciones: ");
        System.out.println("-Todas las cadenas de letras en minúsculas (a-z) que contengan las cinco vocales en orden.");
        System.out.println("-Las vocales pueden estar repetidas (siempre que mantengan el orden), las secuencias de las vocales también pueden repetirse.");
        System.out.println("-La secuencia completa de vocales también puede repetirse.");
        String cadenareg=entrada.next();
        EvaluaCadena(cadenareg);
    }
      
     //PUNTO 1
    public static HashSet<String> GeneraAlfabeto(){
        HashSet<String> simbolos = new HashSet<String>();  
        int tipo,cantidad,primer,ultimo;
        char primasci,ultiasci;
        System.out.println("¿Deseas ingresar tu albafeto por digito o por rango? \n1=digito \n2=rango");
        tipo=entrada.nextInt();
        switch(tipo){
            case 1:
                do{
                    System.out.print("\nCúantos simbolos tiene tu alfabeto? (Debe tener al menos 3)");
                    cantidad=entrada.nextInt();
                    
                }while(cantidad<3);
                System.out.println("Escribe los simbolos: ");
                    for(int i=0;i<cantidad;i++){  
                        System.out.print((i+1)+".- Simbolo:");
                        simbolos.add(entrada.next());
                    }
            break;
            case 2:
                 do{
                        System.out.println("\nIngresa el valor el codigo ASCII del primer simbolo");
                        primer=entrada.nextInt();
                        primasci= (char)primer;
                        System.out.print("\nTu primer simbolo es: "+primasci );
                        System.out.println("\nIngresa el valor en codigo ASCII del ultimo simbolo:");
                        ultimo=entrada.nextInt();
                        ultiasci=(char)ultimo;
                        System.out.println("\nEl ultimo digito es: "+ultiasci);
                    }while(primer>ultimo);
          for (char i=primasci;i<=ultiasci;i++){
            simbolos.add(String.valueOf(i));

          }
            break;
            default:
            System.out.println("Opcion invalida");
        }
        System.out.println("\nEl alfabeto esta formado por: ");
        System.out.println(simbolos);
        return simbolos;
    }
     //PUNTO 2 (revisar)
     public static boolean CadenaEsValida(String cadena, HashSet<String> alfabeto){
        if(cadena.length() == 0){
            return true;
        }
        String simbolo_actual="";
        String ultima_cadena_valida="";
        for(String caracter:cadena.split("")){
            //m i c a d e n a a a 
            //caracater= m
            //caracter i
           simbolo_actual = simbolo_actual+caracter;
           if(alfabeto.contains(simbolo_actual)){
                ultima_cadena_valida=ultima_cadena_valida+simbolo_actual;
                //System.out.println(simbolo_actual);
                simbolo_actual = "";
           }
        }
    return cadena.length()==ultima_cadena_valida.length();
    }
    //PUNTO 3
    public static void EvaluaDosCadenas(String w1, String w2){
        int bandera=0;
        System.out.println("w1 es "+w1);
        System.out.println("w2 es "+w2);
        System.out.print("w1 ");
         //Verifica subsecuencia
         ArrayList <Character> arre = new ArrayList<Character>(); 
       
         for(int i=0;i<w1.length();i++){
             arre.add(w1.charAt(i));
         }
         for(int i=1;i<w2.length()-1;i++){
             //hocolat
             if(!arre.isEmpty()){
                 if(w2.charAt(i)==arre.get(0)){
                     arre.remove(0);
                }
             }
            
         }
         if(arre.isEmpty()&&!w1.equals("")){
             System.out.print("es una subsecuencia");
             bandera=1;
         }
        for(int i=0; i<=(w2.length()-w1.length()); i++){
            if(w1.equals(w2.substring(i,w1.length()+i))&&(w1.length()!=w2.length())&&!w1.equals("")){
                if(i==0){
                    System.out.print("es un prefijo propio");
                    bandera=1;
                }
                if(i>0&&i<(w2.length()-w1.length())){
                    System.out.print(" y es una subcadena");
                    bandera=1;
                }
                if(i==(w2.length()-w1.length())){
                    System.out.print("es un sufijo propio");
                    bandera=1;
                }
            }
        }
        if(w1.equals(w2)||w1.equals("")){
            System.out.print("es un prefijo y sufijo no propio");
            bandera=1;
        }
        if(bandera==0){
            System.out.print("es nada");
        }
        System.out.println(" de w2");
    }


     //PUNTO 4
    public static HashSet<String> GeneraLenguajeAleatorio(HashSet<String> alfabeto, int n, int l){
        HashSet<String> lenguaje = new HashSet<String>(); 
        
        while(lenguaje.size()<n){
            String palabra="";
            int size = alfabeto.size();
            for(int j = 0; j <l ; j++){
                int item = new Random().nextInt(size); 
                int i = 0;
                for(String caracter : alfabeto)
                {
                    if (i == item)
                        palabra+=caracter;
                    i++;
                }
            }
            if(!lenguaje.contains(palabra)){
                lenguaje.add(palabra);
            }
        }
        return lenguaje;
    }
     //PUNTO 5
    public static HashSet<String> DiferenciaDeLenguaje(HashSet<String> lenguaje_1, HashSet<String> lenguaje_2){
        for(String palabra:lenguaje_2){
            if(lenguaje_1.contains(palabra)){
                lenguaje_1.remove(palabra);
            }
        }
        return lenguaje_1;
    }
    //PUNTO 6
    public static HashSet<String> AlfabetoPotencia(HashSet<String> alfabeto, int potencia){
    if(potencia == 0){
        return new HashSet<String>();
    }else if(potencia<0){
        return AlfabetoPotenciaNeg(alfabeto,potencia);
    }else{  
        
        return AlfabetoPotenciaPos(alfabeto,alfabeto,potencia);
    }
}
    public static HashSet<String> AlfabetoPotenciaPos(HashSet<String> alfabeto,HashSet<String> alfabetoPot, int potencia){
    
    if(potencia==1){
        return alfabetoPot;
    }else{  
        HashSet<String> alfabetoNew = new HashSet<String>();
        for(String elemento1 : alfabetoPot){
            for(String elemento2 : alfabeto){
                alfabetoNew.add(elemento1+elemento2);
            }
        }
        potencia=potencia-1;
        return AlfabetoPotenciaPos(alfabeto,alfabetoNew,potencia);
    }    
}
 public static HashSet<String> AlfabetoPotenciaNeg(HashSet<String> alfabeto,int potencia){
        HashSet<String> alfabetoInv = new HashSet<String>();
        for(String elemento : alfabeto){
            alfabetoInv.add(Invertida(elemento));
        }
        return AlfabetoPotenciaPos(alfabetoInv, alfabetoInv,-potencia);
    }
    
public static String Invertida(String cadena){
    String invertida="";
    for(int i=cadena.length()-1; i>=0;i--){
        invertida=invertida+cadena.charAt(i);
    }
    return invertida;
}
    //PUNTO 7
    public static void EvaluaCadena(String cadena){
            String regex="^([abcdfghjklmnñpqrstvwxyz]*a[ebcdfghjklmnñpqrstvwxyz]*e[ibcdfghjklmnñpqrstvwxyz]*i[obcdfghjklmnñpqrstvwxyz]*o[ubcdfghjklmnñpqrstvwxyz]*u[bcdfghjklmnñpqrstvwxyz]*)*$";
        Pattern patron=Pattern.compile(regex);
        Matcher m =patron.matcher(cadena);
        if(m.find()){
            System.out.println("palabra correcta");
        }else{
            System.out.println("palabra incorrecta");
        }
    }
}

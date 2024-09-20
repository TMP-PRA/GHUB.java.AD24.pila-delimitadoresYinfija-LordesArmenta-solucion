package miPrincipal;
import pila.Pila;

import java.util.Scanner;

public class InfijaPostFija	 {
	// Precedencia de operadores
	private static final int ES_TRES = 3;
	private static final int ES_DOS = 2;
    private static final int ES_UNO = 1;
    private static final int ES_CERO = 0;
    private static final int ES_MENOR_CERO = -1;
    
    static Scanner entrada = new Scanner(System.in);

    public InfijaPostFija(){

    }

    public static int precedencia(char operador) {
        switch (operador) {
            case '^':
            	return ES_TRES;
            case '*':
            case '/':
                return ES_DOS;
            case '+':
            case '-':
                return ES_UNO;
            case '(':
			case ')':
            	return ES_CERO;
            default:
                return ES_MENOR_CERO;
        }
    }

	
	public static String convertirAPostfijo(String expresionInfija) {
	
        Pila<String> pila = new Pila<>();
        StringBuilder cadenaPostFija = new StringBuilder();
       
        for(int i=0;i<=expresionInfija.length()-1;i++) {
        	
            char simbolo = expresionInfija.charAt(i);
            
            switch(simbolo) {
            case '+':
            case '-':
            case '*':
            case '/':
            case '^':
        
            	//es un Operador
            	//considero que es un operando
            	//Se repetira mientra siga sacando de la pila Operadores
            	while (true) {
            		//si la pila esta vacia,sin preguntar la meto a la pila
	            	if (pila.esVacia()) {
		            		pila.apilar(caracterToString(simbolo));
		            		break;
		            	}
	            	
	            	else {
	            		//preguntar por la prioridad del operando
	            		if(precedencia(simbolo) > precedencia(pila.cima().charAt(0))) {
		            			pila.apilar(caracterToString(simbolo));
		            			break;
		            		}
	            		else {
	            			//saco el operador de la pila y lo anexo a la cadena postFija
	            			
	            			cadenaPostFija.append(pila.cima());
	            			pila.retirar();
	            		}
	            	}
            	}
            	break;
            case')':
            	  //Iterar hasta que encuentre un primer paréntesis izquierdo
            	 boolean buscandoParentesisIzquierdo = true;
            	 while (buscandoParentesisIzquierdo){
	            	
			            //Sacar operador cima de la pila y 
			            //pasarlo a la expresión postfija
	            		
	            		cadenaPostFija.append(pila.cima());
			    		pila.retirar();
	            	   
			            
			            //Si nueva cima es paréntesis izquierdo, 
			    		//suprimir elemento cima
			    		if(pila.cima().equals("(")) {
			    				pila.retirar();
			    				buscandoParentesisIzquierdo=false;
			    		}
		    		
            	}	
		    	break;
		    			
            case '(':
            	//pasarlo a la pila
            	pila.apilar(caracterToString(simbolo));
            	break;
            case ' ':
            	//es un espacio en blanco y solo lo dejamos pasar
            	break;
            default:
            	//considero que es un operando y lo paso directamente a
            	//la cadena PostFija
            	
            	cadenaPostFija.append(simbolo);
            			
            				
           }//fin de switch
            	
        }//fin de for
        

        for(int i=0;i<=pila.getTamanio();i++) {
           cadenaPostFija.append(pila.cima());
		   pila.retirar();
        }

		return cadenaPostFija.toString();
        
	}
	
	//convierte un char a un objeto tipo String
	private static String caracterToString(char ch) {
		  return String.valueOf(ch);
	}
	
	public static double evaluarPostfija(String expresionPrefija) {
		//Arreglo que almacenará operandos
		double valores[]=new double[expresionPrefija.length()];
		
		
		Pila<String> pila = new Pila<>();
		
		for (int i=0,j=0;i<=expresionPrefija.length()-1;i++) {
			char simbolo = expresionPrefija.charAt(i);
			if (!esOperador(simbolo)) {
				System.out.print("Dame valor para Operador "+simbolo+" ");
				valores[j]= entrada.nextDouble();
				pila.apilar(valores[j]+"");
				j++;
				
			}
			else{
				//Retiro los dos últimos elementos de la pila
				
				double y = Double.parseDouble(pila.cima());
			    pila.retirar();
			    
				double x = Double.parseDouble(pila.cima());
				pila.retirar();
				//efectuo la operación
				double z = calcularOperacion(x,y,simbolo);
				
				//apilo el resultado
				pila.apilar(z+"");
				
			}
			
		}
		
		
		double valor = Double.parseDouble(pila.cima());
		pila.retirar();
		return valor;
		
	}
	
	private static boolean esOperador(char operador) {
        switch (operador) {
            case '^':
            case '*':
            case '/':
            case '+':
            case '-':
            
            	return true;
            default:
                return false;
        }
    }
	public static double calcularOperacion(double a, double b, char car) {
		switch(car) {
			case '^':
				return Math.pow(a, b);
			case '/':
				return a/b;
			case '*':
			   return a*b;
			case '+':
				   return a+b;
			case '-':
				   return a-b;
			default:
				return 0.0;
		
		}
		
	}
	

}
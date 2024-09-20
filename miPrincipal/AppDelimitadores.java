package miPrincipal;
public class AppDelimitadores {
    public static void menu(){
        System.out.println("***********************************");
        System.out.println("  APLICACIONES CON USO DE PILAS    ");
        System.out.println("***********************************");
        System.out.println(" 1) Correspondencia de Delimitadores");
        System.out.println("***********************************");
        String expr = "(a+b/2)";
        Delimitadores objDel = new Delimitadores();
        if (objDel.evaluacionDelimitadores(expr))
           System.out.println("Expresion Correcta");
        else
           System.out.println("Expresion incorrecta");
        
        expr= "while (m<(n[8]+o)) { "+
              "int p=7; "+
               "/*comentarios*/"+
              "               } ";
        
        if (objDel.evaluacionDelimitadores(expr))
            System.out.println("Expresión correcta");
        else
         System.out.println("Expresión Incorrecta");
        expr= "while (m<(n[8]+o)) { "+
            "int p=7; "+
            "/*comentarios*/";
        if (objDel.evaluacionDelimitadores(expr))
             System.out.println("Expresión correcta");
        else
            System.out.println("Expresión Incorrecta");
        
    }
    
}
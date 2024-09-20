package miTest;

import miPrincipal.*;
import pila.Pila;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

class AppTest {

    
    private Delimitadores objDel;

    @BeforeEach
    public void setUp() {
        objDel = new Delimitadores();
    }

    @Test
    public void testEvaluacionDelimitadores_ExpresionCorrecta() {
        String expr = "while (m<(n[8]+0)) { int p=7; /*comentarios*/ }";
        assertTrue(objDel.evaluacionDelimitadores(expr), "La expresión debería ser correcta");
    }

    @Test
    public void testEvaluacionDelimitadores_ExpresionIncorrecta() {
        String expr = "while (m<(n(8]+0)) {{ int p=7; /*comentarios*/ }";
        assertFalse(objDel.evaluacionDelimitadores(expr), "La expresión debería ser incorrecta");
    }

    @Test
    public void testEvaluacionDelimitadores_ExpresionVacia() {
        String expr = "";
        assertTrue(objDel.evaluacionDelimitadores(expr), "La expresión vacía debería ser correcta");
    }

    @Test
    public void testEvaluacionDelimitadores_ExpresionConSoloDelimitadores() {
        String expr = "{}[]()";
        assertTrue(objDel.evaluacionDelimitadores(expr), "La expresión con solo delimitadores debería ser correcta");
    }

    @Test
    public void testUsoDePila() {
        Pila<String> pila = new Pila<>();
        pila.apilar("1");
        pila.apilar("2");
        pila.apilar("3");

        assertEquals("3", pila.cima(), "La cima de la pila debería ser 3");
        pila.retirar();
        assertEquals("2", pila.cima(), "La cima de la pila debería ser 2 después de retirar el elemento superior");
        pila.retirar();
        assertEquals("1", pila.cima(), "La cima de la pila debería ser 1 después de retirar otro elemento");
        pila.retirar();
        assertTrue(pila.esVacia(), "La pila debería estar vacía después de retirar todos los elementos");
    }

    @Test
    public void testBuscar() {
        Pila<String> pila = new Pila<>();
        pila.apilar("A");
        pila.apilar("B");
        pila.apilar("C");

        assertEquals(2, pila.buscar("A"), "La posición de 'A' debería ser 2");
        assertEquals(1, pila.buscar("B"), "La posición de 'B' debería ser 1");
        assertEquals(0, pila.buscar("C"), "La posición de 'C' debería ser 0");
        assertEquals(-1, pila.buscar("D"), "La posición de 'D' debería ser -1 ya que no está en la pila");
    }

    @Test
    public void testPrecedencia() {
        assertEquals(3, InfijaPostFija.precedencia('^'), "El operador ^ debería tener precedencia 3");
        assertEquals(2, InfijaPostFija.precedencia('*'), "El operador * debería tener precedencia 2");
        assertEquals(2, InfijaPostFija.precedencia('/'), "El operador / debería tener precedencia 2");
        assertEquals(1, InfijaPostFija.precedencia('+'), "El operador + debería tener precedencia 1");
        assertEquals(1, InfijaPostFija.precedencia('-'), "El operador - debería tener precedencia 1");
        assertEquals(0, InfijaPostFija.precedencia('('), "El operador ( debería tener precedencia 0");
        assertEquals(0, InfijaPostFija.precedencia(')'), "El operador ) debería tener precedencia 0");
        assertEquals(-1, InfijaPostFija.precedencia('a'), "Cualquier otro operador debería tener precedencia -1");
    }
    @Test
    public void testConvertirAPostfijo() {
        String expresionInfija = "(A+B)*(C-D)";
        String resultadoEsperado = "AB+CD-*";
        String resultado = InfijaPostFija.convertirAPostfijo(expresionInfija);
        assertEquals(resultadoEsperado, resultado, "La conversión de infija a prefija no es correcta");
        expresionInfija = "(X + Z) * W / T ^ Y - V";
        resultadoEsperado = "XZ+W*TY^/V-";
        resultado = InfijaPostFija.convertirAPostfijo(expresionInfija);
        assertEquals(resultadoEsperado, resultado, "La conversión de infija a postfija no es correcta");
    }

    @Test
    public void testCalcularOperacion() {
        assertEquals(9.0, InfijaPostFija.calcularOperacion(3, 2, '^'), 0.0001, "3^2 debería ser 9.0");
        assertEquals(1.5, InfijaPostFija.calcularOperacion(3, 2, '/'), 0.0001, "3/2 debería ser 1.5");
        assertEquals(6.0, InfijaPostFija.calcularOperacion(3, 2, '*'), 0.0001, "3*2 debería ser 6.0");
        assertEquals(5.0, InfijaPostFija.calcularOperacion(3, 2, '+'), 0.0001, "3+2 debería ser 5.0");
        assertEquals(1.0, InfijaPostFija.calcularOperacion(3, 2, '-'), 0.0001, "3-2 debería ser 1.0");
    }

}
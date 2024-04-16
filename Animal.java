// package Zoologico;

public class Animal {
  
  int QtJaula, QtSaida, QtEntrada;
  String Nome, Genero;

  // Animal(){};

  int Cadastrar(int QtJaula, int QtEntrada) {
    QtJaula += QtEntrada;
    return QtJaula;
  }
  
  int Retirar(int QtJaula, int QtSaida) {
    QtJaula -= QtSaida;
    return QtJaula;
    
  }
}
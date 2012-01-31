package comedor

class Test {

    String pruebaEscritura
    
    
    static constraints = {
        pruebaEscritura nullable:true
    }
    
    String toString(){
        return pruebaEscritura;
    }
}

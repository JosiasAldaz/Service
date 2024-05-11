/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package ws;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author LENOVO
 */
@WebService(serviceName = "WSOperaciones")
public class WSOperaciones {

    ArrayList<persona> Lista_personas = new ArrayList();
    persona per1 = new persona();
    
    @WebMethod(operationName = "Login")
    public String Login(@WebParam(name = "User") String User, @WebParam(name = "password") String Password) {
        String retorno = "";
        if(Lista_personas.isEmpty()){
            retorno = "no hay usuarios";
        }else{
            for (int i = 0; i < Lista_personas.size(); i++) {
                if(User.equals(Lista_personas.get(i).getUsuario()) && Password.equals(Lista_personas.get(i).getPassword())){
                retorno = "coincidencia";
                }
            }       
        }
        return retorno;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Procesar_pago")
    public int Procesar_pago(@WebParam(name = "total") int total, @WebParam(name = "pago") int pago) {
        if(pago >= total){
             return pago-total;
        }else{
            return -1;
        }
    }
    
    @WebMethod(operationName = "Agregar_usuario")
    public void Agregar_usuario(@WebParam(name = "usuario") String usuario, @WebParam(name = "Contra") String Contra) {
        per1.setPassword(Contra);
        per1.setUsuario(usuario);
        per1.setSaldo(100);
        Lista_personas.add(per1);
        System.out.println(Lista_personas.size());
    }
    
    @WebMethod(operationName = "Deposito")
    public boolean Agregar_Deposito(@WebParam(name = "usuario") String usuario,@WebParam(name = "deposito") int deposito) {
        boolean retorno = false;
        for (int i = 0; i < Lista_personas.size(); i++) {
            if(Lista_personas.get(i).getUsuario().equals(usuario) ){
                int ingreso = Lista_personas.get(i).getSaldo()+ deposito;
                Lista_personas.get(i).setSaldo(ingreso);
                retorno = true;
            }
        }
        return retorno;
    }

    @WebMethod(operationName = "Retiro")
    public String Retirar(@WebParam(name = "valor") int valor,@WebParam(name = "usuario") String usuario) {
        String retorno = "no hay fondos suficientes";
        for (int i = 0; i < Lista_personas.size(); i++) {
            if(Lista_personas.get(i).getUsuario().equals(usuario) && valor <= Lista_personas.get(i).getSaldo()){
                int cambio = Lista_personas.get(i).getSaldo() - valor;
                Lista_personas.get(i).setSaldo(cambio);
                retorno = "retiro exitoso";
            }
        }
        
        return retorno;
    }
    
    @WebMethod(operationName = "Mostrar_saldo")
    public int Mostrar_saldo(@WebParam(name = "usuario") String usuario) {
        int saldo = 0;
        for (int i = 0; i < Lista_personas.size(); i++) {
            if(Lista_personas.get(i).getUsuario().equals(usuario) ){
                saldo = Lista_personas.get(i).getSaldo();
            }
        }
        return saldo;
    }
}

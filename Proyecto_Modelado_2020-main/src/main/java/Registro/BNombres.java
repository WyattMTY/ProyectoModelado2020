/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author wyatt
 */
public class BNombres {

    List<Nombres> listaPersonas = new ArrayList<Nombres>();

    public void buscarNombres() throws InterruptedException, ExecutionException {
        CollectionReference personas = ConexionFirebase.bd.collection("personas");
        ApiFuture<QuerySnapshot> querysnapshot = personas.get();

        for (DocumentSnapshot document : querysnapshot.get().getDocuments()) {
            Nombres nombres;
            nombres = new Nombres(
                    document.getId(),
                    document.getString("nombre"),
                    document.getString("apellidoPaterno"),
                    document.getString("apellidoMaterno"),
                    document.getString("apellidoPaterno"),
                    document.getString("edad"),
                    document.getString("telefono"),
                    document.getString("correo"),
                    document.getString("celular"));
            System.out.println(document.getId());
        }
    }
}

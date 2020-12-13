/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registro;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author wyatt
 */
public class ConexionFirebase {

    static Firestore bd;

    public static void Conectar() throws IOException {

        FileInputStream serviceAccount
                = new FileInputStream("proyectodm.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://proyectodm-3b3a0.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
        bd = FirestoreClient.getFirestore();
        System.out.println("La conexion fue exitosa");
    }

    public static void main(String[] args) {
        try {
            Conectar();
        } catch (Exception e) {

        }
    }

    public static boolean insertarDatos(
            String coleccion,
            String documento,
            Map<String, Object> data) {
            
        try{
            DocumentReference docRef;
            docRef = bd.collection(coleccion).document(documento);
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time: " + result.get().getUpdateTime());
            return true;
        }catch(Exception e){
            
        }
        return false;

    }

}

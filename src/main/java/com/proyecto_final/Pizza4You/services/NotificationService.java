package com.proyecto_final.Pizza4You.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.proyecto_final.Pizza4You.model.EstadoPedido;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void avisarCambioEstado(Integer idUsuario, Integer idPedido, EstadoPedido nuevoEstado) {
        
   
        String titulo = "Actualización de tu pedido #" + idPedido;
        String cuerpo = "El estado de tu pizza ahora es: " + nuevoEstado.getLabel();


        Message mensaje = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(titulo)
                        .setBody(cuerpo)
                        .build())
                .setTopic("usuario_" + idUsuario) // Canal específico de este usuario
                .build();

   
        try {
            FirebaseMessaging.getInstance().send(mensaje);
            System.out.println("Notificación enviada al usuario " + idUsuario);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación: " + e.getMessage());
        }
    }
    /*
    public void avisarStockBajo(String nombreIngrediente, int stockActual) {
        String titulo = "Stock bajo - " + nombreIngrediente;
        String cuerpo = "Quedan solo " + stockActual + " unidades. Es necesario reponer.";
        
        Message mensaje = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle(titulo)
                        .setBody(cuerpo)
                        .build())
                .setTopic("cocineros") // Topic para todos los cocineros
                .build();
        
        try {
            FirebaseMessaging.getInstance().send(mensaje);
            System.out.println("Notificación de stock bajo enviada para: " + nombreIngrediente);
        } catch (Exception e) {
            System.err.println("Error al enviar notificación de stock: " + e.getMessage());
        }
    } */
}
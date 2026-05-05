/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author valeria
 */
public class MongoConfig {
    private MongoConfig(){ // Constructor privado para evitar que alguien cree instancias de esta clase
        
    }
    
    public static MongoClientSettings buildSettings(String uri){
        ConnectionString conn = new ConnectionString(uri); // Crea un objeto que analiza y valida la cadena de conexión
        
        CodecRegistry pojoCodecRegistry =  // e enseña a MongoDB cómo traducir objetos Java a BSON (el formato de Mongo) y viceversa.
                fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(), // Mantiene el soporte para tipos básicos
                fromProviders(PojoCodecProvider.builder().automatic(true).build()))
        ;
        
        return MongoClientSettings.builder()
                .applyConnectionString(conn)
                .codecRegistry(pojoCodecRegistry)
                .build();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.learnup;

import com.learnup.config.MongoClientProvider;
import com.learnup.dao.InstructorDAO;
import com.learnup.entities.Contact;
import com.learnup.entities.Instructor;
import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author valeria
 */
public class InstructorDAOPruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init();

        InstructorDAO dao = new InstructorDAO();

        try {
            System.out.println("--- Pruebas de InstructorDAO ---");

            Instructor prof = new Instructor();
            prof.setName("Juan Lopez");
            prof.setEmail("juan.dev@example.com");
            prof.setExperienceYears(8);
            prof.setBio("Senior Fullstack Developer y apasionada por la enseñanza.");
            prof.setSpecialities(Arrays.asList("Java", "MongoDB", "React"));
            
            Contact contactInfo = new Contact();
            contactInfo.setPhone("123-456-789");
            contactInfo.setLinkedin("linkedin.com");
            prof.setContact(contactInfo);

            ObjectId id = dao.create(prof);
            System.out.println("Instructor creado con ID: " + id);

            dao.findById(id).ifPresent(i -> {
                System.out.println("[OK] Encontrado: " + i.getName() + " - Bio: " + i.getBio());
            });

            prof.setExperienceYears(9);
            prof.setBio(prof.getBio() + " Actualizado en 2026.");
            
            boolean updated = dao.update(prof);
            if (updated) {
                System.out.println("Datos actualizados correctamente.");
            }

            List<Instructor> lista = dao.findByName("Juan Lopez");
            System.out.println("Coincidencias por nombre: " + lista.size());

            List<Instructor> todos = dao.findAll(5);
            System.out.println("Total en BD (limit 5): " + todos.size());


        } catch (Exception e) {
            System.err.println("Falló la prueba:");
            e.printStackTrace();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.learnup;

import com.learnup.config.MongoClientProvider;
import com.learnup.dao.CourseDAO;
import com.learnup.entities.Course;
import java.util.Arrays;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author valeria
 */
public class LearnUp {

    public static void main(String[] args) {
        MongoClientProvider.INSTANCE.init();
        CourseDAO courseDAO = new CourseDAO();
        try {
            System.out.println("--- Pruebas de CourseDAO ---");

            Course newCourse = new Course();
            newCourse.setTitle("Base de Datos Avanzadas");
            newCourse.setCategory("Programación");
            newCourse.setPrice(49.99);
            newCourse.setInstructorId(new ObjectId()); // ID ficticio de instructor
            newCourse.setTags(Arrays.asList("Java", "NoSQL"));
            
            ObjectId id = courseDAO.create(newCourse);
            System.out.println("Curso creado con ID: " + id);

            courseDAO.findById(id).ifPresent(c -> {
                System.out.println("Curso encontrado: " + c.getTitle());
            });

            Course courseToUpdate = courseDAO.findById(id).get();
            courseToUpdate.setTitle("Base de Datos Avanzadas 2");
            courseToUpdate.setPrice(59.99);
            
            boolean isUpdated = courseDAO.update(courseToUpdate);
            if (isUpdated) {
                System.out.println("Curso actualizado correctamente.");
            }

            List<Course> coursesByName = courseDAO.findByName("Base de Datos Avanzadas 2");
            System.out.println("Cursos encontrados por nombre: " + coursesByName.size());

            List<Course> allCourses = courseDAO.findAll(10);
            System.out.println("Total de cursos en la lista: " + allCourses.size());

        } catch (Exception e) {
            System.err.println("Ocurrió un problema durante las pruebas:");
            e.printStackTrace();
        }
    }
}

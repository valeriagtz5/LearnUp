/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.dao;

import com.learnup.config.MongoClientProvider;
import com.learnup.entities.Instructor;
import com.learnup.entities.Student;
import com.learnup.exception.DAOException;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author valeria
 */
public class StudentDAO implements IStudentDAO{

    private final MongoCollection<Student> col;
    
    public StudentDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("students", Student.class);
    }

    @Override
    public ObjectId create(Student entity) throws DAOException {
        try{
            entity.setCreatedIn(Instant.now());
            var result = col.insertOne(entity);
            return result.getInsertedId().asObjectId().getValue();
        } catch(Exception e){
            throw new DAOException("No se logro insertar al estudiante", e);
        }
    }

    @Override
    public Optional<Student> findById(ObjectId id) {
        try{
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch(Exception e){
            throw new MongoException("No se logro obtener al estudiante", e);
        }
    }

    @Override
    public List<Student> findAll(int limit) {
        try{
            return col.find()
                    .limit(limit > 0 ? limit : 0)
                    .into(new ArrayList<>());
        } catch(Exception e){
            throw new MongoException("No se lograron obtener la lista de estudiantes", e);
        }
    }
    

    @Override
    public List<Student> findByName(String name) {
        try {
            return col.find(Filters.eq("name", name))
                      .into(new ArrayList<>());
        } catch (Exception e) {
            throw new MongoException("No se logró obtener la lista de estudiantes por nombre", e);
        }
    }

    @Override
    public boolean update(Student entity) {
        try {
            var result = col.updateOne(
                    Filters.eq("_id", entity.getId()),
                    Updates.combine(
                            Updates.set("name", entity.getName()),
                            Updates.set("email", entity.getEmail()),
                            Updates.set("enrolledCourses", entity.getEnrolledCourses()),
                            Updates.set("interests", entity.getInterests()),
                            Updates.set("settings", entity.getSettings()),
                            Updates.set("updated_in", Instant.now())
                    )
            );

            if (result.getMatchedCount() == 0) {
                throw new MongoException("No se encontró el estudiante con ID: " + entity.getId());
            }

            return true;

        } catch (Exception e) {
            throw new MongoException("Error al intentar actualizar el estudiante: " + entity.getName(), e);
        }
    }

    @Override
    public boolean deleteById(ObjectId id) {
        try{
            var result = col.deleteOne(Filters.eq("_id",id));
            if(result.getDeletedCount() == 0){
                throw new MongoException("No se encontro el estudiante");
            }
            return true;
        } catch(Exception e){
            throw new MongoException("No se logro eliminar el estudiante", e);
        }
    }

    @Override
    public long deleteAll() {
        try{
            var result = col.deleteMany(new Document());
            return result.getDeletedCount();
        } catch (Exception e){
            throw new MongoException("No se lograron eliminar a los estudiantes", e);
        }
    }
}

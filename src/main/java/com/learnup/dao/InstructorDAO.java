/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.dao;

import com.learnup.config.MongoClientProvider;
import com.learnup.entities.Instructor;
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
public class InstructorDAO implements IInstructorDAO {
    private final MongoCollection<Instructor> col;
    
    public InstructorDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("instructors", Instructor.class);
    }

    @Override
    public ObjectId create(Instructor entity) throws DAOException {
        try{
            entity.setCreatedIn(Instant.now());
            var result = col.insertOne(entity);
            return result.getInsertedId().asObjectId().getValue();
        } catch(Exception e){
            throw new DAOException("No se logro insertar el instructor", e);
        }
    }

    @Override
    public Optional<Instructor> findById(ObjectId id) {
        try{
            return Optional.ofNullable(col.find(Filters.eq("_id", id)).first());
        } catch(Exception e){
            throw new MongoException("No se logro obtener el instructor", e);
        }
    }

    @Override
    public List<Instructor> findAll(int limit) {
        try{
            return col.find()
                    .limit(limit > 0 ? limit : 0)
                    .into(new ArrayList<>());
        } catch(Exception e){
            throw new MongoException("No se lograron obtener la lista de instructores", e);
        }
    }
    

    @Override
    public List<Instructor> findByName(String name) {
        try {
            return col.find(Filters.eq("name", name))
                      .into(new ArrayList<>());
        } catch (Exception e) {
            throw new MongoException("No se logró obtener la lista de instructores por nombre", e);
        }
    }

    @Override
    public boolean update(Instructor entity) {
        try {
            var result = col.updateOne(
                    Filters.eq("_id", entity.getId()),
                    Updates.combine(
                            Updates.set("name", entity.getName()),
                            Updates.set("email", entity.getEmail()),
                            Updates.set("specialities", entity.getSpecialities()),
                            Updates.set("experienceYears", entity.getExperienceYears()),
                            Updates.set("bio", entity.getBio()),
                            Updates.set("contact", entity.getContact()), 
                            Updates.set("coursesTaught", entity.getCoursesTaught()),
                            Updates.set("updated_in", Instant.now())
                    )
            );

            if (result.getMatchedCount() == 0) {
                throw new MongoException("No se encontró el instructor con ID: " + entity.getId());
            }

            return true;

        } catch (Exception e) {
            throw new MongoException("Error al intentar actualizar el instructor", e);
        }
    }

    @Override
    public boolean deleteById(ObjectId id) {
        try{
            var result = col.deleteOne(Filters.eq("_id",id));
            if(result.getDeletedCount() == 0){
                throw new MongoException("No se encontro el instructor");
            }
            return true;
        } catch(Exception e){
            throw new MongoException("No se logro eliminar el instructor", e);
        }
    }

    @Override
    public long deleteAll() {
        try{
            var result = col.deleteMany(new Document());
            return result.getDeletedCount();
        } catch (Exception e){
            throw new MongoException("No se lograron eliminar los instructores", e);
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.dao;

import com.learnup.config.MongoClientProvider;
import com.learnup.dao.IEntityDAO;
import com.learnup.entities.Course;
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
public class CourseDAO implements ICourseDAO{
    private final MongoCollection<Course> col;
    
    public CourseDAO(){
        this.col = MongoClientProvider.INSTANCE.getCollection("courses", Course.class);
    }

    @Override
    public ObjectId create(Course entity) throws DAOException {
        try{
            entity.setCreatedIn(Instant.now());
            var result = col.insertOne(entity);
            return result.getInsertedId().asObjectId().getValue();
        } catch(Exception e){
            throw new DAOException("No se logro insertar el curso", e);
        }
    }

    @Override
    public Optional<Course> findById(ObjectId _id) {
        try{
            return Optional.ofNullable(col.find(Filters.eq("_id", _id)).first());
        } catch(Exception e){
            throw new MongoException("No se logro obtener el curso", e);
        }
    }

    @Override
    public List<Course> findAll(int limit) {
        try{
            return col.find()
                    .limit(limit > 0 ? limit : 0)
                    .into(new ArrayList<>());
        } catch(Exception e){
            throw new MongoException("No se lograron obtener la lista de cursos", e);
        }
    }

    @Override
    public List<Course> findByName(String name) {
        try {
            return col.find(Filters.eq("title", name))
                      .into(new ArrayList<>());
        } catch (Exception e) {
            throw new MongoException("No se logró obtener la lista de cursos por nombre", e);
        }
    }

    @Override
    public boolean update(Course entity) {
        
        try{
            var result = col.updateOne(
                    Filters.eq("_id", entity.getId()),
                    Updates.combine(
                            Updates.set("title", entity.getTitle()),
                            Updates.set("category", entity.getCategory()),
                            Updates.set("instructorId", entity.getInstructorId()),
                            Updates.set("price", entity.getPrice()),
                            Updates.set("rating", entity.getRating()),
                            Updates.set("tags", entity.getTags()),
                            Updates.set("modules", entity.getModules()),
                            Updates.set("updated_in", Instant.now())
                    )
            );
            
            if (result.getMatchedCount() == 0) {
                throw new MongoException("No se encontró el curso con ID: " + entity.getId());
            }

            return true;

        } catch (Exception e) {
            throw new MongoException("Error al intentar actualizar el curso", e);
        }
    }
       
    

    @Override
    public boolean deleteById(ObjectId id) {
        try{
            var result = col.deleteOne(Filters.eq("_id",id));
            if(result.getDeletedCount() == 0){
                throw new MongoException("No se encontro el curso");
            }
            return true;
        } catch(Exception e){
            throw new MongoException("No se logro eliminar el curso", e);
        }
    }

    @Override
    public long deleteAll() {
        try{
            var result = col.deleteMany(new Document());
            return result.getDeletedCount();
        } catch (Exception e){
            throw new MongoException("No se lograron eliminar los cursos", e);
        }
    }

}

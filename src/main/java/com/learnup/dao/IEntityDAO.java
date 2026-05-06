/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.learnup.dao;

import com.learnup.exception.DAOException;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 * @author valeria
 */
public interface IEntityDAO<T>{
    ObjectId create(T entity) throws DAOException; 
    
    Optional<T> findById(ObjectId id);
    
    List<T> findAll(int limit);
    
    List<T> findByName(String name);
    
    boolean update(T entity);
    
    boolean deleteById(ObjectId id);
    
    long deleteAll();
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learnup.exception;

/**
 *
 * @author valeria
 */
public class DAOException extends Exception{

    public DAOException(String message) { super(message); }
    public DAOException(String message, Throwable cause) { super(message, cause); }

}

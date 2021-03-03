/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.projetlo54.util;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.mapping.MetadataSource;

/**
 *
 * @author Yves
 */
public class HibernatUtil implements Serializable{
    
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory ;
    
    public static SessionFactory getSessionFactory(){
        
        if(sessionFactory ==null){
            try{
            // on cree un registry
            registry = new StandardServiceRegistryBuilder().configure().build();
            
            MetadataSources sources = new MetadataSources(registry);
            Metadata meteData = sources.getMetadataBuilder().build();
            
            sessionFactory = meteData.getSessionFactoryBuilder().build();
        
            }catch(Exception e){
                e.printStackTrace();
                if(registry !=null){
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
          }
        
        return sessionFactory;
    }
    
    public void shutdown(){
       if(registry !=null){
         StandardServiceRegistryBuilder.destroy(registry);
       }
}
}



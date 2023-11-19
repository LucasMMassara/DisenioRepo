/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package daos;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Lucas
 */
public interface DAO<T> {
    
    Optional<T> get(int id);
    
    List<T> getAll();
    
    void update(T t);
    
    void delete(T t);
    
    void save(T t);
    
}

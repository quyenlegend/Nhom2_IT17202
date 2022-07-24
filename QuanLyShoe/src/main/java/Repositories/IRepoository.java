/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import java.util.List;

/**
 *
 * @author boquy
 */
public interface IRepoository< KeyType> {
    public List<KeyType> selectAll();
    
    public KeyType selectID(int id);
    
    public KeyType save(KeyType KeyType);
    
    public  int delete(int id);
    public KeyType findById (String id ,String Size, String Mau );
    
}

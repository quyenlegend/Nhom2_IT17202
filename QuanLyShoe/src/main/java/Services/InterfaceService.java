/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import ViewModels.SanPhamModel;
import java.util.List;

/**
 *
 * @author boquy
 */
// model
public interface InterfaceService<KeyType> {
 public List<KeyType> Select();
 public KeyType getID (int id);
 public KeyType createNew(KeyType KeyType);
 public KeyType updateById(KeyType KeyType);
 public int deleteById(int id);
  public List<KeyType> getIDnhieu (String MaHD);
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author boquy
 */
@Entity(name="SIZE")
public class SizeEntity {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int MaSize;
     private String Size;
     
     @OneToMany(mappedBy = "SizeEntity")
     List<SanPhamEntity> _listSize;

    public SizeEntity() {
    }

    public SizeEntity(int MaSize, String Size, List<SanPhamEntity> _listSize) {
        this.MaSize = MaSize;
        this.Size = Size;
        this._listSize = _listSize;
    }

    public int getMaSize() {
        return MaSize;
    }

    public void setMaSize(int MaSize) {
        this.MaSize = MaSize;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public List<SanPhamEntity> getListSize() {
        return _listSize;
    }

    public void setListSize(List<SanPhamEntity> _listSize) {
        this._listSize = _listSize;
    }

 
     
     
}

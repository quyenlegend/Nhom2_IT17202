/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.SanPhamEntity;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Repositories.ISanPhamRepository;

/**
 *
 * @author Admin
 */
public class ProductsRepository implements ISanPhamRepository{

    @Override
    public List<SanPhamEntity> fildAll() {//p:sanPham,T:the loai,m:mau sac,s:sizeh:hangsx
        List<SanPhamEntity> sanpham;
        try 
            (Session session = HibernateUtil.getSessionFactory().openSession()){
//            String hql = "select p.MaSP, h.TenHang, t.TenLoai, m.TenMau,"
//                        +"s.Size, p.TenSP, p.NgayNhap, p.Gia,\n" 
//                        +"p.SoLuong, p.Anh, p.TrangThai \n" 
//                        +"from SANPHAM as p inner join HANGSX as h on p.MaHangSX = h.MaHangSX\n" 
//                        +"inner join THELOAI as t on p.MaTL = t.MaTL\n" 
//                        +"inner join MAUSAC as m on p.MaMS = m.MaMS\n" 
//                        +"inner join SIZE as s  on p.MaSize = s.MaSize";
//            
            String hql = "SELECT p FROM SANPHAM p where TrangThai = 0 AND SoLuong < 5";
            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    @Override
    public SanPhamEntity save(SanPhamEntity sanpham) {
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(sanpham);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                sanpham = null;
            }
        } finally {
            return sanpham;
        }
    }

    @Override
    public SanPhamEntity findById(String id) {
        SanPhamEntity sanpham;
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM SANPHAM p WHERE MaSP = :id ";
            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            query.setParameter("id", id);
            sanpham = query.getSingleResult();
        }
        return sanpham;
    }

    @Override
    public SanPhamEntity findByName(String TenSP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPhamEntity findByMS(int MaMS) {
        SanPhamEntity sanpham;
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM SANPHAM p WHERE p.mauSacId = :MaMS ";
            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            query.setParameter("MaMS", MaMS);
            sanpham = query.getSingleResult();
        }
        return sanpham;
    }

    @Override
    public SanPhamEntity findBYSize(int MaSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamEntity> fildAllActive() {
        List<SanPhamEntity> sanpham;
        try 
            (Session session = HibernateUtil.getSessionFactory().openSession()){
//            String hql = "select p.MaSP, h.TenHang, t.TenLoai, m.TenMau,"
//                        +"s.Size, p.TenSP, p.NgayNhap, p.Gia,\n" 
//                        +"p.SoLuong, p.Anh, p.TrangThai \n" 
//                        +"from SANPHAM as p inner join HANGSX as h on p.MaHangSX = h.MaHangSX\n" 
//                        +"inner join THELOAI as t on p.MaTL = t.MaTL\n" 
//                        +"inner join MAUSAC as m on p.MaMS = m.MaMS\n" 
//                        +"inner join SIZE as s  on p.MaSize = s.MaSize";
//            
            String hql = "select p from SANPHAM p where TrangThai = 0" ;
            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    @Override
    public List<SanPhamEntity> fildAllInActive() {
        List<SanPhamEntity> sanpham;
        try 
            (Session session = HibernateUtil.getSessionFactory().openSession()){
//            String hql = "select p.MaSP, h.TenHang, t.TenLoai, m.TenMau,"
//                        +"s.Size, p.TenSP, p.NgayNhap, p.Gia,\n" 
//                        +"p.SoLuong, p.Anh, p.TrangThai \n" 
//                        +"from SANPHAM as p inner join HANGSX as h on p.MaHangSX = h.MaHangSX\n" 
//                        +"inner join THELOAI as t on p.MaTL = t.MaTL\n" 
//                        +"inner join MAUSAC as m on p.MaMS = m.MaMS\n" 
//                        +"inner join SIZE as s  on p.MaSize = s.MaSize";
//            
            String hql = "select p from SANPHAM p where TrangThai = 1" ;
            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    @Override
    public List<SanPhamEntity> fildAllMa() {
        List<SanPhamEntity> sanpham;
        try 
            (Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT p.MaSP FROM SANPHAM p where TrangThai = 0";
            TypedQuery<SanPhamEntity> query = session.createQuery(hql, SanPhamEntity.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    
    

    

    
}
    


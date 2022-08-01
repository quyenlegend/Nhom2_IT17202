/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package responsitories;

import Utilities.HibernateUtil;
import entities.HangSX;
import entities.SanPham;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class SanPhamRepository implements ISanPhamRepository{

    @Override
    public List<SanPham> fildAll() {//p:sanPham,T:the loai,m:mau sac,s:sizeh:hangsx
        List<SanPham> sanpham;
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
            String hql = "SELECT p FROM SANPHAM p";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    @Override
    public SanPham save(SanPham sanpham) {
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
    public SanPham findById(String id) {
        SanPham sanpham;
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM SANPHAM p WHERE MaSP = :id ";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            query.setParameter("id", id);
            sanpham = query.getSingleResult();
        }
        return sanpham;
    }

    @Override
    public SanPham findByName(String TenSP) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SanPham findByMS(int MaMS) {
        SanPham sanpham;
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p FROM SANPHAM p WHERE p.mauSacId = :MaMS ";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            query.setParameter("MaMS", MaMS);
            sanpham = query.getSingleResult();
        }
        return sanpham;
    }

    @Override
    public SanPham findBYSize(int MaSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPham> fildAllActive() {
        List<SanPham> sanpham;
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
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    @Override
    public List<SanPham> fildAllInActive() {
        List<SanPham> sanpham;
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
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
            sanpham = query.getResultList();
        } 
        return sanpham;
    }

    
    }
    


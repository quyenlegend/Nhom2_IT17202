/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import entities.HangSX;
import entities.MauSac;
import entities.SanPham;
import entities.Size;
import entities.TheLoai;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.QLSanPham;
import service.HangSXService;
import service.IHangSXService;
import service.IMauSacService;
import service.ISanphamService;
import service.ISizeService;
import service.ITheloaiService;
import service.MauSacService;
import service.SanphamService;
import service.SizeService;
import service.TheLoaiService;

/**
 *
 * @author Admin
 */
public class QLySanpham extends javax.swing.JFrame {

    private final ISanphamService ProductService;
    private final ITheloaiService TheloaiService;
    private final ISizeService sizeService;
    private final IHangSXService hangService;
    private final IMauSacService msService;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    private QLyTheLoai TL;
    private QLyHangSX hSX;
    private QLySize size;
    private QLyMauSac mau;
      List<QLSanPham> Tim;
      File fTenAnh;
      String duongDanAnh = "D:\\DUAN1\\DUAN1_QLBANGIAY\\src\\main\\java\\image\\"+fTenAnh;
    
    /**
     * Creates new form QLySanpham
     */
    public QLySanpham() {
        initComponents();
        setLocationRelativeTo(null);
        ProductService = new SanphamService();
        TheloaiService = new TheLoaiService();
        sizeService = new SizeService();
        hangService = new HangSXService();
        msService = new MauSacService();
        loadDataToTable();
        loadDataToComBoSize();
        loadDataToComBoHang();
        loadDataToComBoMS();
        loadDataToComBoTL();
        //cboTimSize.setVisible(false);

        //loadComBoMS();
        //chkTimKiemItemStateChanged(evt);
    }

    private void loadDataToTable() {
        List<QLSanPham> ds = ProductService.getProducts();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (QLSanPham product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangSXId(),
                product.getTheLoaiID(),
                product.getMaSizeId(),
                product.getMauSacId(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.isTrangThai()//==?"Active":"Inactive"
            };
            
            dtm.addRow(rowData);
        }
        
    }
    private void loadDataToTableCBO(){
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (QLSanPham product : Tim) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangSXId(),
                product.getTheLoaiID(),
                product.getMaSizeId(),
                product.getMauSacId(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.isTrangThai()//==?"Active":"Inactive"
            };
            
            dtm.addRow(rowData);
        }
    }
    
    public void loadDataToComBoTL(){
        List<TheLoai> Theloai = TheloaiService.getAllTheLoai();
        cboTL.setModel(new DefaultComboBoxModel((Theloai.toArray())));
    }
    private void loadDataToComBoSize(){
        List<Size> size = sizeService.getAllTheLoai();
        cboSize.setModel(new DefaultComboBoxModel((size.toArray())));
    }
    private void loadDataToComBoHang(){
        List<HangSX> hang = hangService.getAllHangSX();
        cboHang.setModel(new DefaultComboBoxModel((hang.toArray())));
    }
    private void loadDataToComBoMS(){
        List<MauSac> mau = msService.getAllMauSac();
        cboMS.setModel(new DefaultComboBoxModel((mau.toArray())));
    }
    private void loadComBoMS(){
        List<MauSac> mau = msService.getAllMauSac();
        cboTimMS.setModel(new DefaultComboBoxModel((mau.toArray())));
    }
    
    
    
    
    public ImageIcon ResizeImage(String ImagePath){
        ImageIcon MyImage = new ImageIcon(ImagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(newImage);
        return img;
    }
    
    private QLSanPham getSanPhamFromInput() throws ParseException {
        QLSanPham qlProduct = new QLSanPham();

        String proMaSP = txtMaSP.getText();
        qlProduct.setMaSP(proMaSP);
        
        String proTenSP = txtTenSP.getText();
        qlProduct.setTenSP(proTenSP);
        
        String proNgayNhap = txtNgayNhap.getText();
        qlProduct.setNgayNhap(dateFormat.parse(proNgayNhap));
        
        float proDonGia = Float.parseFloat(txtDonGia.getText());
        qlProduct.setGia(proDonGia);
        
        int proSoLuong = Integer.parseInt(txtSoluong.getText());
        qlProduct.setSoLuong(proSoLuong);
        //trạng thái
        boolean trangthai = false;
        if(rdoINActive.isSelected()){
            trangthai = true;
        }
        qlProduct.setTrangThai(trangthai);
        //item cboTL
        TheLoai category = (TheLoai) this.cboTL.getSelectedItem();
        qlProduct.setTheLoaiID(category);
        //item cboHangSX
        HangSX hangSX = (HangSX) this.cboHang.getSelectedItem();
        qlProduct.setHangSXId(hangSX);
        //item cboSize
        Size size = (Size) this.cboSize.getSelectedItem();
        qlProduct.setMaSizeId(size);
        //item cboMauSac
        MauSac mauSac = (MauSac) this.cboMS.getSelectedItem();
        qlProduct.setMauSacId(mauSac);
        
        qlProduct.setAnh(duongDanAnh);
        return qlProduct;
    }
    
    private String getSanPhamIdFromSelectedRow() {
        int selectedRowIndex = tblSanPham.getSelectedRow();
        return String.valueOf(tblSanPham.getValueAt(selectedRowIndex, 0).toString());
    }
    
    private int findIndexComboboxById(int id) {
        int total = this.cboTL.getMaximumRowCount();
        for(int i = 0; i < total; i++) {
           TheLoai theLoai = this.cboTL.getItemAt(i);
            if (id == theLoai.getMaTL()) {
                return i;
            }
        }
        return -1;
    }
    private int findIndexComboHangById(int id) {
        int total = cboHang.getMaximumRowCount();
        for(int i = 0; i < total; i++) {
           HangSX hangSX = cboHang.getItemAt(i);
            if (id == hangSX.getMaHangSX()) {
                return i;
            }
        }
        return -1;
    }
    private int findIndexComboSizeById(int id) {
        int total = cboSize.getMaximumRowCount();
        for(int i = 0; i < total; i++) {
           Size size = cboSize.getItemAt(i);
            if (id == size.getMaSize()) {
                return i;
            }
        }
        return -1;
    }
    private int findIndexComboMSById(int id) {
        int total = cboMS.getMaximumRowCount();
        for(int i = 0; i < total; i++) {
           MauSac mauSac = cboMS.getItemAt(i);
            if (id == mauSac.getMaMS()) {
                return i;
            }
        }
        return -1;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        chkTimKiem = new javax.swing.JCheckBox();
        chkTimMa = new javax.swing.JCheckBox();
        txtTimMa = new javax.swing.JTextField();
        chkTimMS = new javax.swing.JCheckBox();
        chkTimTen = new javax.swing.JCheckBox();
        chkTimSize = new javax.swing.JCheckBox();
        cboTimSize = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        cboTL = new javax.swing.JComboBox<>();
        cboHang = new javax.swing.JComboBox<>();
        cboSize = new javax.swing.JComboBox<>();
        cboMS = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        lblAnh = new javax.swing.JLabel();
        btnChoose = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNgayNhap = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnInActive = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtTimTen = new javax.swing.JTextField();
        cboTimMS = new javax.swing.JComboBox<>();
        btlDM = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        btnMS = new javax.swing.JButton();
        btnSize = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        btnActive = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rdoINActive = new javax.swing.JRadioButton();
        rdoActive = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản Lý Sản Phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        chkTimKiem.setText("Tìm Kiếm");
        chkTimKiem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimKiemItemStateChanged(evt);
            }
        });

        chkTimMa.setText("Theo Mã");

        chkTimMS.setText("Theo Màu Sắc");
        chkTimMS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimMSItemStateChanged(evt);
            }
        });

        chkTimTen.setText("Theo Tên");

        chkTimSize.setText("Theo Size");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "HangSX", "Loại SP", "Size", "Màu Sắc", "TênSP", "Ngày Nhập", "Đơn Giá", "Số Lượng", "Ảnh", "trạng Thái"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel1.setText("Mã SP");

        jLabel2.setText("Tên SP");

        jLabel3.setText("Loại SP");

        jLabel4.setText("Hãng SX");

        jLabel5.setText("Màu Sắc");

        jLabel6.setText("Size");

        jLabel7.setText("Số Lượng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh"));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnChoose.setText("Choose file");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        jLabel8.setText("Đơn Giá");

        jLabel9.setText("Ngày Nhập");

        jButton2.setText("NEW");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnInActive.setText("InActive");
        btnInActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActiveActionPerformed(evt);
            }
        });

        jButton5.setText("Tìm Kiếm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        cboTimMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Màu Sắc" }));
        cboTimMS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTimMSItemStateChanged(evt);
            }
        });

        btlDM.setText("+");
        btlDM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btlDMActionPerformed(evt);
            }
        });

        btnHang.setText("+");
        btnHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHangActionPerformed(evt);
            }
        });

        btnMS.setText("+");
        btnMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMSActionPerformed(evt);
            }
        });

        btnSize.setText("+");
        btnSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSizeActionPerformed(evt);
            }
        });

        jButton10.setText("RESET");

        btnActive.setText("Active");
        btnActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActiveActionPerformed(evt);
            }
        });

        jLabel10.setText("Trạng Thái");

        buttonGroup1.add(rdoINActive);
        rdoINActive.setText("Inactive");

        buttonGroup1.add(rdoActive);
        rdoActive.setText("Active");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaSP)
                                    .addComponent(txtTenSP)
                                    .addComponent(cboTL, 0, 275, Short.MAX_VALUE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cboHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSize, 0, 271, Short.MAX_VALUE)
                                    .addComponent(txtSoluong)
                                    .addComponent(cboMS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btlDM)
                            .addComponent(btnHang)
                            .addComponent(btnMS)
                            .addComponent(btnSize))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton10)
                                            .addComponent(jButton2)))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(btnChoose)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnActive)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnInActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNgayNhap))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(31, 31, 31)
                                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoINActive)
                                    .addComponent(rdoActive))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkTimKiem)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(chkTimMa)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTimMa, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(chkTimTen)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(chkTimMS)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTimMS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkTimSize)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboTimSize, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 38, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkTimKiem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkTimMa)
                    .addComponent(txtTimMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTimMS)
                    .addComponent(chkTimTen)
                    .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTimSize)
                    .addComponent(cboTimSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btlDM)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHang))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMS))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSize))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21)
                                .addComponent(btnChoose))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton3)
                                    .addComponent(jButton2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5)
                                    .addComponent(jButton10))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnInActive)
                                    .addComponent(btnActive))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(rdoINActive))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(rdoActive)
                                .addContainerGap())))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        //showDetail(tblSanPham.getSelectedRow());
        int row = tblSanPham.getSelectedRow();
        String MaSP = tblSanPham.getValueAt(row, 0).toString();
        HangSX hangSX = (HangSX)tblSanPham.getValueAt(row, 1);
        TheLoai loaiSP = (TheLoai)tblSanPham.getValueAt(row, 2);
        Size size = (Size)tblSanPham.getValueAt(row, 3);
        MauSac maMS = (MauSac)tblSanPham.getValueAt(row, 4);
        String tenSP = tblSanPham.getValueAt(row, 5).toString();
        String ngayNhap = tblSanPham.getValueAt(row, 6).toString();
        String donGia = tblSanPham.getValueAt(row, 7).toString();
        String soLuong = tblSanPham.getValueAt(row, 8).toString();
        String anh = tblSanPham.getValueAt(row, 9).toString();
        String status = tblSanPham.getValueAt(row, 10).toString();
        
        txtMaSP.setText(MaSP);
        txtTenSP.setText(tenSP);
        txtSoluong.setText(soLuong);
        txtDonGia.setText(donGia);
        txtNgayNhap.setText(ngayNhap);
        //radio
        if(status.equalsIgnoreCase("Active")){
            rdoActive.setSelected(true);
            rdoINActive.setSelected(false);
        }else{
            rdoActive.setSelected(false);
            rdoINActive.setSelected(true);
        }
        //combobox
//        int i = 0;
//        while(true){
//            String nameTL = cboTL.getItemAt(i).toString();
//            if(nameTL.equalsIgnoreCase(loaiSP)){
//                cboTL.setSelectedIndex(i);
//                break;
//            }
//            i++;
//        }
            int tlIndex = findIndexComboboxById(loaiSP.getMaTL());
            cboTL.setSelectedIndex(tlIndex);
            
            int hangIndex = findIndexComboHangById(hangSX.getMaHangSX());
            cboHang.setSelectedIndex(hangIndex);
            
            int sizeIndex = findIndexComboSizeById(size.getMaSize());
            cboSize.setSelectedIndex(sizeIndex);
            
            int msIndex = findIndexComboMSById(maMS.getMaMS());
            cboMS.setSelectedIndex(msIndex);
        

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        // TODO add your handling code here:
        try {
          
        JFileChooser file = new JFileChooser("D//");
        file.setDialogTitle("Mở File");
        file.showOpenDialog(null);
         fTenAnh = file.getSelectedFile();
        duongDanAnh = fTenAnh.getAbsolutePath();
            if(duongDanAnh != null){
                lblAnh.setIcon(ResizeImage(String.valueOf(duongDanAnh)));
                System.out.println(duongDanAnh);
                //System.out.println(fTenAnh);
            }  
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Chưa chọn ảnh");
        }
        
    }//GEN-LAST:event_btnChooseActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         try {
            QLSanPham  newProduct=null;
             newProduct = getSanPhamFromInput();
        if (ProductService.createNewProduct(newProduct) != null) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        loadDataToTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
          
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        QLSanPham updatedProduct = null;
        try {
            updatedProduct = getSanPhamFromInput();
            String updatedProductId = getSanPhamIdFromSelectedRow();
            updatedProduct.setMaSP(String.valueOf(updatedProductId));
            if (ProductService.updateProductById(updatedProduct) != null) {
            JOptionPane.showMessageDialog(this, "Thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thất bại");
        }
        loadDataToTable();
        } catch (ParseException ex) {
            ex.printStackTrace();
            Logger.getLogger(QLySanpham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void chkTimKiemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimKiemItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
            chkTimMa.setVisible(true);
            txtTimMa.setVisible(true);
            chkTimTen.setVisible(true);
            txtTimTen.setVisible(true);
            chkTimMS.setVisible(true);
            cboTimMS.setVisible(true);
            chkTimSize.setVisible(true);
            cboTimSize.setVisible(true);
        }else{
            chkTimMa.setVisible(false);
            txtTimMa.setVisible(false);
            chkTimTen.setVisible(false);
            txtTimTen.setVisible(false);
            chkTimMS.setVisible(false);
            cboTimMS.setVisible(false);
            chkTimSize.setVisible(false);
            cboTimSize.setVisible(false);
        }
    }//GEN-LAST:event_chkTimKiemItemStateChanged

    private void chkTimMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimMSItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() == ItemEvent.SELECTED){
            //cboTimMS.setVisible(true);
            loadComBoMS();
        }
    }//GEN-LAST:event_chkTimMSItemStateChanged

    private void cboTimMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTimMSItemStateChanged
        // TODO add your handling code here:
        
        try {
            List<QLSanPham> list = ProductService.getProducts();
                for (QLSanPham x : list) {
                    if(x.getMauSacId().getMaMS()==cboTimMS.getSelectedIndex()){
                        Tim.add(x);
                        
                    }
                
            }
             loadDataToTableCBO();
            
//            int MaMS = cboTimMS.getSelectedIndex();
//            //String MaMS = (String) cboTimMS.getSelectedItem().toString();
//            if(ProductService.getProductByMaMS(MaMS) != null){
//                System.out.println("mã"+ MaMS);
//            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTimMSItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String MaSP = txtTimMa.getText();
        if(MaSP.length()==0){
            JOptionPane.showMessageDialog(this, "Bạn Chưa nhập Mã SP");
            loadDataToTable();
        }else{
        List<QLSanPham> ds = ProductService.getProductById(MaSP);
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (QLSanPham product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangSXId(),
                product.getTheLoaiID(),
                product.getMaSizeId(),
                product.getMauSacId(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.isTrangThai()//==?"Active":"Inactive"
            };
            
            dtm.addRow(rowData);
        }
    }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btlDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlDMActionPerformed
        // TODO add your handling code here:
        TL = new QLyTheLoai();
        TL.setVisible(true);
        //TL.setDefaultCloseOperation(TL.EXIT_ON_CLOSE));
    }//GEN-LAST:event_btlDMActionPerformed

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        // TODO add your handling code here:
        hSX=new QLyHangSX();
        hSX.setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

    private void btnActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveActionPerformed
        // TODO add your handling code here:
        List<QLSanPham> ds = ProductService.getProductsActive();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (QLSanPham product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangSXId(),
                product.getTheLoaiID(),
                product.getMaSizeId(),
                product.getMauSacId(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.isTrangThai()//==?"Active":"Inactive"
            };
            
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_btnActiveActionPerformed

    private void btnInActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActiveActionPerformed
        // TODO add your handling code here:
        List<QLSanPham> ds = ProductService.getProductsinActive();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (QLSanPham product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangSXId(),
                product.getTheLoaiID(),
                product.getMaSizeId(),
                product.getMauSacId(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.isTrangThai()//==?"Active":"Inactive"
            };
            
            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_btnInActiveActionPerformed

    private void btnMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMSActionPerformed
        // TODO add your handling code here:
        mau = new QLyMauSac();
        mau.setVisible(true);
    }//GEN-LAST:event_btnMSActionPerformed

    private void btnSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSizeActionPerformed
        // TODO add your handling code here:
        size = new QLySize();
        size.setVisible(true);
    }//GEN-LAST:event_btnSizeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QLySanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLySanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLySanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLySanpham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLySanpham().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btlDM;
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnInActive;
    private javax.swing.JButton btnMS;
    private javax.swing.JButton btnSize;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<HangSX> cboHang;
    private javax.swing.JComboBox<MauSac> cboMS;
    private javax.swing.JComboBox<Size> cboSize;
    private javax.swing.JComboBox<TheLoai> cboTL;
    private javax.swing.JComboBox<String> cboTimMS;
    private javax.swing.JComboBox<String> cboTimSize;
    private javax.swing.JCheckBox chkTimKiem;
    private javax.swing.JCheckBox chkTimMS;
    private javax.swing.JCheckBox chkTimMa;
    private javax.swing.JCheckBox chkTimSize;
    private javax.swing.JCheckBox chkTimTen;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JRadioButton rdoActive;
    private javax.swing.JRadioButton rdoINActive;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimMa;
    private javax.swing.JTextField txtTimTen;
    // End of variables declaration//GEN-END:variables
}

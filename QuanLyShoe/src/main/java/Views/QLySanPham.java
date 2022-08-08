/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModels.HangEntity;
import DomainModels.MauSacEntity;
import DomainModels.SanPhamEntity;
import DomainModels.SizeEntity;
import DomainModels.TheLoaiEntity;
import Services.HangService;
import Services.IHangSXService;
import Services.IMauSacService;
import Services.ISanphamService;
import Services.ISizeService;
import Services.ITheloaiService;
import Services.MauSacService;
import Services.ProductsService;
import Services.SizeService;
import Services.TheLoaiService;
import ViewModels.SanPhamModel;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.QLyHangSX;
import views.QLyMauSac;
import views.QLySize;
import views.QLyTheLoai;

/**
 *
 * @author Admin
 */
public class QLySanPham extends javax.swing.JPanel {

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
    List<SanPhamModel> Tim;
    String fileName = "";

    /**
     * Creates new form QLySanPham
     */
    public QLySanPham() {
        initComponents();
        ProductService = new ProductsService();
        TheloaiService = new TheLoaiService();
        sizeService = new SizeService();
        hangService = new HangService();
        msService = new MauSacService();
        loadDataToTable();
        loadDataToComBoSize();
        loadDataToComBoHang();
        loadDataToComBoMS();
        loadDataToComBoTL();
        txtTimMa.setEnabled(false);
        txtTimTen.setEnabled(false);
        cboTimMS.setEnabled(false);
        cboTimSize.setEnabled(false);
        btnKhoiPhuc.setEnabled(false);
    }

    //loadTable
    private void loadDataToTable() {
        List<SanPhamModel> ds = ProductService.getProductsActive();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPhamModel product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangEntity(),
                product.getTheLoaiEntity(),
                product.getSizeEntity(),
                product.getMauSacEntity(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.getTrangThai() == 0 ? "Active" : "Inactive"
            };

            dtm.addRow(rowData);
        }

    }

    private void loadDataToTableCBO() {
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPhamModel product : Tim) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangEntity(),
                product.getTheLoaiEntity(),
                product.getSizeEntity(),
                product.getMauSacEntity(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.getTrangThai() == 0 ? "Active" : "Inactive"
            };

            dtm.addRow(rowData);
        }
    }
    ////

    public void loadDataToComBoTL() {
        List<TheLoaiEntity> Theloai = TheloaiService.getAllTheLoai();
        cboTL.setModel(new DefaultComboBoxModel((Theloai.toArray())));
    }

    private void loadDataToComBoSize() {
        List<SizeEntity> size = sizeService.getAllSIZE();
        cboSize.setModel(new DefaultComboBoxModel((size.toArray())));
    }

    private void loadDataToComBoHang() {
        List<HangEntity> hang = hangService.getAllHangSX();
        cboHang.setModel(new DefaultComboBoxModel((hang.toArray())));
    }

    private void loadDataToComBoMS() {
        List<MauSacEntity> mau = msService.getAllMauSac();
        cboMS.setModel(new DefaultComboBoxModel((mau.toArray())));
    }

    private void loadComBoMS() {
        List<MauSacEntity> mau = msService.getAllMauSac();
        cboTimMS.setModel(new DefaultComboBoxModel((mau.toArray())));
    }

    private void loadComBoSize() {
        List<SizeEntity> size = sizeService.getAllSIZE();
        cboTimSize.setModel(new DefaultComboBoxModel((size.toArray())));
    }

    /////////////
    private SanPhamModel getSanPhamFromInput() throws ParseException {
        SanPhamModel qlProduct = new SanPhamModel();

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
        String trangthai = "";
        if (rdoINActive.isSelected()) {
            trangthai = "1";
        } else {
            trangthai = "0";
        }
        qlProduct.setTrangThai(Integer.parseInt(trangthai));
        //item cboTL
        TheLoaiEntity category = (TheLoaiEntity) this.cboTL.getSelectedItem();
        qlProduct.setTheLoaiEntity(category);
        //item cboHangSX
        HangEntity hangSX = (HangEntity) this.cboHang.getSelectedItem();
        qlProduct.setHangEntity(hangSX);
        //item cboSize
        SizeEntity size = (SizeEntity) this.cboSize.getSelectedItem();
        qlProduct.setSizeEntity(size);
        //item cboMauSac
        MauSacEntity mauSac = (MauSacEntity) this.cboMS.getSelectedItem();
        qlProduct.setMauSacEntity(mauSac);

        qlProduct.setAnh(fileName);
        return qlProduct;
    }

    private SanPhamModel getSanPhamFromInputActive() throws ParseException {
        SanPhamModel qlProduct = new SanPhamModel();
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
        int trangthai = 1;
//        if (rdoINActive.isSelected()) {
//            trangthai = "1";
//        } else {
//            trangthai = "0";
//        }
        qlProduct.setTrangThai(trangthai);
        //item cboTL
        TheLoaiEntity category = (TheLoaiEntity) this.cboTL.getSelectedItem();
        qlProduct.setTheLoaiEntity(category);
        //item cboHangSX
        HangEntity hangSX = (HangEntity) this.cboHang.getSelectedItem();
        qlProduct.setHangEntity(hangSX);
        //item cboSize
        SizeEntity size = (SizeEntity) this.cboSize.getSelectedItem();
        qlProduct.setSizeEntity(size);
        //item cboMauSac
        MauSacEntity mauSac = (MauSacEntity) this.cboMS.getSelectedItem();
        qlProduct.setMauSacEntity(mauSac);

        qlProduct.setAnh(fileName);
        return qlProduct;
    }

    private SanPhamModel getSanPhamFromInputInActive() throws ParseException {
        SanPhamModel qlProduct = new SanPhamModel();
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
        int trangthai = 0;
//        if (rdoINActive.isSelected()) {
//            trangthai = "1";
//        } else {
//            trangthai = "0";
//        }
        qlProduct.setTrangThai(trangthai);
        //item cboTL
        TheLoaiEntity category = (TheLoaiEntity) this.cboTL.getSelectedItem();
        qlProduct.setTheLoaiEntity(category);
        //item cboHangSX
        HangEntity hangSX = (HangEntity) this.cboHang.getSelectedItem();
        qlProduct.setHangEntity(hangSX);
        //item cboSize
        SizeEntity size = (SizeEntity) this.cboSize.getSelectedItem();
        qlProduct.setSizeEntity(size);
        //item cboMauSac
        MauSacEntity mauSac = (MauSacEntity) this.cboMS.getSelectedItem();
        qlProduct.setMauSacEntity(mauSac);

        qlProduct.setAnh(fileName);
        return qlProduct;
    }

    /////
    private String getSanPhamIdFromSelectedRow() {
        int selectedRowIndex = tblSanPham.getSelectedRow();
        return String.valueOf(tblSanPham.getValueAt(selectedRowIndex, 0).toString());
    }

    private String getSanPhamIdFromSelectedRowInActive() {
        int selectedRowIndex = tblSanPhamInactive.getSelectedRow();
        return String.valueOf(tblSanPhamInactive.getValueAt(selectedRowIndex, 0).toString());
    }

    private int findIndexComboboxById(int id) {
        int total = this.cboTL.getMaximumRowCount();
        for (int i = 0; i < total; i++) {
            TheLoaiEntity theLoai = this.cboTL.getItemAt(i);
            if (id == theLoai.getMaTL()) {
                return i;
            }
        }
        return -1;
    }

    private int findIndexComboHangById(int id) {
        int total = cboHang.getMaximumRowCount();
        for (int i = 0; i < total; i++) {
            HangEntity hangSX = cboHang.getItemAt(i);
            if (id == hangSX.getMaHangSX()) {
                return i;
            }
        }
        return -1;
    }

    private int findIndexComboSizeById(int id) {
        int total = cboSize.getMaximumRowCount();
        for (int i = 0; i < total; i++) {
            SizeEntity size = cboSize.getItemAt(i);
            if (id == size.getMaSize()) {
                return i;
            }
        }
        return -1;
    }

    private int findIndexComboMSById(int id) {
        int total = cboMS.getMaximumRowCount();
        for (int i = 0; i < total; i++) {
            MauSacEntity mauSac = cboMS.getItemAt(i);
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
        chkTimKiem2 = new javax.swing.JCheckBox();
        chkTimMa = new javax.swing.JCheckBox();
        txtTimMa = new javax.swing.JTextField();
        chkTimMS = new javax.swing.JCheckBox();
        chkTimTen = new javax.swing.JCheckBox();
        chkTimSize = new javax.swing.JCheckBox();
        cboTimSize = new javax.swing.JComboBox<>();
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
        btnNew = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnInActive = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        txtTimTen = new javax.swing.JTextField();
        cboTimMS = new javax.swing.JComboBox<>();
        btlDM = new javax.swing.JButton();
        btnHang = new javax.swing.JButton();
        btnMS = new javax.swing.JButton();
        btnSize = new javax.swing.JButton();
        btnResrt = new javax.swing.JButton();
        btnActive = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        rdoINActive = new javax.swing.JRadioButton();
        rdoActive = new javax.swing.JRadioButton();
        TabbedPane1 = new javax.swing.JTabbedPane();
        jPanelSanPham = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanelInactive = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamInactive = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();
        btnVoHieu = new javax.swing.JButton();
        btnKiemTra = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        chkTimKiem2.setText("Tìm Kiếm");
        chkTimKiem2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimKiem2ItemStateChanged(evt);
            }
        });

        chkTimMa.setText("Theo Mã");
        chkTimMa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimMaItemStateChanged(evt);
            }
        });

        chkTimMS.setText("Theo Màu Sắc");
        chkTimMS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimMSItemStateChanged(evt);
            }
        });

        chkTimTen.setText("Theo Tên");
        chkTimTen.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimTenItemStateChanged(evt);
            }
        });

        chkTimSize.setText("Theo Size");
        chkTimSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkTimSizeItemStateChanged(evt);
            }
        });

        cboTimSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTimSizeItemStateChanged(evt);
            }
        });

        jLabel1.setText("Mã SP");

        jLabel2.setText("Tên SP");

        jLabel3.setText("Loại SP");

        jLabel4.setText("Hãng SX");

        jLabel5.setText("Màu Sắc");

        jLabel6.setText("Size");

        txtMaSP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaSPFocusLost(evt);
            }
        });

        jLabel7.setText("Số Lượng");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ảnh"));

        lblAnh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        btnChoose.setText("Choose file");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        jLabel8.setText("Đơn Giá");

        jLabel9.setText("Ngày Nhập");

        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnInActive.setText("InActive");
        btnInActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInActiveActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm Kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
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

        btnResrt.setText("RESET");
        btnResrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResrtActionPerformed(evt);
            }
        });

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
        rdoActive.setSelected(true);
        rdoActive.setText("Active");

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

        javax.swing.GroupLayout jPanelSanPhamLayout = new javax.swing.GroupLayout(jPanelSanPham);
        jPanelSanPham.setLayout(jPanelSanPhamLayout);
        jPanelSanPhamLayout.setHorizontalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 899, Short.MAX_VALUE)
            .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelSanPhamLayout.setVerticalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 255, Short.MAX_VALUE)
            .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        TabbedPane1.addTab("Sản Phẩm", jPanelSanPham);

        tblSanPhamInactive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "HangSX", "Loại SP", "Size", "Màu Sắc", "TênSP", "Ngày Nhập", "Đơn Giá", "Số Lượng", "Ảnh", "trạng Thái"
            }
        ));
        tblSanPhamInactive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamInactiveMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamInactive);

        javax.swing.GroupLayout jPanelInactiveLayout = new javax.swing.GroupLayout(jPanelInactive);
        jPanelInactive.setLayout(jPanelInactiveLayout);
        jPanelInactiveLayout.setHorizontalGroup(
            jPanelInactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 899, Short.MAX_VALUE)
            .addGroup(jPanelInactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelInactiveLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 887, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanelInactiveLayout.setVerticalGroup(
            jPanelInactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
            .addGroup(jPanelInactiveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelInactiveLayout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        TabbedPane1.addTab("InActive", jPanelInactive);

        btnKhoiPhuc.setText("Khôi phục");
        btnKhoiPhuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhoiPhucActionPerformed(evt);
            }
        });

        btnVoHieu.setText("Vo Hieu");
        btnVoHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoHieuActionPerformed(evt);
            }
        });

        btnKiemTra.setText("Kiểm Tra");
        btnKiemTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKiemTraActionPerformed(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("(*)");

        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("(*)");

        jLabel13.setForeground(new java.awt.Color(252, 0, 0));
        jLabel13.setText("(*)");

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("(*)");

        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("(*)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSize, 0, 286, Short.MAX_VALUE)
                                    .addComponent(cboMS, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoluong)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel11))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel12)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cboTL, javax.swing.GroupLayout.Alignment.TRAILING, 0, 286, Short.MAX_VALUE)
                                    .addComponent(cboHang, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMaSP))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btlDM)
                            .addComponent(btnHang)
                            .addComponent(btnMS)
                            .addComponent(btnSize))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel14))
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoINActive)
                                    .addComponent(rdoActive))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(btnChoose)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnKiemTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnNew, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnResrt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnActive, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnKhoiPhuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnInActive, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnVoHieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TabbedPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkTimKiem2)
                                    .addGroup(layout.createSequentialGroup()
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
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkTimKiem2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkTimMa)
                    .addComponent(txtTimMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTimMS)
                    .addComponent(chkTimTen)
                    .addComponent(txtTimTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTimMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkTimSize)
                    .addComponent(cboTimSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChoose))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btlDM)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(cboHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHang)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnUpdate)
                                    .addComponent(btnNew))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnTimKiem)
                                    .addComponent(btnResrt))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnInActive)
                                    .addComponent(btnActive))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnKhoiPhuc)
                                    .addComponent(btnVoHieu))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cboMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMS)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnKiemTra)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSize)
                            .addComponent(jLabel8)
                            .addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(rdoINActive)
                            .addComponent(jLabel14))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtNgayNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoActive)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkTimKiem2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimKiem2ItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            chkTimMa.setVisible(true);
            txtTimMa.setVisible(true);
            chkTimTen.setVisible(true);
            txtTimTen.setVisible(true);
            chkTimMS.setVisible(true);
            cboTimMS.setVisible(true);
            chkTimSize.setVisible(true);
            cboTimSize.setVisible(true);
        } else {
            chkTimMa.setVisible(false);
            txtTimMa.setVisible(false);
            chkTimTen.setVisible(false);
            txtTimTen.setVisible(false);
            chkTimMS.setVisible(false);
            cboTimMS.setVisible(false);
            chkTimSize.setVisible(false);
            cboTimSize.setVisible(false);
        }
    }//GEN-LAST:event_chkTimKiem2ItemStateChanged

    private void chkTimMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimMSItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //chkTimTen.setVisible(false);
            txtTimTen.setEnabled(false);
            //txtTimMa.setEnabled(true);

            //chkTimMa.setVisible(false);
            txtTimMa.setEnabled(false);

            //chkTimMS.setVisible(true);
            cboTimMS.setEnabled(true);

            //chkTimSize.setVisible(false);
            cboTimSize.setEnabled(false);
            loadComBoMS();
        } else {
            //chkTimMa.setVisible(true);
            txtTimMa.setEnabled(false);

            //chkTimTen.setVisible(true);
            txtTimTen.setEnabled(false);

            //chkTimSize.setVisible(true);
            cboTimSize.setEnabled(false);

            cboTimMS.setEnabled(false);
        }
    }//GEN-LAST:event_chkTimMSItemStateChanged

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser fchoose = new JFileChooser();
            int result = fchoose.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fchoose.getSelectedFile();
                String fullPath = file.getAbsolutePath();
                fileName = fchoose.getSelectedFile().getName();
                Uphinh(fullPath);
                //duong dan
                Path src = Paths.get(fullPath);
                Path desl = Paths.get("D:\\DUAN1\\DUAN1_QLBANGIAY\\src\\main\\java\\image\\" + fileName);
                Files.copy(src, desl, StandardCopyOption.REPLACE_EXISTING);
                Uphinh(fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        int choin = JOptionPane.showConfirmDialog(this, "Bạn có muốn Thêm Sản Phẩm không ?");
        if (choin == 1) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy");
        } else if (choin == 0) {
            // addd vao day
            checkEmpty();
            try {
                //checkDate();
                SanPhamModel newProduct = null;
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
            //JOptionPane.showMessageDialog(this, "Thêm Khóa Học thành công");
        }


    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int choin = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật sản phẩm không ?");
        if (choin == 1) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy");
        } else if (choin == 0) {
            // addd vao day
            SanPhamModel updatedProduct = null;
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
            } catch (Exception e) {
                e.printStackTrace();
            }
            //JOptionPane.showMessageDialog(this, "Thêm Khóa Học thành công");
        }


    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnInActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInActiveActionPerformed
        // TODO add your handling code here:
        List<SanPhamModel> ds = ProductService.getProductsinActive();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPhamInactive.getModel();
        dtm.setRowCount(0);
        for (SanPhamModel product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangEntity(),
                product.getTheLoaiEntity(),
                product.getSizeEntity(),
                product.getMauSacEntity(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.getTrangThai() == 1 ? "InActive" : "Active"
            };

            dtm.addRow(rowData);
        }
        btnKhoiPhuc.setEnabled(true);
        btnVoHieu.setEnabled(false);
        jPanelSanPham.setVisible(false);
        //jPanelSanPham.setEnabled(false);
        jPanelInactive.setVisible(true);
        //TabbedPane1.setVisible(false);
    }//GEN-LAST:event_btnInActiveActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String MaSP = txtTimMa.getText();
        if (MaSP.length() == 0) {
            JOptionPane.showMessageDialog(this, "Bạn Chưa nhập Mã SP");
            loadDataToTable();
        } else {
            List<SanPhamModel> ds = ProductService.getProductById(MaSP);
            DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
            dtm.setRowCount(0);
            for (SanPhamModel product : ds) {
                Object[] rowData = {
                    product.getMaSP(),
                    product.getHangEntity(),
                    product.getTheLoaiEntity(),
                    product.getSizeEntity(),
                    product.getMauSacEntity(),
                    product.getTenSP(),
                    dateFormat.format(product.getNgayNhap()),
                    product.getGia(),
                    product.getSoLuong(),
                    product.getAnh(),
                    product.getTrangThai() == 0 ? "Active" : "Inactive"
                };

                dtm.addRow(rowData);
            }
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void cboTimMSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTimMSItemStateChanged
        // TODO add your handling code here:

        try {
            Tim = new ArrayList<>();
            List<SanPhamModel> list = ProductService.getProductsActive();
            for (SanPhamModel x : list) {
                if (x.getMauSacEntity().getMaMS() == cboTimMS.getSelectedIndex()+1) {
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

    private void btlDMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btlDMActionPerformed
        // TODO add your handling code here:
        TL = new QLyTheLoai();
        TL.setVisible(true);
        //TL.setDefaultCloseOperation(TL.EXIT_ON_CLOSE));
    }//GEN-LAST:event_btlDMActionPerformed

    private void btnHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHangActionPerformed
        // TODO add your handling code here:
        hSX = new QLyHangSX();
        hSX.setVisible(true);
    }//GEN-LAST:event_btnHangActionPerformed

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

    private void btnActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActiveActionPerformed
        // TODO add your handling code here:
        List<SanPhamModel> ds = ProductService.getProductsActive();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPhamModel product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangEntity(),
                product.getTheLoaiEntity(),
                product.getSizeEntity(),
                product.getMauSacEntity(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.getTrangThai() == 0 ? "Active" : "Inactive"
            };

            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_btnActiveActionPerformed

    private void Uphinh(String anh) {
        ImageIcon image = new ImageIcon("D:\\DUAN1\\DUAN1_QLBANGIAY\\src\\main\\java\\image\\" + anh);
        Image im = image.getImage();
        ImageIcon icon = new ImageIcon(im.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), im.SCALE_SMOOTH));
        lblAnh.setIcon(icon);
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        //showDetail(tblSanPham.getSelectedRow());
        int row = tblSanPham.getSelectedRow();
        String MaSP = tblSanPham.getValueAt(row, 0).toString();
        HangEntity hangSX = (HangEntity) tblSanPham.getValueAt(row, 1);
        TheLoaiEntity loaiSP = (TheLoaiEntity) tblSanPham.getValueAt(row, 2);
        SizeEntity size = (SizeEntity) tblSanPham.getValueAt(row, 3);
        MauSacEntity maMS = (MauSacEntity) tblSanPham.getValueAt(row, 4);
        String tenSP = tblSanPham.getValueAt(row, 5).toString();
        String ngayNhap = tblSanPham.getValueAt(row, 6).toString();
        String donGia = tblSanPham.getValueAt(row, 7).toString();
        String soLuong = tblSanPham.getValueAt(row, 8).toString();
        //String anh = tblSanPham.getValueAt(row, 9).toString();
        Uphinh(tblSanPham.getValueAt(row, 9).toString());
        //Boolean status = Boolean.parseBoolean(tblSanPham.getValueAt(row, 10).toString());
        //int status = Integer.parseInt(tblSanPham.getValueAt(row, 10).toString());
        String status = String.valueOf(tblSanPham.getValueAt(row, 10).toString());
        if (status.equalsIgnoreCase("Active")) {
            rdoActive.setSelected(true);
        } else {
            rdoINActive.setSelected(true);
        }
        txtMaSP.setText(MaSP);
        txtTenSP.setText(tenSP);
        txtSoluong.setText(soLuong);
        txtDonGia.setText(donGia);
        txtNgayNhap.setText(ngayNhap);
        //radio
        //        if(status == 0){
        //            rdoActive.setSelected(true);
        //        }else{
        //            rdoINActive.setSelected(true);
        //        }
        int tlIndex = findIndexComboboxById(loaiSP.getMaTL());
        cboTL.setSelectedIndex(tlIndex);

        int hangIndex = findIndexComboHangById(hangSX.getMaHangSX());
        cboHang.setSelectedIndex(hangIndex);

        int sizeIndex = findIndexComboSizeById(size.getMaSize());
        cboSize.setSelectedIndex(sizeIndex);

        int msIndex = findIndexComboMSById(maMS.getMaMS());
        cboMS.setSelectedIndex(msIndex);

        txtMaSP.setEnabled(false);
        txtTenSP.setEnabled(false);
        txtNgayNhap.setEnabled(false);
        rdoActive.setEnabled(false);
        rdoINActive.setEnabled(false);

        txtDonGia.setEnabled(true);
        cboMS.setEnabled(true);
        cboSize.setEnabled(true);
        txtSoluong.setEnabled(true);
        
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamInactiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamInactiveMouseClicked
        // TODO add your handling code here:
        int row = tblSanPhamInactive.getSelectedRow();
        String MaSP = tblSanPhamInactive.getValueAt(row, 0).toString();
        HangEntity hangSX = (HangEntity) tblSanPhamInactive.getValueAt(row, 1);
        TheLoaiEntity loaiSP = (TheLoaiEntity) tblSanPhamInactive.getValueAt(row, 2);
        SizeEntity size = (SizeEntity) tblSanPhamInactive.getValueAt(row, 3);
        MauSacEntity maMS = (MauSacEntity) tblSanPhamInactive.getValueAt(row, 4);
        String tenSP = tblSanPhamInactive.getValueAt(row, 5).toString();
        String ngayNhap = tblSanPhamInactive.getValueAt(row, 6).toString();
        String donGia = tblSanPhamInactive.getValueAt(row, 7).toString();
        String soLuong = tblSanPhamInactive.getValueAt(row, 8).toString();
        //String anh = tblSanPham.getValueAt(row, 9).toString();
        Uphinh(tblSanPhamInactive.getValueAt(row, 9).toString());
        //Boolean status = Boolean.parseBoolean(tblSanPham.getValueAt(row, 10).toString());
        //int status = Integer.parseInt(tblSanPham.getValueAt(row, 10).toString());
        String status = String.valueOf(tblSanPhamInactive.getValueAt(row, 10).toString());
        if (status.equalsIgnoreCase("Active")) {
            rdoActive.setSelected(true);
        } else {
            rdoINActive.setSelected(true);
        }
        txtMaSP.setText(MaSP);
        txtTenSP.setText(tenSP);
        txtSoluong.setText(soLuong);
        txtDonGia.setText(donGia);
        txtNgayNhap.setText(ngayNhap);
        //radio
        //        if(status == 0){
        //            rdoActive.setSelected(true);
        //        }else{
        //            rdoINActive.setSelected(true);
        //        }
        int tlIndex = findIndexComboboxById(loaiSP.getMaTL());
        cboTL.setSelectedIndex(tlIndex);

        int hangIndex = findIndexComboHangById(hangSX.getMaHangSX());
        cboHang.setSelectedIndex(hangIndex);

        int sizeIndex = findIndexComboSizeById(size.getMaSize());
        cboSize.setSelectedIndex(sizeIndex);

        int msIndex = findIndexComboMSById(maMS.getMaMS());
        cboMS.setSelectedIndex(msIndex);
        //vo hieu hoa text
        txtMaSP.setEnabled(false);
        txtTenSP.setEnabled(false);
        txtDonGia.setEnabled(false);
        txtNgayNhap.setEnabled(false);
        txtSoluong.setEnabled(false);
        //vohieuhoa cbo
        cboHang.setEnabled(false);
        cboTL.setEnabled(false);
        cboMS.setEnabled(false);
        cboSize.setEnabled(false);
        //radio
        rdoActive.setEnabled(false);
        rdoINActive.setEnabled(false);
        //
    }//GEN-LAST:event_tblSanPhamInactiveMouseClicked

    private void btnKhoiPhucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhoiPhucActionPerformed
        // TODO add your handling code here:
        int choin = JOptionPane.showConfirmDialog(this, "Bạn có muốn khôi phục sản phẩm không ?");
        if (choin == 1) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy");
        } else if (choin == 0) {
           // addd vao day
           SanPhamModel updatedProduct = null;
        try {
            updatedProduct = getSanPhamFromInputInActive();
            String updatedProductId = getSanPhamIdFromSelectedRowInActive();
            updatedProduct.setMaSP(String.valueOf(updatedProductId));
            if (ProductService.updateProductById(updatedProduct) != null) {
                JOptionPane.showMessageDialog(this, "Thành công");

            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
            loadDataToTable();
            loadDataToTableInActive();
        } catch (Exception e) {
            e.printStackTrace();
        }
            //JOptionPane.showMessageDialog(this, "Thêm Khóa Học thành công");
        }
        
    }//GEN-LAST:event_btnKhoiPhucActionPerformed

    private void btnResrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResrtActionPerformed
        // TODO add your handling code here:
        txtDonGia.setText("");
        txtMaSP.setText("");
        txtNgayNhap.setText("");
        txtSoluong.setText("");
        txtTenSP.setText("");
        cboHang.setSelectedIndex(0);
        cboMS.setSelectedIndex(0);
        cboSize.setSelectedIndex(0);
        cboTL.setSelectedIndex(0);
        rdoActive.setSelected(true);
        //vo hieu hoa text
        txtMaSP.setEnabled(true);
        txtTenSP.setEnabled(true);
        txtDonGia.setEnabled(true);
        txtNgayNhap.setEnabled(true);
        txtSoluong.setEnabled(true);
        //vohieuhoa cbo
        cboHang.setEnabled(true);
        cboTL.setEnabled(true);
        cboMS.setEnabled(true);
        cboSize.setEnabled(true);
        //radio
        rdoActive.setEnabled(true);
        rdoINActive.setEnabled(true);
        btnVoHieu.setEnabled(true);
        btnKhoiPhuc.setEnabled(false);
        loadDataToTable();
    }//GEN-LAST:event_btnResrtActionPerformed

    private void chkTimSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimSizeItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //chkTimTen.setVisible(false);
            txtTimTen.setEnabled(false);
            //txtTimMa.setEnabled(true);

            //chkTimMa.setVisible(false);
            txtTimMa.setEnabled(false);

            //chkTimMS.setVisible(false);
            cboTimMS.setEnabled(false);

            //chkTimSize.setVisible(true);
            cboTimSize.setEnabled(true);
            loadComBoSize();
        } else {
            //chkTimMa.setEnabled(true);
            txtTimMa.setEnabled(false);

            //chkTimTen.setVisible(true);
            txtTimTen.setEnabled(false);

            //chkTimMS.setVisible(true);
            cboTimMS.setEnabled(false);

            cboTimSize.setEnabled(false);
        }
    }//GEN-LAST:event_chkTimSizeItemStateChanged

    private void cboTimSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTimSizeItemStateChanged
        // TODO add your handling code here:
        try {
            Tim = new ArrayList<>();
            List<SanPhamModel> list = ProductService.getProductsActive();
            for (SanPhamModel x : list) {
                if (x.getSizeEntity().getMaSize() == (cboTimSize.getSelectedIndex() + 1)) {
                    Tim.add(x);

                }

            }
            loadDataToTableCBO();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_cboTimSizeItemStateChanged

    private void chkTimMaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimMaItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            txtTimMa.setEnabled(true);
            txtTimMa.setEnabled(true);

            //chkTimTen.setVisible(false);
            txtTimTen.setEnabled(false);

            //chkTimMS.setVisible(false);
            cboTimMS.setEnabled(false);

            //chkTimSize.setVisible(false);
            cboTimSize.setEnabled(false);
        } 
        else {
            txtTimMa.setEnabled(false);

            //chkTimTen.setVisible(true);
            txtTimTen.setEnabled(false);

            //chkTimMS.setVisible(true);
            cboTimMS.setEnabled(false);

            //chkTimSize.setVisible(true);
            cboTimSize.setEnabled(false);
        }
    }//GEN-LAST:event_chkTimMaItemStateChanged
    private void loadDataToTableInActive() {
        List<SanPhamModel> ds = ProductService.getProductsinActive();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPhamInactive.getModel();
        dtm.setRowCount(0);
        for (SanPhamModel product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangEntity(),
                product.getTheLoaiEntity(),
                product.getSizeEntity(),
                product.getMauSacEntity(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.getTrangThai() == 1 ? "InActive" : "Active"
            };

            dtm.addRow(rowData);
        }
    }

    private void btnVoHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoHieuActionPerformed
        // TODO add your handling code here:SanPhamModel updatedProduct = null;
        int choin = JOptionPane.showConfirmDialog(this, "Bạn có muốn vô hiệu hoá sản phẩm không ?");
        if (choin == 1) {
            JOptionPane.showMessageDialog(this, "Bạn đã hủy");
        } else if (choin == 0) {
           // addd vao day
           SanPhamModel updatedProduct = null;
        try {
            updatedProduct = getSanPhamFromInputActive();
            String updatedProductId = getSanPhamIdFromSelectedRow();
            updatedProduct.setMaSP(String.valueOf(updatedProductId));
            if (ProductService.updateProductById(updatedProduct) != null) {
                JOptionPane.showMessageDialog(this, "Thành công");

            } else {
                JOptionPane.showMessageDialog(this, "Thất bại");
            }
            loadDataToTable();
            loadDataToTableInActive();
        } catch (Exception e) {
            e.printStackTrace();
        }
            JOptionPane.showMessageDialog(this, "Thêm Khóa Học thành công");
        }
        
    }//GEN-LAST:event_btnVoHieuActionPerformed

    private void chkTimTenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkTimTenItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            txtTimTen.setEnabled(true);
            //txtTimMa.setEnabled(true);

            //chkTimMa.setVisible(true);
            txtTimMa.setEnabled(false);

            //chkTimMS.setVisible(false);
            cboTimMS.setEnabled(false);

            //chkTimSize.setVisible(false);
            cboTimSize.setEnabled(false);
        } else {
            txtTimTen.setEnabled(false);

            //chkTimMa.setVisible(true);
            txtTimMa.setEnabled(false);

            //chkTimMS.setVisible(true);
            cboTimMS.setEnabled(false);

            //chkTimSize.setVisible(true);
            cboTimSize.setEnabled(false);
        }
    }//GEN-LAST:event_chkTimTenItemStateChanged
    private void checkDate() {
        SimpleDateFormat dateFor = new SimpleDateFormat();
        dateFor.applyPattern("dd/MM/YYYY");
        try {
            Date date = dateFor.parse(txtNgayNhap.getText().toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sai định dạng ngày.(Ví Dụ: 01/01/2022)");
            return;
        }
    }
    
//    private boolean checkSo() {
//            Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
//            Matcher matcher = pattern.matcher(txtSoluong.getText());
//            //JOptionPane.showMessageDialog(this, "Sai định dạng số lượng.(Là các số 0-9)");
//            txtSoluong.setBackground(Color.red);
//            return matcher.matches();
//    }
    boolean checkSo() {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(txtSoluong.getText().replace(".", ","));
        return matcher.matches();
    }
    private void checkMa(){
        String MaSP = txtMaSP.getText();
        Tim = new ArrayList<>();
            List<SanPhamModel> list = ProductService.getProductsActive();
            for (SanPhamModel x : list) {
                if(x.getMaSP().equalsIgnoreCase(MaSP)){
                    //txtMaSP.setBackground(Color.yellow);
                    JOptionPane.showMessageDialog(this, "Mã Sản Phẩm Không Được trùng!");
                }
                //Tim.add(x);
            }
            
//            for(int i=0; i<Tim.size();i++ ){
//                if (Tim.equals(txtMaSP.getText())) {
//                    
//                    txtMaSP.setBackground(Color.yellow);
//                    return;
//                }
//                txtMaSP.setBackground(Color.white);
//            }
            
    }
    
    private void checkEmpty() {
        if (txtMaSP.getText().equals("")) {//ktra ma
            JOptionPane.showMessageDialog(this, "Mã Sản Phẩm Không Để trống!");
            return;
        }else {
            checkMa();
        }
        if (txtTenSP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên Sản Phẩm Không Để trống!");
            return;
        }//ktra soluong
        if (txtSoluong.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Số Lượng Không Để trống!");
            return;
        }
        else if(!checkSo()){//Integer.parseInt(txtSoluong.getText())<0
            txtSoluong.setBackground(Color.yellow);
            //JOptionPane.showMessageDialog(this, "Số Lượng >0");
            JOptionPane.showMessageDialog(this, "Sai định dạng số lượng.(Là các số 0-9)");
            return;
        }else{
            txtSoluong.setBackground(Color.white);
        }
        //ktra don gia
        if (txtDonGia.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Đơn Giá Không Để trống!");
            return;
        } else if (Float.parseFloat(txtDonGia.getText()) < 0) {
            txtDonGia.setBackground(Color.yellow);
            JOptionPane.showMessageDialog(this, "Đơn Giá >0");
            return;
        }
        //ktra ngay nhap
        if (txtNgayNhap.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Ngày Nhập Không Để trống!");
            return;
        } else {
            checkDate();
        }

    }
    private void txtMaSPFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaSPFocusLost
        // TODO add your handling code here:
        String MaSp = txtMaSP.getText();
        if (MaSp.length() > 0) {
            txtMaSP.setBackground(Color.white);
            if (MaSp.length() > 10) {
                JOptionPane.showMessageDialog(this, "Mã Sản Phấm Quá Dài");
                txtMaSP.setBackground(Color.yellow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Mã Sản Phẩm Không Để Trống");
            txtMaSP.setBackground(Color.red);
        }
    }//GEN-LAST:event_txtMaSPFocusLost

    private void btnKiemTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKiemTraActionPerformed
        // TODO add your handling code here:
        List<SanPhamModel> ds = ProductService.getProducts();
        DefaultTableModel dtm = (DefaultTableModel) this.tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPhamModel product : ds) {
            Object[] rowData = {
                product.getMaSP(),
                product.getHangEntity(),
                product.getTheLoaiEntity(),
                product.getSizeEntity(),
                product.getMauSacEntity(),
                product.getTenSP(),
                dateFormat.format(product.getNgayNhap()),
                product.getGia(),
                product.getSoLuong(),
                product.getAnh(),
                product.getTrangThai() == 0 ? "Active" : "InActive"
            };

            dtm.addRow(rowData);
        }
    }//GEN-LAST:event_btnKiemTraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane TabbedPane1;
    private javax.swing.JButton btlDM;
    private javax.swing.JButton btnActive;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnHang;
    private javax.swing.JButton btnInActive;
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnKiemTra;
    private javax.swing.JButton btnMS;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnResrt;
    private javax.swing.JButton btnSize;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnVoHieu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<HangEntity> cboHang;
    private javax.swing.JComboBox<MauSacEntity> cboMS;
    private javax.swing.JComboBox<SizeEntity> cboSize;
    private javax.swing.JComboBox<TheLoaiEntity> cboTL;
    private javax.swing.JComboBox<String> cboTimMS;
    private javax.swing.JComboBox<String> cboTimSize;
    private javax.swing.JCheckBox chkTimKiem2;
    private javax.swing.JCheckBox chkTimMS;
    private javax.swing.JCheckBox chkTimMa;
    private javax.swing.JCheckBox chkTimSize;
    private javax.swing.JCheckBox chkTimTen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelInactive;
    private javax.swing.JPanel jPanelSanPham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JRadioButton rdoActive;
    private javax.swing.JRadioButton rdoINActive;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamInactive;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimMa;
    private javax.swing.JTextField txtTimTen;
    // End of variables declaration//GEN-END:variables
}

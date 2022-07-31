/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;
// WebCame

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//import sử lý
import DomainModels.HoaDonEntity;
import DomainModels.SanPhamEntity;
import Services.ChiTietHoaDonService;
import Services.HoaDonService;
import Services.InterfaceService;
import Services.SanPhamService;
import ViewModels.ChiTietHoaDonModel;
import ViewModels.HoaDonModel;
import ViewModels.SanPhamModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author boquy
 */
public class QL_BanGiay extends javax.swing.JFrame implements Runnable, ThreadFactory {

    String maSP;
    int i;
    int j;
    int c;

    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);

    JPopupMenu popupMenu;
    JMenuItem _itemAdd;

    InterfaceService<SanPhamModel> _InterfService;
    InterfaceService<HoaDonModel> _InterfHDmodel;
    InterfaceService<ChiTietHoaDonModel> _InterChiTietHD;

    DefaultTableModel _tableSanPham;
    DefaultTableModel _tableHoaDon;
    DefaultTableModel _tableChiTietHoaDon;

    List<HoaDonModel> _listHDModle;
    List<HoaDonModel> _ListTrangThai;
    List<HoaDonModel> _listChuaHD;
    List<ChiTietHoaDonModel> _listChiTietHD;
    List<ChiTietHoaDonModel> _listTimKiem;
    List<SanPhamModel> _ListSanPham;
    List<SanPhamModel> _TimSanPham;
    List<ChiTietHoaDonModel> _list_Cler_ChiTietHD;

    public QL_BanGiay() {
        initComponents();
        popupMenu = new JPopupMenu();
        _itemAdd = new JMenuItem("Thêm");
        popupMenu.add(_itemAdd);
        _itemAdd.addActionListener(addSanpham);

        _InterfService = new SanPhamService();
        _InterfHDmodel = new HoaDonService();
        _InterChiTietHD = new ChiTietHoaDonService();
        _listHDModle = new ArrayList<>();
        _ListTrangThai = new ArrayList<>();
        _listChuaHD = new ArrayList<>();
        _listChiTietHD = new ArrayList<>();
        _listTimKiem = new ArrayList<>();
        _list_Cler_ChiTietHD = new ArrayList<>();
        _TimSanPham = new ArrayList<>();

        _ListSanPham = _InterfService.Select();
        getModel();
        loadateSanPham();
    }

    void getModel() {
        //hoadon
        _tableHoaDon = (DefaultTableModel) tbl_hoaDon.getModel();
        _tableHoaDon.setRowCount(0);
        //sanpham
        _tableSanPham = (DefaultTableModel) tbl_sanPham.getModel();
        _tableSanPham.setRowCount(0);
        //Chitiet
        _tableChiTietHoaDon = (DefaultTableModel) tbl_chiTietHoaDon.getModel();
        _tableChiTietHoaDon.setRowCount(0);
    }

    // lấy sp lên table sp
    public void loadateSanPham() {
        for (SanPhamModel x : _ListSanPham) {
            _tableSanPham.addRow(new Object[]{stt(), x.getTenSP(), x.getSizeEntity().getSize(), x.getMauSacEntity().getTenMau(), x.getHangEntity().getTenHang(), x.getTheLoaiEntity().getTenLoai(), x.getSoLuong(), x.getGia()});

        }
    }

    int stt() {
        for (i = 0; i < _tableSanPham.getRowCount(); i++) {
        }
        return i;
    }

    // Table Hóa Đơn
    int sttHD() {
        for (j = 0; j < _tableHoaDon.getRowCount(); j++) {
        }
        return j;
    }

    HoaDonModel GuiHD() {
        return new HoaDonModel(txt_MaHD.getText(), JdateChoes.getDate(),
                "Nguyễn Văn A", txt_TenKH.getText(),
                Float.parseFloat(String.valueOf("1")),
                Float.parseFloat(String.valueOf("1")),
                Float.parseFloat(String.valueOf("1")), 1);
    }

    HoaDonModel GuiHDVsql() {
        return new HoaDonModel(txt_MaHD.getText(), JdateChoes.getDate(),
                "Nv1", txt_TenKH.getText(),
                Float.parseFloat(txt_TienKhachDua.getText()),
                Float.parseFloat(txt_TienThua.getText()),
                Float.parseFloat(txt_ThanhTien.getText()), 1);
    }

    public void LoadhoaDon() {
        for (HoaDonModel x : _listHDModle) {
            if (x.getTrangThai() == 1) {
                _tableHoaDon.addRow(new Object[]{sttHD(), x.getMaHD(), x.getMaNV(), x.getTenKH(), "", "Chưa Hoàn Thành"});
            }
        }
    }

    // hoadonchiTiet
    int stt2() {
        for (c = 0; c < _tableChiTietHoaDon.getRowCount(); c++) {
        }
        return c;
    }

    // chỉ để lấy lên _table_ChiTietHD
    ChiTietHoaDonModel guiChiTietHD(int soLuong) {

        int dex = tbl_sanPham.getSelectedRow();
        return new ChiTietHoaDonModel(-1, GuiHD_Entity(),
                guiSP(),
                String.valueOf(tbl_sanPham.getValueAt(dex, 1)),
                String.valueOf(tbl_sanPham.getValueAt(dex, 2)),
                String.valueOf(tbl_sanPham.getValueAt(dex, 3)),
                String.valueOf(tbl_sanPham.getValueAt(dex, 4)),
                String.valueOf(tbl_sanPham.getValueAt(dex, 5)),
                soLuong,
                Float.parseFloat(String.valueOf(tbl_sanPham.getValueAt(dex, 7))));
    }

    // load lại hóa đơn chi tiết
    void loadchiTiet() {
        for (ChiTietHoaDonModel x : _listChiTietHD) {
            _tableChiTietHoaDon.addRow(new Object[]{stt2(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()});
        }
    }

// lấy mã sp đưa vào hóa đơn chi tiết
    String maSp() {
        int dex = tbl_sanPham.getSelectedRow();
        List<SanPhamModel> sp = _InterfService.Select();
        for (SanPhamModel x : sp) {
            if (x.getTenSP().equals(String.valueOf(tbl_sanPham.getValueAt(dex, 1))) && x.getSizeEntity().getSize().equals(String.valueOf(tbl_sanPham.getValueAt(dex, 2))) && x.getMauSacEntity().getTenMau().equals(String.valueOf(tbl_sanPham.getValueAt(dex, 3)))) {
                maSP = x.getMaSP();
            }
        }
        return maSP;

    }
// gui sanPhamEntity

    SanPhamEntity guiSP() {
        return new SanPhamEntity(maSp());
    }
// guiHoaDonEntity

    HoaDonEntity GuiHD_Entity() {
        int index = tbl_hoaDon.getSelectedRow();
        return new HoaDonEntity(String.valueOf(tbl_hoaDon.getValueAt(index, 1)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_BanHang = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btn_TraHang = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        JPanChinh = new javax.swing.JPanel();
        Jpane_BanHang = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_chiTietHoaDon = new javax.swing.JTable();
        jPaneNhap = new javax.swing.JPanel();
        Btn_Tatcame = new javax.swing.JButton();
        Btn_BatCame = new javax.swing.JButton();
        JpaneThayCame = new javax.swing.JPanel();
        Pane_Texx = new javax.swing.JPanel();
        txt_MaHD = new javax.swing.JTextField();
        txt_TienThua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn_TaoDon = new javax.swing.JButton();
        btn_ThanhToan = new javax.swing.JButton();
        btn_HuyDon = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JdateChoes = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txt_SDT = new javax.swing.JTextField();
        txt_TenKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_TienKhachDua = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_hoaDon = new javax.swing.JTable();
        cbc_TrangThai = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_BanHang.setBackground(new java.awt.Color(249, 223, 5));
        btn_BanHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_BanHang.setText("Bán Hàng");
        btn_BanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BanHangActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(249, 223, 5));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Thống Kê");

        jButton3.setBackground(new java.awt.Color(249, 223, 5));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Nhân Viên");

        jButton4.setBackground(new java.awt.Color(249, 223, 5));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Đăng Xuất");

        jButton5.setBackground(new java.awt.Color(249, 223, 5));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Khách Hàng");

        btn_TraHang.setBackground(new java.awt.Color(249, 223, 5));
        btn_TraHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TraHang.setText("Trả Hàng");
        btn_TraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TraHangActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(249, 223, 5));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Sản Phảm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_BanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_TraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btn_BanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(btn_TraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(149, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        JPanChinh.setBackground(new java.awt.Color(204, 255, 255));
        JPanChinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51)));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn chi tiết"));

        tbl_chiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Tên SP", "Size", "Màu ", "Hãng", "Thể Loại", "Số Lượng", "Đơn Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_chiTietHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPaneNhap.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập"));
        jPaneNhap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Btn_Tatcame.setText("Tắt Came");
        Btn_Tatcame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TatcameActionPerformed(evt);
            }
        });
        jPaneNhap.add(Btn_Tatcame, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 100, 30));

        Btn_BatCame.setText("Bật Came");
        Btn_BatCame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_BatCameActionPerformed(evt);
            }
        });
        jPaneNhap.add(Btn_BatCame, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 100, 30));

        JpaneThayCame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Pane_Texx.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 0)));
        Pane_Texx.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        Pane_Texx.add(txt_MaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 136, -1));
        Pane_Texx.add(txt_TienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 360, -1));

        jLabel4.setText("Tiền Thừa :");
        Pane_Texx.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        btn_TaoDon.setBackground(new java.awt.Color(255, 102, 51));
        btn_TaoDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TaoDon.setForeground(new java.awt.Color(255, 255, 255));
        btn_TaoDon.setIcon(new javax.swing.ImageIcon("E:\\DUAN1\\Nhom2_IT17202\\QuanLyShoe\\src\\main\\java\\IMG\\icons8_bill_25px_2.png")); // NOI18N
        btn_TaoDon.setText("Tạo Đơn");
        btn_TaoDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoDonActionPerformed(evt);
            }
        });
        Pane_Texx.add(btn_TaoDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        btn_ThanhToan.setBackground(new java.awt.Color(255, 102, 51));
        btn_ThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_ThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btn_ThanhToan.setIcon(new javax.swing.ImageIcon("E:\\DUAN1\\Nhom2_IT17202\\QuanLyShoe\\src\\main\\java\\IMG\\icons8_payment_history_25px_3.png")); // NOI18N
        btn_ThanhToan.setText("Thanh Toán");
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });
        Pane_Texx.add(btn_ThanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));

        btn_HuyDon.setBackground(new java.awt.Color(255, 102, 51));
        btn_HuyDon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_HuyDon.setForeground(new java.awt.Color(255, 255, 255));
        btn_HuyDon.setIcon(new javax.swing.ImageIcon("E:\\DUAN1\\Nhom2_IT17202\\QuanLyShoe\\src\\main\\java\\IMG\\icons8_delete_25px_2.png")); // NOI18N
        btn_HuyDon.setText("Hủy Đơn");
        btn_HuyDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyDonActionPerformed(evt);
            }
        });
        Pane_Texx.add(btn_HuyDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 180, 125, -1));

        jLabel7.setText("Thành Tiền");
        Pane_Texx.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));
        Pane_Texx.add(txt_ThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 110, 136, -1));

        jLabel5.setText("Tiền Đưa :");
        Pane_Texx.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel3.setText("Ngày Tạo :");
        Pane_Texx.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        Pane_Texx.add(JdateChoes, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 132, -1));

        jLabel6.setText("SDT");
        Pane_Texx.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, -1, -1));
        Pane_Texx.add(txt_SDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 136, -1));
        Pane_Texx.add(txt_TenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, 136, -1));

        jLabel2.setText("Tên KH");
        Pane_Texx.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jLabel1.setText("MaHD :");
        Pane_Texx.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));
        Pane_Texx.add(txt_TienKhachDua, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 136, -1));

        javax.swing.GroupLayout JpaneThayCameLayout = new javax.swing.GroupLayout(JpaneThayCame);
        JpaneThayCame.setLayout(JpaneThayCameLayout);
        JpaneThayCameLayout.setHorizontalGroup(
            JpaneThayCameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JpaneThayCameLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Pane_Texx, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        JpaneThayCameLayout.setVerticalGroup(
            JpaneThayCameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpaneThayCameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Pane_Texx, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPaneNhap.add(JpaneThayCame, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 486, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Sản phẩm"));

        tbl_sanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Tên SP", "Size", "Màu", "Hãng", "Thể Loại", "Số Lượng", "Đơn GIá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_sanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_sanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_sanPham);

        btn_TimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_TimKiem.setIcon(new javax.swing.ImageIcon("E:\\DUAN1\\Nhom2_IT17202\\QuanLyShoe\\src\\main\\java\\IMG\\icons8_search_25px_2.png")); // NOI18N
        btn_TimKiem.setText("Tìm Kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        txt_TimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_TimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TimKiem)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_TimKiem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tbl_hoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "MaHD", "TênNV", "Tên KH", "Tổng Tiền", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_hoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_hoaDon);

        cbc_TrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trạng Thái", "Chưa Hoàn Thành", "Hoàn Thành" }));
        cbc_TrangThai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbc_TrangThaiItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cbc_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbc_TrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout Jpane_BanHangLayout = new javax.swing.GroupLayout(Jpane_BanHang);
        Jpane_BanHang.setLayout(Jpane_BanHangLayout);
        Jpane_BanHangLayout.setHorizontalGroup(
            Jpane_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Jpane_BanHangLayout.createSequentialGroup()
                .addGroup(Jpane_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Jpane_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPaneNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        Jpane_BanHangLayout.setVerticalGroup(
            Jpane_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Jpane_BanHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Jpane_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPaneNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Jpane_BanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout JPanChinhLayout = new javax.swing.GroupLayout(JPanChinh);
        JPanChinh.setLayout(JPanChinhLayout);
        JPanChinhLayout.setHorizontalGroup(
            JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jpane_BanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        JPanChinhLayout.setVerticalGroup(
            JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Jpane_BanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPanChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPanChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TaoDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoDonActionPerformed
        // Tạo hóa đơn
        // GUI
        _listHDModle.add(GuiHD());
        _tableHoaDon.setRowCount(0);
        LoadhoaDon();
        // changeTrangThai();
        _tableChiTietHoaDon.setRowCount(0);
        _list_Cler_ChiTietHD.clear();


    }//GEN-LAST:event_btn_TaoDonActionPerformed

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
        // click để thêm sp lên hóa đơn chi tiết

        if (tbl_hoaDon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn rồi chọn sản phẩm");
            return;
        }
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupMenu.show(tbl_sanPham, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbl_sanPhamMouseClicked

    private void tbl_hoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoaDonMouseClicked
        //load sản phẩm sang bên hóa đơn chi tiết
        _tableChiTietHoaDon.setRowCount(0);
        int index = tbl_hoaDon.getSelectedRow();
        for (ChiTietHoaDonModel x : _listChiTietHD) {
            if (x.getHoaDonEntity().getMaHD() == String.valueOf(tbl_hoaDon.getValueAt(index, 1))) {
                _listTimKiem.add(x);
            }
        }
        loadNguocLai();
        _listTimKiem.clear();
        // load lên text
        float tien = 0;
        for (int k = 0; k < _tableChiTietHoaDon.getRowCount(); k++) {
            float tongTien = Float.parseFloat(String.valueOf(tbl_chiTietHoaDon.getValueAt(k, 7))) * Integer.parseInt(String.valueOf(tbl_chiTietHoaDon.getValueAt(k, 6)));
            tien += tongTien;
        }
        txt_ThanhTien.setText(String.valueOf(tien));
        tbl_hoaDon.setValueAt(tien, tbl_hoaDon.getSelectedRow(), 4);
        txt_MaHD.setText(String.valueOf(tbl_hoaDon.getValueAt(index, 1)));


    }//GEN-LAST:event_tbl_hoaDonMouseClicked

    private void btn_HuyDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyDonActionPerformed
        // Hủy Hóa đơn

        int index = tbl_hoaDon.getSelectedRow();
        _tableHoaDon.removeRow(index);

    }//GEN-LAST:event_btn_HuyDonActionPerformed

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        // Thanh toán

        // Sử lý tiền và trạng thái của hóa đơn
        String Thiếu = String.valueOf(Float.parseFloat(txt_ThanhTien.getText()) - Float.parseFloat(txt_TienKhachDua.getText()));
        if (Float.parseFloat(txt_TienKhachDua.getText()) < Float.parseFloat(txt_ThanhTien.getText())) {
            JOptionPane.showMessageDialog(this, "Bạn Còn thiếu " + Thiếu + "VND");
            txt_TienKhachDua.requestFocus();
            return;
        }
        txt_TienThua.setText(String.valueOf(Float.parseFloat(txt_TienKhachDua.getText()) - Float.parseFloat(txt_ThanhTien.getText())));
        int index = tbl_hoaDon.getSelectedRow();
        _listHDModle.get(index).setTrangThai(2);

        // SQL_Thêm hóa Đơn vào 
        if (_InterfHDmodel.createNew(GuiHDVsql()) != null) {
            JOptionPane.showMessageDialog(this, "Thêm  Thành  Công");

        } else {
            JOptionPane.showMessageDialog(this, "Thêm Không Công");
        }

        //SQL Thêm hoa dơn Chi Tiết
        for (ChiTietHoaDonModel x : _listChiTietHD) {
            if (x.getHoaDonEntity().getMaHD().equals(String.valueOf(tbl_hoaDon.getValueAt(tbl_hoaDon.getSelectedRow(), 1)))) {
                if (_InterChiTietHD.createNew(new ChiTietHoaDonModel(-1, x.getHoaDonEntity(), x.getSanPhamEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien())) != null) {
                    JOptionPane.showMessageDialog(this, "Thêm  Thành  Công");
                }
            }
            System.out.println("chạy ra ngoài luôn rồi");
        }

        // Đổi trạng thái thành hoàn thành và chuyển vào mục hoàn thành
        changeTrangThai();
        _tableChiTietHoaDon.setRowCount(0);


    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void cbc_TrangThaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbc_TrangThaiItemStateChanged
        // TODO add your handling code here:

        int index = cbc_TrangThai.getSelectedIndex();
        for (HoaDonModel z : _listHDModle) {
            if (z.getTrangThai() == index) {
                _ListTrangThai.add(z);
            }
        }
        _tableHoaDon.setRowCount(0);
        LoadTrangThai();
        _ListTrangThai.clear();

    }//GEN-LAST:event_cbc_TrangThaiItemStateChanged

    private void btn_TraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TraHangActionPerformed
        // TODO add your handling code here:

        JPanChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JPanChinh.removeAll();
        JPanel pane = new TraHang();
        pane.setPreferredSize(new Dimension(1150, 760));
        JPanChinh.add(pane);
        revalidate();
        repaint();
    }//GEN-LAST:event_btn_TraHangActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed

        if (txt_TimKiem.getText().equals("") || txt_TimKiem.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Nhập vào ô Tìm Kiếm");
            return;
        }
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void txt_TimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemKeyReleased
        // TODO add your handling code here:
        _tableSanPham.setRowCount(0);
        for (SanPhamModel x : _ListSanPham) {
            if (x.getTenSP().toLowerCase().startsWith(txt_TimKiem.getText().toLowerCase())) {
                _TimSanPham.add(x);
            }
        }
        System.out.println(_TimSanPham.size());
        for (SanPhamModel x : _TimSanPham) {
            _tableSanPham.addRow(new Object[]{stt(), x.getTenSP(), x.getSizeEntity().getSize(), x.getMauSacEntity().getTenMau(), x.getHangEntity().getTenHang(), x.getTheLoaiEntity().getTenLoai(), x.getSoLuong(), x.getGia()});
        }
        _TimSanPham.clear();
    }//GEN-LAST:event_txt_TimKiemKeyReleased

    private void btn_BanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BanHangActionPerformed
        // TODO add your handling code here:
        JPanChinh.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JPanChinh.removeAll();
        Jpane_BanHang.setPreferredSize(new Dimension(1150, 760));
        JPanChinh.add(Jpane_BanHang);
        revalidate();
        repaint();
        btn_BanHang.setBackground(Color.GRAY);

    }//GEN-LAST:event_btn_BanHangActionPerformed

    private void Btn_BatCameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_BatCameActionPerformed
        // TODO add your handling code here:
        JpaneThayCame.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JpaneThayCame.removeAll();
        initWebCam();
        revalidate();
        repaint();


    }//GEN-LAST:event_Btn_BatCameActionPerformed

    private void Btn_TatcameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TatcameActionPerformed
        // TODO add your handling code here:
        JpaneThayCame.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        JpaneThayCame.removeAll();
       
       
        JpaneThayCame.add(Pane_Texx);
        revalidate();
        repaint();
        webcam.close();

    }//GEN-LAST:event_Btn_TatcameActionPerformed

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

        } catch (InstantiationException ex) {

        } catch (IllegalAccessException ex) {
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_BanGiay().setVisible(true);
            }
        });
    }

    void loadNguocLai() {
        for (ChiTietHoaDonModel x : _listTimKiem) {
            _tableChiTietHoaDon.addRow(new Object[]{stt2(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()});

        }
    }

    public void LoadTrangThai() {
        for (HoaDonModel x : _ListTrangThai) {
            _tableHoaDon.addRow(new Object[]{sttHD(), x.getMaHD(), x.getMaNV(), x.getTenKH(), "", x.getTrangThai() == 2 ? "Đã Hoàn Thành" : "Chưa Hoàn Thành"});
        }
    }

    void changeTrangThai() {
        for (HoaDonModel z : _listHDModle) {
            if (z.getTrangThai() == 1) {
                _listChuaHD.add(z);
            }
        }
        _tableHoaDon.setRowCount(0);
        LoadChưaHD();
    }

    public void LoadChưaHD() {
        _tableHoaDon.setRowCount(0);
        for (HoaDonModel x : _listChuaHD) {
            _tableHoaDon.addRow(new Object[]{sttHD(), x.getMaHD(), x.getMaNV(), x.getTenKH(), "", x.getTrangThai() == 2 ? "Đã Hoàn Thành" : "Chưa Hoàn Thành"});
        }
    }

    public void loadChiTietClear() {
        for (ChiTietHoaDonModel x : _list_Cler_ChiTietHD) {
            _tableChiTietHoaDon.addRow(new Object[]{stt2(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()});
        }
    }
    ActionListener addSanpham = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // int chom = JOptionPane.showConfirmDialog(tbl_sanPham, "Thêm giày " + String.valueOf(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 1)));
            String soluong = JOptionPane.showInputDialog(tbl_sanPham, "Nhập số lượng");
            int row = tbl_sanPham.getSelectedRow();
            _listChiTietHD.add(guiChiTietHD(Integer.parseInt(soluong)));
            _list_Cler_ChiTietHD.add(guiChiTietHD(Integer.parseInt(soluong)));
            _tableChiTietHoaDon.setRowCount(0);
            loadChiTietClear();
            tbl_sanPham.setValueAt(Integer.parseInt(String.valueOf(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 6))) - Integer.parseInt(soluong), tbl_sanPham.getSelectedRow(), 6);
            ///
            int index_CT = tbl_chiTietHoaDon.getSelectedRow();
            float tien = 0;
            for (int k = 0; k < _tableChiTietHoaDon.getRowCount(); k++) {
                float tongTien = Float.parseFloat(String.valueOf(tbl_chiTietHoaDon.getValueAt(k, 7))) * Integer.parseInt(String.valueOf(tbl_chiTietHoaDon.getValueAt(k, 6)));
                tien += tongTien;
            }
            txt_ThanhTien.setText(String.valueOf(tien));
            tbl_hoaDon.setValueAt(tien, tbl_hoaDon.getSelectedRow(), 4);

        }
    };

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_BatCame;
    private javax.swing.JButton Btn_Tatcame;
    private javax.swing.JPanel JPanChinh;
    private com.toedter.calendar.JDateChooser JdateChoes;
    private javax.swing.JPanel JpaneThayCame;
    private javax.swing.JPanel Jpane_BanHang;
    private javax.swing.JPanel Pane_Texx;
    private javax.swing.JButton btn_BanHang;
    private javax.swing.JButton btn_HuyDon;
    private javax.swing.JButton btn_TaoDon;
    private javax.swing.JButton btn_ThanhToan;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_TraHang;
    private javax.swing.JComboBox<String> cbc_TrangThai;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPaneNhap;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tbl_chiTietHoaDon;
    private javax.swing.JTable tbl_hoaDon;
    private javax.swing.JTable tbl_sanPham;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_ThanhTien;
    private javax.swing.JTextField txt_TienKhachDua;
    private javax.swing.JTextField txt_TienThua;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            Result result = null;
            BufferedImage image = null;

            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (Exception e) {
            }
            if (result != null) {
                txt_TimKiem.setText(result.getText());
            }

        } while (true);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }

    private void initWebCam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);

        JpaneThayCame.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
        executor.execute(this);
    }

}

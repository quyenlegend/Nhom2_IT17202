/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import DomainModels.HoaDonEntity;
import DomainModels.SanPhamEntity;
import Services.ChiTietHoaDonService;
import Services.HoaDonService;
import Services.InterfaceService;
import Services.SanPhamService;
import ViewModels.ChiTietHoaDonModel;
import ViewModels.HoaDonModel;
import ViewModels.SanPhamModel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author boquy
 */
public class QL_BanGiay extends javax.swing.JFrame {

    String maSP;
    int i;
    int j;
    int c;
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

    int dex;

    public QL_BanGiay() {
        initComponents();
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
        dex = tbl_sanPham.getSelectedRow();
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
        return new ChiTietHoaDonModel(GuiHD_Entity(),
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btn_TraHang = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        JPanChinh = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_chiTietHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_sanPham = new javax.swing.JTable();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_MaHD = new javax.swing.JTextField();
        txt_TienKhachDua = new javax.swing.JTextField();
        txt_TienThua = new javax.swing.JTextField();
        txt_TenKH = new javax.swing.JTextField();
        txt_ThanhTien = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        btn_ThanhToan = new javax.swing.JButton();
        btn_HuyDon = new javax.swing.JButton();
        btn_TaoDon = new javax.swing.JButton();
        JdateChoes = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_hoaDon = new javax.swing.JTable();
        cbc_TrangThai = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Bán Hàng");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Thống Kê");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Nhân Viên");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Đăng Xuất");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Khách Hàng");

        btn_TraHang.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_TraHang.setText("Trả Hàng");
        btn_TraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TraHangActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setText("Sản Phảm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btn_TraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

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
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập"));

        jLabel1.setText("MaHD :");

        jLabel2.setText("Tên KH");

        jLabel3.setText("Ngày Tạo :");

        jLabel4.setText("Tiền Thừa :");

        jLabel5.setText("Tiền Đưa :");

        jLabel6.setText("SDT");

        jLabel7.setText("Thành Tiền");

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

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_TienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(JdateChoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(4, 4, 4)))))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6))
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TienThua))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btn_TaoDon)
                        .addGap(34, 34, 34)
                        .addComponent(btn_ThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(btn_HuyDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(JdateChoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(txt_TienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_TienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HuyDon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TaoDon, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
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

        javax.swing.GroupLayout JPanChinhLayout = new javax.swing.GroupLayout(JPanChinh);
        JPanChinh.setLayout(JPanChinhLayout);
        JPanChinhLayout.setHorizontalGroup(
            JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanChinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        JPanChinhLayout.setVerticalGroup(
            JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanChinhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JPanChinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
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
            .addComponent(JPanChinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        //SQL

    }//GEN-LAST:event_btn_TaoDonActionPerformed

    private void tbl_sanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_sanPhamMouseClicked
        // click để thêm sp lên hóa đơn chi tiết
        if (tbl_hoaDon.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chọn Hóa Đơn rồi chọn sản phẩm");
            return;
        }
        int chom = JOptionPane.showConfirmDialog(this, "Thêm giày " + String.valueOf(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 1)));

        if (chom == 0) {

            if (SwingUtilities.isLeftMouseButton(evt)) {
                String soluong = JOptionPane.showInputDialog(this, "Nhập Số Lượng");
                int row = tbl_sanPham.getSelectedRow();
                _listChiTietHD.add(guiChiTietHD(Integer.parseInt(soluong)));
                _list_Cler_ChiTietHD.add(guiChiTietHD(Integer.parseInt(soluong)));
                _tableChiTietHoaDon.setRowCount(0);
                loadChiTietClear();
                // loadchiTiet();
                tbl_sanPham.setValueAt(Integer.parseInt(String.valueOf(tbl_sanPham.getValueAt(tbl_sanPham.getSelectedRow(), 6))) - Integer.parseInt(soluong), tbl_sanPham.getSelectedRow(), 6);
            }
            int index_CT = tbl_chiTietHoaDon.getSelectedRow();
            float tien = 0;
            for (int k = 0; k < _tableChiTietHoaDon.getRowCount(); k++) {
                float tongTien = Float.parseFloat(String.valueOf(tbl_chiTietHoaDon.getValueAt(k, 7))) * Integer.parseInt(String.valueOf(tbl_chiTietHoaDon.getValueAt(k, 6)));
                tien += tongTien;
            }
            txt_ThanhTien.setText(String.valueOf(tien));
            tbl_hoaDon.setValueAt(tien, tbl_hoaDon.getSelectedRow(), 4);

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
                if (_InterChiTietHD.createNew(new ChiTietHoaDonModel(x.getHoaDonEntity(), x.getSanPhamEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien())) != null) {
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
        pane.setPreferredSize(new Dimension(1150, 680));
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanChinh;
    private com.toedter.calendar.JDateChooser JdateChoes;
    private javax.swing.JButton btn_HuyDon;
    private javax.swing.JButton btn_TaoDon;
    private javax.swing.JButton btn_ThanhToan;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_TraHang;
    private javax.swing.JComboBox<String> cbc_TrangThai;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Views;

import DomainModels.ChiTietHoaDonEntity;
import DomainModels.DoiTraHangEntity;
import DomainModels.HoaDonEntity;
import Services.ChiTietHoaDonService;
import Services.ChiTietTraHangService;
import Services.DoiTraHangService;
import Services.HoaDonService;
import Services.InterfaceService;
import ViewModels.ChiTietHoaDonModel;
import ViewModels.ChiTietTraHangModel;
import ViewModels.DoiTraHangModel;
import ViewModels.HoaDonModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author boquy
 */
public class TraHang extends javax.swing.JPanel {

    int IDCT;
    int IDDoiHang;
    String TenSPCT;
    int i;
    JPopupMenu popupMenu;
    JMenuItem _itemAdd;

    DefaultTableModel _tableHoaDon;
    DefaultTableModel _tableChiTiet;
    DefaultTableModel _tableTraHang;
    DefaultTableModel _tableChiTietTra;

    InterfaceService<HoaDonModel> _InterfHDmodel;
    InterfaceService<ChiTietHoaDonModel> _InterChiTietHD;
    InterfaceService<DoiTraHangModel> _IterDoiHang;
    InterfaceService<ChiTietTraHangModel> _IterChiTietTraHang;

    List<HoaDonModel> _ListHoaDon;
    List<HoaDonModel> _ListTimHD;

    List<ChiTietHoaDonModel> _listCTHD;
    List<ChiTietHoaDonModel> _ListloadNguocLai;
    List<ChiTietHoaDonModel> _ListlayID_CT;

    List<DoiTraHangModel> _listDoiTra;
    List<ChiTietTraHangModel> _ListCTTraHang;

    public TraHang() {
        initComponents();
        popupMenu = new JPopupMenu();
        _itemAdd = new JMenuItem("Trả Hàng");
        popupMenu.add(_itemAdd);
        _itemAdd.addActionListener(addSanpham);

        _InterfHDmodel = new HoaDonService();
        _InterChiTietHD = new ChiTietHoaDonService();
        _IterDoiHang = new DoiTraHangService();
        _IterChiTietTraHang = new ChiTietTraHangService();

        _ListHoaDon = _InterfHDmodel.Select();
        _listCTHD = _InterChiTietHD.Select();

        _ListTimHD = new ArrayList<>();
        _ListloadNguocLai = new ArrayList<>();
        _ListlayID_CT = new ArrayList<>();
        _listDoiTra = new ArrayList<>();
        _ListCTTraHang = new ArrayList<>();

        getModel();
        loadHoaDon();
        // ldddd();
        // lay();
        //
        IDTra();
    }

    void getModel() {
        //hoadon
        _tableHoaDon = (DefaultTableModel) tbl_HoaDon.getModel();
        _tableHoaDon.setRowCount(0);
        //sanpham
        _tableTraHang = (DefaultTableModel) tbl_HDtra.getModel();
        _tableTraHang.setRowCount(0);
        //Chitiet
        _tableChiTiet = (DefaultTableModel) tbl_ChiTietHD.getModel();
        _tableChiTiet.setRowCount(0);
        //ChiTietTraHang
        _tableChiTietTra = (DefaultTableModel) tbl_ChiTietTraHang.getModel();
        _tableChiTietTra.setRowCount(0);
    }

    //Lấy hóa đơn lên table_hoaDon
    void loadHoaDon() {
        for (HoaDonModel x : _ListHoaDon) {
            _tableHoaDon.addRow(new Object[]{x.getMaHD(), x.getMaNV(), x.getTenKH(), x.getNgayTao(), x.getTongTien()});
        }
    }

    //Lấy chiTietHoaDon lên _tableChiTiet;
    void LoadchiTietHoaDon() {
        for (ChiTietHoaDonModel x : _listCTHD) {
            _tableChiTiet.addRow(new Object[]{x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()});
        }
    }

    //Load len TableDoiTra
    public void loadDoiTraHang() {
        for (DoiTraHangModel x : _listDoiTra) {
            _tableTraHang.addRow(new Object[]{x.getHoaDonEntity().getMaHD(), x.getMaNV(), x.getTenKH()});
        }
    }

    //Gui doitraHang
    DoiTraHangModel GuiTra() {
        return new DoiTraHangModel(-1, GuiHD_Entity(), txt_TenKH.getText(), JdateNgayTra.getDate(), 0, "Nguyễn Văn A");
    }

    //lấy HoaDonEntity.getMaHD()
    HoaDonEntity GuiHD_Entity() {
        int index = tbl_HoaDon.getSelectedRow();
        return new HoaDonEntity(String.valueOf(tbl_HoaDon.getValueAt(index, 0)));
    }

    //lay tu bang chi tiết xuống ChiTietTra
    ChiTietTraHangModel GuiTraHangChiTiet(int SoLuong) {
        int index = tbl_ChiTietHD.getSelectedRow();
        return new ChiTietTraHangModel(GuiIDTra(), GuiCTHD_ID(), String.valueOf(tbl_ChiTietHD.getValueAt(index, 0)),
                String.valueOf(tbl_ChiTietHD.getValueAt(index, 1)), String.valueOf(tbl_ChiTietHD.getValueAt(index, 2)), String.valueOf(tbl_ChiTietHD.getValueAt(index, 3)),
                String.valueOf(tbl_ChiTietHD.getValueAt(index, 4)), SoLuong,
                Float.parseFloat(String.valueOf(tbl_ChiTietHD.getValueAt(index, 6))));
    }

    // LoadChiTietTra
    public void LoadChiTietTra() {
        for (ChiTietTraHangModel x : _ListCTTraHang) {
            _tableChiTietTra.addRow(new Object[]{x.getDoiTraHangEntity().getMaDTH(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()});
        }

    }

    int sttCTTraHang() {
        for (i = 0; i < _tableChiTietTra.getRowCount(); i++) {
        }
        return i;
    }

    //LấyIdChietHoaDOn
    int MaIDCH() {
        int dex = tbl_ChiTietHD.getSelectedRow();
        int index = tbl_HoaDon.getSelectedRow();
        for (ChiTietHoaDonModel x : _listCTHD) {
            if (x.getHoaDonEntity().getMaHD().equals(txt_MaDH.getText()) && x.getTenSP().equals(String.valueOf(tbl_ChiTietHD.getValueAt(dex, 0))) && x.getSize().equals(tbl_ChiTietHD.getValueAt(dex, 1)) && x.getMau().equals(tbl_ChiTietHD.getValueAt(dex, 2))) {
                IDCT = x.getIDChiTiet();
                //  TenSPCT = x.getTenSP();
            }
        }
        _ListlayID_CT.add(new ChiTietHoaDonModel(IDCT));
        return IDCT;

    }
    
 // truyeenf vao cai GuiIdtra
    int IDTra() {
        List<DoiTraHangModel> _ListIDtra = _IterDoiHang.Select();
        for (DoiTraHangModel x : _ListIDtra) {
            IDDoiHang = x.getMaDTH();
        }
        System.out.println(IDDoiHang);

        return IDDoiHang;
    }

    // Gui doitrahang Lay ID
    DoiTraHangEntity GuiIDTra() {
        return new DoiTraHangEntity(IDTra());
    }

    // GUiChiTietHoaDOn Lay ID
    // int M
    ChiTietHoaDonEntity GuiCTHD_ID() {
        return new ChiTietHoaDonEntity(MaIDCH());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_ChiTietHD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_HoaDon = new javax.swing.JTable();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_HDtra = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_TenKH = new javax.swing.JTextField();
        txt_TienHoaDon = new javax.swing.JTextField();
        txt_MaDH = new javax.swing.JTextField();
        txt_TienTraLai = new javax.swing.JTextField();
        JdateNgayTra = new com.toedter.calendar.JDateChooser();
        Btn_TraHang = new javax.swing.JButton();
        btn_TaoDonTra = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_ChiTietTraHang = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(1155, 680));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết hóa đơn"));

        tbl_ChiTietHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tên SP", "Size", "Màu Sắc", "Hãng ", "Thể Loại", "Số Lượng", "Đơn Giá"
            }
        ));
        tbl_ChiTietHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChiTietHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_ChiTietHD);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa đơn"));

        tbl_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "MaHD", "Tên NV", "Tên KH", "Ngày Mua", "Tổng Tiền"
            }
        ));
        tbl_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_HoaDon);

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_TimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_TimKiem)
                    .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Trả hàng"));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn trả"));

        tbl_HDtra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Hóa Đơn", "Tên NV", "Tên KH", "Tiền Hoàn"
            }
        ));
        tbl_HDtra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_HDtraMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_HDtra);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập"));

        jLabel1.setText("Khách Hàng :");

        jLabel2.setText("Mã Hóa Đơn");

        jLabel3.setText("Ngày Trả");

        jLabel4.setText("Tiền Hoàn");

        jLabel5.setText("Tiền Thanh Toán");

        Btn_TraHang.setBackground(new java.awt.Color(255, 102, 51));
        Btn_TraHang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Btn_TraHang.setForeground(new java.awt.Color(255, 255, 255));
        Btn_TraHang.setIcon(new javax.swing.ImageIcon("E:\\DUAN1\\Nhom2_IT17202\\QuanLyShoe\\src\\main\\java\\IMG\\icons8_checkout_25px.png")); // NOI18N
        Btn_TraHang.setText("Trả Hàng");
        Btn_TraHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_TraHangActionPerformed(evt);
            }
        });

        btn_TaoDonTra.setText("Tạo Đơn");
        btn_TaoDonTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaoDonTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_MaDH, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_TienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btn_TaoDonTra)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JdateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txt_TienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Btn_TraHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txt_TenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btn_TaoDonTra)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_MaDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_TienHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(JdateNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_TienTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Btn_TraHang, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("ChiTietTra"));

        tbl_ChiTietTraHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Tên SP", "Size", "Màu Sắc", "Hãng ", "Thể Loại", "Số Lượng", "Đơn Giá"
            }
        ));
        jScrollPane4.setViewportView(tbl_ChiTietTraHang);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane4)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 761, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        if (txt_TimKiem.getText().equals("") || txt_TimKiem.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Nhập vào ô Tìm Kiếm");
            return;
        }
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void txt_TimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_TimKiemKeyReleased
        // Tìm gần đúng
        _tableHoaDon.setRowCount(0);
        for (HoaDonModel x : _ListHoaDon) {
            if (x.getTenKH().toLowerCase().startsWith(txt_TimKiem.getText().toLowerCase())) {
                _ListTimHD.add(x);
            }
        }
        for (HoaDonModel x : _ListTimHD) {
            _tableHoaDon.addRow(new Object[]{x.getMaHD(), x.getMaNV(), x.getTenKH(), x.getNgayTao(), x.getTongTien()});
        }
        _ListTimHD.clear();
    }//GEN-LAST:event_txt_TimKiemKeyReleased

    private void tbl_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HoaDonMouseClicked
        // TODO add your handling code here:
        _tableChiTiet.setRowCount(0);
        int index = tbl_HoaDon.getSelectedRow();
        txt_MaDH.setText(String.valueOf(tbl_HoaDon.getValueAt(index, 0)));
        txt_TenKH.setText(String.valueOf(tbl_HoaDon.getValueAt(index, 2)));
        txt_TienHoaDon.setText(String.valueOf(Float.parseFloat(String.valueOf(tbl_HoaDon.getValueAt(index, 4)))));
        for (ChiTietHoaDonModel x : _listCTHD) {
            if (x.getHoaDonEntity().getMaHD().equals(tbl_HoaDon.getValueAt(tbl_HoaDon.getSelectedRow(), 0))) {
                _ListloadNguocLai.add(x);
            }
        }

        for (ChiTietHoaDonModel x : _ListloadNguocLai) {
            _tableChiTiet.addRow(new Object[]{x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien()});
        }
        _ListloadNguocLai.clear();

    }//GEN-LAST:event_tbl_HoaDonMouseClicked

    private void tbl_ChiTietHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChiTietHDMouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            popupMenu.show(tbl_ChiTietHD, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbl_ChiTietHDMouseClicked

    private void tbl_HDtraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_HDtraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_HDtraMouseClicked

    private void btn_TaoDonTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaoDonTraActionPerformed
        // Tao Đơn tra demo
        _listDoiTra.add(GuiTra());
        _tableTraHang.setRowCount(0);
        loadDoiTraHang();

    }//GEN-LAST:event_btn_TaoDonTraActionPerformed

    private void Btn_TraHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_TraHangActionPerformed
        // Trả Hàng sử lí ở cái hóa đơn đã
        if (_IterDoiHang.createNew(GuiTraSQL()) != null) {
            JOptionPane.showMessageDialog(this, "Thêm  Thành  Công");

        }

        // thêm vào chi tiết Trả Hàng SQL
      
        for (ChiTietTraHangModel x : _ListCTTraHang) {
            System.out.println("Day laf cais moi nhat day" + x.getDoiTraHangEntity().getMaDTH());
            System.out.println("DDaay la cua chi tiet Hd"+x.getChiTietHoaDonEntity().getIDChiTiet());
            if (_IterChiTietTraHang.createNew(new ChiTietTraHangModel(x.getDoiTraHangEntity(), x.getChiTietHoaDonEntity(), x.getTenSP(), x.getSize(), x.getMau(), x.getTenHang(), x.getTheLoai(), x.getSoLuong(), x.getGiaTien())) != null) {

                System.out.println("đã cahayj vào đây");
                JOptionPane.showMessageDialog(this, "Thêm  Thành  Công");

            }
            JOptionPane.showMessageDialog(this, "Thêm Chưa  Công");

        }

    }//GEN-LAST:event_Btn_TraHangActionPerformed

    // sự kiện để xóa
    ActionListener addSanpham = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Thêm vào list
            int index = tbl_ChiTietHD.getSelectedRow();

            String soluong = JOptionPane.showInputDialog(tbl_ChiTietHD, "Nhập số Lượng Trả");
            if (Integer.parseInt(soluong) > Integer.parseInt(String.valueOf(_tableChiTiet.getValueAt(index, 5))) || Integer.parseInt(soluong) <= 0) {
                JOptionPane.showMessageDialog(tbl_ChiTietTraHang, "Nhập lại số lượng");
                return;
            }
            _ListCTTraHang.add(GuiTraHangChiTiet(Integer.parseInt(soluong)));
            _tableChiTietTra.setRowCount(0);
            LoadChiTietTra();

            System.out.println("Phân cách ra chạy vào đây và load đk lên chi tiết trả hàng");
            // xóa sp dó đi
            tbl_ChiTietHD.setValueAt(Integer.parseInt(String.valueOf(_tableChiTiet.getValueAt(index, 5))) - Integer.parseInt(soluong), index, 5);
            System.out.println(" vào phần sét lại cho nó");
            if (Integer.parseInt(String.valueOf(_tableChiTiet.getValueAt(index, 5))) == 0) {
                _tableChiTiet.removeRow(index);
                _listCTHD.remove(index);
            }
            // inra cái list kia
            System.out.println("Dây là lis chứ ID chi tiết để xóa =   " + _ListlayID_CT.size());

            // load lại tiền cần trả cho khách hàng
            float tien = 0;
            for (int k = 0; k < _tableChiTietTra.getRowCount(); k++) {
                float tongTien = Float.parseFloat(String.valueOf(_tableChiTietTra.getValueAt(k, 7))) * Integer.parseInt(String.valueOf(_tableChiTietTra.getValueAt(k, 6)));
                tien += tongTien;
            }
            txt_TienTraLai.setText(String.valueOf(tien));
            tbl_HDtra.setValueAt(tien, 0, 3);
        }
    };

    //GuiSQLTra
    DoiTraHangModel GuiTraSQL() {
        return new DoiTraHangModel(-1, GuiHD_Entity(), txt_TenKH.getText(), JdateNgayTra.getDate(), Float.parseFloat(String.valueOf(txt_TienTraLai.getText())), "Nv1");
    }

    void ldddd() {
        for (ChiTietTraHangModel x : _IterChiTietTraHang.Select()) {
            _tableChiTietTra.addRow(new Object[]{x.getDoiTraHangEntity().getMaDTH(), x.getChiTietHoaDonEntity().getIDChiTiet()});

        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_TraHang;
    private com.toedter.calendar.JDateChooser JdateNgayTra;
    private javax.swing.JButton btn_TaoDonTra;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable tbl_ChiTietHD;
    private javax.swing.JTable tbl_ChiTietTraHang;
    private javax.swing.JTable tbl_HDtra;
    private javax.swing.JTable tbl_HoaDon;
    private javax.swing.JTextField txt_MaDH;
    private javax.swing.JTextField txt_TenKH;
    private javax.swing.JTextField txt_TienHoaDon;
    private javax.swing.JTextField txt_TienTraLai;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.bookingg;

import common_function.UI;
import controller.RoomDAO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.BookedRoom;
import model.Booking;
import model.Room;
import model.User;
import view.user.LoginFrm;

/**
 *
 * @author LENOVO
 */
public class SearchFreeRoomFrm extends javax.swing.JFrame implements ActionListener {

    /**
     * Creates new form SearchFreeRoomFrm
     */
    private User u;
    private ArrayList<Room> listFreeRoom;
    private Date checkInDate;
    private Date checkOutDate;
    private Booking booking;
    private ArrayList<BookedRoom> listBookedRooms;

    public SearchFreeRoomFrm(User u) {
        initComponents();
        //UI
        setTitle("Hotel Management System - Search Free Rooms And Book");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.getContentPane().setBackground(Color.getHSBColor(106, 52, 50));
        UI.setFontForLabel(jLabel1);
        UI.setFontForLabel(jLabel2);
        UI.setFontForLabel(jLabel3);
        UI.setFontForLabel(jLabel4);
        UI.setFontForLabel(jLabel5);
        UI.setFontForLabel(txtRoleLabel);
        UI.setFontForLabel(txtUserLabel);
        txtUserFullName.setText(u.getFullName());
        txtRole.setText(u.getPosition());
        btnLogOut.addActionListener((e) -> {
            (new LoginFrm()).setVisible(true);
            this.dispose();
        });
        
        
        this.u = u;
        
        // click search de hien thi len bang free rooms (xem chi tiet duoi ham actionPerformed())
        btnSearch.addActionListener(this);
        
        //chuan bi model cho bang booked rooms
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Type");
        model.addColumn("Price");
        model.addColumn("Description");
        model.addColumn("Check In");
        model.addColumn("Check Out");
        tblBookedRoom.setModel(model);

        
        //khoi tao booking, list booked rooms
        booking = new Booking();
        listBookedRooms= new ArrayList<BookedRoom>();
        
        // click chon 1 dong trong bang free rooms thi no se dong goi du lieu vao bookedRoom roi hien thi them 1 dong trong bang Booked Room
        // co the click nhieu dong de dat nhieu Booked Room trong 1 lan Booking
        tblFreeRoom.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                BookedRoom bookedRoom = new BookedRoom();
                int column = tblFreeRoom.getColumnModel().
                        getColumnIndexAtX(e.getX()); // get the coloum of the button
                int row = e.getY() / tblFreeRoom.getRowHeight(); // get row 

                // *Checking the row or column is valid or not
                if (row < tblFreeRoom.getRowCount() && row >= 0
                        && column < tblFreeRoom.getColumnCount() && column >= 0) {

                    var room = listFreeRoom.get(row);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    model.addRow(new Object[]{room.getID(), room.getName(), room.getType(), room.getPrice(), room.getDescription(), sdf.format(checkInDate), sdf.format(checkOutDate)});
                    bookedRoom.setPrice(room.getPrice());
                    bookedRoom.setCheckIn(checkInDate);
                    bookedRoom.setCheckOut(checkOutDate);
                    bookedRoom.setAmount(room.getPrice());
                    bookedRoom.setRoom(room);
                    listBookedRooms.add(bookedRoom);
                }
            }
        });
        
        // sau khi click de chon du so Booked Room roi, khi ta click vao nut search client de chuyen frame, ta can phai dong goi Booking, nhet' cac Booked Room da chon vao 1 doi tuong Booking de gui qua frame sau
        btnSearchClient.addActionListener((e) -> {
            booking.setUser(u);
            var currentLocalDate = LocalDate.now();
            // Getting system timezone
            ZoneId systemTimeZone = ZoneId.systemDefault();
		
		// converting LocalDateTime to ZonedDateTime with the system timezone
		ZonedDateTime zonedDateTime = currentLocalDate.atStartOfDay(systemTimeZone);
		
		// converting ZonedDateTime to Date using Date.from() and ZonedDateTime.toInstant()
		Date utilDate = Date.from(zonedDateTime.toInstant());
            booking.setBookday(utilDate);
            float totalAmount =0;
            for(BookedRoom r: listBookedRooms){
                totalAmount+= r.getPrice();
            }
            booking.setTotalAmount(totalAmount);
            booking.setBookedRooms(listBookedRooms);
            
            (new SearchClientFrm(booking)).setVisible(true);
            this.dispose();
            
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCheckIn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCheckOut = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        scrollRoom = new javax.swing.JScrollPane();
        tblFreeRoom = new javax.swing.JTable();
        scrollBookedRoom = new javax.swing.JScrollPane();
        tblBookedRoom = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSearchClient = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtUserLabel = new javax.swing.JLabel();
        txtUserFullName = new javax.swing.JLabel();
        txtRoleLabel = new javax.swing.JLabel();
        txtRole = new javax.swing.JLabel();
        btnLogOut = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Check In Date:");

        jLabel2.setText("Check Out Date:");

        btnSearch.setText("Search Free Room");

        tblFreeRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Price", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollRoom.setViewportView(tblFreeRoom);

        tblBookedRoom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "Price", "Description", "CheckIn", "CheckOut"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollBookedRoom.setViewportView(tblBookedRoom);

        jLabel3.setText("Free Rooms:");

        jLabel4.setText("Booked Rooms:");

        btnSearchClient.setText("Confirm Booked Room(s)");

        jButton1.setText("<---");

        txtUserLabel.setText("User:");

        txtUserFullName.setText("jLabel6");

        txtRoleLabel.setText("Role:");

        txtRole.setText("jLabel7");

        btnLogOut.setText("Log out");

        jLabel5.setText("Search Free Rooms And Book Them");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(scrollRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(227, 227, 227)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUserLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUserFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(208, 208, 208)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)))
                                .addComponent(txtRoleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(45, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearchClient, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollBookedRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 847, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(txtUserLabel)
                    .addComponent(txtUserFullName)
                    .addComponent(btnLogOut)
                    .addComponent(txtRoleLabel)
                    .addComponent(txtRole))
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtCheckOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchClient))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBookedRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchClient;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane scrollBookedRoom;
    private javax.swing.JScrollPane scrollRoom;
    private javax.swing.JTable tblBookedRoom;
    private javax.swing.JTable tblFreeRoom;
    private javax.swing.JTextField txtCheckIn;
    private javax.swing.JTextField txtCheckOut;
    private javax.swing.JLabel txtRole;
    private javax.swing.JLabel txtRoleLabel;
    private javax.swing.JLabel txtUserFullName;
    private javax.swing.JLabel txtUserLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            // giao tiep voi co so du lieu de lay 1 list cac free room
            RoomDAO roomDAO = new RoomDAO();
            var sdf = new SimpleDateFormat("dd/MM/yyyy");
            checkInDate = sdf.parse(txtCheckIn.getText());
            checkOutDate = sdf.parse(txtCheckOut.getText());
            listFreeRoom = roomDAO.searchFreeRoom(checkInDate, checkOutDate);

            //hien thi len bang free room
            String data[][] = new String[listFreeRoom.size()][5];
            for (int i = 0; i < listFreeRoom.size(); i++) {
                var room = listFreeRoom.get(i);
                data[i][0] = room.getID()+"";
                data[i][1] = room.getName();
                data[i][2] = room.getType();
                data[i][3] = room.getPrice() + "";
                data[i][4] = room.getDescription();
            }
            String[] columnNames = {"ID", "name", "type", "price", "des"};
            DefaultTableModel dtm = new DefaultTableModel(data, columnNames);
            tblFreeRoom.setModel(dtm);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
}

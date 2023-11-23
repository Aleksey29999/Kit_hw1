//package net.ddns.wakhsavior;
package org.example;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.example.WriteFileChat.writeFileChat;

/*
Создать окно клиента чата. Окно должно содержать JtextField для ввода логина, пароля,
IP-адреса сервера, порта подключения к серверу, область ввода сообщений, JTextArea
область просмотра сообщений чата и JButton подключения к серверу и отправки сообщения в чат.
Желательно сразу сгруппировать компоненты, относящиеся к серверу сгруппировать на JPanel
сверху экрана, а компоненты, относящиеся к отправке сообщения – на JPanel снизу
 */
public class Chat extends JFrame {
    public static String fileName = "chat.txt";
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;
    JButton btnSend = new JButton("Отправить.");
    JLabel lblLogin = new JLabel("Login:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblIP = new JLabel("IP:");
    JLabel lblMessage = new JLabel("Сообщение:");
    JTextField txtFieldLogin = new JTextField();
    JTextField txtFieldPassword = new JTextField();
    JTextField txtFieldIP = new JTextField();
    JTextField txtFieldMessage = new JTextField();
    JTextArea areaMessage = new JTextArea();
    JPanel panServer = new JPanel(new GridLayout(6, 2));
    JPanel panClient = new JPanel(new GridLayout(4, 1));
    String login;
    String password;
    String IP;
    String message;
    String messageFile;


    Chat() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("ChatWindow");
        setResizable(false);


        lblMessage.setFocusable(true);

        panServer.add(lblLogin);
        panServer.add(txtFieldLogin);
        panServer.add(lblPassword);
        panServer.add(txtFieldPassword);
        panServer.add(lblIP);
        panServer.add(txtFieldIP);
        panClient.add(lblMessage);
        try {
            messageFile = ReaderFileChat.read(fileName);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        areaMessage.append(messageFile);
        panClient.add(areaMessage);

        panClient.add(txtFieldMessage);
        panClient.add(btnSend);
        System.out.println("Предыдущие сообщения: " + messageFile);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
                {
                    areaMessage.append(message);

                }

                System.out.println("Отправлено сообщение: " + message);
                try {
                    WriteFileChat.writeFileChat(fileName, message);
                } catch (
                        IOException ex) {
                    throw new RuntimeException(ex);
                }
                txtFieldMessage.setText(null);
            }
        });

        txtFieldMessage.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    message = txtFieldLogin.getText() + ": " + txtFieldMessage.getText() + "\n";
                    areaMessage.append(message);
                    System.out.println("Отправлено сообщение: " + message);
                    try {
                        WriteFileChat.writeFileChat(fileName, message);
                    } catch (
                            IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    txtFieldMessage.setText(null);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setLayout(new GridLayout(2, 1));

        add(panServer);

        add(panClient);

        setVisible(true);

    }


}

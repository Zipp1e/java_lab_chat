package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;


class ChatWindow extends JFrame {

    private JLayeredPane lpane = new JLayeredPane();
    private JPanel mainPanel = new JPanel();
    private JPanel smilePanel = new JPanel();

    ChatWindow() {
        super("telegram");

        setSize(630, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(lpane, BorderLayout.CENTER);

        Chat chat = new Chat();
        java.util.List<String> chattersList = new ArrayList<>();
        chattersList.add(chat.getPeople()[0].name());
        chattersList.add(chat.getPeople()[1].name());
        JList<String> chats = new JList<>((String[]) chattersList.toArray(new String[chattersList.size()]));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainPanel.setBounds(0, 0, 600, 500);

        JPanel chatArea = new JPanel();
        chatArea.setLayout(new BoxLayout(chatArea, BoxLayout.Y_AXIS));

        JTextArea outArea = new JTextArea();

        JPanel sendArea = new JPanel();
        sendArea.setLayout(new BoxLayout(sendArea, BoxLayout.X_AXIS));

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try (FileWriter fileWriter = new FileWriter(chattersList.get(0) + ".txt")) {
                    String stringToSave = outArea.getText();
                    fileWriter.write(stringToSave);
                } catch (IOException e) {
                    // exception handling...
                }

                System.exit(0);
            }
        });

        JTextField inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(500, 30));
        inputField.setPreferredSize(new Dimension(350, 30));
        inputField.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent point) {
                smilePanel.setVisible(false);
            }
        });


        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                outArea.append(inputField.getText() + "\n");
                outArea.append(chat.getReply(inputField.getText()) + "\n");
                inputField.setText("");
            }
        });
        sendButton.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent point) {
                smilePanel.setVisible(false);
            }
        });

        JButton smileButton = new JButton("Smiles");
        smileButton.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent point) {
                smilePanel.setVisible(true);
            }
        });
        smileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                smilePanel.setVisible(false);
            }
        });


        sendArea.add(quitButton);
        sendArea.add(inputField);
        sendArea.add(smileButton);
        sendArea.add(sendButton);

        chatArea.add(outArea);
        chatArea.add(sendArea);

        JPanel chatters = new JPanel();
        chats.setLayoutOrientation(JList.VERTICAL);

        chats.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (chats.getSelectedIndex() != 0) {
                    try (FileWriter fileWriter = new FileWriter(chattersList.get(0) + ".txt")) {
                        String stringToSave = outArea.getText();
                        fileWriter.write(stringToSave);
                    } catch (IOException e) {
                        // exception handling...
                    }

                    outArea.setText("");

                    String temp = chattersList.get(0);
                    chattersList.set(0, chattersList.get(1));
                    chattersList.set(1, temp);

                    if (chattersList.get(0).equals("feminist")) {
                        chat.setFeminist();
                    } else {
                        chat.setRapper();
                    }

                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(chattersList.get(0) + ".txt"))) {
                        String stringToRead = bufferedReader.readLine();
                        while (stringToRead != null) {
                            outArea.append(stringToRead + "\n");
                            stringToRead = bufferedReader.readLine();
                        }
                    } catch (IOException e) {
                        // exception handling
                    }
                    chats.setListData(chattersList.toArray(new String[chattersList.size()]));
                    chats.repaint();
                }
            }
        });

        chatters.add(chats);

        outArea.setText("");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("feminist.txt"))) {
            String stringToRead = bufferedReader.readLine();
            while (stringToRead != null) {
                outArea.append(stringToRead + "\n");
                stringToRead = bufferedReader.readLine();
            }
        } catch (IOException e) {
            // exception handling
        }

        mainPanel.add(chatters);
        mainPanel.add(chatArea);
        mainPanel.setOpaque(true);
        lpane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);

        smilePanel.setOpaque(true);
        smilePanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            Emoji emoji = new Emoji(i);
            JButton smile = new JButton(emoji.toString());
            smile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    inputField.setText(inputField.getText() + emoji.toString());
                }
            });
            smilePanel.add(smile);
        }
        smilePanel.setVisible(false);
        smilePanel.setBounds(425, 319, 150, 150);
        lpane.add(smilePanel, JLayeredPane.PALETTE_LAYER);
        setVisible(true);
    }
}

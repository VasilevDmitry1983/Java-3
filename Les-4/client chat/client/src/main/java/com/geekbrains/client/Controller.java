package com.geekbrains.client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import org.apache.commons.io.input.ReversedLinesFileReader;

public class Controller implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField msgField, loginField;

    @FXML
    HBox msgPanel, authPanel;

    @FXML
    PasswordField passField;

    @FXML
    ListView<String> clientsList;

    private boolean authenticated;
    private String nickname;

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        msgPanel.setVisible(authenticated);
        msgPanel.setManaged(authenticated);
        clientsList.setVisible(authenticated);
        clientsList.setManaged(authenticated);
        if (!authenticated) {
            nickname = "";
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAuthenticated(false);
        clientsList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String nickname = clientsList.getSelectionModel().getSelectedItem();
                msgField.setText("/w " + nickname + " ");
                msgField.requestFocus();
                msgField.selectEnd();
            }
        });



        linkCallbacks();
    }

    public void sendAuth() {
        Network.sendAuth(loginField.getText(), passField.getText());
        loginField.clear();
        passField.clear();
    }

    public void sendMsg() {
        if (Network.sendMsg(msgField.getText())) {
            msgField.clear();
            msgField.requestFocus();
        }
    }

    public void showAlert(String msg) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING, msg, ButtonType.OK);
            alert.showAndWait();
        });
    }

    public void linkCallbacks() {

        Network.setCallOnException(args -> showAlert(args[0].toString()));

        Network.setCallOnCloseConnection(args -> setAuthenticated(false));

        Network.setCallOnAuthenticated(args -> {
            setAuthenticated(true);
            nickname = args[0].toString();


            try {                                  // Задание 3-3-2 . Выводим в чат логи из файла file
                LogsIn();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });



        Network.setCallOnMsgReceived(args -> {
            String msg = args[0].toString();



            if (msg.startsWith("/")) {
                if (msg.startsWith("/clients ")) {
                    String[] tokens = msg.split("\\s");
                    Platform.runLater(() -> {
                        clientsList.getItems().clear();
                        for (int i = 1; i < tokens.length; i++) {
                            clientsList.getItems().add(tokens[i]);
                        }
                    });
                }
            } else {
                textArea.appendText(msg + "\n");
                try {
                    msgOut(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void msgOut(String msg) throws IOException {           // Запись логов в файл file.txt Задание 3-3-1

        PrintWriter pr = new PrintWriter(new FileOutputStream(new File("./file.txt"), true));
        pr.println(msg);
        pr.close();
    }

    public void LogsIn() throws IOException {    //   Чтение логов из файла file. И их вывод в окно чата Задание 3-3-2


        File file = new File("./file.txt");
        int n_lines = 10;
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(file);
        List <String> list = new ArrayList<>();
        while(counter < n_lines) {
            list.add(object.readLine());
            counter++;
        }
        for (int i = list.size()-1; i >= 0 ; i--) {
            textArea.appendText(list.get(i) + "\n");
        }

        


    }
}
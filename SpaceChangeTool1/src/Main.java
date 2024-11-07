import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("スペース変換ツール");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);


        JLabel inputLabel = new JLabel("入力文:");
        inputLabel.setBounds(20, 20, 80, 25);
        frame.add(inputLabel);

        JTextField inputField = new JTextField();
        inputField.setBounds(100, 20, 250, 25);
        frame.add(inputField);

        JLabel outputLabel = new JLabel("変換後:");
        outputLabel.setBounds(20, 60, 80, 25);
        frame.add(outputLabel);

        JTextField outputField = new JTextField();
        outputField.setBounds(100, 60, 250, 25);
        outputField.setEditable(false);  // 出力欄は編集不可にする
        frame.add(outputField);

        JButton convertButton = new JButton("変換");
        convertButton.setBounds(150, 100, 100, 30);
        frame.add(convertButton);


        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 入力フィールドのテキストを取得
                String inputText = inputField.getText();

                // 半角スペースを全角スペースに変換
                String outputText = inputText.replace(" ", "　");

                // 変換結果を出力フィールドに表示
                outputField.setText(outputText);
            }
        });

        //デスクトップ通知
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                String outputText = inputText.replace(" ", "　");
                outputField.setText(outputText);

                // 詳細設定
                if (SystemTray.isSupported()) {
                    SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("n"));

                    TrayIcon trayIcon = new TrayIcon(image, "変換完了");
                    trayIcon.setImageAutoSize(true);
                    trayIcon.setToolTip("スペース変換ツール");

                    try {
                        tray.add(trayIcon);
                        trayIcon.displayMessage("SpaceChangeTool", "正常に実行されました", TrayIcon.MessageType.INFO);
                        tray.remove(trayIcon);  // 通知後にアイコンを削除
                    } catch (AWTException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        frame.setVisible(true);
    }
}
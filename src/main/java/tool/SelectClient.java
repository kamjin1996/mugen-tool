/*
 * Created by JFormDesigner on Fri Mar 13 12:20:53 CST 2020
 */

package tool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.miginfocom.swing.*;

/**
 * @author a
 */
public class SelectClient extends JFrame {


    public SelectClient() {
        initComponents();
    }

    private void fileSelectButtonActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int dialog = chooser.showDialog(new JLabel(), "选择人物包所在文件夹");

        //重置
        if (JFileChooser.CANCEL_OPTION == dialog) {
            selectedPathText.setText("空");
        } else {
            characterParentDir = chooser.getSelectedFile();
            selectedPathText.setText(characterParentDir.getAbsoluteFile().toString());
        }
    }

    private String getChildsText() {
        if (characterParentDir == null || characterParentDir.isFile()) {
            JOptionPane.showMessageDialog(null, "未选中文件夹！");
        }
        File[] files = characterParentDir.listFiles();
        if (files != null && files.length > 0) {
            List<String> collect = Arrays.stream(files)
                    .filter(File::isDirectory)
                    .map(File::getName)
                    .collect(Collectors.toList());
            StringBuilder sb = new StringBuilder();
            collect.forEach(x -> {
                sb.append(x);
                sb.append("\n");
            });
            return sb.toString();
        } else {
            JOptionPane.showMessageDialog(null, "该路径下没有文件夹");
            throw new RuntimeException();
        }
    }

    private void okButtonActionPerformed(ActionEvent e) {
        childsText = this.getChildsText();
        showChildsText.setText(childsText);
    }

    private void importToSelectdefButtonActionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showDialog(new JLabel(), "选中select.def文件");
        selectDefFile = chooser.getSelectedFile();
        if (selectDefFile.isFile() && selectDefFile.getName().equalsIgnoreCase("select.def")) {

            //弹窗确认是否追加到这个select.def下
            int confirmNum = JOptionPane.showConfirmDialog(null, "是否将当前分析的人物追加到" + selectDefFile.getAbsolutePath() + "中？", "提示", JOptionPane.YES_NO_OPTION);
            if (confirmNum == JOptionPane.YES_OPTION) {
                //追加内容到select.def的[character] 标签下
                try {
                    if (childsText == null) {
                        childsText = this.getChildsText();
                    }
                    this.appendToFile(selectDefFile, childsText, "[Characters]");
                    JOptionPane.showMessageDialog(null, "追加成功！");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "追加失败！，请查看文件或内容是否有误");
                }


            }

        } else {
            JOptionPane.showMessageDialog(null, "请选中正确的select.def文件！", "提示", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        selectButton = new JButton();
        scrollPane1 = new JScrollPane();
        selectedPathText = new JTextArea();
        scrollPane2 = new JScrollPane();
        showChildsText = new JTextArea();
        tip1 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();
        importToSelectdefButton = new JButton();

        //======== this ========
        setTitle("mugen\u4eba\u7269\u5305\u6dfb\u52a0\u5de5\u5177");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {

                //---- selectButton ----
                selectButton.setText("\u70b9\u51fb\u9009\u62e9\u4eba\u7269\u5305\u6240\u5728\u8def\u5f84");
                selectButton.addActionListener(e -> fileSelectButtonActionPerformed(e));

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(selectedPathText);
                }

                //======== scrollPane2 ========
                {
                    scrollPane2.setViewportView(showChildsText);
                }

                //---- tip1 ----
                tip1.setText("\u2191 \u8bf7\u624b\u52a8\u9009\u4e2d\u590d\u5236\uff0c\u6216\u8005\u70b9\u51fb\u5bfc\u5165\u5c06\u4eba\u7269\u8ffd\u52a0\u5230select.def\u4e2d");

                //---- label3 ----
                label3.setText("\u5df2\u9009\u62e9\u8def\u5f84\uff1a");

                //---- label4 ----
                label4.setText("\u6216\u8005\u70b9\u51fb\u5bfc\u5165\u6309\u94ae\u9009\u4e2dselect.def\u81ea\u52a8\u5bfc\u5165\u4eba\u7269 \u2193");

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                    .addComponent(tip1, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                                    .addGap(47, 47, 47))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(label3)
                                        .addGroup(contentPanelLayout.createSequentialGroup()
                                            .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(selectButton)))
                                    .addGap(0, 57, Short.MAX_VALUE))))
                        .addGroup(GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addComponent(label4)
                            .addGap(0, 83, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addComponent(label3)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(selectButton))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tip1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                            .addComponent(label4))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                    "insets dialog,alignx right",
                    // columns
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[fill]" +
                    "[button,fill]" +
                    "[button,fill]",
                    // rows
                    "[]" +
                    "[]"));

                //---- okButton ----
                okButton.setText("\u5206\u6790");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, "cell 8 1");

                //---- importToSelectdefButton ----
                importToSelectdefButton.setText("\u5bfc\u5165\u5230select.def");
                importToSelectdefButton.addActionListener(e -> importToSelectdefButtonActionPerformed(e));
                buttonBar.add(importToSelectdefButton, "cell 9 1");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JButton selectButton;
    private JScrollPane scrollPane1;
    private JTextArea selectedPathText;
    private JScrollPane scrollPane2;
    private JTextArea showChildsText;
    private JLabel tip1;
    private JLabel label3;
    private JLabel label4;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton importToSelectdefButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private File characterParentDir;
    private File selectDefFile;
    private String childsText;

    /**
     * 追加内容到文件中
     *
     * @param additionalText 内容
     * @param mark           追加到文件中的某个内容下
     */
    private void appendToFile(File targetFile, String additionalText, String mark) {
        BufferedReader br = null;
        PrintWriter pw = null;
        StringBuffer buff = new StringBuffer();
        String separator = System.getProperty("line.separator");
        try {
            br = new BufferedReader(new FileReader(targetFile));
            String str;
            while ((str = br.readLine()) != null) {
                if (str.equals(mark)) {
                    str = str + separator + additionalText;
                }
                buff.append(str).append(separator);
            }
            pw = new PrintWriter(new FileWriter(targetFile), true);
            pw.println(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (pw != null)
                pw.close();
        }
    }

}

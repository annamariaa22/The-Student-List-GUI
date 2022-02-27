import javax.swing.*;

import poo.util.ExceptieListaGoala;
import poo.util.ExceptieListaPlina;
import poo.util.ListaDeComparable;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class InterfataGrafica extends JFrame {
    private JLabel L_Nume = new JLabel("Nume:");
    private JTextField T_Nume = new JTextField("", 10);
    private JLabel L_Prenume = new JLabel("Prenume:");
    private JTextField T_Prenume = new JTextField("", 10);
    private JLabel L_Prezente = new JLabel("Prezente:");
    private JTextField T_Prezente = new JTextField("", 10);
    private JLabel L_Afisare = new JLabel("Afisare:");
    private JTextArea T_Afisare = new JTextArea("");
    private JButton AdugareStudent = new JButton("Adauga");
    private JButton StergeStudent = new JButton("Sterge");
    private JButton SortareStudent = new JButton("Sorteaza");
    private JButton SalvareLista = new JButton("Salvare");

    ListaDeComparable<Student> lista = new ListaDeComparable<>(6, Student.class);
    String nume;
    String prenume;
    int prezente;

    class AscultatorEvenimenteButon implements ActionListener {
        PrintWriter salvare = null;

        @Override
        public void actionPerformed(ActionEvent e) {
            Student student = new Student();
            if (e.getSource() == AdugareStudent) {
                try {
                    nume = T_Nume.getText();
                    prenume = T_Prenume.getText();
                    prezente = Integer.parseInt(T_Prezente.getText());
                    student.setNume(nume);
                    student.setPrenume(prenume);
                    student.setPrezente(prezente);
                    lista.adaugareElement(student);
                    T_Afisare.setText(lista.afisareElemente());
                    JOptionPane.showMessageDialog(null, "A fost adaugat un student!");

                } catch (ExceptieListaPlina exceptie) {
                    JOptionPane.showMessageDialog(null, "Lista este plina!");
                }

            } else if (e.getSource() == StergeStudent) {
                try {
                    lista.eliminareElement();
                    T_Afisare.setText(lista.afisareElemente());
                    JOptionPane.showMessageDialog(null, "A fost sters un student!");
                } catch (ExceptieListaGoala exceptie) {
                    JOptionPane.showMessageDialog(null, "Nu exista element de sters");
                }

            } else if (e.getSource() == SortareStudent) {
                try {
                    lista.sortareElemente();
                    T_Afisare.setText(lista.afisareElemente());
                    JOptionPane.showMessageDialog(null, "Lista a fost sortata!");
                } catch (ExceptieListaGoala exceptie) {
                    JOptionPane.showMessageDialog(null, "Nu exista elemente de sortat");
                }

            } else {
                try {
                    salvare = new PrintWriter(new FileWriter("Studenti.txt"));
                    salvare.println(lista.afisareElemente());
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (salvare != null) {
                        salvare.close();
                    }

                }
                JOptionPane.showMessageDialog(null, "Lista salvata!");
            }

        }
    }

    private AscultatorEvenimenteButon buton = new AscultatorEvenimenteButon();

    public InterfataGrafica() throws IOException, ExceptieListaPlina {
        BufferedReader citire = null;
        Scanner scanare = null;

        try {
            citire = new BufferedReader(new FileReader("Studenti.txt"));
            String line_fisier;

            while ((line_fisier = citire.readLine()) != null) {
                Student student = new Student();
                scanare = new Scanner(line_fisier);
                nume = scanare.next();
                prenume = scanare.next();
                prezente = scanare.nextInt();
                student.setNume(nume);
                student.setPrenume(prenume);
                student.setPrezente(prezente);
                lista.adaugareElement(student);
            }
            T_Afisare.setText(lista.afisareElemente());

        } catch (ExceptieListaPlina e) {
            T_Afisare.setText(lista.afisareElemente());
            JOptionPane.showMessageDialog(null, "Lista este plina! Apar doar primii 6 studenti");
        } finally {
            if (citire != null) {
                citire.close();
            }
            if (scanare != null) {
                scanare.close();
            }
        }
        L_Nume.setFont(new Font("Arial", Font.ITALIC, 13));
        L_Prenume.setFont(new Font("Arial", Font.ITALIC, 13));
        L_Prezente.setFont(new Font("Arial", Font.ITALIC, 13));
        L_Afisare.setFont(new Font("Arial", Font.ITALIC, 13));
        AdugareStudent.setFont(new Font("Arial", Font.ITALIC, 13));
        StergeStudent.setFont(new Font("Arial", Font.ITALIC, 13));
        SortareStudent.setFont(new Font("Arial", Font.ITALIC, 13));
        SalvareLista.setFont(new Font("Arial", Font.ITALIC, 13));

        AdugareStudent.addActionListener(buton);
        StergeStudent.addActionListener(buton);
        SortareStudent.addActionListener(buton);
        SalvareLista.addActionListener(buton);

        Box bh1 = Box.createHorizontalBox();
        bh1.add(L_Nume);
        bh1.add(Box.createHorizontalStrut(10));
        bh1.add(T_Nume);
        bh1.add(Box.createHorizontalStrut(10));
        bh1.add(L_Prenume);
        bh1.add(Box.createHorizontalStrut(10));
        bh1.add(T_Prenume);
        bh1.add(Box.createHorizontalStrut(10));
        bh1.add(Box.createHorizontalGlue());

        Box bh3 = Box.createHorizontalBox();
        bh3.add(L_Prezente);
        bh3.add(Box.createHorizontalStrut(10));
        bh3.add(T_Prezente);
        bh3.add(Box.createHorizontalStrut(10));
        bh3.add(Box.createHorizontalGlue());

        Box bh4 = Box.createHorizontalBox();
        bh4.add(L_Afisare);
        bh4.add(Box.createHorizontalStrut(10));
        bh4.add(T_Afisare);
        bh3.add(Box.createHorizontalStrut(10));
        bh3.add(Box.createHorizontalGlue());

        Box bv1 = Box.createVerticalBox();
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh1);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh3);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh4);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(Box.createVerticalGlue());

        Box bv2 = Box.createVerticalBox();
        bv2.add(Box.createVerticalStrut(10));
        bv2.add(AdugareStudent);
        bv2.add(Box.createVerticalStrut(10));
        bv2.add(StergeStudent);
        bv2.add(Box.createVerticalStrut(10));
        bv2.add(SortareStudent);
        bv2.add(Box.createVerticalStrut(10));
        bv2.add(SalvareLista);
        bv2.add(Box.createVerticalStrut(10));
        bv2.add(Box.createVerticalGlue());

        Box bh5 = Box.createHorizontalBox();
        bh5.add(Box.createHorizontalStrut(10));
        bh5.add(bv1);
        bh5.add(Box.createHorizontalStrut(10));
        bh5.add(bv2);
        bh5.add(Box.createHorizontalStrut(10));
        bh5.add(Box.createHorizontalGlue());
        Container cp = getContentPane();
        cp.add(BorderLayout.CENTER, bh5);
    }

    public static void main(String[] args)
            throws ExceptieListaGoala, ExceptieListaPlina, IOException, NegativeArraySizeException {

        try {
            InterfataGrafica Catalog = new InterfataGrafica();
            Catalog.setTitle("Lista studentilor");
            Catalog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Catalog.setSize(500, 350);
            Catalog.setVisible(true);
            Catalog.getContentPane().setBackground(new Color(132, 201, 247));
        } catch (NegativeArraySizeException e) {
            JOptionPane.showMessageDialog(null, "Dimensiune negativa");
        }

    }
}

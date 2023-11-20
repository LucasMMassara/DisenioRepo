package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import dto.ClienteDTO;
import logica.TipoDocumento;

public class PanelCliente extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean selected = false;

    PanelCliente() {

        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 50));
        this.setBackground(Color.GRAY);

        JPanel nroCliente = new JPanel(new GridBagLayout());
        nroCliente.setPreferredSize(new Dimension(100, 10));
        JPanel apellido = new JPanel(new GridBagLayout());
        apellido.setPreferredSize(new Dimension(100, 30));
        JPanel nombre = new JPanel(new GridBagLayout());
        nombre.setPreferredSize(new Dimension(100, 30));
        JPanel tipoDoc = new JPanel(new GridBagLayout());
        tipoDoc.setPreferredSize(new Dimension(100, 10));
        JPanel nroDoc = new JPanel(new GridBagLayout());
        nroDoc.setPreferredSize(new Dimension(100, 40));

        PanelText nroClienteT = new PanelText("Nro. Cliente", "BOLD", 16, "WEST");
        PanelText apellidoT = new PanelText("Apellido", "BOLD", 16, "WEST");
        PanelText nombreT = new PanelText("Nombre", "BOLD", 16, "WEST");
        PanelText tipoDocT = new PanelText("Tipo doc.", "BOLD", 16, "WEST");
        PanelText nroDocT = new PanelText("Nro. Documento", "BOLD", 16, "WEST");

        nroCliente.setBackground(new Color(200, 200, 200));
        apellido.setBackground(new Color(200, 200, 200));
        nombre.setBackground(new Color(200, 200, 200));
        tipoDoc.setBackground(new Color(200, 200, 200));
        nroDoc.setBackground(new Color(200, 200, 200));
        nroClienteT.setBackgroundColor(200, 200, 200);
        apellidoT.setBackgroundColor(200, 200, 200);
        nombreT.setBackgroundColor(200, 200, 200);
        tipoDocT.setBackgroundColor(200, 200, 200);
        nroDocT.setBackgroundColor(200, 200, 200);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.anchor = GridBagConstraints.WEST; // Anchor the components to the left
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 0;

        nroCliente.add(nroClienteT, gbc2);
        apellido.add(apellidoT, gbc2);
        nombre.add(nombreT, gbc2);
        tipoDoc.add(tipoDocT, gbc2);
        nroDoc.add(nroDocT, gbc2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 2, 1);
        this.add(nroCliente, gbc);

        gbc.weightx = 0.35;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 1, 2, 1);
        this.add(apellido, gbc);

        gbc.weightx = 0.3;
        gbc.gridx = 2;
        this.add(nombre, gbc);

        gbc.weightx = 0.05;
        gbc.gridx = 3;
        this.add(tipoDoc, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 4;
        gbc.insets = new Insets(0, 1, 2, 0);
        this.add(nroDoc, gbc);

    }

    PanelCliente(BuscarCliente panel, int a, ClienteDTO cliente) {

        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 50));
        this.setBackground(Color.GRAY);

        JPanel nroCliente = new JPanel(new GridBagLayout());
        nroCliente.setPreferredSize(new Dimension(100, 10));
        JPanel apellido = new JPanel(new GridBagLayout());
        apellido.setPreferredSize(new Dimension(100, 30));
        JPanel nombre = new JPanel(new GridBagLayout());
        nombre.setPreferredSize(new Dimension(100, 30));
        JPanel tipoDoc = new JPanel(new GridBagLayout());
        tipoDoc.setPreferredSize(new Dimension(100, 10));
        JPanel nroDoc = new JPanel(new GridBagLayout());
        nroDoc.setPreferredSize(new Dimension(100, 40));

        PanelText nroClienteT = new PanelText(cliente.getNumCliente(), "BOLD", 16, "WEST");
        PanelText apellidoT = new PanelText(cliente.getApellido(), "BOLD", 16, "WEST");
        PanelText nombreT = new PanelText(cliente.getNombre(), "BOLD", 16, "WEST");

        String tipoDocumento = cliente.getTipoDocumento();

        PanelText tipoDocT = new PanelText(tipoDocumento, "BOLD", 16, "WEST");
        PanelText nroDocT = new PanelText(cliente.getNumDocumento(), "BOLD", 16, "WEST");

        if (a % 2 == 1) {
            nroCliente.setBackground(Color.WHITE);
            apellido.setBackground(Color.WHITE);
            nombre.setBackground(Color.WHITE);
            tipoDoc.setBackground(Color.WHITE);
            nroDoc.setBackground(Color.WHITE);
            nroClienteT.setBackgroundColor(255, 255, 255);
            apellidoT.setBackgroundColor(255, 255, 255);
            nombreT.setBackgroundColor(255, 255, 255);
            tipoDocT.setBackgroundColor(255, 255, 255);
            nroDocT.setBackgroundColor(255, 255, 255);
        }

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.anchor = GridBagConstraints.WEST; // Anchor the components to the left
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 0;

        nroCliente.add(nroClienteT, gbc2);
        apellido.add(apellidoT, gbc2);
        nombre.add(nombreT, gbc2);
        tipoDoc.add(tipoDocT, gbc2);
        nroDoc.add(nroDocT, gbc2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 1);
        this.add(nroCliente, gbc);

        gbc.weightx = 0.35;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 1, 0, 1);
        this.add(apellido, gbc);

        gbc.weightx = 0.3;
        gbc.gridx = 2;
        this.add(nombre, gbc);

        gbc.weightx = 0.05;
        gbc.gridx = 3;
        this.add(tipoDoc, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 4;
        gbc.insets = new Insets(0, 1, 0, 0);
        this.add(nroDoc, gbc);

        addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!panel.isSelected() || selected) {
                    selected = !selected; // Toggle the selection state
                    panel.setSelected(selected);
                    enviarCliente(panel);
                    repaint(); // Redraw the panel to reflect the selection state
                }
            }
        });

    }

    PanelCliente(BuscarCliente panel, int a) {

        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(200, 50));
        this.setBackground(Color.GRAY);

        JPanel nroCliente = new JPanel(new GridBagLayout());
        nroCliente.setPreferredSize(new Dimension(100, 10));
        JPanel apellido = new JPanel(new GridBagLayout());
        apellido.setPreferredSize(new Dimension(100, 30));
        JPanel nombre = new JPanel(new GridBagLayout());
        nombre.setPreferredSize(new Dimension(100, 30));
        JPanel tipoDoc = new JPanel(new GridBagLayout());
        tipoDoc.setPreferredSize(new Dimension(100, 10));
        JPanel nroDoc = new JPanel(new GridBagLayout());
        nroDoc.setPreferredSize(new Dimension(100, 40));

        String placeholder = "placeholder";

        PanelText nroClienteT = new PanelText(placeholder, "BOLD", 16, "WEST");
        PanelText apellidoT = new PanelText(placeholder, "BOLD", 16, "WEST");
        PanelText nombreT = new PanelText(placeholder, "BOLD", 16, "WEST");
        PanelText tipoDocT = new PanelText(placeholder, "BOLD", 16, "WEST");
        PanelText nroDocT = new PanelText(placeholder, "BOLD", 16, "WEST");

        if (a % 2 == 1) {
            nroCliente.setBackground(Color.WHITE);
            apellido.setBackground(Color.WHITE);
            nombre.setBackground(Color.WHITE);
            tipoDoc.setBackground(Color.WHITE);
            nroDoc.setBackground(Color.WHITE);
            nroClienteT.setBackgroundColor(255, 255, 255);
            apellidoT.setBackgroundColor(255, 255, 255);
            nombreT.setBackgroundColor(255, 255, 255);
            tipoDocT.setBackgroundColor(255, 255, 255);
            nroDocT.setBackgroundColor(255, 255, 255);
        }

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.NONE;
        gbc2.anchor = GridBagConstraints.WEST; // Anchor the components to the left
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.weightx = 0;

        nroCliente.add(nroClienteT, gbc2);
        apellido.add(apellidoT, gbc2);
        nombre.add(nombreT, gbc2);
        tipoDoc.add(tipoDocT, gbc2);
        nroDoc.add(nroDocT, gbc2);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0, 0, 0, 1);
        this.add(nroCliente, gbc);

        gbc.weightx = 0.35;
        gbc.gridx = 1;
        gbc.insets = new Insets(0, 1, 0, 1);
        this.add(apellido, gbc);

        gbc.weightx = 0.3;
        gbc.gridx = 2;
        this.add(nombre, gbc);

        gbc.weightx = 0.05;
        gbc.gridx = 3;
        this.add(tipoDoc, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 4;
        gbc.insets = new Insets(0, 1, 0, 0);
        this.add(nroDoc, gbc);

        addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (!panel.isSelected() || selected) {
                    selected = !selected; // Toggle the selection state
                    panel.setSelected(selected);
                    enviarCliente(panel);
                    repaint(); // Redraw the panel to reflect the selection state
                }
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color borderColor = Color.BLUE;
        int borderThickness = 2;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        LineBorder noBorder = new LineBorder(borderColor, 0);

        if (selected) {
            this.setBorder(customBorder);
            // g.setColor(Color.BLUE); // Change the background color when selected
        } else {
            this.setBorder(noBorder);
            // g.setColor(Color.GRAY);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    void enviarCliente(BuscarCliente panel) {

        panel.setCliente(this);

        return;
    }

    void setSelected(Boolean bool) {
        selected = bool;
    }

}

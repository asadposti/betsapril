package gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;



/**
 * Predefined JButton class for the buttons that go in the left menu
 */
public class MenuButton extends JButton{
	
	private static final long serialVersionUID = 1L;

	public MenuButton(String text, ImageIcon icon) {
		this.setText(text);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("Source Code Pro Medium", Font.BOLD, 16));
		setFocusPainted(false);
		setBorderPainted(false);
		setIcon(icon);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(51, 51, 51));
		addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(new Color(128,128,128));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				if(isSelected()) {
					setBackground(new Color(105, 105, 105));
				}
				else {
					setBackground(new Color(51, 51, 51));
				}
			}
		});
		
	}
	
	
}

package gui.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Extended Jtextfield with the possibility of displaying hint text
 *
 */
public class HintTextField extends JTextField implements FocusListener {
	private static final long serialVersionUID = 1L;

	private final String hint;
	private boolean showingHint;

	public HintTextField(final String hint) { 
		super(hint);
		this.hint = hint;
		this.setBackground(new Color(0,0,0));
		this.showingHint = true;
		super.addFocusListener(this);
		this.setForeground(new Color(169, 169, 169));
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(this.getText().isEmpty()) {
			super.setText("");
			showingHint = false;
			this.setForeground(new Color(255,255, 255));
			this.setCaretColor(new Color(255,255,255));
		}
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(this.getText().isEmpty()) {
			super.setText(hint);
			showingHint = true;
			this.setForeground(new Color(169, 169, 169));
		}
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}
}
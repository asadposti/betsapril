package gui.Panels;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class FeedbackPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public FeedbackPanel() {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("", "[][117.00][550.00][][]", "[][][][24.00][35.00][395.00][]"));
		
		JLabel lblFeedback = new JLabel("Feedback");
		lblFeedback.setFont(new Font("Source Code Pro ExtraLight", Font.BOLD, 25));
		add(lblFeedback, "cell 1 1 3 1");

	}

}

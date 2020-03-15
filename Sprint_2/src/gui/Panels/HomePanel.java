package gui.Panels;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class HomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 156, 10, 104, 92, 40, 113, 0, 0, 30, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 33, 30, 20, 0, 0, 30, 0, 0, 30, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel logoLabel = new JLabel("");
		GridBagConstraints gbc_logoLabel = new GridBagConstraints();
		gbc_logoLabel.gridheight = 3;
		gbc_logoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_logoLabel.gridx = 1;
		gbc_logoLabel.gridy = 1;
		add(logoLabel, gbc_logoLabel);
		logoLabel.setIcon(new ImageIcon("images/beticon.png"));
		
		JLabel lblBrand = new JLabel("BET & RUIN\r\n");
		lblBrand.setFont(new Font("Source Sans Pro Black", Font.BOLD, 25));
		GridBagConstraints gbc_lblBrand = new GridBagConstraints();
		gbc_lblBrand.gridheight = 2;
		gbc_lblBrand.anchor = GridBagConstraints.WEST;
		gbc_lblBrand.gridwidth = 2;
		gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrand.gridx = 2;
		gbc_lblBrand.gridy = 2;
		add(lblBrand, gbc_lblBrand);
		
		JLabel lblLive = new JLabel("Live:");
		lblLive.setFont(new Font("Source Code Pro Black", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLive = new GridBagConstraints();
		gbc_lblLive.anchor = GridBagConstraints.WEST;
		gbc_lblLive.insets = new Insets(0, 0, 5, 5);
		gbc_lblLive.gridx = 1;
		gbc_lblLive.gridy = 5;
		add(lblLive, gbc_lblLive);
		
		JLabel lblNews = new JLabel("News:");
		lblNews.setFont(new Font("Source Code Pro Black", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNews = new GridBagConstraints();
		gbc_lblNews.anchor = GridBagConstraints.WEST;
		gbc_lblNews.insets = new Insets(0, 0, 5, 5);
		gbc_lblNews.gridx = 6;
		gbc_lblNews.gridy = 5;
		add(lblNews, gbc_lblNews);
		
		JPanel panelLive = new JPanel();
		GridBagConstraints gbc_panelLive = new GridBagConstraints();
		gbc_panelLive.gridwidth = 4;
		gbc_panelLive.insets = new Insets(0, 0, 5, 5);
		gbc_panelLive.fill = GridBagConstraints.BOTH;
		gbc_panelLive.gridx = 1;
		gbc_panelLive.gridy = 6;
		add(panelLive, gbc_panelLive);
		
		JPanel panelNews = new JPanel();
		GridBagConstraints gbc_panelNews = new GridBagConstraints();
		gbc_panelNews.gridwidth = 3;
		gbc_panelNews.insets = new Insets(0, 0, 5, 5);
		gbc_panelNews.fill = GridBagConstraints.BOTH;
		gbc_panelNews.gridx = 6;
		gbc_panelNews.gridy = 6;
		add(panelNews, gbc_panelNews);
		
		JLabel lblUpcomingEvents = new JLabel("Upcoming events:");
		lblUpcomingEvents.setFont(new Font("Source Code Pro Black", Font.PLAIN, 14));
		lblUpcomingEvents.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblUpcomingEvents = new GridBagConstraints();
		gbc_lblUpcomingEvents.anchor = GridBagConstraints.WEST;
		gbc_lblUpcomingEvents.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpcomingEvents.gridx = 1;
		gbc_lblUpcomingEvents.gridy = 8;
		add(lblUpcomingEvents, gbc_lblUpcomingEvents);
		
		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 14));
		GridBagConstraints gbc_lblResults = new GridBagConstraints();
		gbc_lblResults.anchor = GridBagConstraints.WEST;
		gbc_lblResults.insets = new Insets(0, 0, 5, 5);
		gbc_lblResults.gridx = 6;
		gbc_lblResults.gridy = 8;
		add(lblResults, gbc_lblResults);
		
		JPanel panelUpcEvents = new JPanel();
		GridBagConstraints gbc_panelUpcEvents = new GridBagConstraints();
		gbc_panelUpcEvents.gridwidth = 4;
		gbc_panelUpcEvents.insets = new Insets(0, 0, 5, 5);
		gbc_panelUpcEvents.fill = GridBagConstraints.BOTH;
		gbc_panelUpcEvents.gridx = 1;
		gbc_panelUpcEvents.gridy = 9;
		add(panelUpcEvents, gbc_panelUpcEvents);
		
		JPanel panelResults = new JPanel();
		GridBagConstraints gbc_panelResults = new GridBagConstraints();
		gbc_panelResults.gridwidth = 3;
		gbc_panelResults.insets = new Insets(0, 0, 5, 5);
		gbc_panelResults.fill = GridBagConstraints.BOTH;
		gbc_panelResults.gridx = 6;
		gbc_panelResults.gridy = 9;
		add(panelResults, gbc_panelResults);

	}

}

package gui.components;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import domain.BetType;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class multibetOption extends JPanel {
	
	private JNumericField stakeField;
	
	private float stake;
	private int combinations;
	private BetType type;	

	/**
	 * Create the panel.
	 */
	public multibetOption( BetType type, int combinations) {
		this.type = type;
		setBackground(new Color(240, 255, 255));
		setLayout(new MigLayout("", "[195.00px][50px][67.00px]", "[26px]"));
		JLabel typeLabel = new JLabel(type.name());
		typeLabel.setFont(new Font("Source Sans Pro", Font.PLAIN, 14));
		add(typeLabel, "cell 0 0,grow");
		
		stakeField = new JNumericField(7, JNumericField.DECIMAL);
		stakeField.setPrecision(2);
		stakeField.setAllowNegative(false);
		add(stakeField, "cell 2 0,grow");
		stakeField.setColumns(10);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		stakeField.getDocument().addDocumentListener(new stakeListener());
		
		JLabel combinationsLabel = new JLabel("x" + combinations);
		combinationsLabel.setFont(new Font("Source Sans Pro", Font.PLAIN, 15));
		combinationsLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		add(combinationsLabel, "cell 1 0,grow");

	}

	public float getStake() {
		return stake;
	}


	public void setStake(float stake) {
		this.stake = stake;
	}

	
	public JTextField getBetField() {
		return stakeField;
	}


	public void setBetField(JNumericField betField) {
		this.stakeField = betField;
	}


	public int getCombinations() {
		return combinations;
	}


	public void setCombinations(int combinations) {
		this.combinations = combinations;
	}
	
	
	public BetType getType() {
		return type;
	}


	public void setType(BetType type) {
		this.type = type;
	}
	
	@Override
	public Dimension getPreferredSize()
	{		
		Dimension d = new Dimension(getParent().getWidth(), 36); 
		return d;
	}
	
	@Override
	public Dimension getMaximumSize()
	{		
		Dimension d = new Dimension(getParent().getWidth(), 36);
		return d;
	}
	
	public class stakeListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			stake = stakeField.getFloat();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			stake = stakeField.getFloat();
			
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			stake = stakeField.getFloat();
			
		}
		
		
	}
}

package fxopintoloki;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Luokka hoitaa tulostuksen
 * @author Olli Mehtonen ja Justus Uurtimo
 * @version 3.5.2019
 */
public class TulostusController implements ModalControllerInterface<String> {

	@FXML TextArea tulostusAlue;
	
	/**
	 * @return palauttaa teksti alueen
	 */
	public TextArea getTextArea() {
		return tulostusAlue;
	}
	
	
	/**
	 * toteaa ettei osata tulostaa
	 */
	@FXML void tulostus() {
		Dialogs.showMessageDialog("Ei osata viel‰ tulostaa :(");
	}
	
	
	/**
	 * N‰ytt‰‰ tulostusalueesta tekstin
	 * @param tulostus tulostettava teksti
	 * @return palauttaa kontrollerin jolta pyydet‰‰n lis‰‰ tietoa.
	 */
	public static TulostusController tulosta(String tulostus) {
		TulostusController tulostusCtrl = ModalController.showModeless(TulostusController.class.getResource("opintolokiTulosta.fxml"), "Tulostus", tulostus);
		
		return tulostusCtrl;
	}
	


	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void handleShown() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * asettaa defaultin tekstin
	 */
	@Override
	public void setDefault(String arg0) {
		// TODO Auto-generated method stub
		tulostusAlue.setText(arg0);
	}
}
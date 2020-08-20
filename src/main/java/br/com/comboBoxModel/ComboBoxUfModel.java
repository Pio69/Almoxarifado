package br.com.comboBoxModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import br.com.dao.GenericDao;
import br.com.model.UnidadeFederativa;

public class ComboBoxUfModel extends AbstractListModel implements ComboBoxModel {

	private List<UnidadeFederativa> unidadeFederativas = new ArrayList<>();
	private UnidadeFederativa unidadeFederativa;
	
	public ComboBoxUfModel() {
		GenericDao genericDAO = new GenericDao();
		unidadeFederativas = (List<UnidadeFederativa>) genericDAO.select(new UnidadeFederativa());
	}

	@Override
	public Object getElementAt(int index) {
		//Retorna o elemento do index que retorna a lista (Linha Selecionada)
		return this.unidadeFederativas.get(index);
	}

	@Override
	public int getSize() {
		//Retorna a quantia de elementos no comboBox
		return this.unidadeFederativas.size();
	}

	@Override
	public Object getSelectedItem() {
		//Retorna o item selecionado
		if(unidadeFederativas.isEmpty()) {
			return null;
		} else {
			return this.unidadeFederativa;
		}
	}

	@Override
	public void setSelectedItem(Object comboBox) {
		//Informa o item selecionado
		if (comboBox instanceof UnidadeFederativa) {
			this.unidadeFederativa = (UnidadeFederativa) comboBox;
		}
		
		//Atualiza o comboBox
		fireContentsChanged(this.unidadeFederativas, 0, this.unidadeFederativas.size());
	}

}

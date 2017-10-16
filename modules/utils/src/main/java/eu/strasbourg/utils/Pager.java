package eu.strasbourg.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe calculant les informations nécessaires afin de gérer la pagination
 * d'une liste d'éléments
 * 
 * @author Benjamin Bini
 * 
 */
public class Pager {

	/*
	 * Nombre total d'items
	 */
	private int count;

	/*
	 * Nombre d'items par page
	 */
	private int delta;

	/*
	 * Index de la page courante (première page = 1)
	 */
	private int currentPage;

	/*
	 * Index de la page de fin
	 */
	private int lastPage;

	/*
	 * Constructeur initialisant les données nécessaires
	 */
	public Pager(int count, int delta, int currentPage) {
		this.count = count;
		this.delta = delta;
		this.currentPage = currentPage;
		
		this.lastPage = count / delta;
		if (count % delta > 0) {
			this.lastPage++;
		}
	}
	
	/**
	 * Retourne true si on est sur la première page
	 */
	public boolean isOnFirstPage() {
		return this.currentPage == 1;
	}

	
	/**
	 * Retourne true si on est sur la dernière page
	 */
	public boolean isOnLastPage() {
		return this.lastPage == this.currentPage;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	
	public List<PagerItem> getPages() {
		List<PagerItem> pagerItems = new ArrayList<PagerItem>();
		for (int i = 1; i <= this.lastPage; i++) {
			pagerItems.add(new PagerItem(i, String.valueOf(i)));
		}
		return pagerItems;
	}
	
	public class PagerItem {
		private int index;
		private String label;
		
		public PagerItem(int index, String label) {
			this.setIndex(index);
			this.setLabel(label);
		}
		
		public boolean isALink() {
			return index > 0;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

}
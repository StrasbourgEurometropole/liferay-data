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
	 * Retourne true si la page courante est la première page
	 */
	public boolean isOnFirstPage() {
		return this.currentPage == 1;
	}

	/**
	 * Retourne true si la page courante est la dernière page
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
	
	/**
	 * Retourne la liste des PagerItem à afficher, soit des chiffres correspondant à des numéros de pages, soit des séparateurs "..."
	 */
	public List<PagerItem> getPages() {
		List<PagerItem> pagerItems = new ArrayList<PagerItem>();
		List<Integer> pages = new ArrayList<Integer>();
		
		for (int i = 1; i <= this.lastPage; i++) {
			if (i == 1 || i == this.lastPage || isPageCloseToCurrentPage(i)) {
				pages.add(i);
			}
		}
		
		int previousPage = -1;
		for (int i = 0; i < pages.size(); i++) {
			int page = pages.get(i);
			if (previousPage > 0) {
				if (page - previousPage == 2) {
					pagerItems.add(new PagerItem(page - 1, String.valueOf(page - 1)));
				} else if (page - previousPage > 2) {
					pagerItems.add(new PagerItem(0, "..."));
				}
			}
			
			pagerItems.add(new PagerItem(page, String.valueOf(page)));
			previousPage = page;
		}
		return pagerItems;
	}
	
	private boolean isPageCloseToCurrentPage(int pageIndex) {
		return Math.abs(pageIndex - this.currentPage) <= 2;
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
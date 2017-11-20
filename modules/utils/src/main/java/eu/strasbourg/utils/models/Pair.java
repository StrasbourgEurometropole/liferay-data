package eu.strasbourg.utils.models;

public class Pair<T, U> {
	private T first;
	private U second;

	private Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	public static <T, U> Pair<T, U> of(T first, U second) {
		return new Pair<T, U>(first, second);
	}

	public void setFirst(T first) {
		this.first = first;
	}

	public void setSecond(U second) {
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public U getSecond() {
		return second;
	}
}
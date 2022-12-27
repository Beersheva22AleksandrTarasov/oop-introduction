package telran.util.test;

import java.util.function.Predicate;

public class DividorPredicate implements Predicate<Integer> {
	
	int dividor;

	@Override
	public boolean test(Integer t) {

		return t % dividor == 0;
	}

	public DividorPredicate(int dividor) {
		this.dividor = dividor;
	}

}
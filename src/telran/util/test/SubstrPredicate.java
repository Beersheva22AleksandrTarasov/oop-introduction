package telran.util.test;

import java.util.function.Predicate;

public class SubstrPredicate implements Predicate<String> {

	String substr;

	@Override
	public boolean test(String t) {

		return t.contains(substr);
	}

	public SubstrPredicate(String substr) {
		this.substr = substr;
	}

}

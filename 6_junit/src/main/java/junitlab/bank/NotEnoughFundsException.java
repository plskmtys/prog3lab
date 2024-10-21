package junitlab.bank;

/**
 * Ez a kiv�tel jelzi, ha �rv�nytelen vagy nem l�tez� banksz�mlasz�mot
 * adtunk meg valamelyik tranzakci� sor�n.
 */
public class NotEnoughFundsException extends BankException {

	/**
	 * A kiv�tel l�trehoz�sa.
	 * @param accountNumber A sz�mlasz�m, melyen alacsony az egyenleg.
	 */
	public NotEnoughFundsException(String accountNumber) {
		super(accountNumber, "Not enough funds on account " + accountNumber);
	}
}

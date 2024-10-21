package junitlab.bank;

/**
 * Egy kezdetleges takar�ksz�vetkezet interf�sze.
 * Alapvet� sz�mlakezel�si m�veleteket defini�l.  
 */
public interface Bank {

    /**
     * Egy �j sz�mla megnyit�sa. A sz�ml�n kezdetben nulla az egyenleg.
     * @return A frissen l�trej�tt sz�mla sz�ma.
     */
    public String openAccount();

    /**
     * �res sz�mla megsz�ntet�se.
     * @param accountNumber A megsz�ntetend� sz�mlasz�m.
     * @return Siker�lt-e elv�gezni a m�veletet. Ha a sz�mla egyenlege nem nulla, nem lehet megsz�ntetni.
     * @throws AccountNotExistsException Ha a megadott sz�mlasz�m nem l�tezik.
     */
    public boolean closeAccount(String accountNumber) throws AccountNotExistsException;

    /**
     * Az aktu�lis egyenleg lek�rdez�se.
     * @param accountNumber A k�rd�ses sz�mlasz�m.
     * @return Az egyenleg.
     * @throws AccountNotExistsException Ha a megadott sz�mlasz�m nem l�tezik.
     */
    public long getBalance(String accountNumber) throws AccountNotExistsException;

    /**
     * Egy adott �sszeg befizet�se a sz�ml�ra. Technikai okokb�l csak 100-zal
     * oszthat� �sszegeket lehet befizetni a sz�ml�ra. Az ett�l elt�r� �sszegeket
     * a kerek�t�s szab�lyai szerint kell kezelni.
     * @param accountNumber A felt�ltend� sz�mlasz�m.
     * @param amount A befizetend� �sszeg. Mindig pozit�vnak kell lennie.
     * @throws AccountNotExistsException Ha a megadott sz�mlasz�m nem l�tezik.
     */
    public void deposit(String accountNumber, long amount) throws AccountNotExistsException;

    /**
     * Egy adott �sszeg kiv�tele a sz�ml�r�l. Technikai okokb�l csak 100-zal
     * oszthat� �sszegeket lehet kifizetni a sz�ml�r�l. Az ett�l elt�r� �sszegeket
     * a kerek�t�s szab�lyai szerint kell kezelni.
     * @param accountNumber A terhelend� sz�mlasz�m.
     * @param amount A kifizetend� �sszeg. Mindig pozit�vnak kell lennie.
     * @throws AccountNotExistsException Ha a megadott sz�mlasz�m nem l�tezik.
     * @throws NotEnoughFundsException Ha kevesebb p�nz van a sz�ml�n, mint amit megadtunk.
     */
    public void withdraw(String accountNumber, long amount) throws AccountNotExistsException, NotEnoughFundsException;

    /**
     * Egy adott �sszeg �tutal�sa egyik sz�ml�r�l a m�sikra. Az �tutal�sokra
     * nem vonatkozik a sz�zas kerek�t�si szab�ly, tetsz�leges �sszeg �tutalhat�.
     * @param sourceAccount A terhelend� sz�mlasz�m.
     * @param targetAccount A c�l sz�mlasz�m, amire utalunk.
     * @param amount A kifizetend� �sszeg. Mindig pozit�vnak kell lennie.
     * @throws AccountNotExistsException Ha a megadott sz�mlasz�m nem l�tezik.
     * @throws NotEnoughFundsException Ha kevesebb p�nz van a forr�ssz�ml�n, mint amit �t akarunk utalni.
     */
    public void transfer(String sourceAccount, String targetAccount, long amount) throws AccountNotExistsException, NotEnoughFundsException;
}

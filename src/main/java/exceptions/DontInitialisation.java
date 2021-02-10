package exceptions;
/**
 * If consumers have don't initialization fields.
 *
 * @author Ruslan Pipan
 * @version 1.0
 * */
public class DontInitialisation extends Exception {
    public DontInitialisation(String ms) {
        super(ms);
    }
}

package dnds.model.items;

/**
 *
 * @author Brandon Pozil
 */
public enum DamageAttackRoll {
    ONED4("1D4"),
    TWOD4("2D4"),
    ONED6("1D6"),
    TWOD6("2D6"),
    ONED8("1D8"),
    ONED10("1D10"),
    ONED12("1D12");
    private String damageAttackRoll;

    @Override
    public String toString() {
        return damageAttackRoll;
    }

    private DamageAttackRoll(String _damageAttackRoll) {
        this.damageAttackRoll = _damageAttackRoll;
    }

}

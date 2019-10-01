package dnds.model.items;

/**
 *
 * @author Brandon Pozil
 */
public enum DamageAttackRoll {
    1D4("1D4"),
    2D4("2D4"),
    1D6("1D6"),
    2D6("2D6"),
    1D8("1D8"),
    1D10("1D10"),
    1D12("1D12");
    private String damageAttackRoll;

/**
 * Returns the type of weapon.
 * @return The type of weapon.
 */
    @Override
    public String toString() {
        return damageAttackRoll;
    }

    private DamageAttackRoll(String _damageAttackRoll) {
        this.damageAttackRoll = _damageAttackRoll;
    }

}

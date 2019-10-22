package model.items;

/**
 *
 * @author Brandon Pozil
 */
public enum DamageType {
    BLUDGE("Bludeoning"),
    PIERCE("Piercing"),
    SLASH("Slashing");
    private String damageType;

    @Override
    public String toString() {
        return damageType;
    }

    private DamageType(String _damageType) {
        this.damageType = _damageType;
    }

}

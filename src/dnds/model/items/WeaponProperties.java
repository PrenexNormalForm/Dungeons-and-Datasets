/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dnds.model.items;

/**
 *
 * @author Brandon Pozil
 */
public enum WeaponProperties {
    LIGHT("Light"),
    HEAVY("Heavy"),
    FINESSE("Finesse"),
    VERSATILE("Versatile"),
    TWO_HANDED("Two-Handed"),
    ONE_HANDED("One-Handed");
    private String weaponProperties;

    @Override
    public String toString() {
        return weaponProperties;
    }

    private WeaponProperties(String _weaponProperties) {
        this.weaponProperties = weaponProperties;
    }
}

package items;

 public class Weapon extends Item {

        private String[] weaponType = {"Simple Melee Weapon", "Simple Ranged Weapon", "Martial Melee Weapon", "Martial Ranged Weapon"};
        private String[] damageAttackRoll = {"1d4", "2d4", "1d6", "2d6", "1d8", "1d10", "1d12"};
        private String[] damageType = {"Bludgeoning", "Piercing", "Slashing"};
        private String[] weaponProperties = {"Light", "Heavy", "Finesse", "Versatile", "Two-Handed", "One-Handed"};

        public Weapon(int _itemCost, int _itemWeight, String _weaponType, String _damageAttackRoll, String _damageType, String _weaponProperties ) {
            super(_itemCost, _itemWeight);
            this.weaponType = weaponType;
            this.damageAttackRoll = damageAttackRoll;
            this.damageType = damageType;
            this.weaponProperties = weaponProperties;
        }
 }

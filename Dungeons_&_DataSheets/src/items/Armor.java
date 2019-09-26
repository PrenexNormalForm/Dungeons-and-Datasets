package DandD.items;

    public class Armor extends Item {

        private int armorClass;
        public int highestArmorClass = 29;

        public Armor(int _itemCost, int _itemWeight, int _armorClass) {
            super(_itemCost, _itemWeight);
            this.armorClass = armorClass;
        }

    }

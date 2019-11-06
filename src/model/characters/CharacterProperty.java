package model.characters;

/**
 *
 * @author Eva Moniz
 */
public enum CharacterProperty {
    NAME("name", String.class),
    LEVEL("level", int.class),
    CLASS("characterClass", CharacterClass.class),
    STRENGTH("strength", int.class),
    DEXTERITY("dex", int.class),
    CONSTITUTION("constitution", int.class),
    INTELLIGENCE("intelligence", int.class),
    WISDOM("wisdom", int.class),
    CHARISMA("charisma", int.class);

    private final String fieldName;
    private final Class type;

    public String getGetterName() {
        return "get" + this.fieldName;
    }

    public String getSetterName() {
        return "set" + this.fieldName;
    }

    public Class getType() {
        return this.type;
    }

    private CharacterProperty(String _fieldName, Class _type) {
        //Store fieldname with first letter capitalized as they are in getters
        //and setters.
        this.fieldName = _fieldName.substring(0, 1).toUpperCase()
                + _fieldName.substring(1);

        this.type = _type;
    }
}

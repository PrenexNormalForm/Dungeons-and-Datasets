package model.characters;
/*
Last updated November 5, 2019.

Represents the configurable properties of a character object. These are
associated with Characters fields that have getters and setters.

Contributors:
Eva Moniz
 */

/**
 * Represents the configurable properties of a character object. These are
 * associated with Characters fields that have getters and setters. This is
 * especially important in separating the abstract notion of a character from
 * the implementation details of the model. This class is mainly useful for
 * communication of character information between the model and the view. It
 * allows for the view to have some notion of the properties that the user can
 * modify in a character, without exposing the implementation to the view.
 *
 * @author Eva Moniz
 */
public enum CharacterProperty {
    NAME("name", String.class),
    RACE("race", String.class),
    ALIGN("align", String.class),
    BACKSTORY("backstory", String.class),
    INVENTORY("inventory", String.class),
    LEVEL("level", int.class),
    CLASS("characterClass", CharacterClass.class),
    STRENGTH("strength", int.class),
    DEXTERITY("dex", int.class),
    CONSTITUTION("constitution", int.class),
    INTELLIGENCE("intelligence", int.class),
    WISDOM("wisdom", int.class),
    CHARISMA("charisma", int.class);

    /**
     * The name of the field within the Characters class that corresponds to
     * this property.
     */
    private final String fieldName;
    /**
     * The Java type of the property.
     */
    private final Class type;

    /**
     * Returns the name of the getter method of this field in the Characters
     * class.
     *
     * @return The name of the get method
     */
    public String getGetterName() {
        return "get" + this.fieldName;
    }

    /**
     * Returns the name of the setter method of this field in the Characters
     * class.
     *
     * @return The name of the set method
     */
    public String getSetterName() {
        return "set" + this.fieldName;
    }

    /**
     * Returns the Java type of the property
     *
     * @return The Java type of the property
     */
    public Class getType() {
        return this.type;
    }

    /**
     * Private enum constructor.
     *
     * @param _fieldName The name of the Characters field associated with the
     * property.
     * @param _type The Java type of the property
     */
    private CharacterProperty(String _fieldName, Class _type) {
        //Store fieldname with first letter capitalized as they are in getters
        //and setters.
        this.fieldName = _fieldName.substring(0, 1).toUpperCase()
                + _fieldName.substring(1);

        this.type = _type;
    }
}

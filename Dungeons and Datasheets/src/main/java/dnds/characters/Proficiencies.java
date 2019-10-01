package characters;

public class Proficiencies {
    
    private final int MODIFIER = 10;
    private final int DIVISOR = 2;
    
    public int getStrengthModifier(CharacterInfo _character) {
        return (_character.getStrength()-MODIFIER)/DIVISOR;
    }
    
    public int getDexModifier(CharacterInfo _character) {
        return (_character.getDex()-MODIFIER)/DIVISOR;
    }
    
    public int getConstitutionModifier(Characters _character) {
        return (_character.getConstitution()-MODIFIER)/DIVISOR;
    }
    
    public int getIntelligenceModifier(Characters _character) {
        return (_character.getIntelligence()-MODIFIER)/DIVISOR;
    }
    
    public int getWisdomModifier(Characters _character) {
        return (_character.getWisdom()-MODIFIER)/DIVISOR;
    }
    
    public int getCharismaModifier(Characters _character) {
        return (_character.getCharisma()-MODIFIER)/DIVISOR;
    }
    
}

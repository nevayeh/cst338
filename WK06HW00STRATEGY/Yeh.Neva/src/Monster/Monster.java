package Monster;

import Ability.Attack;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public abstract class Monster {
    private Integer hp;
    private Integer xp;
    Integer agi = 5;
    Integer def = 5;
    Integer str = 5;
    Attack attack;
    private Integer maxHP;
    private HashMap<String, Integer> items;

    public Monster(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
        this.maxHP = maxHP;
        hp = this.maxHP;
        this.xp = xp;
        this.items=  items;
    }

    public Integer attackTarget(Monster target) {
        return attack.attack(target);
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getXp() {
        return xp;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public Integer getMaxHP() {
        return maxHP;
    }

    public Integer getAgi() {
        return agi;
    }

    public Integer getDef() {
        return def;
    }

    public Integer getStr() {
        return str;
    }

    /**
     * This method returns an integer value between min and max
     * This is goofy. rand.nextInt(n) returns a number between 0-n
     * to get the value we want, a value between str - maxStr,
     * We need to get a random number from maxStr-str and
     * add str back in
     * @param min an integer
     * @param max an integer
     * @return a random integer between min and max
     */
    Integer getAttribute(Integer min, Integer max) {
        Random rand = new Random();
        if(min > max) {
            Integer temp = min;
            min = max;
            max = temp;
        }
        //returns a random number between min and max inclusive
        return rand.nextInt(max-min) + min;
    }

    @Override
    public boolean equals(Object other) {
        if(other == this) {
            return true;
        }

        if(!(other instanceof Monster)) {
            return false;
        }

        Monster monster = (Monster)other;

        return monster.hp.equals(hp)
                && monster.xp.equals(xp)
                && monster.maxHP.equals(maxHP)
                && monster.items.equals(items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hp, xp, maxHP, items);
    }

    @Override
    public String toString() {

        // WORKING - gets name only
        //return getClass().getSimpleName();

        return "hp=" + hp + "/" + maxHP;
    }
}

package Monster;

import java.util.HashMap;
import java.util.Objects;

public abstract class Monster {
    private Integer hp;
    private Integer xp;
    private Integer maxHP;
    private HashMap<String, Integer> items;

    public Monster(Integer maxHP, Integer xp, HashMap<String, Integer> items) {
        this.maxHP = maxHP;
        hp = this.maxHP;
        this.xp = xp;
        this.items=  items;
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

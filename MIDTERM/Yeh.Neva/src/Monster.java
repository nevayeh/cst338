import java.util.HashMap;

public abstract class Monster {

    protected String name;
    protected String monsterKind;
    private static int monsterCount;

    public Monster() {
        this("Trogdor");
    }

    public Monster(String name) {
        this.name = name;
        addMonster();
    }

    public abstract String eat(String food);

    private static void addMonster() {
        monsterCount++;
    }

    public static void addMonster(HashMap<Integer, Monster> monsters, Monster monster) {
        monsters.put(monsters.size() + 1, monster);
    }


    public String getName() {
        return name;
    }

    //Not necessary, but here in case
    public void setName(String name) {
        this.name = name;
    }

    public String getMonsterKind() {
        return monsterKind;
    }

    public void setMonsterKind(String monsterKind) {
        this.monsterKind = monsterKind;
    }

    public static int getMonsterCount() {
        return monsterCount;
    }

}

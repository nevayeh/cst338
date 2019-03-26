import java.util.HashMap;

public abstract class Monster {

    protected String name;
    protected String monsterKind;
    private static int monsterCount;

    Monster() {
        this("Trogdor");
    }

    Monster(String name) {
        this.name = name;
        addMonster();
    }

    public abstract String eat(String food);

    public void addMonster(HashMap<Integer, Monster> map, Monster monster) {
        map.put(map.size() + 1, monster);
    }

    private static void addMonster() {
        monsterCount++;
    }

    public String getName() {
        return name;
    }

    public String getMonsterKind() {
        return monsterKind;
    }

    public static int getMonsterCount() {
        return monsterCount;
    }
}

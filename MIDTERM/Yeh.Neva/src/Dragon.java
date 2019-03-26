public class Dragon extends Monster {

    Dragon() {
        this("trogdor", "wingely");
    }

    Dragon(String name, String kind) {
        super(name);
        monsterKind = kind;
    }

    public String flap(int num) {
        if(num < 20 || num % 2 == 0) {
            return getName() + " flaps it's tiny wings " + num + " times";
        } else {
            return getName() + " can't even";
        }
    }

    public static String burninate(Monster monster, Double num) {
        if(monster != null && monster.getClass().getSimpleName().equals("Dragon")) {
            return monster.getName() + " burninates " + num + " peasants";
        } else {
            return "I don't know what this thing eats";
        }
    }

    public String trample(String thing) {
        if(thing.contains("cottage")) {
            return "the cottage is trampled by " + getName();
        } else {
            return "the wall resists " + getName();
        }
    }

    public String eat(String food) {
        if(food.contains("peasants")) {
            return "Burna-licious!";
        } else {
            return "Dragons don't eat " + food;
        }
    }

}

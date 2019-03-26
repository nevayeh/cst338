public class CookieMonster extends Monster {

    CookieMonster() {
        this("cookieM", "Blue Monster");
    }

    CookieMonster(String name, String type) {
        super(name);
        monsterKind = type;
    }

    public String sing(int mins) {
        if(mins % 2 == 1 && mins < 15) {
            return getName() + " sings C IS FOR COOKIE!! for " + mins + " minutes";
        } else {
            return getName() + " says it is not time for singing. Maybe in 41 minutes.";
        }
    }

    public static String bake(Monster m) {
        if(m != null && m.getClass().getSimpleName().equals("CookieMonster")) {
            return m.getName() + " bakes cookies in an oven";
        } else {
            return "I don't know what kind of monster that is";
        }
    }

    public String share(String name) {
        if(name.equals(getName())) {
            return name + " does not share any cookies =-(";
        } else {
            return getName() + " shares cookies with " + name;
        }
    }

    public String eat(String food) {
        if(food.equals("cookie")) {
            return "OM NOM NOM!";
        } else {
            return "C is for cookie not " + food;
        }
    }

}

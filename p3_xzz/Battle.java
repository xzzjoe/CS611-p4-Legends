public class Battle {
    private Hero hero;
    private Monster monster;
    private boolean isHeroTurn;
    
    public Battle(Hero hero, Monster monster) {
        this.hero = hero;
        this.monster = monster;
        this.isHeroTurn = true;
    }
    
    public boolean start() {
//    	Return true if hero wins, false if hero loses
        System.out.println("A battle has begun between " + hero.getName() + " and " + monster.getName() + "!");
        
        while (hero.isAlive() && monster.isAlive()) {
            if (isHeroTurn) {
                hero.makeMove(monster);
                isHeroTurn = false;
            } else {
                monster.makeMove(hero);
                isHeroTurn = true;
            }
        }
        
        if (hero.isAlive()) {
            System.out.println(hero.getName() + " has defeated " + monster.getName() + "!");
            hero.gainXP(monster.expGiven());
            hero.gainGold(monster.goldGiven());
            return true;
        } else {
            System.out.println(monster.getName() + " has defeated " + hero.getName() + "!");
            return false;
        }
    }
    
    public Hero getHero() {
    	return this.hero;
    }
    
    public Monster getMonster() {
    	return this.monster;
    }
}
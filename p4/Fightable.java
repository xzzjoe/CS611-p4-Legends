interface Fightable {
    public abstract boolean isAlive();
    public abstract void takeDamage(int damage);
    public abstract void attack(Fightable F);
}
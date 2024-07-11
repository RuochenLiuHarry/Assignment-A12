package model;

public class Player {
    private String name;
    private int score;
    private boolean isComputer;

    public Player(String name, boolean isComputer) {
        this.name = name;
        this.score = 0;
        this.isComputer = isComputer;
    }

    public String getName() { return name; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public boolean isComputer() { return isComputer; }
}
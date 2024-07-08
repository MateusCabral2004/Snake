package snakeGame.GameLogic.domain;

public enum ImagePaths {

    PLAYER1("src/main/resources/player1.png"),
    PLAYER2("src/main/resources/player2.png"),
    PLAYER3("src/main/resources/player3.png");

    private String path;

    ImagePaths(String path) {
        this.path = path;
    };

    public String getPath() {
        return path;
    }

}

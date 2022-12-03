package day2;

public enum ChoiceE {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    private final int value;

    ChoiceE(final int value) {
        this.value = value;
    }

    public static int getWinner(final ChoiceE player1Choice, final ChoiceE player2Choice) {
        if (player1Choice.equals(player2Choice)) {
            return 0;
        }

        final var player1Wins = player1Choice.equals(ChoiceE.PAPER) && player2Choice.equals(ChoiceE.ROCK)
                || player1Choice.equals(ChoiceE.ROCK) && player2Choice.equals(ChoiceE.SCISSORS)
                || player1Choice.equals(ChoiceE.SCISSORS) && player2Choice.equals(ChoiceE.PAPER);

        return player1Wins ? 1 : -1;
    }

    public static ChoiceE getChoiceForGameResult(final ChoiceE opponentChoice, final GameResultE gameResult) {
        ChoiceE myChoice = null;

        if (gameResult.equals(GameResultE.DRAW)) {
            myChoice = opponentChoice;
        }

        if (gameResult.equals(GameResultE.WIN)) {
            myChoice = switch (opponentChoice) {
                case ROCK -> ChoiceE.PAPER;
                case PAPER -> ChoiceE.SCISSORS;
                case SCISSORS -> ChoiceE.ROCK;
            };
        }

        if (gameResult.equals(GameResultE.LOSE)) {
            myChoice = switch (opponentChoice) {
                case ROCK -> ChoiceE.SCISSORS;
                case PAPER -> ChoiceE.ROCK;
                case SCISSORS -> ChoiceE.PAPER;
            };
        }

        return myChoice;
    }

    public int getValue() {
        return this.value;
    }
}

package fr.mythseur;


public class Action {

    public static Action parse(String action) {
        String[] parts = action.split(" ");
        switch (EAction.valueOf(parts[0])) {
            case WAIT:
                return new Action(EAction.WAIT);
            case SEED:
                return new Action(EAction.SEED, Integer.valueOf(parts[1]), Integer.valueOf(parts[2]));
            case GROW:
            case COMPLETE:
            default:
                return new Action(EAction.valueOf(parts[0]), Integer.valueOf(parts[1]));
        }
    }

    public EAction type;
    public Integer targetCellIdx;
    public Integer sourceCellIdx;

    public Action(EAction type, Integer sourceCellIdx, Integer targetCellIdx) {
        this.type = type;
        this.targetCellIdx = targetCellIdx;
        this.sourceCellIdx = sourceCellIdx;
    }

    public Action(EAction type, Integer targetCellIdx) {
        this(type, null, targetCellIdx);
    }

    public Action(EAction type) {
        this(type, null, null);
    }

    @Override
    public String toString() {
        if (type == EAction.WAIT) {
            return EAction.WAIT.toString();
        }
        if (type == EAction.SEED) {
            return String.format("%s %d %d", EAction.SEED.toString(), sourceCellIdx, targetCellIdx);
        }
        return String.format("%s %d", type, targetCellIdx);
    }
}
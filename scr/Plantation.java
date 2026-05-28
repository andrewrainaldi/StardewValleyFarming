package scr;

public abstract class Plantation extends Entity {

    protected int growthStage = 0;
    protected int maxGrowthStage;

    protected long tempoCrescitaMilli;
    protected long ultimoAggiornamento;

    protected boolean harvestable = false;

    private boolean alive = true;

    public Plantation(
        double x,
        double y,
        double width,
        double height,
        int maxGrowthStage,
        long tempoCrescitaMilli
    ) {

        // speed = 0 perché le piante non si muovono
        super(x, y, width, height, 0);

        this.maxGrowthStage = maxGrowthStage;
        this.tempoCrescitaMilli = tempoCrescitaMilli;

        this.ultimoAggiornamento = System.currentTimeMillis();
    }

    @Override
    public void update() {

        if (harvestable || !alive) {
            return;
        }

        long ora = System.currentTimeMillis();

        // crescita
        if (ora - ultimoAggiornamento >= tempoCrescitaMilli) {

            if (growthStage < maxGrowthStage - 1) {

                growthStage++;

                ultimoAggiornamento = ora;

            } else {

                harvestable = true;
            }
        }
    }

    public abstract void harvest();

    public void destroy() {

        alive = false;
    }

    public boolean isAlive() {

        return alive;
    }

    public boolean isHarvestable() {

        return harvestable;
    }
}
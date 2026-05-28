package scr;

public abstract class Plantation extends Entity {
    protected int growthStage = 0;
    protected int maxGrowthStage;
    protected long tempoCrescitaMilli; // Tempo richiesto tra uno stadio e l'altro
    protected long ultimoAggiornamento;
    protected boolean harvestable = false;
    private boolean alive = true;

    public Plantation(double x, double y, double width, double height, int maxGrowthStage, long tempoCrescitaMilli) {
        super((int)x, (int)y, (int)width, (int)height, maxGrowthStage);
        this.maxGrowthStage = maxGrowthStage;
        this.tempoCrescitaMilli = tempoCrescitaMilli;
        this.ultimoAggiornamento = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if (harvestable || !alive) return;

        long ora = System.currentTimeMillis();
        // Se è passato il tempo necessario, avanza di uno stadio di crescita
        if (ora - ultimoAggiornamento >= tempoCrescitaMilli) {
            if (growthStage < maxGrowthStage - 1) {
                growthStage++;
                ultimoAggiornamento = ora;
            } else {
                harvestable = true; // Arrivato al massimo!
            }
        }
    }

    public abstract void harvest();

    public void destroy() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isHarvestable() {
        return harvestable;
    }
}
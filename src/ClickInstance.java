public class ClickInstance {
    private int xCoord;
    private int yCoord;
    public double milliSinceLastClick;

    ClickInstance(int xCoord, int yCoord, double milliSinceLastClick){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.milliSinceLastClick = milliSinceLastClick;
    }

    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public double getMilliSinceLastClick() {
        return milliSinceLastClick;
    }


}

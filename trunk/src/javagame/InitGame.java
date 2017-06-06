package javagame;

public class InitGame {

    public static boolean GameStop;

    public InitGame() {
        this.GameStop = false;
    }

    public void AutoMoveSheep(long sleepInMilliSecond,int randRate) {
        AutoCreateCar();
        
        Sheep.AutoMove = true;
        
        // Create instance an object for auto move sheep
        Thread threadAutoMoveSheep = new Thread(new AutoMoveSheep(sleepInMilliSecond, randRate));
        threadAutoMoveSheep.start();
    }

    // Auto create Cars Method
    public void AutoCreateCar() {

        // Create instance an object for create cars in a thread
        AutoCreateCar autoCreateCar = new AutoCreateCar();

        // Create instance an object for game graphics
        InitGraphic base = new InitGraphic(autoCreateCar.getLines());
        Thread threadBase = new Thread(base);
        threadBase.start();

        // Create instance an object for auto create car
        Thread threadAutoCreateCar = new Thread(autoCreateCar);
        autoCreateCar.InitLine();
        threadAutoCreateCar.start();

//        TakeOver sebghat = new TakeOver(autoCreateCar.getLines());
//        Thread threadSebghat = new Thread(sebghat);
//        threadSebghat.start();
//        
        // TakeOver takeOver = new TakeOver(autoCreateCar.getLines());
    }

}

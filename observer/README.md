Head First设计模式读书总结——观察者模式

有一个模式可以帮你的对象知悉现况，不会错过该对象感兴趣的事。对象甚至在运行时可决定是否要继续被通知。
题例：Internet气象观测站。
一个WeatherData对象负责追踪目前的天气状况（温度，湿度，气压）。我们希望你们能建立一个应用，有三种布告板，分别显示目前的状况、气象统计及简单的预报。当WeatherObject对象获得最新的测量数据时，三种布告板必须实时更新。
而且，这是一个可以扩展的气象站，Weather-O-Rama气象站希望公布一组API，好让其他开发人员可以写出自己的气象布告板，并插入此应用中，我们希望能提供这样的API。
气象监测应用的概况
此系统中的三个部分是气象站（获取实际气象数据的物理装置）、WeatherData对象（最总来自气象站的数据，并更新布告板）和布告板（显示目前天气状况给用户看）。
瞧一瞧刚送到的WeatherData类

class WeatherData{
        public int getTemperature(){
        }
        public int getHumidity(){
        }
        public int getPressure(){
        }
        public void measurementsChanged(){
        //一旦气象测量更新，此方法会被调用
        }
}
我们目前知道些什么？
1.WeatherData类具有getter方法，可以取得三个测量值。
2.当心的测量数据备妥时，measurementsChanged（）方法就会被调用（我们不在乎此方法是如何被调用的，我们只在乎它被调用了）
3.我们需要实现三个使用天气数据的布告板，一旦WeatherData有新的测量，这些布告板必须马上更新。
4.此系统必须可扩展，让其他开发人员建立定制的布告板，用户可以随心所欲地添加或删除任何布告板。
错误的示范

public class WeatherData {
    // 实例变量声明
    public void measurementsChanged() {
       float temp = getTemperature();
       float humidity = getHumidity();
       float pressure = getPressure();
       currentConditionsDisplay.update(temp, humidity, pressure);
       statisticsDisplay.update(temp, humidity, pressure);
       forecastDisplay.update(temp, humidity, pressure);
    }
// 这里是其他WeatherData方法
}
问题：在我们的第一个视线中，下列哪些说法正确？
1.我们是针对具体实现编程，而非针对接口。
2.对于每个新的布告板，我们都得修改代码。
3我们无法再运行时动态地增加（或删除）布告板。
4布告板没有实现一个公共的接口。
5.我们尚未封装改变的部分。
6我们侵犯了WeatherData类的封装。
我们的实现有什么不对？

     currentConditionsDisplay.update(temp, humidity, pressure);
     statisticsDisplay.update(temp, humidity, pressure);
     forecastDisplay.update(temp, humidity, pressure);
三个方法针对具体实现编程，会导致我们以后再增加或删除布告板时必须修改程序。
布告板的方法名称都是update（），参数都是温度，湿度， 气压。这里看起来像是一个统一的接口。

我们先来看观察者模式，然后再回来看看如何将此模式应用到气象观测站。
认识观察者模式
新的例题：我们看看报纸和杂志的订阅是怎么回事：
1.报社的业务就是出版报纸。
2向某家报社订阅报纸，只要他们有新报纸出版，就会给你送来，只要你是他们的订户，你就会一直收到信报纸。
3当你不想再看报纸的时候，取消订阅，他们就不会再送新报纸来。
4只要报社还在运营，就会一直有人（或单位）向他们订阅报纸或取消订阅报纸。
出版者+订阅者=观察者模式
如果你了解报纸的订阅，其实就知道观察者模式是怎么回事了，只是名称不太一样：出版者改称为“主题”，订阅者改称为“观察者”。
第二个模式：观察者模式
定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的所有依赖者都会受到通知并自动更新

定义观察者模式：类图
松耦合的威力
当两个对象之间松耦合，他们依然可以互交，但是不太清楚彼此的细节。
观察者模式提供了一种对象设计，让主题和观察者之间松耦合。

关于观察者的一切，主题只知道观察者实现了某个接口（也就是Observer接口）。主题不需要知道观察者的具体类是谁，做了些什么或者其他任何细节。
任何时候我们都可以增加新的观察者。因为主题唯一依赖的东西是一个实现Observer接口的对象列表，所以我们可以随时增加观察者。事实上，在运行时我们可以用心的观察者取代现有的观察者，主题不会受到任何影响。同样的，也可以在任何时候删除某些观察者。

有新类型的观察者出现时，主题的代码不需要修改，假如我们有个新的具体类需要当观察者，我们不需要为了兼容新类型而修改主题的代码，所有要做的就是在新的类里实现此观察者接口，人后注册为观察者即可，主题不在乎别的，它只会发送通知给所有实现了观察者接口的对象。
第四个设计原则：为了交互对象之间松耦合设计而努力。

我们的WeatherData类正是此处所说的“一”，而我们的“多”正是使用天气观测的各种布告板。
设计气象站

设计气象站
实现气象站

//主题接口
interface Subject{
    //注册观察者
    public void registerObserver(Observer o);
    //删除观察者
    public void removeObserver(Observer o);
    //当主题状态改变时，这个方法会被调用，以通知所有的观察者
    public void notifyObserver();
}

interface Observer {
    //当气象观测值改变时，主题会把这些状态值当作方法的参数，传送给观察者
    public void update(float temp,float humidity,float pressure);
}

interface DisplayElement{
    //当布告板需要显示时，调用此方法
    public void display();
}
问题：把观测值直接传入观察者中是更新状态的最直接的方法。你认为这样做法明智吗？提示：这些观测值的种类和个数在未来有可能改变吗？如果以后会改变，这些变化是否被很好地封装？或者是需要修改许多代码才能办到？

在WeatherData中实现主题接口

class WeatherData implements Subject{

    private ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers=new ArrayList<Observer>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i=observers.indexOf(o);
        if(i>=0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for(Observer observer:observers){
            observer.update(temperature,humidity,pressure);
        }
    }
    //当从气象站得到更新观测值时，我们通知观察者
    public void measurementsChanged(){
        notifyObserver();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }
    //WeatherData的其他方法
}
建立布告板
其中的一个布告板：

class CurrentConditionDisplay implements Observer,DisplayElement{
    private float temperature;
    private float humidity;
    private Subject weatherData;

    public CurrentConditionDisplay(Subject weatherData){
        this.weatherData=weatherData;
        weatherData.registerObserver(this);
    }
    @Override
    public void display() {
        System.out.println("数据");
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature=temp;
        this.humidity=humidity;
        display();
    }
}
使用Java内置的观察者模式
在java api 有内置的观察者模式。java.util包内包含最基本的Observer接口与Observable类，这和我们的Subject接口与Observer接口和相似。

java内置观察者模式

Java内置的观察者模式如何运作
WeatherData（也就是我们的主题）现在扩展自Observable类，并继承到一些增加、删除、通知观察者的方法……
如何把对象变成观察者
实现观察者接口（java.uitl.Observer）,人调用任何Obsecable对象的addObserver（）方法。不再当观察者时，调用deleteObserver()方法就可以了。
利用内置的支持重写WeatherData

class WeatherDataTWO extends Observable{
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherDataTWO(){

    }

    public void measurementsChanged(){
        //在调用notifyObservers（）之前，要先调用setChanged（）来指示状态已经改变
        setChanged();
        //我们没有调用notifyObservers传送数据对象，表示我们采用的做法是拉。
        notifyObservers();
    }

    public void setMeasurements(float temperature,float humidity,float pressure){
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        measurementsChanged();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
利用内置观察者重写布告板

class CurrentConditionsDisplay implements java.util.Observer,DisplayElement{

    Observable observable;
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable){
        this.observable=observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("数据");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  WeatherDataTWO){
            WeatherDataTWO weatherDataTWO= (WeatherDataTWO) o;
            this.temperature=weatherDataTWO.getTemperature();
            this.humidity=weatherDataTWO.getHumidity();
            display();
        }
    }
}
java内置观察者的黑暗面
java.util.Observable是一个类而不是接口，你必须设计一个雷继承它。如果某个类想同时具有Observable类和另一个超类的行为，就完了，java不支持多重继承。所以有时候需要自己实现一整套观察者模式。